import java.util.*;
import java.util.stream.IntStream;
import java.io.*;

public class Main {

	static class Edge implements Comparable<Edge> {
		int no;
		int cost;
		
		public Edge(int no, int cost) {
			super();
			this.no = no;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
		
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		
		for(int test_case = 1 ; test_case <= t; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			int[] dist = new int[n+1];
			List<Edge>[] graph = (List<Edge>[]) new List[n+1];
			
			for (int i = 1 ; i <= n ; i++) {
				graph[i] = new ArrayList<>();
				dist[i] = Integer.MAX_VALUE;
			}
			
			for (int i = 0 ; i < d; i++ ) {
				st = new StringTokenizer(bf.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				graph[y].add(new Edge(x, z)); 
			}
			
			PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
			dist[c] = 0;
			pq.add(new Edge(c, 0));
			
			
			while(!pq.isEmpty()) {
				Edge here = pq.poll();
				int no = here.no;

				for (int i = 0 ; i < graph[no].size(); i++ ) {
					Edge there = graph[no].get(i);

					if (dist[there.no] > dist[here.no] + there.cost) {
						dist[there.no] = dist[here.no] + there.cost;
						pq.add(new Edge(there.no, dist[there.no]));
					}
				}

			}
			
			System.out.println( Arrays.stream(dist).filter( v -> v != Integer.MAX_VALUE).count() - 1 + " " +   Arrays.stream(dist).filter( v -> v != Integer.MAX_VALUE).max().getAsInt());
			
		}	
	
		
	}


}
