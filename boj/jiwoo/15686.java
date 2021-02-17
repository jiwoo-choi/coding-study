import java.io.*;
import java.util.*;

public class Main {

	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] arr;
	static int[][] visited;
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {1, -1, 0, 0};
	
	
	public static int bfs(Queue<Pair> q) {
		int n = visited.length;
		for (int i = 0 ; i < n; i++) {
			Arrays.fill(visited[i], -1);
		}
		
		for (Pair p : q) {
			visited[p.x][p.y] = 0;
		}
		
		while(!q.isEmpty()) {
			
			Pair p = q.poll();
			int x = p.x;
			int y = p.y;
			
			for (int i = 0 ; i < 4 ; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx >= 0 && ny >= 0 && nx < n && ny < n && visited[nx][ny] == -1) {
					visited[nx][ny] = visited[x][y] + 1;
					q.add(new Pair(nx,ny));
				}
			}
			

		}
		int result = 0;
		for (int i = 0 ; i< n ; i++) {
			for (int j = 0 ; j < n ; j++) {
				if (arr[i][j] == 1 && visited[i][j] > 0) {
					result += visited[i][j];
				}
			}
		}
		return result;
		// 초기 큐가 담겨서 들어온다.
		
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] a = bf.readLine().split(" ");
		int n = Integer.parseInt(a[0]);
		int m = Integer.parseInt(a[1]);
		
		List<Pair> chicks = new ArrayList();
		arr = new int[n][];
		visited = new int[n][n];
		
		for (int i = 0 ; i < n ; i++ ) {
			arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0 ; j < n ; j++) {
				if (arr[i][j] == 2) {
					chicks.add(new Pair(i,j));
				}
			}
		}
		
		// 치킨집이 최대 13개라고 했으므로, 조합으로 구할 수 있다.
		// 치킨집에 대한 조합을 구하고 => BFS로 최소 거리를 구한다.
		// 최소거리의 합을 업데이트한다. 2^13 * 50 * 50 = 1억 한 참 아래.
		
		int numChicks = chicks.size();
		int answer = Integer.MAX_VALUE;
		for (int i = 1 ; i < (1 << numChicks) ; i++ ) {
			Queue<Pair> q = new LinkedList();
			for (int j = 0 ; j < numChicks ; j++) {
				if (( i & (1 << j)) > 0) {
					q.add(chicks.get(j));
				}
			}
			if (q.size() != m) { continue; }
			answer = Integer.min(bfs(q), answer);			
		}
		
		System.out.println(answer);
	}

}
