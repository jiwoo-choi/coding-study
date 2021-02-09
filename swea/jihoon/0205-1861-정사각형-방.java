package swea1861정사각형_방;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static final int MAX_N = 1010;
	static int N;
	static int[][] board = new int[MAX_N][MAX_N];
	static int[][] cnt = new int[MAX_N][MAX_N];
	
	static int[] dr = {-1 , 0, 1, 0};
	static int[] dc = { 0 ,-1, 0, 1};
	
	static int resNum, resCnt;
	
	static int dfs(int r, int c) {
		if (cnt[r][c] != -1) // 이미 방문한 곳이면 이전에 계산해놓은 값 리턴
			return cnt[r][c];
		
		cnt[r][c] = 1; // 자기 자신
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;
			if (board[nr][nc] != board[r][c] + 1)
				continue;
			
			cnt[r][c] = dfs(nr, nc) + 1;
			break;
		}
		return cnt[r][c];
	}
	
	static void calMaxCnt() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int curCnt = cnt[i][j];
				if (resCnt < curCnt) {
					resCnt = curCnt;
					resNum = board[i][j];
				}
				else if (resCnt == curCnt) {
					resNum = Math.min(resNum, board[i][j]);
				}
			}
		}
	}
	
	static void solution() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (cnt[i][j] == -1) // 방문 여부 확인
					dfs(i, j);

		calMaxCnt();
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("in.txt"));
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int testcase = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= testcase; tc++) {
			StringBuilder sb = new StringBuilder();
			
			// init
			resNum = 1;
			resCnt = 1;
			for (int i = 0; i < MAX_N; i++)
				Arrays.fill(cnt[i], -1);
			
			N = Integer.parseInt(in.readLine());
			for (int i = 0 ; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// solution
			solution();
			
			// print result
			System.out.println("#" + tc + " " + resNum + " " + resCnt);
		}
	}
}
