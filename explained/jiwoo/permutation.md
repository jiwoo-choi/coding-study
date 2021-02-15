# 순열 정리

nPr : n개중에 r개를 선택해 줄 세우는 방법. 

## r이 충분히 낮을경우.
단순하게 for문으로 돌린다.

```
for (int i = 0 ; i < 3 ; i++) {
  for (int j = 0 ; j < 3; j++) {
    if (j == i) continue;
    for (int k = 0 ; k < 3; k++) {
      if (k == i || k == j) continue;
      System.out.println(i + " " + j + " " + k);
  }
 }
}
```

```
0 1 2
0 2 1
1 0 2
1 2 0
2 0 1
2 1 0
```

## 일반적인 방법 : 재귀
순서가 다르면, 각각 의미가 달라지므로 조합처럼 idx를 기준으로 재귀를 돌지 않고, isSelected라는 array로 중복을 체크한다.
단, isSelected는 전역변수로 관리되기 때문에 상태를 되돌려주어야한다.

```
static int n = 3;
static int r = 3;
static int arr[] = {1,2,3};
static int number[] = new int[3];
static boolean isSelected[] = new isSelected[3];

public static void permutation(int cnt) {
    if (cnt == r) {
      System.out.println(Arrays.toString(number));
    } else {
      for (int i = 0 ; i < n ; i++) {
        if (isSelected[i]) continue;
        isSelected[i] = true;
        number[cnt] = arr[i]; // 순열 기록용 배열 'number'
        permutation(cnt+1);
        isSelected[i] = false;
    }
  }
}
```
```
[1, 2, 3]
[1, 3, 2]
[2, 1, 3]
[2, 3, 1]
[3, 1, 2]
[3, 2, 1]
```

## 재귀응용 : 비트마스크
비트마스크를 활용해 isSelected의 과정을 대체할 수 있다.
isSelected라는 배열을 비트로 대체가능하다.

보통 10개미만만 순열로 가능하다. 따라서 int형(32비트) 정도면 무리없이 표현 가능하다.
isSelected와 달리, 전역상태(모두가 공유하는 상태) 를 변경하는게 아니라, 들어온 flag에 대한 비트를 변경해서 넘겨주므로, 굳이 1로 켰던 비트를 꺼줄 필요는 없다.

비트 체크에는 & 연산을
비트 추가에는 | 연산을 활용하였다.

[비트를 지울때는 and나 xor를 활용할 수 있다.](https://github.com/jiwoo-choi/coding-study/blob/main/explained/jiwoo/boj_1062_combination_bitmask.md)

```
static int n = 3;
static int r = 3;
static int arr[] = {1,2,3};
static int number[] = new int[3];

public static void permutation(int cnt, int flag) {
    if (cnt == r) {
      System.out.println(Arrays.toString(number));
    } else {
      for (int i = 0 ; i < n ; i++) {
        if ((flag & (1 << i)) != 0) continue; 
        number[cnt] = arr[i]; // 순열 기록용 배열 'number'
        permutation(cnt+1, flag | 1 << i);
    }
  }
}
```

```
[1, 2, 3]
[1, 3, 2]
[2, 1, 3]
[2, 3, 1]
[3, 1, 2]
[3, 2, 1]
```


## next_permutation 구현하기.
n == r일 때 활용 가능. 사전순으로 순열을 뽑을 수 있음.

1. 배열 정렬을 한다.
2. A[i-1] < A[i]를 만족하는 가장 큰 i를 찾는다. (꼭대기찾기)
2. 꼭대기보다 뒤에 있으면서, 꼭대기 앞에 있는 값보다 큰 것들을 찾는다. (j>=i 이면서, a[j] > a[i-1]을 만족하는 가장 큰 j)
3. A[i-1]과 A[j]를 swap.
4. A[i]부터 내림차순으로 정렬되어있는 배열을, 오름차순으로 변경함.

- 다음 순열을 구하는 시간 복잡도 O(N)
- 전체 순열을 구하는 시간 복잡도 O(N!)
- 시간복잡도가 O(N! * N) 이므로, N이 10 이하일 때 사용 가능.


```
public static boolean next_permutation() {
  int i = n-1;
  while ( i > 0 && opp[i-1] >= opp[i]) i = i -1; // ...(1)번
  if ( i <= 0 ) return false; 
  int j = n-1;
  // i는 꼭대기를 의미한다. 
  // j는 바꿀 타겟이다.
  // 내림차순이기 때문에 끝부터 순차적으로 훑는다.
  while (opp[j] <= opp[i-1]) j = j - 1; // ...(2)번
  swap(opp, i-1, j); // i-1과 j를 교체해줘야한다. // ...(3)번

  // i이후의 배열은 내림차순이기때문에
  // 교차해서 바꿔줄 경우, 내림차순 -> 오름차순으로 변경 가능하다.
  int k = n-1; 
  while ( i < k ) { // ...(4)번
    swap(opp, i , k);
    i += 1;
    k -= 1;
  }
  return true;
}
```


### next_permutation 응용 : 조합 구하기.

```
static int[] opp = { 0, 0, 0, 1, 1};
	
public static boolean next_permutation() {
  int i = n-1;

  while ( i > 0 && opp[i-1] >= opp[i]) i = i -1;
  if ( i <= 0 ) return false;
  int j = n-1;
  while (opp[j] <= opp[i-1]) j = j - 1;
  swap(opp, i-1, j);

  int k = n-1;
  while ( i < k ) {
    swap(opp, i , k);
    i += 1;
    k -= 1;
  }
  return true;
}
 ```
 
```
do {
  System.out.println(Arrays.toString(opp));
} while(next_permutation());
```
```
[0, 0, 0, 1, 1]
[0, 0, 1, 0, 1]
[0, 0, 1, 1, 0]
[0, 1, 0, 0, 1]
[0, 1, 0, 1, 0]
[0, 1, 1, 0, 0]
[1, 0, 0, 0, 1]
[1, 0, 0, 1, 0]
[1, 0, 1, 0, 0]
[1, 1, 0, 0, 0]
```
1이 있는 부분이 선택되었다고 가정하고 계산을 진행할 수 있다.
