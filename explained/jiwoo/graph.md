# 그래프 순회

## BFS

## DFS

### 노드를 통한 구현
### 노드 없이 구현

## 플로이드-와샬

이름은 거창하지만 모든 쌍의 최단 거리를 구하는 알고리즘.
O(n^3)이 걸리는 알고리즘이지만, n = 3
n이 400개 이하여야지만 안전하게 구할 수 있다.

```
for (int k = 1 ; k <= n ; k++) {
  for (int i = 1; i <= n ; i++) {
    for (int j = 1; j <=n ; j++) {
      if (d[i][j] > d[i][k] + d[k][j]) {
        d[i][j] = d[i][k] + d[k][j];
      }
   }
 }
```
d[k][i][j] = i->j로 이동하는 최단 경로인데, 중간에 1부터 k를 거치는 모든 경우의 수.
d[k][i][j] = i->j에 k가 경로에 없을 경우, i->j를 가는데 k가 경로에 있는 경우.
* i->j에 k가 경로에 없을 경우 : d[k-1][i][j];
* i->j에 k가 경로에 있는 경우 : d[k-1][i][k] + d[k-1][k][j]; // i->k 로 가는 경로 + k->j 가는 경로. 

k는 어처피 항상 k-1밖에 나오지 않으므로, 3차원 배열이 아닌 2차원으로 구현해도 무방하다.

```
/* 노드 별로 초기화 단계 (노드의 수 n) */
for (int i = 1; i <= n ; i++) {
  for (int j = 1 ; j <= n ; j++) {
      if (i == j) arr[i][j] = 0;
      else arr[i][j] = INFINITY;
   }
}
```
```
/* 간선 정보 만큼 셋팅 (간선의 수 m) */
for (int i = 0; i < m ; i++) {
  input : from, to, cost;
  if (a[from][to] == INFINITY) {
    a[from][to] = cost;
  }
}
```
```
/* 플로이드 와샬 */
for (int k = 1 ; k <= n ; k++) {
  for (int i = 1; i <= n ; i++) {
    for (int j = 1; j <=n ; j++) {
      if (d[i][j] > d[i][k] + d[k][j]) { 
        /* 만약 i->k , k->j의 경로가 없다면 INFINITY 이므로 if문 통과 */
        /* 만약 경로가 있다면, 최단 경로를 업데이트 */
        d[i][j] = d[i][k] + d[k][j];
      }
   }
 }
```
```
/* 결과 출력 */
for (int i=1; i<=n; i++) {
  for (int j=1; j<=n ;j++){
     if (d[i][j] == 0) { continue; }
     else if (d[i][j] == INFINITY) {continue;}
     else {
        output: i -> j로 가는 최적경로는 d[i][j] 만큼 걸린다.
     }  
     
  }
}
```



## 다익스트라


# 응용

## 트리의 지름




