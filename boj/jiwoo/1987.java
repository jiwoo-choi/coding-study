import java.io.*;
import java.util.*;

public class Main {

	static char[][] map;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int n;
	static int m;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int[] inputs = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		n = inputs[0];
		m = inputs[1];
		
		map = new char[n][];
		
		for (int i = 0 ; i < n ; i++) {
			map[i] = bf.readLine().toCharArray();
		}
		
		System.out.println(dfs(0,0,(1<<map[0][0]-'A')));

	}
	
	private static int dfs(int x, int y, int bits) {
		

		int ret = 0;
		
		for (int k = 0 ; k < 4;  k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				
			if ((bits & (1 << map[nx][ny]-'A')) >= 1) continue;
			ret = Math.max(ret,dfs(nx,ny,bits | (1 << map[nx][ny]-'A')));
			
		}
		
		return ret + 1;
	}
	
	
}
