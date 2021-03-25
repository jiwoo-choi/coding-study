import java.util.*;
import java.io.*;

public class Solution {
	
	static double getDistance(Pair p1, Pair p2, double e) {
		double xdist = Math.abs(p1.x - p2.x);
		double ydist = Math.abs(p1.y - p2.y);
		
	    return Math.pow(Math.sqrt(ydist * ydist + xdist * xdist),2) * e;
	}
	
	static class Pair {
		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		
		int from;
		int to;
		double weight;
		
		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
	
	static int parents[];
	static int V;
	static int E;
	static int targetE;

	public static void make() {
		parents = new int[V];
		for (int i = 0 ; i < V ; i++) {
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
	
	
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int test_case = 1 ; test_case <= t; test_case ++) {
			
			List<Pair> list = new ArrayList<Pair>();

			int edges = sc.nextInt();
			for (int i = 0 ; i < edges; i++) {
				int x = sc.nextInt();
				list.add(new Pair(x,0));
			}

			for (int i = 0 ; i< edges; i++) {
				int y = sc.nextInt();
				list.get(i).y = y;
			}
			
			//pair별로 edge를 구하자.
			double e = sc.nextDouble();
			PriorityQueue<Edge> pq = new PriorityQueue<>();

			for (int i = 0 ; i < edges; i++) {
				for (int j = i+1; j < edges; j++) {
					double weight = getDistance(list.get(i), list.get(j), e);
					pq.add(new Edge(i, j, weight));
				}
			}
			
			
			V = list.size();
			E = pq.size();
			targetE = V -1;
			
			make();

			double result = 0;
			while(targetE >= 0 && !pq.isEmpty()) {
				Edge edge = pq.poll();
				if (union(edge.from, edge.to) == true) {
					result += edge.weight;
					targetE--;
				} 
			}
			System.out.println("#" + test_case + " " + Math.round(result));
		}
	}

}
