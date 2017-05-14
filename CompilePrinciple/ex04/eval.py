# -*- coding: utf-8 -*-

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
        if exp[-1] == ";":
            exp = exp[:-1]
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
        try:
            ans = eval(line)
            print(ans)
        except Exception:
            print("语义错误")

for key in dic:
    print("%s = %d"% (key,dic[key]))



def func():
    if (":=" in line):
        pos = line.index(":=")
        Id = line[0:pos].strip()
        exp = line[pos + 2:].strip()
        isHadV = False
        for c in exp:
            if c >= 'a' and c <= 'z':
                isHadV = True
                break
        if isHadV == False:
            num = eval(exp)
            dic[Id] = num
        else:
            for key in dic:
                if (key in exp):
                    tmp = str(dic[key])

                    pos = exp.index(key)
                    exp = exp[:pos] + tmp + exp[pos + len(key):]

            num = eval(exp)
            dic[Id] = num

def func2():
    if (":=" in line):
        pos = line.index(":=")
        Id = line[0:pos].strip()
        exp = line[pos + 2:].strip()
        isHadV = False
        for c in exp:
            if c >= 'a' and c <= 'z':
                isHadV = True
                break
        if isHadV == False:
            num = eval(exp)
            dic[Id] = num
        else:
            for key in dic:
                if (key in exp):
                    tmp = str(dic[key])

                    pos = exp.index(key)
                    exp = exp[:pos] + tmp + exp[pos + len(key):]

            num = eval(exp)
            dic[Id] = num

# def func3():
#     LR0_Analyser(std::vector < std::pair < std::string, int >> inputs)
#     {
#         this->inputs = inputs;
#     this->inputs.push_back(std::pair < std::string, int > (END_OF_INPUT, 0));
#     }
#
#     LR0_Analyser()
#     {
#
#     }
#
#     void
#     Input(std::vector < std::pair < std::string, int >> inputs)
#     {
#         this->inputs = inputs;
#     this->inputs.push_back(std::pair < std::string, int > (END_OF_INPUT, 0));
#     }
#
#     void
#     AddProduction(std::string
#     left, std::string
#     right1)
#     {
#         std::vector < std::string > right_list;
#     right_list.push_back(right1);
#     production_list.push_back(std::pair < std::string, std::vector < std::string >> (left, right_list));
#     }
#
#     void
#     AddProduction(std::string
#     left, std::string
#     right1, std::string
#     right2)
#     {
#         std::vector < std::string > right_list;
#     right_list.push_back(right1);
#     right_list.push_back(right2);
#     production_list.push_back(std::pair < std::string, std::vector < std::string >> (left, right_list));
#     }
#
#     void
#     AddProduction(std::string
#     left, std::string
#     right1, std::string
#     right2, std::string
#     right3)
#     {
#         std::vector < std::string > right_list;
#     right_list.push_back(right1);
#     right_list.push_back(right2);
#     right_list.push_back(right3);
#     production_list.push_back(std::pair < std::string, std::vector < std::string >> (left, right_list));
#     }
#
#     void
#     AddProduction(std::string
#     left, std::string
#     right1, std::string
#     right2, std::string
#     right3, std::string
#     right4)
#     {
#         std::vector < std::string > right_list;
#     right_list.push_back(right1);
#     right_list.push_back(right2);
#     right_list.push_back(right3);
#     right_list.push_back(right4);
#     production_list.push_back(std::pair < std::string, std::vector < std::string >> (left, right_list));
#     }
#
#     void
#     AddProduction(std::string
#     left, std::string
#     right1, std::string
#     right2, std::string
#     right3, std::string
#     right4, std::string
#     right5)
#     {
#         std::vector < std::string > right_list;
#     right_list.push_back(right1);
#     right_list.push_back(right2);
#     right_list.push_back(right3);
#     right_list.push_back(right4);
#     right_list.push_back(right5);
#     production_list.push_back(std::pair < std::string, std::vector < std::string >> (left, right_list));
#     }
#
#     void
#     AddAction(int
#     status, std::string
#     input, char
#     type, int
#     value)
#     {
#         Action
#     new_action;
#     new_action.status = status;
#     new_action.input = input;
#     new_action.type = type;
#     new_action.value = value;
#     action_list.push_back(new_action);
#     }
#
#     void
#     AddGoto(int
#     status, std::string
#     input, int
#     value)
#     {
#         Goto
#     new_goto;
#     new_goto.status = status;
#     new_goto.input = input;
#     new_goto.value = value;
#     goto_list.push_back(new_goto);
#     }
#
#     int
#     Analyse()
#     {
#         std::vector < int > status_stack;
#     std::vector < std::string > chars_stack;
#     std::vector < int > value_stack;
#
#     status_stack.push_back(0);
#     size_t
#     index = 0;
#
#     for (index = 0; index < inputs.size(); ++index)
#     {
#     // Show(status_stack);
#     // Show(chars_stack);
#
#     char type;
#     int value;
#     if (!getAction(status_stack.back(), inputs[index].first, type, value))
#     return index;
#
#     if (type == 's' | | type == 'S')
#         {
#             status_stack.push_back(value);
#         chars_stack.push_back(inputs[index].first);
#         value_stack.push_back(inputs[index].second);
#     continue;
#     }
#
#     if (type == 'r' | | type == 'R')
#     {
#     --index;
#     std::pair < std::string, std::vector < std::string >> production = production_list[value - 1];
#
#     int
#     new_value;
#
#     if (value - 1 == 0)
#         {
#         if (value_stack[value_stack.size() - 1 - 1] == ADD_OPERATOR)
#         new_value = value_stack[value_stack.size() - 1 - 2] + value_stack[value_stack.size() - 1 - 0];
#         else
#         new_value = value_stack[value_stack.size() - 1 - 2] - value_stack[value_stack.size() - 1 - 0];
#         }
#
#         if (value - 1 == 1)
#             {
#             if (value_stack[value_stack.size() - 1 - 1] == MUL_OPERATOR)
#             new_value = value_stack[value_stack.size() - 1 - 2] * value_stack[value_stack.size() - 1 - 0];
#             else
#             {
#             if (value_stack[value_stack.size() - 1 - 0] == 0)
#         return index;
#         new_value = value_stack[value_stack.size() - 1 - 2] / value_stack[value_stack.size() - 1 - 0];
#     }
#
#     }
#
#     if (value - 1 == 2)
#     {
#     new_value = value_stack[value_stack.size() - 1 - 1];
#     }
#
#     if (value - 1 == 3)
#     {
#     new_value = value_stack[value_stack.size() - 1 - 0];
#     }
#
#     while (!production.second.empty())
#     {
#         production.second.pop_back();
#     chars_stack.pop_back();
#     status_stack.pop_back();
#     value_stack.pop_back();
#     }
#     chars_stack.push_back(production.first);
#     int
#     goto_value;
#     if (!getGoto(status_stack.back(), production.first, goto_value))
#     return index;
#     status_stack.push_back(goto_value);
#     result = new_value;
#     value_stack.push_back(new_value);
#     continue;
#     }
#
#     if (type == 'a' | | type == 'A')
#     {
#     std::cout << "Result is " << getResult() << std::endl;
#     return -1;
#
#     }
#
#     }
#
#     return index;
#     }
#
#     int
#     getResult()
#     {
#     return result;
#     }
#
#     static
#     const
#     std::string
#     END_OF_INPUT;
#     static
#     const
#     std::string
#     ALL_OF_INPUT;
#
#     static
#     const
#     int
#     ADD_OPERATOR;
#     static
#     const
#     int
#     SUB_OPERATOR;
#     static
#     const
#     int
#     MUL_OPERATOR;
#     static
#     const
#     int
#     DIV_OPERATOR;
#
#     protected:
#     struct
#     Action
#     {
#     int
#     status;
#     std::string
#     input;
#     char
#     type;
#     int
#     value;
#     };
#
#     struct
#     Goto
#     {
#     int
#     status;
#     std::string
#     input;
#     int
#     value;
#     };
#
#     bool
#     getAction(int
#     status, std::string
#     input, char & type, int & value)
#     {
#     for (Action action: action_list)
#     {
#     if (action.status == status & & (action.input == input | | action.input == ALL_OF_INPUT))
#     {
#         type = action.type;
#     value = action.value;
#     return true;
#     }
#     }
#     return false;
#     }
#
#     bool
#     getGoto(int
#     status, std::string
#     input, int & value)
#     {
#     for (Goto g: goto_list)
#     {
#     if (g.status == status & & g.input == input)
#     {
#         value = g.value;
#     return true;
#     }
#     }
#     return false;
#     }
#     void
#     Show(std::vector < int > status_stack)
#     {
#     for (auto it: status_stack)
#     std::cout << it << " ";
#     std::cout << std::endl;
#     }
#     void
#     Show(std::vector < std::string > chars_stack)
#     {
#     std::cout << "#" << " ";
#     for (auto it: chars_stack)
#     std::cout << it << " ";
#     std::cout << std::endl;
#     }
#     private:
#
#     std::vector < std::pair < std::string, int >> inputs;
#     std::vector < Action > action_list;
#     std::vector < Goto > goto_list;
#     std::vector < std::pair < std::string, std::vector < std::string >> > production_list;
#     int
#     result;
#     };

def fun4():

** /
LEVEL_TYPE CompareLevel(char operator1, char operator2)
{       char
levelTable1[] = { '(', '+', '-', '*', '/', ')'}
char
levelTable2[] = { ')', '-', '+', '/', '*', '('}
int nTable1Index1, nTable1Index2;
int nTable2Index1, nTable2Index2;

// 不合法情况判断
if ((operator1 == ')' & & operator2 == '(') | |
    (operator1 == '#' & & operator2 == ')') | |
    (operator1 == '(' & & operator2 == '#'))
{
return LEVEL_INVALID;
}

// 判断相等情况
if ((operator1 == '(' & & operator2 == ')') | |
(operator1 == '#' & & operator2 == '#'))
{
return LEVEL_SAME;
}

// 判断两个操作符在两个优先级表中的位置
nTable1Index1 = nTable1Index2 = -1;
nTable2Index1 = nTable2Index2 = -1;

while (levelTable1[++nTable1Index1] != operator1);
while (levelTable1[++nTable1Index2] != operator2);
while (levelTable2[++nTable2Index1] != operator1);
while (levelTable2[++nTable2Index2] != operator2);

if (nTable1Index1 - nTable1Index2 < 0 & & nTable2Index1 - nTable2Index2 < 0 | |
    operator1 == '(' | |
    operator2 == '(')
    {
return LEVEL_SMALLER;
}
else
{
return LEVEL_BIGGER;
}
}


    char
levelTable1[] = {'#', '(', '+', '-', '*', '/', ')'};
char
levelTable2[] = {'#', ')', '-', '+', '/', '*', '('};
int
nTable1Index1, nTable1Index2;
int
nTable2Index1, nTable2Index2;

// 不合法情况判断
if ((operator1 == ')' & & operator2 == '(') | |
(operator1 == '#' & & operator2 == ')') | |
(operator1 == '(' & & operator2 == '#'))
{
return LEVEL_INVALID;
}

if ((operator1 == '(' & & operator2 == ')') | |
(operator1 == '#' & & operator2 == '#'))
{
return LEVEL_SAME;
}


}
