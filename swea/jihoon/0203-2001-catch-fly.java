package hw03_swea2001;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	
	static final int MAX_N = 16;
	static int N, M;
	static int[][] board = new int[MAX_N][MAX_N];
	static int[][] sum = new int[MAX_N][MAX_N];
	
	static int calColSum(int r, int c) {
		int res = 0;
		for (int j = c-M+1; j <= c; j++)
			res += board[r][j];
		
		return res;
	}
	
	static int calRowSum(int r, int c) {
		int res = 0;
		for (int k = 0; k < M; k++)
			res += sum[r-k][c];

		return res;
	}
	
	
	static int solution() {
		for (int i = 0; i < N; i++) {
			for (int j = M-1; j < N; j++) {
				if (sum[i][j] != 0) {
					sum[i][j] = sum[i][j-1] + board[i][j] - board[i][j-M];
					continue;
				}
				sum[i][j] = calColSum(i, j);
			}
		}
		
		int res = 0;
		for (int i = M-1; i < N; i++)
			for (int j = M-1; j < N; j++)
				res = Math.max(res, calRowSum(i, j));
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("in.txt"));
		Scanner scanner = new Scanner(System.in);
		
		int testcase = scanner.nextInt();
	    for (int tc = 1; tc <= testcase; tc++) {
	    	for (int i = 0; i < N; i++) {
	    		Arrays.fill(sum[i], 0);
	    	}
	    	
	    	N = scanner.nextInt(); M = scanner.nextInt();
	    	for (int i = 0; i < N; i++) {
	    		for (int j = 0; j < N; j++) {
	    			board[i][j] = scanner.nextInt();
	    		}
	    	}
	    	
	    	System.out.println("#"+ tc + " " + solution());
	    }
	}
}
