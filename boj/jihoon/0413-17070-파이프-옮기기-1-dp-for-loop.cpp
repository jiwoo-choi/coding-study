#include <cstdio>
#include <iostream>
#include <algorithm>
#include <functional>

using namespace std;

#define MAX_N 20
#define WALL 1

#define VERTICAL 0
#define HORIZONTAL 1
#define DIAGONAL 2

int N;
int board[MAX_N][MAX_N];
// dp[i][j][k] : k 방향으로 파이프를 두어 (i, j)에 도달 했을 떄의 경우의 수
int dp[MAX_N][MAX_N][3];

void solution() {
	dp[1][2][1] = 1;

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (board[i][j] == WALL) continue;

			if (board[i-1][j] != WALL)
				dp[i][j][0] += (dp[i-1][j][0] + dp[i-1][j][2]);
			if (board[i][j-1] != WALL)
				dp[i][j][1] += (dp[i][j-1][1] + dp[i][j-1][2]);
			if (board[i-1][j-1] != WALL && board[i][j-1] != WALL && board[i-1][j] != WALL)
				dp[i][j][2] += (dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2]);
		}
	}
}

int main() {
	scanf("%d", &N);
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			scanf("%d", &board[i][j]);

	solution();
	int res = 0;
	for (int i = 0; i < 3; i++)
		res += dp[N][N][i];

	printf("%d\n", res);
}
