package com.ssafy.graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/* swea contact */
import java.util.StringTokenizer;


class Solution
{
	static int max = 0;
	public static void main(String args[]) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuffer sb = new StringBuffer();
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(in.readLine()," ");
			int len = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			boolean[][] adjMatrix = new boolean[101][101];
			
			st = new StringTokenizer(in.readLine()," ");
			for(int i = 0; i < len ; i+=2 ) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());		
				adjMatrix[from][to] = true;
			}
			max = 0;
			bfs(start, adjMatrix);
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(max);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	private static void bfs(int start,boolean[][] adjMatrix) {
		
		boolean[] visited = new boolean[101];
		int last = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		
		visited[start] = true;
		q.offer(start);
		
		while(!q.isEmpty()){
			int breadth = q.size();
			int temp = 0;
			while(0 < breadth--) {
				int cur = q.poll();
				for(int i = 1 ; i <= 100; i++) {
					if( adjMatrix[cur][i] && !visited[i]) {
						visited[i] = true;
						q.offer(i);
					}
				}
				temp = (temp < cur )? cur: temp;
			}
			last = temp;
		}
		max = last;
	}
	
}
