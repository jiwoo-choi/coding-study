import java.io.*;
import java.util.*;

public class Main {
	
	/**
	 * 두 좌표의 차이를 구하는 함수.
	 */
	public static int distance(Pair p1, Pair p2) {
		return Math.abs(p2.x - p1.x) + Math.abs(p2.y-p1.y);
	}

	/**
	 * X좌표와 Y좌표를 나타내는 클래스.
	 */
	static class Pair {
		int x;
		int y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	/** 
	 * Pair처럼 X좌표와 Y좌표를 나타내지만,
	 * 죽었는지, 맵에서 빠져나왔는지 등의 추가 상태 변수를 가지고 있는 클래스.
	 */
	static class Enemy extends Pair {
	    /// 궁수에 의해 죽었으면, 이 상태가 true가 됩니다.
		boolean killed = false;
		/// 죽었거나, 맵 밖으로 나가면 이 상태가 true가 됩니다.
		boolean disappear = false;
		Enemy(int x, int y) {
			super(x, y);
		}
	}
	
	/**
	 * 이 함수는 enemy배열의 깊은 복사본을 만들어 줍니다. (배열 안에까지 다 새롭게 만드는)
	 * 
	 * 매 `simulate()`마다 모든 enemy의 상태는 초기화 되어야합니다.
	 * 그 과정을 쉽게 하기 위해서, simulate() 함수에 원본이 아닌 복사본을 넘겨줍니다. 
	 * 이 과정은 복사본을 만드는 함수입니다.
	 * ⚠️ 하지만 이런 방식은 시간복잡도나, 공간복잡도에서 불리할 수 있는 코드입니다.
	 */
	public static Enemy[] copyEnemies(Enemy[] enemies) {
		int len = enemies.length;
		Enemy[] temp = new Enemy[len];
		for (int k = 0 ;  k< len; k++) {
			temp[k] = new Enemy(enemies[k].x, enemies[k].y);
		}
		return temp;
	}
	
	/**
	 * 이 함수는 3명의 선택된 궁수로 play를 하였을 때, 얼마만큼의 적들을 죽일 수 있는지를 반환해주는 코드입니다.
	 * 
	 * @param enemies 이번 시뮬레이션에서 등장할 적 객체.
	 * @param picked 이번 시뮬레이션 선택된 궁수.
	 * @param d distance 제한 (문제에서 제공)
	 * @param n n 제한 (문제에서 제공)
	 * @return 죽인 몬스터의 숫자.
	 */
	public static int simulate(Enemy[] enemies, Pair[] picked ,int d, int n) {
		/// 궁수가 죽인 적들의 개수를 구함.
		int result = 0;
		/// 현재 필드에 나와있는 enemy의 개수.
		int enemyCnt = enemies.length;
		
		while(true) {
			
			/// 1. 각 선택된 궁수들은,
			for (Pair achor : picked) {
				
				int dist = Integer.MAX_VALUE;
				/// 궁수가 죽일 적의 idx번호.
				int idx = -1;
				
				/// 2. enemy list를 살펴보며 죽일 수 있는 enemy가 있는지 찾아봅니다.
				for (int j = 0 ; j < enemies.length; j++) {	
					
					/// 3-1. 만약 이미 사라져 있는 적이라면, 굳이 비교할 필요 없습니다.
					if (enemies[j].disappear) continue;
						/// 3-1-1. 다만, 여러 achor가 한명의 적을 동시에 노릴 수 있으므로, 죽은 상태인지 여부는 알 필요 없습니다.
					
					/// 3-2. 필드에 남아있는 적이라면, 궁수와 적의 거리 차이를 잽니다.
					int tempDist;
					if ((tempDist = distance(achor, enemies[j])) <= dist && tempDist <= d ) {
						
						/// 3-3. 동일 거리라면, y가 더 적은게 우선순위 입니다.
						if (tempDist == dist) {
							if (enemies[idx].y < enemies[j].y) { 
								continue;
							}
						}
						/// 3-4. 위 조건을 모두 통과하여 만약 죽여야 할 대상이 바뀐다면 바꿔줍니다.
						dist = tempDist;
						idx = j;
					}
				}

				/// 4. 죽여야할 대상이 있다면, 그 대상을 죽입니다.
				if (idx != -1) {
					enemies[idx].killed = true;
				}
				/// 5. 선택된 achor의 역할은 끝났습니다. 다음 achor로 가서 다시 이 과정을 수행합니다.
			}
			
			/// achor들이 한 턴동안 해야할 수행을 모두 했습니다.
			/// 이제 enemy들의 상태 정보를 보고 필드 정보를 (필드에 남아있는 적, 궁수가 몇명을 죽였는) 업데이트해줍니다.
			
			for (Enemy enemy : enemies) {
				/// 만약 이미 사라진 대상이라면 업데이트 할 대상이 아닙니다.
				if (enemy.disappear) continue;
				/// 죽은 대상이라면 필드에서 없어졌다고 표시해주고, 총 enemy count를 줄여줍니다.
				if (enemy.killed && !enemy.disappear) {
					enemy.disappear = true;
					enemyCnt--;
					/// 궁수가 죽인 적들의 숫자입니다.
					result++;
				} else {
					/// 궁수가 죽이지도 않았고, 아직 필드에 남아있다면 한칸씩 전진해줘야합니다.
					
					if (enemy.x+1 >= n) {
						/// 다만, 끝짜락에 도달한 적들은 맵에서 사라져야합니다.
						/// 이 경우 궁수가 죽인것이 아니기 때문에 적 숫자만 줄여줍니다.
						enemy.disappear = true;
						enemyCnt--;
					} else {
						/// 나머지 적들은 그냥 x좌표가 (행 좌표가) 한칸 늘어납니다.
						enemy.x++;
					}
				}
			}
			/// 필드에 적이 아무도 없다면 이 무한 loop를 끝냅니다.
			if (enemyCnt == 0) break;
		}
		
		return result;
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = bf.readLine().split(" ");
		int n = Integer.parseInt(inputs[0]);
		int m = Integer.parseInt(inputs[1]);
		int d = Integer.parseInt(inputs[2]);
		
		/// 궁수정보.
		Pair[] achor = new Pair[m];
		
		for (int i = 0 ; i < m ; i++) {
			achor[i] = new Pair(n,i);
		}
		
		Enemy[] enemies = new Enemy[n * m];
		int cnt = 0;
		
		// 적 정보.
		for (int i = 0 ; i < n ; i++ ) {
			int temp[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0 ; j < m ; j++) {
				if (temp[j] == 1) {
					enemies[cnt++] = new Enemy(i, j);
				}
			}
		}
		
		/// 처음에 적의 사이즈를 최악의 경우인 n*m으로 설정했는데,
		/// enemy 숫자만큼만 딱 맞게 배열을 재조정합니다.
		enemies = Arrays.copyOf(enemies, cnt);
		
		/// simulate()함수에서 빠르게 찾을 수 있도록 정렬을 해둡니다.
		/// 하지만, simulate() 함수에서 초기 정렬된 정보를 크게 활용하진 않았습니다. 
		Arrays.sort(enemies, (p1, p2) -> {
			if (p1.x == p2.x) {
				return p1.y - p2.y;
			} else {
				return p2.x - p1.x;
			}
		});
		
				
		int answer = 0;
		
		/// 조합을 구하는 코드. 비트마스크를 부분집합을 통한 조합 구하기.
		OUTER:
		for (int i = 1 ; i < (1 << m) ; i++) {
			Pair[] achorPicked = new Pair[3];
			int pickCnt = 0;
			for (int j = 0 ; j < m; j++) {
				if ( (i & (1 << j)) > 0 ) {
					/// 만약 3개가 넘어가면.. 의미가 없는지라 탈출시켜줍니다.
					if (pickCnt >= 3) continue OUTER;
					achorPicked[pickCnt++] = achor[j];
				}
			}
			if (pickCnt == 3) {
				/// 3개가 뽑혔다면..
				/// 1. 복사된 enemies
				/// 2. 선택된 궁수 정보 
				/// 등등.. 을 simulate()에 넘깁니다.
				/// 매번 최대값을 업데이트 해줍니다.
				answer = Math.max(simulate(copyEnemies(enemies), achorPicked, d, n), answer);
			}
		}
		
		// 답 출력
		System.out.println(answer);
	}

}
