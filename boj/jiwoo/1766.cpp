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

    int n, m;
    cin >> n;
    cin >> m;
    arr = vector<vector<int>>(n+1);
    indeg = vector<int>(n+1);

    for (int i = 0 ; i < m ; i++) {
        int from, to;
        cin >> from >> to;
        arr[from].emplace_back(to);
        indeg[to]++;
    }
    queue<int> q;
    priority_queue<int> pq;

    for (int i = 1 ; i <= n ; i++) {
        if (indeg[i] == 0) {
            pq.push(-i);
        }
    }

    while(!pq.empty()) {
        int here = -pq.top();
        answer.push_back(here);
        pq.pop();
        int size = arr[here].size();
        for (int i = 0 ; i < size; i++) {
            int there = arr[here][i];
            indeg[there]--;
            if (indeg[there] == 0) {
                pq.push(-there);
            }
        }
        //pq에 있는걸 다 빼주기.
    }

    for (int data : answer) {
        cout << data << ' ';
    }
    
  
}
