#include <iostream>
#include <string>
#include <vector>
#include <cmath>
#include <algorithm>
#include <set>
#include <cstring>

using namespace std;

int used;
int words[51];
int answer = 0;
int n;
int k;

void combi(int cnt, int idx) { 

    if (cnt == (k - 5)) {
        int count = 0;
        for (int i = 0 ; i < n; i++) {
            if (((words[i] ^ used) & words[i]) > 0) continue; // 0보다 크다는 의미는, word_bit가 used bit에 포함되지 않음을 의미.
            count++;
        }
        answer = max(count, answer);
        return;
    }

    for (int i = idx; i < 27; i++) { // 0부터 25번째까지의 총 26개의 idx를 활용하는것이 아니라
        // 'a','c','i','n','t' 중복 가지치기.
        // 'a','c','i','n','t' 를 false로 바꾸는것 방지.
        if (used & (1 << i)) continue; 
        used |= (1 << i); // 1을 추가하기 위해 OR연산 활용 
        combi(cnt+1, i+1); // 중복을 막기 위해 i+1 부터 combination call.
        used ^= (1 << i); // 1을 제거하기 위해 XOR연산 활용.
    }
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    used = 0;
    answer = 0;
    memset(words, 0, sizeof(words));

    cin >> n >> k; 

    char used_list[] = {'a','c','i','n','t'};
    for (int i = 0 ; i < 5; i ++) {
        used |= (1 << (used_list[i]-'a'));
    }

    for (int i = 0 ; i < n ; i++) {
        string s;
        cin >> s;
        int word_in_bit = 0;
        for (int j = 0 ; j < s.length(); j++) {
            int val = s[j] - 'a';
            word_in_bit = word_in_bit | (1 << (val)); // word를 bit로 기록해줍니다. OR연산을 이용합니다.
            words[i] = word_in_bit; // set 자료구조를 사용하면 안됩니다. 단어들이 포함관계에 있으면 비트가 똑같이 나오기 때문에 단어가 제대로 count되지 않습니다.
            // 예를들어 abac = 111000...
            // abc = 111000...
            // 두개의 단어가 따로 저장되지 않음!
        }
    }
    
    if (k < 5) { // 만약 5개 이하면, 'a','c','i','n','t'를 배울 수 없으므로 0을 출력해야합니다.
        cout << 0; 
    } else {
        combi(0,0); // 조합을 돌립니다.
        cout << answer;
    }
}
