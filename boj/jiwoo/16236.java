import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int arr[][];
	static boolean visited[][];
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	static int time;
	static int curSize;
	static int curExp;
	
	static class Point implements Comparable {
		
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			Point obj = (Point) o;
			if (obj.x == this.x) {
				return this.y - obj.y;
			} else {
				return this.x - obj.x;
			}
		}
	}
	
	
	public static int bfs(Point p) {
		// 레벨별로 나누기 시도.

		for (int i = 0 ; i < n ; i++) {
			for (int j = 0 ; j < n ; j++) {
				visited[i][j] = false;
			}
		}
		
		Queue<Point> q = new ArrayDeque<>();
		List<Point> fishes = new LinkedList<>();
		q.add(p);
		visited[p.x][p.y]= true; 
		
		int level = 0;
		while(!q.isEmpty()) {
			
			int size = q.size();
			
			for (int i = 0 ; i < size; i++) {
				
				Point cp = q.poll();
				
				int x = cp.x;
				int y = cp.y;
				
				visited[cp.x][cp.y] = true;
				
				for (int k = 0 ; k < 4 ; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];
					if (nx < 0 || nx >= n || ny < 0 || ny >= n ) continue;
					if (visited[nx][ny] == false && arr[nx][ny] <= curSize) {
						
						Point np = new Point(nx,ny);
						q.add(np);
						visited[nx][ny] = true;
						
						if (arr[nx][ny] > 0 && arr[nx][ny] < curSize) {
							fishes.add(np);
						}
					}
				}
			}
			level++;
			if (fishes.size() > 0) {
				break;
			}
		}
		
		
		if (fishes.size() == 0) {
			return -1;
		}
		
		Collections.sort(fishes);

		p.x = fishes.get(0).x;
		p.y = fishes.get(0).y;
		arr[p.x][p.y] = 0;
		
		return level;
	}
		

	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		arr = new int[n][];
		visited = new boolean[n][n];
		
		curSize = 2;
		curExp = 0;
		
		Point baby = null;
	
		for (int i = 0 ; i < n ; i++) {
			arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0 ; j < n ; j++) {
				if (arr[i][j] == 9) {
					baby = new Point(i,j);
					arr[i][j] = 0; // 0으로 안바궈주는거.
				}
			}
		}
		
		time = 0;
		int res = 0;
		while((time = bfs(baby)) > 0) {
			res += time;
			curExp++;
			if (curExp == curSize) {
				curSize ++;
				curExp = 0;
			}
		}
		
		System.out.println(res);
	}

}
