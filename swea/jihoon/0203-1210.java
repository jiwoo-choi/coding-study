package swea1210;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	
	static int sr, sc;
	// ^, >, v, <
	static int[] dr = new int[]{-1, 0, 1,  0};
	static int[] dc = new int[]{ 0, 1, 0, -1};
	
	static final int MAX_N = 105;
	static int N;
	static int[][] board = new int[MAX_N][MAX_N];
	
	static int solution() {
		int dir = 0;
		int r = sr;
		int c = sc;
		while(true) {
			switch(dir) {
				case 0:
					if (r == 0)
						return c;

					for (int i = 1; i < 4; i++) {
						if (i == 2) continue;

						int nr = r + dr[i];
						int nc = c + dc[i];
						if (nr < 0 || nr >= N || nc < 0 || nc >= N)
							continue;
						if (board[nr][nc] == 1) {
							dir = i;
							break;
						}
					}
					break;
				case 1: 
				case 3: 
					int nr = r + dr[dir];
					int nc = c + dc[dir];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 0) {
						dir = 0;
					}
					break;
			}
			r += dr[dir];
			c += dc[dir];
		}
	}

	public static void main(String[] args) throws Exception {
	   System.setIn(new FileInputStream("in.txt"));
	    Scanner scanner = new Scanner(System.in);
	    
	    int testcase = 10;
	    for (int tc = 1; tc <= testcase; tc++) {
	        scanner.nextInt();
	        
	        N = 100;
	        for (int i = 0 ; i < N; i++)
	        	for (int j = 0; j < N; j++)
	        		board[i][j] = scanner.nextInt();

        	for (int j = 0; j < N; j++) {
	    		if (board[N-1][j] == 2) {
	    			sr = N-1; 
	    			sc = j;
	    		}
	    	}
	        
	        System.out.println("#"+ tc + " " + solution());
	    }
	    scanner.close();
	}
}
