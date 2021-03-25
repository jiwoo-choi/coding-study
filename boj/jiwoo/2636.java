import java.util.*;
import java.io.*;

public class Main {
	
	
	static int graph[][];
	static int visited[][];
	static int N;
	static int M;
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	
	static int outerzero = 0;
	static int outerone = 0;
	
	static void bfs() {
		
		for (int i = 0 ; i < N; i++) {
			for (int j = 0 ; j < M ; j++) {
				visited[i][j] = 0;
			}
		}
		outerzero = 0;
		outerone = 0;
		
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {0, 0});
		visited[0][0] = 1;
		
		while(!q.isEmpty()) {
			int[] pair = q.poll();
			int x = pair[0];
			int y = pair[1];
			outerzero++;
			
			for (int i = 0 ; i < 4 ; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
			
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (visited[nx][ny] == 0 && graph[nx][ny] == 0) {
					q.add(new int[] {nx,ny});
					visited[nx][ny] = 1;
				} 
				
				if (graph[nx][ny] == 1) {
					graph[nx][ny] = 9;
					outerone++;
				}
			}
		}
	}
	
	static boolean next_clear() {
		int ret = 0;
		for (int i = 0 ; i < N; i++) {
			for (int j = 0 ; j < M ; j++) {
				if (graph[i][j] == 9) {
					graph[i][j] = 0;
				}
			}
		}
		
		for (int i = 0 ; i < N; i++) {
			for (int j = 0 ; j < M ; j++) {
				if (graph[i][j] != 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		graph = new int[N][];
		visited = new int[N][M];
		for (int i = 0; i < N ; i++) {
			graph[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		int time = 0;
		do {
			time++;
			bfs();
		} while(next_clear());

		System.out.println(time);
		System.out.println(outerone);
		
		
	}
}
