import java.util.*;
import java.io.*;

public class Main {

	
	static int V;
	static int E;
	static int targetE;
	
	static int parents[];
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;
		
		public Edge(int[] inputs) {
			this.from = inputs[0];
			this.to = inputs[1];
			this.weight = inputs[2];
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void make() {
		parents = new int[V+1];
		for (int i = 1 ; i <= V ; i++) {
			parents[i] = i;
		}
	}
	
	public static int find(int x) {
		if (parents[x] == x) return x;
		return parents[x] = find(parents[x]);
		
	}

	public static boolean union(int x, int y) {
		int aRoot = find(x);
		int bRoot = find(y);
		if (aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		// 목표치는 E.
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int inputs[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		V = inputs[0];
		E = inputs[1];
		targetE = V -1;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0 ; i < E ; i++) {
			int edges[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			pq.add(new Edge(edges));
		}
		
		make();
		int result = 0;
		while(targetE >= 0 && !pq.isEmpty()) {
			Edge e = pq.poll();
			if (union(e.from, e.to) == true) {
				result += e.weight;
				targetE--;
			} 
		}
		System.out.println(result);
	}

}
