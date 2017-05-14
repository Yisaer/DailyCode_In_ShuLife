#include <stdio.h>
#include <ntsid.h>
#include <netinet/in.h>
#include <pthread.h>
#include <unistd.h>
#include <string.h>
#include <sys/stat.h>
#include <tkDecls.h>
#include <ctype.h>

#define ISspace(x) isspace((int)(x))
#define SERVER_STRING "Server: httpd/0.1.0\r\n"

void accept_request(int);       // 处理套接字监听到的一个HTTP请求
void back_request(int);         // 返回给客户端这是个错误的请求
void cat(int ,FILE *);          // 读取某个文件到套接字
void cannot_execute(int);       // 执行cgi程序错误
void error_die(const char *);   // 处理错误信息写到perror
void execute_cgi(int,const char *,const char * ,const char *); //运行cgi程序
int get_line(int,char *,int);   // 读取套接字的一行
void headers(int ,const char*); // 把HTTP响应的头部写到套接字
void not_found(int);            // 处理找不到文件的情况
void serve_file(int,const char *); //初始化http服务，绑定端口，进行监听
int startup(u_short *); //初始化httpd服务器，简历套接字，绑定端口，进行监听
void unimplemented(int);        // 返回所用的Method不被支持

/*
 * 处理服务器上一个从accept上接收到的请求
 * 参数: client的socket
 */
void accept_request(int client){
    char buf[1024];
    int numchars;
    char method[255];
    char url[255];
    char path[512];
    size_t i ,j;
    struct stat st;
    int cgi = 0; // 如果是一个cgi程序，将会变成true
    char * query_string = NULL;

    numchars =get_line(client,buf,sizeof(buf));
    i = 0; j= 0 ;
    /*
     * 把客户端的请求方法存到method数组里
     */
    while (ISspace(buf[j]) && (i< sizeof(method)-1) ){
        method[i] = buf[j];
        i++;j++;
    }
     method[i] = '\0';
    /*
     * 既不是GET又不是POST则无法处理
     */
    if(strcasecmp(method,"GET") && strcasecmp(method,"POST")){
        unimplemented(client);
        return;
    }
    /*
     * POST的时候开启CGI
     */
    if(strcasecmp(method,"POST")==0){
        cgi = 1;
    }
    /*
     * 读取url地址
     */
    i = 0;
    while(ISspace(buf[j])  && (j< sizeof(buf)-1)){
        j++;
    }
    while(!ISspace((buf[j])) && (i<sizeof(url)-1) && (j< sizeof(buf))){
        url[i] = buf[j];
        i++;j++;
    }
    url[i] = '\0';
    /*
     * 处理get方法
     */
    if(strcasecmp(method,"GET") == 0){
        /*
         * 待处理请求url
         */
        query_string = url;
        while( (*query_string!= '?' ) && (*query_string !='\0')){
            query_string++;
        }
        if(*query_string == '?'){
            cgi = 1;
            *query_string = '\0';
            query_string++;
        }
    }
    /*
     * 格式化url到path数组，html文件在htdocs中
     */
    sprintf(path,"htdocs%s",url);
    /*
     * 默认情况为index.html
     */
    if(path[strlen(path)-1] =='/'){
        strcat(path,"index.html");
    }
    /*
     * 根据路径找到文件
     */
    if(stat(path,&st) == -1){
        /*
         * 丢弃所有Header信息
         */
        while( (numchars >0 ) && strcmp("\n",buf)){
            numchars = get_line(client,buf,sizeof(buf));
        }
        not_found(client);
    }
    else{
        /*
         * 如果是个目录，则使用该目录下的index.html文件
         */
        if( (st.st_mode & S_IFMT) == S_IFDIR){
            strcat(path,"/index.html");
        }
        if( (st.st_mode & S_IXUSR) || (st.st_mode & S_IXGRP) || (st.st_mode && S_IXOTH)){
            cgi = 1;
        }
        /*
         * 不是cgi,直接把服务器文件返回，否则执行cgi
         */
        if(!cgi){
            serve_file(client,path);
        }
        else{
            execute_cgi(client,path,method,query_string);
        }
    }
    close(client);
}

/*
 * startup函数用来初始化Web连接
 */
int startup(u_short * port){
    int httpd = 0;
    struct sockaddr_in name;
    /*
     * 建立socket
     */
    httpd = socket(PF_INET,SOCK_STREAM,0);
    if(httpd == -1){
        error_die("socket");
    }
    memset(&name,0,sizeof(name));
    name.sin_family = AF_INET;
    name.sin_port = htons(*port);
    name.sin_addr.s_addr = htonl(INADDR_ANY);
    if(bind(httpd,(struct sockaddr*)&name,sizeof(name))<0){
        error_die("bind");
    }
    /*
     * 动态分配一个端口
     */
    if( *port == 0 ){
        int namelen = sizeof(name);
        if(getsockname(httpd,(struct sockaddr *)&name,&namelen)==-1){
            error_die("getsockname");
        }
        *port = ntohs(name.sin_port);
    }
    if(listen(httpd,5)<0){
        error_die("listen");
    }
    /*
     * 返回socket id
     */
    return httpd;
}


int main() {
    int server_sock = -1;
    u_short port = 0;
    int client_sock = -1;
    struct sockaddr_in client_name;
    int client_name_len = sizeof(client_name);
    pthread_t newthread;

    /*
     * 在对应端口创建httpd服务
     */
    server_sock = startup(&port);
    printf("httpd is running on port %d\n",port);
    while(1){
        client_sock = accept(server_sock,(struct sockaddr *)&client_name,&client_name_len);
        if(client_sock == -1){
            error_die("accept");
        }
        if(pthread_create(&newthread,NULL,accept_request,client_sock)!=0){
            perror("pthread_create");
        }
    }
    close(server_sock);
    return 0;
}