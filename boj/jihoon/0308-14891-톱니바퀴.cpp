#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>
#include <cmath>
#include <cstdlib>

using namespace std;

#define MAX_K 22
#define CW 1
#define CCW -1

int K;
deque<int> deq[4];
bool isVisited[4];

int rotate(int idx, int dir)
{
	int tmp;
	if (dir == CW)
	{
		tmp = deq[idx].back();
		deq[idx].pop_back();
		deq[idx].push_front(tmp);
	}
	else
	{
		tmp = deq[idx].front();
		deq[idx].pop_front();
		deq[idx].push_back(tmp);
	}
	return 0;
}

int simul(int idx, int dir)
{
	if (idx < 0 || idx >= 4)
		return 0;

	isVisited[idx] = true;

	// 왼쪽
	if (idx - 1 >= 0 && !isVisited[idx - 1] && deq[idx].at(6) != deq[idx - 1].at(2))
		simul(idx - 1, -dir);

	// 오른쪽
	if (idx + 1 <  4 && !isVisited[idx + 1] && deq[idx].at(2) != deq[idx + 1].at(6))
		simul(idx + 1, -dir);

	rotate(idx, dir);
}

int main()
{
	int testcase = 1;
	for (int tc = 1; tc <= testcase; tc++)
	{
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 8; j++)
			{
				int tmp;
				scanf("%1d", &tmp);
				deq[i].push_back(tmp);
			}

		scanf("%d", &K);
		for (int i = 0; i < K; i++)
		{
			memset(isVisited, 0, sizeof(isVisited));

			int idx, dir;
			scanf("%d%d", &idx, &dir);
			simul(idx - 1, dir);
		}

		int res = 0;
		for (int i = 0; i < 4; i++)
		{
			res += deq[i].front() == 1 ? pow(2, i) : 0;
			deq[i].clear();
		}
		printf("%d\n", res);
	}
}
