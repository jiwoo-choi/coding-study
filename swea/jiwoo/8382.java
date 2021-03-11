import java.util.*;
import java.io.*;

public class Solution {


	static final int MAX = 100;
	static final int MIN = -100;
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		
		for (int test_case = 1 ; test_case <= t; test_case++) {
			int[] inputs = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int sx = inputs[0];
			int sy = inputs[1];
			int tx = inputs[2];
			int ty = inputs[3];
			
			int[][][] visited = new int[202][202][2];
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[]{inputs[0] , inputs[1], 0});
			q.add(new int[]{inputs[0] , inputs[1], 1});
			visited[sx+100][sy+100][0] = 1;
			visited[sx+100][sy+100][1] = 1;
			//0 이면 100
			//1이면 101 (100 + 1)
			//100 + 201
			// -1이면 // 99
			// -100  // 0
			
			while(!q.isEmpty()) {
				
				int[] top = q.poll();
				int x = top[0];
				int y = top[1];
				int direction = top[2];
				
				if (x == tx && y == ty) {
					break;			
				}
				
				int start = (direction == 0) ? 0 : 2;
				int end = (direction == 0) ? 2 : 4;
				for(int i = start ; i < end; i ++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (nx < MIN || nx > MAX || ny < MIN || ny > MAX  ) continue;
					if (visited[nx+100][ny+100][1-direction] == 0) {
						visited[nx+100][ny+100][1-direction] = visited[x+100][y+100][direction] + 1;
						q.add(new int[] {nx, ny, 1-direction});
					}
				}
			}
		
			int result = (visited[tx+100][ty+100][0] != 0) ? visited[tx+100][ty+100][0]: Integer.MAX_VALUE;
			int result2 = (visited[tx+100][ty+100][1] != 0) ?  visited[tx+100][ty+100][1]: Integer.MAX_VALUE;
			
			System.out.println("#" + test_case + " " + (Math.min(result,  result2) - 1));

		}


	}

}
