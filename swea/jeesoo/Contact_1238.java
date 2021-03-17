package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Contact_1238 {

	public static int[][] tel;
	public static int max;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int testcase = 1; testcase <= 10; testcase++) {
			
			tel = new int[101][101];
			
			st = new StringTokenizer(br.readLine());
			
			int cnt=Integer.parseInt(st.nextToken());
			int startNum=Integer.parseInt(st.nextToken());
			
			int pair = cnt/2;
			st = new StringTokenizer(br.readLine());
			
			int x=0;
			int y=0;
			
			for (int i = 0; i < pair; i++) {
				x=Integer.parseInt(st.nextToken());
				y=Integer.parseInt(st.nextToken());
				
				tel[x][y]=1;
			}
			
			bfs(startNum);
			sb.append("#").append(testcase).append(" ").append(max).append("\n");
			
		}//tc
			
		System.out.println(sb);
		
	}//Main

	public static void bfs(int startNum) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[101];
		
		queue.offer(startNum); 
		visited[startNum]=true;
		
		while(!queue.isEmpty()) {
			max=0; // 동일 너비의 애들 중 가장 큰 번호
			int size=queue.size(); // 같은 레벨(너비)인 애들의 개수
			
			while (size > 0) {
				int cur = queue.poll();
				visited[cur]=true;
				max = Math.max(max, cur); // 각 레벨에서의 최댓값 갱신, 동시에 연락받은 사람 중 가장 큰 번호
				
				for (int i = 0; i < 101; i++) {
					if (tel[cur][i] == 1 && !visited[i]) {
						queue.offer(i);
						visited[i]=true;
					}
				}
				size--;
			}//같은 레벨 작업
			
		}
	}//bfs

}//Class
