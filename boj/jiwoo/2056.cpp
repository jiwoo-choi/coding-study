#include <iostream>
#include <queue>
#include <vector>
using namespace std;

vector<vector<int>> arr;
vector<int> indeg;
vector<int> answer;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;
    arr = vector<vector<int>>(n+1);
    indeg = vector<int>(n+1);
    answer = vector<int>(n+1);
    vector<int> time = vector<int>(n+1);

    for (int i = 1; i <= n ; i++) {
        int ti, m, from;
        cin >> ti;
        time[i] = ti;
        cin >> m;
        for (int j = 0 ; j < m; j++) {
            cin >> from;
            arr[from].emplace_back(i);
            indeg[i]++;
        }
    }

    queue<int> q;

    for (int i = 1 ; i <= n ; i++) {
        if (indeg[i] == 0) {
            q.push(i);
            // answer[i] = time[i];
        }
    }

    while(!q.empty()) {
        int here = q.front();
        q.pop();
        int size = arr[here].size();
        answer[here] += time[here];
        for (int i = 0 ; i < size; i++) {
            int there = arr[here][i];
            indeg[there]--;
            answer[there] = max(answer[here], answer[there]);
            if (indeg[there] == 0) {
                q.push(there);
            }
        }
    }
    int ans = -1;
    for (int i = 1 ; i <= n ; i++) {
        ans = max(answer[i],ans);
    }
    cout << ans;
}
