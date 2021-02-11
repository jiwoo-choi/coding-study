n = int(input());
dic = {}
for i in range(n):
    val = input()
    if val in dic:
        dic[val] = dic[val]+1;
    else:
        dic[val] = 1;

t = sorted(dic.items(), key=lambda x: (-x[1] , x[0]))
print(t[0][0]);

