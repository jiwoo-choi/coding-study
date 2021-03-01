#include <iostream>
#include <queue>
#include <vector>
using namespace std;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n;
    cin >> m;

    vector<vector<int>> arr = vector<vector<int>>(n+1);
    vector<int> indeg = vector<int>(n+1);

    for (int i = 0 ; i < m ; i++) {
        int from, to;
        cin >> from >> to;
        arr[from].emplace_back(to);
        indeg[to]++;
    }

    queue<int> q;
    vector<int> answer;
    for (int i = 1 ;i <=n ; i++) {
        if (indeg[i] == 0) { 
            q.push(i);
            answer.push_back(i);
        }
    }

    while(!q.empty()) {
        int here = q.front();
        q.pop();
        int size = arr[here].size();
        for (int i = 0 ; i < size ;i++) {
            int there = arr[here][i];
            indeg[there]--;
            if (indeg[there] <= 0) {
                q.push(there);
                answer.push_back(there);
            }
        }
    }

    for( int val : answer) {
        cout << val << ' ' ;
    }
}
