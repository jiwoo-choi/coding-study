from itertools import combinations
import sys
from collections import deque

# bfs

dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]



n, m, k = map(int, input().split(' '));

arr = [ ['.' for _ in range(m) ] for _ in range(n)]
visited =  [ [0 for _ in range(m) ] for _ in range(n)]
total = [ 0 for _ in range(n*m)]



def bfs(x,y, group):
    
    q = deque([(x,y)])
    visited[x][y] = group

    while q:
        curx, cury = q.popleft();
        for k in range(4):
            nx = curx + dx[k];
            ny = cury + dy[k];
            if (nx >= 0 and ny >= 0 and nx < n and ny < m and arr[nx][ny] == '#' and visited[nx][ny] == 0):
                visited[nx][ny] = group
                q.append((nx,ny))
    
   
for i in range(k):
    x,y = map(int, input().split(' '))
    arr[x-1][y-1] = '#'

group = 0

for i in range(n):
    for j in range(m):
        if (arr[i][j] == '#' and visited[i][j] == 0):
            bfs(i,j, group+1)
            group = group+1


for i in range(n):
    for j in range(m):
        if (visited[i][j] != 0):
            total[visited[i][j]] += 1

print(max(total))

