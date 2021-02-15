#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int n, m;
    cin >> n;
    vector<int> a;
    vector<int> b;
    for (int i = 0 ; i < n ; i++) {
        int temp;
        cin >> temp;
        a.push_back(temp);
    }
    sort(a.begin(), a.end());
    
    cin >> m;
    for (int i = 0 ; i < m ; i++) {
        int temp;
        cin >> temp;
        auto ss = lower_bound(a.begin(), a.end(), temp);
        if (ss == a.end()) cout << 0 << '\n';
        else if (temp == a[ss - a.begin()]) cout << 1 << '\n'; 
        else cout << 0 << '\n';
    }

}
