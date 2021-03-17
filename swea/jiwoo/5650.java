import java.util.*;
import java.io.*;

import java.io.BufferedReader;

public class Solution {
	
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {-1, 1, 0, 0}; // 왼, 오, 위, 아래.
	
	static int LEFT = 0;
	static int RIGHT = 1;
	static int UP = 2;
	static int DOWN = 3;
	
	static class HoleManager {
		LinkedList<int[]>[] list = new LinkedList[5];
		
		public HoleManager() {
			for(int i = 0 ; i < 5; i++) {
				list[i] = new LinkedList<>();
			}
		}
		
		public void add(int num, int sx, int sy) {
			list[num - 6].add(new int[] {sx, sy });
		}
		
		public int[] search(int num, int tx, int ty) {
			if (list[num - 6].size() == 0) return null;
			if (list[num - 6].get(0)[0] == tx && list[num - 6].get(0)[1] == ty) {
				return list[num-6].get(1);
			} else {
				return list[num-6].get(0);
			}
		}
	}
	public static void main(String[] args) throws Exception {
		
		
//		System.out.println(nextDirection(LEFT, 1) == UP);
//		System.out.println(nextDirection(RIGHT, 1) == LEFT);
//		System.out.println(nextDirection(UP, 1) == DOWN);
//		System.out.println(nextDirection(DOWN, 1) == RIGHT);

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <=t ; test_case++) {
			int n = Integer.parseInt(bf.readLine());
			HoleManager hm = new HoleManager();
			int[][] map = new int[n][];
			for (int i = 0 ; i < n ; i++) {
				map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for (int j = 0 ; j < n ; j++) {
					int element = map[i][j];
					if (element >= 6 && element <= 10) hm.add(element, i, j);
				}
			}
			
//				map[i] = Arrays.stream(bf.readLine().split(" ")).reduce( "", (prev,curr) -> prev + curr.charAt(0)).toCharArray();
			
			
			
//			System.out.println("finished");
			int answer = Integer.MIN_VALUE;
			for (int i = 0 ; i < n ; i++) {
				for (int j = 0 ; j < n ; j++) {

					if (map[i][j] == 0) {
						// 왼 오 위 아래.
						int x = i;
						int y = j;
						
						for (int k = 0 ; k < 4 ; k++) {
							int direction = k;
							int hitCnt = 0;
							int nx = x + dx[direction];
							int ny = y + dy[direction];
													
							while(x != nx || y != ny) {
//								System.out.println("i , j : " + i + " " + j + "DIRECTION : " + direction);
//								System.out.println(nx + " " + ny);
								// 범위 밖으로 나간경우.
								if (!(nx >= 0 && ny >= 0 && nx < n && ny < n)) {
									hitCnt++;
									direction = nextDirection(direction, 5);
								} else {

									int element = map[nx][ny];

									if (map[nx][ny] == -1) {
										// 블랙홀이면 탈출하기.
										hitCnt = Math.max(answer, hitCnt);
										break;
									} else if (element >= 1 && element <= 5) {
										// 벽이면 다음 방향으로 바꿔준뒤 hit Cnt ++;
										hitCnt++;
										direction = nextDirection(direction, element);
									} else if (element >= 6 && element <= 10) {
										// 웜홀인지 체크하기.
										int[] pair = hm.search(element, nx, ny);
										nx = pair[0];
										ny = pair[1];
										
									} else {
										// 아무것도아님.
									}
								}
								nx += dx[direction];
								ny += dy[direction];
							}
							answer = Math.max(hitCnt, answer);
						}
					}
				}
			}
			System.out.println("#" + test_case + " " + answer);
		}
		
	}
	
	public static int nextDirection(int prevDirection, int blockType) {
		
		if (blockType == 1) { // done
			if (prevDirection == UP) return DOWN;
			else if (prevDirection == DOWN) return RIGHT;
			else if (prevDirection == LEFT) return UP;
			else if (prevDirection == RIGHT) return LEFT;
		} else if (blockType == 2) { // done
			if (prevDirection == UP) return RIGHT;
			else if (prevDirection == DOWN) return UP;
			else if (prevDirection == LEFT) return DOWN;
			else if (prevDirection == RIGHT) return LEFT;
		} else if (blockType == 3) { // done
			if (prevDirection == UP) return LEFT;
			else if (prevDirection == DOWN) return UP;
			else if (prevDirection == LEFT) return RIGHT;
			else if (prevDirection == RIGHT) return DOWN;
		} else if (blockType == 4) { // done
			if (prevDirection == UP) return DOWN;
			else if (prevDirection == DOWN) return LEFT;
			else if (prevDirection == LEFT) return RIGHT;
			else if (prevDirection == RIGHT) return UP;
		} else if (blockType == 5) { // done
			if (prevDirection == UP) return DOWN;
			else if (prevDirection == DOWN) return UP;
			else if (prevDirection == LEFT) return RIGHT;
			else if (prevDirection == RIGHT) return LEFT;
		}
		return UP;
	}
	

}
