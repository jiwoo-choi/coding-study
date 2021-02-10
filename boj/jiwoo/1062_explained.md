# 문제
https://www.acmicpc.net/problem/1062

# 조합을 활용한 풀이.

조합을 활용해 풀 수 있습니다.

하지만, 26 C k * O(n) * O(각 글자길이) 입니다.

n = 50, k = 13, length = 15면

꽤 숫자가 커지기 때문에 가지치기를 해야합니다.

- a,n,t,i,c 에 대해서는 항상 배워야 글자를 읽을 수 있으므로, 26개가 아닌 남은 글자 21개 중에서 뽑아야합니다.
- 실질적으로 비교해야할 문자는 7글자 입니다.
- set 자료구조들을 사용하여 중복되는것은 제거합니다.

최악의 경우에도 1억이 넘지 않으므로 조합으로 충분히 풀 수 있는 알고리즘이 됩니다.

```cpp
void combi(int cnt, int idx) { 

    if (cnt == (k - 5)) {
        int count = 0;
        for (string st : words) {
            bool flag = true;
            for(char ch : st) {
                if (!used[ch-'a']) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }
        answer = max(count, answer);
        return;
    }

    for (int i = idx; i < 26; i++) {
        // 'a','c','i','n','t' 중복 가지치기.
        // 'a','c','i','n','t' 를 false로 바꾸는것 방지.
        if (used[i]) continue; 
        // 조합. string에서 used를 O(1)시간만에 조회하기 위해, 조합과 다르게 used 배열을 사용.
        used[i] = true;
        combi(cnt+1, i+1); // 중복을 막기 위해 i+1 부터 combination call.
        used[i] = false;
    }
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> k; 

    for (int i = 0 ; i < n ; i++) {
        string s;
        cin >> s;
				// set 자료구조 사용.
        words.insert(s.substr(4, s.length()-8));
    }

    char used_list[] = {'a','c','i','n','t'};
    for (int i = 0 ; i < 5; i ++) {
        used[used_list[i]-'a'] = true;
    }

    if (k < 5) { // 만약 5개 이하면, 'a','c','i','n','t'를 배울 수 없으므로 0을 출력해야합니다.
        cout << 0; 
    } else if (k == 26) { // 모든 단어를 다 배웠으므로, 무조건 n 출력.
        cout << n;
    } else {
        combi(0,0); // 조합을 돌립니다.
        cout << answer;
    }
}
```

# 비트를 활용한 풀이.

## bitwise operator

### shift 연산자

- 1 << 0 // 0001

    1을 0 비트 위치 만큼 민다 = 가장 오른쪽 비트에 1을 추가한다.

- 1 << 1 // 0010

    1을 1 비트 위치만큼 민다.

- 1 << 2 // 0100

    1을 2 비트 위치만큼 민다. 

### OR 연산자 - 비트 추가.

```cpp
int A = b'1010;
A = A | (1 << 2);

// (1 << 2 ) = 100;
// 1010 | 0100 = 1110
```

결과적으로 비트를 추가한 형태가 되었습니다.

### XOR 연산자 - 비트 toggle.

```cpp
int A = b'0000;
A = A ^ (1 << 2); // 0100
A = A ^ (1 << 2); // 0000
```

결과적으로 비트를 0→1 1→0 하는데 사용하였습니다.

또 다른 비트 toggle방식으로 OR과 AND를 활용해서 표현 가능합니다.

```cpp
int A = b'0000;
A = A | (1 << 2); // 0100
A = A & ~(1 << 2); // 0000
// ~(1 << 2) = 1011
```

## 비트 계산

O(n) * O(각 글자길이) 를 비트연산으로 바꾸면 글자 비교 시간을 O(N) * O(1) 로 줄일 수 있습니다. 

### XOR연산의 활용 - 비트 포함 여부 확인.

xor은 비트가 다르면 1, 비트가 같으면 0이 됩니다.

`A XOR B` 연산을 통해서 나온 비트는 A 혹은 B 에만 있는 비트를 의미합니다.

A = 1100

B = 0101

A ^ B = 1001 (A 혹은 B 비트에만 있는 비트 모음 리스트)

XOR의 결과로 나온 1이 한 비트로부터 나왔다면..?

A = 1001

B = 1000

A ^ B = 0001 ( XOR의 결과에서 나온 1은 A비트때문에만 생겼다.)

즉, 4번째 비트를 제외하고 나머지 A와 B의 모든 비트는 같다라는 의미이고, A만 추가적으로 4번째 비트가 있다는 의미이므로 B는 A 비트에 포함됩니다.

XOR의 결과에 B의 비트가 포함되어 있지 않다는걸 확인하는 방법은, XOR의 결과에 & 연산을 하여 0이 나오는지 테스트 하면 됩니다.

정리하자면,

- A.contains(B)  == TRUE
- (A ^ B) & B = 0

```cpp
A = 1001
B = 1000
A^B = 0001
(A^B)&B = 0000 = 0 (B는 A에 포함)
```

```cpp
A = 1001
B = 1100
A ^ B = 0101
(A ^ B) & B = 0100 ≠ 0 (B는 A에 포함되지 않음)
```

위를 활용하여 비트를 만들 수 있습니다.

위 식을 축약하면 하나의 **AND**연산으로만 바꿀 수 있습니다.

```python
A & B == B 
// b가 a에 포함되어있다.
```

## string을 int형으로 표현.

int형은 32비트 이기 때문에, 문제에서 주어진 26개의 글자들을 각 26비트로 표현하는데 문제가 없습니다. 따라서, 단어를 string이 아닌 비트를 나타내는 int형으로 표기 가능합니다.

예를들어,

```cpp
string s = "abc"; 
int int_string = 11100000000.... // (2^26 + 2^25 + 2^24)
```

이를 활용하여 코드를 다시 짜보면, 아래와 같습니다.

```cpp
#include <iostream>
#include <string>
#include <vector>
#include <cmath>
#include <algorithm>
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

    for (int i = idx; i < 26; i++) {
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
            words[i] = word_in_bit; 
						// set 자료구조를 사용하면 안됩니다.
						// 단어들이 포함관계에 있으면 비트가 똑같이 나오기 때문에 각각의 단어가 제대로 count되지 않습니다.
            // 예를들어 abac = 111000...
            // abc = 111000...
            // 두개의 단어가 따로 저장되어야 하는데 같은 단어로 인식됩니다.
        }
    }
    
    if (k < 5) { // 만약 5개 이하면, 'a','c','i','n','t'를 배울 수 없으므로 0을 출력해야합니다.
        cout << 0; 
    } else {
        combi(0,0); // 조합을 돌립니다.
        cout << answer;
    }
}
```

## 4번 using python

어제 시간초과과 났던 파이썬 문제를 다시 비트문제로 변경하였습니다.

시간 초과난 코드의 큰 원인은:

1. 조합 코드의 실수: (i+1)을 넣어줘야할걸 (idx+1)을 넣어줌 ⇒ 이 부분 수정.
2. 비교 과정에서 연산과정이 비효율적이었음 ⇒ 비트 비교로 수정.

4번이 통과되는지 체크하기 위해 해당 로직을 bit연산으로 바꿔보겠습니다.

```python
import sys

n, k = map(int, sys.stdin.readline().split(' '))
used = 0;
words = []
answer = 0;

todos = ['a','c','i','n','t'];
for todo in todos:
    used = used | (1 << getIdx(todo))
    

## string을 bit로 변경
for i in range(n):
    word = sys.stdin.readline()[:-1]
    word_in_bit = 0;
    for char in word:
        word_in_bit = word_in_bit | 1 << getIdx(char)
    words.append(word_in_bit)

def combi(cnt, idx):
    global answer;
    global used;
    
    if cnt == (k-5) :
        count = 0
        for word in words:
            if ((word ^ used) & word) > 0:
                continue;
            count = count + 1;
        answer = max(count, answer)
        return;
    else:
        for i in range(idx, 27):
            if (used & (1 << i)) : continue;
            used = used | (1 << i)
            combi(cnt+1, i+1);
            used = used ^ (1 << i)
            

if (k < 5) :
    print(0)
elif (k == 26):
    print(n);
else:    
    combi(0, 0);    
    print(answer)
```

이 코드는 정말 아슬아슬하게 통과 됩니다.

### 다른 파이썬 통과된 코드

위 코드도 아슬아슬하게 통과되었습니다.

조금 더 가지를 쳐서 26가지 letter순회를 21가지로 줄이는 코드로 최적화 할 수 있습니다.

```python
import sys

def combi(cnt, idx):
    global answer;
    global used;
    
    if cnt == k :
        count = 0
        for word in words:
            if (used & word) == word:
                count = count + 1;
        answer = max(count, answer)
        return;
    else:
        ## 27개가 아닌 21개만 돈다.
        for i in range(idx, 21):
            if not used & (1 << i) :
                used = used | (1 << i)
                combi(cnt+1, i+1);
                used = used & ~(1 << i)
        return

n, k = map(int, sys.stdin.readline().split(' '))
used = 0;
words = []
answer = 0;

# 필요없는 글자들은 순회하기 위해 관련 정보 저장..
alpha_num = {}

# 계산할 글자들에 대해서 인덱스 정보 저장.
for i, a in enumerate('bdefghjklmopqrsuvwxyz'):
    alpha_num[a] = i
        

## string을 bit로 변경
for i in range(n):
    word = sys.stdin.readline()[4:-4]
    word_in_bit = 0;
    for char in word:
        
        # 필요 없는 antic은 넘어감.
        if char not in 'antic':    
            word_in_bit = word_in_bit | (1 << alpha_num[char])
    words.append(word_in_bit)

if (k < 5) :
    print(0)
elif (k == 26):
    print(n);
else:
    k -= 5
    combi(0, 0);    
    print(answer)

```

조금 더 안정적으로 통과가 됩니다. 

# 결론

파이썬은 간단한 자료구조와 정렬을 활용하는 문제에선 강력한것같습니다.

따로 함수를 만들어야하는 복잡한 로직에서는 안쓰는게 좋을것같습니다.
