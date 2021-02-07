package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SquareRoom {

	static int sNum;
	static int maxCnt;
	static int[][] arr;
	static int N;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int TestCase = 1; TestCase <= T; TestCase++) {
			
			sNum=Integer.MAX_VALUE;
			maxCnt=0;
		
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					count(y,x,1,arr[y][x]);
				}
			}
			
			sb.append("#").append(TestCase).append(" ").append(sNum).append(" ").append(maxCnt).append('\n');
		}//TC
		
		System.out.print(sb);
		
	}//Main
	
	static void count(int y, int x, int cnt, int sPos) {
		
		boolean flag = false;
		
		for(int i=0;i<4;i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			// 경계를 안넘고, +1 차이나면 이동할거야
			if(ny>=0 && ny<N && nx>=0 && nx<N && arr[ny][nx]==arr[y][x]+1) {
				flag = true;
				count(ny,nx,cnt+1,sPos);
			}
		}
		
		if(flag == false) {
			if(maxCnt==cnt) {
				if(sNum > sPos) {
					sNum = sPos;
				}
			}
			if(maxCnt<cnt) {
				sNum = sPos;
				maxCnt = cnt;
			}
		}
	}
}//Class
