#include <iostream>
#include <queue>
#include <algorithm>
#include <string>

using namespace std;

int d[51][51];
int main() {

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;
    int MAX_LENGTH = 5000000;

    for (int i = 1 ; i <= n ;i++) {
        for (int j = 1; j <= n ; j++) {
            if (i == j) d[i][j] = 0;
            else d[i][j] = MAX_LENGTH;
        }
    }

    for (int i = 1 ; i <= n; i++) {
        string s;
        cin >> s;
        for (int j = 1 ; j <= s.length(); j++) {
            
            if (d[i][j] == MAX_LENGTH && s[j-1] == 'Y') {
                d[i][j] = 1; 
                d[j][i] = 1;
            }
        }
    }

    for (int k = 1; k <= n; k++) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1 ; j <= n ;j ++) {
                if (i != j && i != k && k != j && d[i][k] == 1 && d[k][j] == 1 && d[i][j] == MAX_LENGTH) {
                    d[i][j] = 2;
                }
            }
        }
    }

    int answer = 0;
    for (int i = 1; i <= n ; i++) {
        int result = 0;
        for(int j = 1; j <=n ;j++) {
            // cout << d[i][j] << ' ';
            if (d[i][j] == 0) continue;
            else if (d[i][j] > 2) continue;
            else result++;
        }
        answer = max(result, answer);
    }
    cout << answer;
}
