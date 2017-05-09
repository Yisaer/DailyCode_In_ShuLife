dic = {}

file = open("Test.txt")
while 1:
    line = file.readline()
    if not line:
        break
    if(":=" in line):
        pos  = line.index(":=")
        Id = line[0:pos].strip()
        exp = line[pos+2:].strip()
        isHadV = False
        for c in exp :
            if c >= 'a' and c<='z':
                isHadV = True
                break
        if isHadV == False:
            num = eval(exp)
            dic[Id] = num
        else:
            for key in dic:
                if(key in exp):
                    tmp = str(dic[key])

                    pos = exp.index(key)
                    exp =exp[:pos]+tmp+exp[pos+len(key):]

            num = eval(exp)
            dic[Id] = num
    else:
        ans = eval(line)
        print(ans)

for key in dic:
    print("%s = %d"% (key,dic[key]))



