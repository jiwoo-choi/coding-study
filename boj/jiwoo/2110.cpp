#include <iostream>
#include <queue>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

int n;
int c;
vector<int> arr;
// 인접한 최대거리가 d일 때, 설치하는 라우터의 개수가 c 이상인가?
bool f(int d) {

    int cnt = 1;
    int prev = arr[0];

    for (int i = 1 ; i < n ; i++) {
        if (arr[i] - prev >= d) {
            cnt++;
            prev = arr[i];
        }
    }
    return cnt >= c; 
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n;
    cin >> c;

    arr = vector<int>(n);
    for (int i = 0 ; i < n ; i++) {
        cin >> arr[i];
    }
    sort(arr.begin(), arr.end());
    int start = 1; // 가장 최소 길이.
    int end = arr[n-1] - arr[0]; // 가장 최대 길이.
    int ans = 0;
    while(start <= end) { // ㅁ목표 : 최소로 나오는 1을 구하라.
        int mid = (start + end) / 2;
        if (f(mid)) {
            ans = mid;
            start = mid + 1;
        } else {
            end = mid - 1;
        }
    }
    cout << ans;
}
