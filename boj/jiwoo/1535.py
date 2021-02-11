from itertools import combinations
import sys

n = int(input())
energys = sys.stdin.readline()
joys = sys.stdin.readline()

joy = list(map(int,joys.split(' ')))
energy = list(map(int,energys.split(' ')))
index = [n for n in range(n)]


answer = 0

for i in range(1, n+1):
    c = combinations(index,i);
    ene = 100;
    for j in c:
        
        tempene = 100
        tempanswer = 0
        for ele in j:
            
            tempanswer += joy[ele]
            tempene -= energy[ele];
        
        if (tempene > 0) :
            answer = max(answer, tempanswer)

print(answer)
