import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	
	public static int distance(Pair p1, Pair p2) {
		return Math.abs(p2.x - p1.x) + Math.abs(p2.y-p1.y);
	}

	static class Pair {
		int x;
		int y;
		boolean killed = false;
		boolean disappear = false;
		boolean attacked = false;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static int simulate(Pair[] enemies, Pair[] picked ,int d, int n) {
		int result = 0;
		int enemyCnt = enemies.length;
		while(true) {
			
			for (Pair p1 : picked) {
				int dist = Integer.MAX_VALUE;
				int idx = -1;
				for (int j = 0 ; j < enemies.length; j++) {	
					
					// 이미 사라진 적은 비교할 필요 없음.
					if (enemies[j].disappear) continue;
					
					// 왼쪽부터 돌면서 가장 가까운거 픽.
					int tempDist;
					if ((tempDist = distance(p1, enemies[j])) <= dist && tempDist <= d ) {
						// 만약에 distance가 같다면, 왼쪽 여부를 체크한다.
						if (tempDist == dist) {
							if (enemies[idx].y < enemies[j].y) { 
								continue;
							}
						}
						dist = tempDist;
						idx = j;
					}
				}
				// 찾았다면 죽여준다.
				if (idx != -1) {
					enemies[idx].killed = true;
				}
			}
			
			for (Pair enemy : enemies) {
				if (enemy.disappear) continue;
				if (enemy.killed && !enemy.disappear) {
					enemy.disappear = true;
					enemyCnt--;
					result++;
				} else {
					if (enemy.x+1 >= n) {
						enemy.disappear = true;
						enemyCnt--;
					} else {
						enemy.x++;
					}
				}
			}
			
			if (enemyCnt == 0) break;
		}
		
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		// 적의 위치를 담아놓는 리스트.
		// 적의 위치는 y의 기준 -> x 기준으로 compare 되어야함.
		// distan
		//ce되는대로 쏴 죽임.
		// 두번째는 nextPermutation이 좋을듯?
		// 0,0, (0,1)
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = bf.readLine().split(" ");
		int n = Integer.parseInt(inputs[0]);
		int m = Integer.parseInt(inputs[1]);
		int d = Integer.parseInt(inputs[2]);
		
		Pair[] achor = new Pair[m];
		
		for (int i = 0 ; i < m ; i++) {
			achor[i] = new Pair(n,i);
		}
		
		Pair[] enemies = new Pair[n * m];
		int cnt = 0;
		for (int i = 0 ; i < n ; i++ ) {
			int temp[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0 ; j < m ; j++) {
				if (temp[j] == 1) {
					enemies[cnt++] = new Pair(i, j);
				}
			}
		}
		
		enemies = Arrays.copyOf(enemies, cnt);
		
		Arrays.sort(enemies, (p1, p2) -> {
			if (p1.x == p2.x) {
				return p1.y - p2.y;
			} else {
				return p2.x - p1.x;
			}
		});
		
				
		int value = 0;
		
		OUTER:
		for (int i = 1 ; i < (1 << m) ; i++) {
			Pair[] picked = new Pair[3];
			int pickCnt = 0;
			for (int j = 0 ; j < m; j++) {
				if ( (i & (1 << j)) > 0 ) {
					if (pickCnt >= 3) continue OUTER;
					picked[pickCnt++] = achor[j];
				}
			}
			if (pickCnt == 3) {
				Pair[] temp = new Pair[cnt];
				for (int k = 0 ;  k< cnt; k++) {
					temp[k] = new Pair(enemies[k].x, enemies[k].y);
				}
				value = Math.max(simulate(temp, picked, d, n), value);
			}
		}
		
		System.out.println(value);
	
	
	}

}
