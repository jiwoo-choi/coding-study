#include <cstdio>
#include <iostream>
#include <algorithm>
#include <string>
#include <functional>
#include <vector>

using namespace std;

#define MAX_N 201

int A, B, C;
bool visited[MAX_N][MAX_N][MAX_N];
vector<int> res;

void go(int a, int b, int c) {
	if (a == 0) res.push_back(c);

	int limits[3] = {A, B, C};
	int arr[3];
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			if (i == j) continue;

			arr[0] = a; arr[1] = b; arr[2] = c;

			int diff = limits[j] - arr[j];
			int move = min(diff, arr[i]);
			arr[i] -= move;
			arr[j] += move;

			if (visited[arr[0]][arr[1]][arr[2]])
				continue;

			visited[arr[0]][arr[1]][arr[2]] = true;

			go(arr[0], arr[1], arr[2]);
		}
	}
	sort(res.begin(), res.end());
}

int main() {
	scanf("%d%d%d", &A, &B, &C);
	visited[0][0][C] = true;
	go(0, 0, C);

	for (int i = 0; i < res.size(); i++)
		printf("%d ", res[i]);
}
