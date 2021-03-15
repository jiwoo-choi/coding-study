import java.util.*;
import java.io.*;

public class Solution {

	
	static class Batch {
		int x;
		int y;
		int area;
		
		public Batch(int x, int y, int area) {
			this.x = x;
			this.y = y;
			this.area = area;
		}

		@Override
		public String toString() {
			return "Batch [x=" + x + ", y=" + y + ", area=" + area + "]";
		}
	}
	
	static int[] selected;
	static int[][] gmap;
	static int ans = Integer.MAX_VALUE;
	
	static void permutation(int n, int w, int h, int cnt) {
		
		if (cnt== n) {
						
//			System.out.println(Arrays.toString(selected));

			int[][] map = new int[h][w];
			for (int i = 0; i < h ; i ++) {
				for (int j = 0 ; j < w ; j++) {
					map[i][j] = gmap[i][j];
				}
			}
			
//			System.out.println("BEFORE");
//			for (int i = 0; i < h ; i ++) {
//				for (int j = 0 ; j < w ; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}			

			
			for(int i = 0 ; i < n ;i++) {
				List<Batch> batches = batchAdd(w, h , 0, selected[i], map);
				if (batches != null) {
					batchExec(batches, w, h, map);
					trim(w, h, map);
				}
			}
			
//			System.out.println("AFTER");
//			for (int i = 0; i < h ; i ++) {
//				for (int j = 0 ; j < w ; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}			
			
			int answer = 0;
			
			for (int i = 0; i < h ; i ++) {
				for (int j = 0 ; j < w ; j++) {
//					System.out.print(map[i][j] + " ");
					if (map[i][j] > 0) {
						answer++;
					}
				}
			}			
			ans = Math.min(answer, ans);

			return;
		} else {
			for (int i = 0 ; i < w ; i++) {
				selected[cnt] = i;
				permutation(n , w, h,cnt+1);
			}
		}
		return;
	}
	
	
	static int dx[] = { 0, 0, -1, 1};
	static int dy[] = { -1, 1, 0, 0};

	public static List<Batch> batchAdd(int w, int h, int sx, int sy, int[][] map) {

		boolean [][] isContained = new boolean[h][w];
		
		while(sx < h && map[sx][sy] == 0) {
			sx++;
		}
		
		if (sx >= h) {
			return null;
		}
//		System.out.println("SX : " + sx + " SY :  " + sy);
		Queue<Batch> q = new LinkedList<>();
		q.add(new Batch(sx, sy, map[sx][sy]));
		isContained[sx][sy] = true;
		List<Batch> batchList = new LinkedList<>();
		
		while(!q.isEmpty()) {		
			
			Batch batch = q.poll();
			
			int x = batch.x;
			int y = batch.y;
			int area = batch.area;
			batchList.add(batch);
			
			for (int k = 0 ; k < 4 ; k++) {
				int tempArea = area;
				int nx = x + dx[k];
				int ny = y + dy[k];
				tempArea--;
				
				while(nx >= 0 && nx < h && ny >= 0 && ny < w && tempArea >= 1) {
					
					if (map[nx][ny] > 0 && !isContained[nx][ny]) {
						q.add(new Batch(nx, ny, map[nx][ny]));
						isContained[nx][ny] = true;
					}
					tempArea--;
					nx = nx + dx[k];
					ny = ny + dy[k];
				}
			}
		}
		return batchList;
	}
	
	public static void batchExec(List<Batch> batchList, int w, int h, int[][] map) {
		// batchList에 모든걸 제거하는 연산을 수행합니다.
		
		for (Batch batch : batchList) {
			for (int i = 0 ; i < 4; i++) {
				// 4방향을 돌며 모두 0으로 만듭니다.
				int nx = batch.x;
				int ny = batch.y;
				int area = batch.area;
				
				while(nx >= 0 && nx < h && ny >= 0 && ny < w && area > 0) {
					map[nx][ny] = 0;
					nx = nx + dx[i];
					ny = ny + dy[i];
					area--;
				}
			}
		}
		
	}
	
	public static void trim(int w, int h, int[][] map) {
		//0부터 빈곳을 다 채워준다.
		
		for (int j = 0; j < w ; j++) {
			int space = 0;
			for (int i = h-1 ; i >= 0 ; i--) {
				if (map[i][j] == 0) {
					space++;
				} 
				
				if (map[i][j] != 0 && space > 0) {
					map[i+space][j] = map[i][j];
					map[i][j] = 0;
				} 
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= t; test_case++) {
			int[] inputs = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int n = inputs[0];
			int w = inputs[1];
			int h = inputs[2];
			selected = new int[n];
			gmap = new int[h][w];
			ans = Integer.MAX_VALUE;
			for (int i = 0; i < h ; i++) {
				gmap[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			permutation(n, w, h, 0);
			System.out.println("#" + test_case + " " + ans);
		}

	}

}
