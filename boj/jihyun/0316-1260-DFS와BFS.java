import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 백준 1260번 - 인접리스트로 풀자! */
public class Main {

	static int N; 
	static ArrayList<Integer>[] adjlist;
	static boolean[] visited;//dfs 방문처리를 위한 배열 
	public static void main(String[] args) throws IOException {
		// 입력을 받는다. 
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		adjlist = new ArrayList[N+1];
		visited = new boolean[N+1];
		for(int i = 0 ; i <= N ; i++) {
			adjlist[i] = new ArrayList<Integer>();
		}
    //간선입력에 대해...
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(in.readLine()," ");
			int ver1 = Integer.parseInt(st.nextToken());
			int ver2 = Integer.parseInt(st.nextToken());
			adjlist[ver1].add(ver2);
			adjlist[ver2].add(ver1);
		}
    //모든 입력이 끝난 후 인접리스트들의 원소들이 작은 값 순서로 탐색될 수 있도록 정렬한다.
		for(int i = 1 ; i <= N ; i++) {
			Collections.sort(adjlist[i]);
		}
		dfs(V);
		System.out.println();
		bfs(V);
	}
	private static void dfs(int V) {
		System.out.print(V + " ");
		visited[V] = true;
		ArrayList<Integer> temp = adjlist[V];
		int size = temp.size(), i = 0;
		
		while( i < size) {
			if(!visited[temp.get(i)]) dfs(temp.get(i));
			i++;
		}
	}
	private static void bfs(int V) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visit = new boolean[N+1]; 
		queue.add(V);
		visit[V] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			System.out.print(cur + " ");
			int size = adjlist[cur].size();
			for(int i = 0 ; i < size ; i++) {
				int node = adjlist[cur].get(i);
				if( !visit[node]) {
					queue.add(node);
					visit[node] = true;
				}
			}
		}
	}
}
