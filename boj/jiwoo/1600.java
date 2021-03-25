import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.*;

public class Main {
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0};
	static int dkx[] = {-1, -2, -2, -1, 1, 2, 1, 2};
	static int dky[] = {-2, -1, 1, 2, -2, -1, 2, 1};

	static class Pair {
		int x;
		int y;
		int power;
		public Pair(int x, int y, int power) {
			super();
			this.x = x;
			this.y = y;
			this.power = power;
		}
		
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(bf.readLine());
		int[] inputs = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int m = inputs[0];
		int n = inputs[1];
		int[][] arr = new int[n][];
		int[][][] visited = new int[n][m][k+1];

		for (int i = 0 ; i < n ; i++) {
			arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		Queue<Pair> q = new ArrayDeque<Pair>();
		
		q.add(new Pair(0, 0, 0));
		int fx = n-1;
		int fy = m-1;
		visited[0][0][0] = 1;
		
		while(!q.isEmpty()) {
			
			Pair pair = q.poll();
			int x = pair.x;
			int y = pair.y;
			int p = pair.power;
			int np = Math.min(k, p+1);
			
			if (p < k) {
				
				for (int i = 0 ; i < 8 ; i++) {
					int nx = x + dkx[i];
					int ny = y + dky[i];
					if (nx < 0 || nx >= n || ny < 0 || ny >= m ) continue;
					if (arr[nx][ny] == 0 && visited[nx][ny][np] == 0)  {
						visited[nx][ny][np] = visited[x][y][p] + 1;
						q.add(new Pair(nx,ny,np));
					}
				}
			}
			
			for (int i = 0 ; i < 4 ; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m ) continue;
				if (arr[nx][ny] == 0 && visited[nx][ny][p] == 0)  {
					visited[nx][ny][p] = visited[x][y][p] + 1;
					q.add(new Pair(nx,ny,p));
				}
			}
			
		}
		
		int ans = Integer.MAX_VALUE;
		for (int i = 0 ; i <= k ; i++) {
			if (visited[fx][fy][i] != 0) {
				ans = Math.min(ans, visited[fx][fy][i] - 1);
			} 
		}
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

}
