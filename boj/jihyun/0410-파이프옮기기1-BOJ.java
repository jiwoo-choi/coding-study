package boj.pipe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] dirs = {{0,-1}, {-1,0},{-1,-1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int [][] grid = new int[N][N];
		int [][][] dp = new int[N][N][3]; 
		int newr, newc;
		StringTokenizer st;
		
		for( int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine()," ");
			for( int j = 0 ; j < N ; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//처리 - 나한테 오는거 생각하좌왕 
		// 0 - 가로, 1 - 세로, 2 - 대각 
		dp[0][1][0] = 1; //가로 
		
		for( int i = 0 ; i < N ; i++) {	
			for( int j = 1 ; j < N ; j++) {
				for(int k = 0 ; k < 3 ; k++) {
					newr = i + dirs[k][0];
					newc = j + dirs[k][1];
					if(newr < 0 || newc < 0 || grid[i][j] == 1 || (i == 0 && j == 1 && k == 0)) continue;
					// 가로로 놓을 떄 
					if(k == 0 ) dp[i][j][0] = dp[newr][newc][0] + dp[newr][newc][2]; 
					// 세로로 놓을 때
					else if(k == 1 ) dp[i][j][1] = dp[newr][newc][1] + dp[newr][newc][2]; 
					// 대각선일 때 다올 수 있지만 + 위, 앞 그자리 다 체크
					else if(grid[i-1][j] == 0 && grid[i][j-1]== 0) dp[i][j][2] = dp[newr][newc][0] + dp[newr][newc][1] + dp[newr][newc][2]; 
				}
			}
		}
		System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);

	}

}
