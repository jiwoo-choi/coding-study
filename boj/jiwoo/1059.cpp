#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;
int main() {

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int n;
    cin>> n;
    
    vector<int> a = vector<int>(n);
    for (int i = 0 ; i < n ; i++) {
        cin >> a[i]; 
    }
    sort(a.begin(), a.end());

    int t;
    cin >> t;
    // t이상의 숫자가 처음으로 나오는 구간.
    auto val = upper_bound(a.begin(), a.end(), t-1);
    int idx = val-a.begin();

    if (a[idx] == t) {
        cout << 0;
        return 0;
    } else {
        int start;
        int end;

        if (idx == 0)  {
            start = 1;
            end = a[idx] - 1;   
        } else {
            start = a[idx-1]+1;
            end = a[idx]-1;
            if (t== a[idx-1]) {  
                cout << 0; 
                return 0;    
            }
        }

        // start나 end값이 겹치는지 판단하기.
        int result = 0;
        while(start <= t) {
            result += (end - t + 1);
            start++;
        }
        cout << result - 1;
    }


}
