#include <iostream>
#include <queue>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);


    int n;
    cin >> n;
    for (int i = 0; i < n ;i++) {
        stack<char> left;
        stack<char> right;
        string s;
        cin >> s;

        for (int j = 0 ; j < s.length(); j++) {
            if (s[j] == '<') {
                if (left.empty()) continue;
                right.push(left.top());
                left.pop();
            } else if (s[j] == '>') {
                if (right.empty()) continue;
                left.push(right.top());
                right.pop();
            } else if (s[j] == '-') {
                if (left.empty()) continue;
                left.pop();
            } else {
                left.push(s[j]);
            }
        }

        vector<char> answer;

        while(!left.empty()) {
            answer.push_back(left.top());
            left.pop();
        }

        reverse(answer.begin(), answer.end());
        while(!right.empty()){
            answer.push_back(right.top());
            right.pop();
        }

        for (char ch : answer) {
            cout << ch;
        }
        cout <<'\n';
    }

}
