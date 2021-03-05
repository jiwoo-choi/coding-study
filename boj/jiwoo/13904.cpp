#include <iostream>
#include <queue>

using namespace std;
int main() {

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    
    priority_queue<pair<int,int>> q;
    vector<int> ans(1001,0);
    int n;
    cin >> n;
    
    for (int i = 0 ; i < n ; i++) {
        int a , b;
        cin >> a >> b;
        q.push({b , -a});
    }

    while(!q.empty()) {
        int day = -q.top().second;
        int value = q.top().first;
        q.pop();
        int idx = day;
        while(idx > 0) {
            if (ans[idx] == 0) {
                ans[idx] = value;
                break;
            } else {
                idx--;
            }
        } 
    }
    int result = 0;
    for (int ele : ans) {
        result += ele;
    }
    cout << result;
}
