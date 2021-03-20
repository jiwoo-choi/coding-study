#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <stack>
#include <cstring>
#include <cstdlib>

using namespace std;

#define MAX_N 1000
#define EMPTY -1
#define NOT_YET 0
#define DONE 1

int N, M;
int board[MAX_N][MAX_N];
int isVisited[MAX_N][MAX_N];
queue<pair<int, int> > q;

int dx[4] = {  0, -1, 1, 0 };
int dy[4] = { -1,  0, 0, 1 };

bool isFinish() {
    for (int i = 0; i < N; i++) 
        for (int j = 0; j < M; j++) 
            if (board[i][j] == NOT_YET)
                return false;

    return true;
}

int bfs() {
    int cnt = 0;
    while (!q.empty()) {
        if (isFinish()) return cnt;

        int qSize = q.size();
        for (int qs = 0; qs < qSize; qs++) {
            int r = q.front().first;
            int c = q.front().second;
            q.pop();
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || isVisited[nr][nc] || board[nr][nc] == EMPTY)
                    continue;
                
                isVisited[nr][nc] = true;
                board[nr][nc] = DONE;
                q.push({ nr, nc });
            }
        }
        cnt++;
    }
    return -1;
}

int main() {
    memset(isVisited, 0, sizeof(isVisited));

    scanf("%d%d", &M, &N);
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            scanf("%d", &board[i][j]);
            if (board[i][j] == DONE) {
                q.push({ i, j });
                isVisited[i][j] = true;
            }
        }
    }
    printf("%d\n", bfs());
}
