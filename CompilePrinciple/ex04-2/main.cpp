#include <iostream>
#include <cstring>
#include <cstdio>
#include <vector>
#include <stack>
#include <map>
#include <set>
#include <algorithm>
#include <string>
#include <cstdlib>
#include <cctype>
#define MAX 507

using namespace std;

class WF
{
public:
    string left;
    vector<string> right;
    WF ( const string& str )
    {
        left = str;
    }
    void insert ( char str[] )
    {
        right.push_back(str);
    }
    void print ( )
    {
        printf ( "%s->%s" , left.c_str() , right[0].c_str() );
        for ( int i = 1 ; i < right.size() ; i++ )
            printf ( "|%s" , right[i].c_str() );
        puts("");
    }
};

char relation[MAX][MAX];
vector<char> VT;
vector<WF> VN_set;
map<string,int> VN_dic;
set<char> first[MAX];
set<char> last[MAX];
int used[MAX];
int vis[MAX];


void dfs (  int x )
{
    if ( vis[x] ) return;
    vis[x] = 1;
    string& left = VN_set[x].left;
    for ( int i = 0 ; i < VN_set[x].right.size() ; i++ )
    {
        string& str = VN_set[x].right[i];
        if ( isupper(str[0]) )
        {
            int y = VN_dic[str.substr(0,1)]-1;
            if ( str.length() > 1 && !isupper(str[1] ) )
                first[x].insert ( str[1] );
            dfs ( y );
            set<char>::iterator it = first[y].begin();
            for ( ; it!= first[y].end() ; it++ )
                first[x].insert ( *it );
        }
        else
            first[x].insert ( str[0] );
    }
}

void make_first ( )
{
    memset ( vis , 0 , sizeof ( vis ) );
    for ( int i = 0 ; i < VN_set.size() ; i++ )
        if ( vis[i] ) continue;
        else dfs ( i );
#define DEBUG
#ifdef DEBUG
    puts("------------FIRSTVT集-------------------");
    for ( int i = 0 ; i < VN_set.size() ; i++ )
    {
        printf ( "%s : " , VN_set[i].left.c_str() );
        set<char>::iterator it = first[i].begin();
        for ( ; it!= first[i].end() ; it++ )
            printf ( "%c " , *it );
        puts ("" );
    }
#endif
}

void dfs1 ( int x )
{
    if ( vis[x] ) return;
    vis[x] = 1;
    string& left = VN_set[x].left;
    for ( int i = 0 ; i < VN_set[x].right.size() ; i++ )
    {
        string& str = VN_set[x].right[i];
        int n = str.length() -1;
        if ( isupper(str[n] ) )
        {
            int y = VN_dic[str.substr(n,1)]-1;
            if ( str.length() > 1 && !isupper(str[n-1]) )
                last[x].insert ( str[1] );
            dfs1 ( y );
            set<char>::iterator it = last[y].begin();
            for ( ; it != last[y].end() ; it++ )
                last[x].insert ( *it );
        }
        else
            last[x].insert ( str[n] );
    }
}


void make_last ( )
{
    memset ( vis , 0 , sizeof ( vis ) );
    for ( int i = 0 ; i < VN_set.size() ; i++ )
        if ( vis[i] ) continue;
        else dfs1 ( i );
#define DEBUG
#ifdef DEBUG
    puts("--------------LASTVT集---------------------");
    for ( int i = 0 ; i < VN_set.size() ; i++ )
    {
        printf ( "%s : " , VN_set[i].left.c_str() );
        set<char>::iterator it = last[i].begin();
        for ( ; it!= last[i].end() ; it++ )
            printf ( "%c " , *it );
        puts ("" );
    }
#endif
}

void make_table ( )
{
    for ( int i = 0 ; i < MAX ; i++ )
        for ( int j = 0 ; j < MAX ; j++ )
            relation[i][j] = ' ';
    for ( int i = 0 ; i < VN_set.size() ; i++ )
        for ( int j = 0 ; j < VN_set[i].right.size() ; j++ )
        {
            string& str = VN_set[i].right[j];
            for ( int k = 0 ; k < str.length()-1 ; k++ )
            {
                if ( !isupper(str[k]) && !isupper(str[k+1]) )
                    relation[str[k]][str[k+1]] = '=';
                if ( !isupper(str[k]) && isupper(str[k+1]) )
                {
                    int x = VN_dic[str.substr(k+1,1)]-1;
                    set<char>::iterator it = first[x].begin();
                    for ( ; it != first[x].end() ; it++ )
                        relation[str[k]][*it] = '<';
                }
                if ( isupper(str[k]) && !isupper(str[k+1]) )
                {
                    int x = VN_dic[str.substr(k,1)]-1;
                    set<char>::iterator it = last[x].begin();
                    for ( ; it != last[x].end() ; it++ )
                        relation[*it][str[k+1]] = '>';
                }
                if ( k > str.length()-2 ) continue;
                if ( !isupper(str[k]) && !isupper(str[k+2]) && isupper(str[k+1]) )
                    relation[str[k]][str[k+2]] = '=';
            }
        }
#define DEBUG
#ifdef DEBUG
    for ( int i = 0 ; i < VT.size()*5 ; i++ )
        printf ("-");
    printf ( "算符优先关系表" );
    for ( int i = 0 ; i < VT.size()*5 ; i++ )
        printf ( "-" );
    puts("");
    printf ( "|%8s|" , "" );
    for ( int i = 0 ; i < VT.size() ; i++ )
        printf ( "%5c%5s" , VT[i] , "|" );
    puts ("");
    for ( int i = 0 ; i < (VT.size()+1)*10 ; i++ )
        printf ("-");
    puts("");
    for ( int i = 0 ; i < VT.size() ; i++ )
    {
        printf ( "|%4c%5s" , VT[i] , "|");
        for ( int j = 0 ; j < VT.size() ; j++ )
            printf ( "%5c%5s" , relation[VT[i]][VT[j]] , "|" );
        puts ("");
        for ( int i = 0 ; i < (VT.size()+1)*10 ; i++ )
            printf ("-");
        puts("");
    }
#endif
}


int main ( )
{
        VT.push_back()
        make_table();
}