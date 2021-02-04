package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SnailNum {
	static int arr[][];
	static int N;
	static int Count;
	static int totalCount;
	static int dy[] = {0,1,0,-1};
	static int dx[] = {1,0,-1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			totalCount = N*N;
			Count=1;
			
			int y=0;
			int x=0;
			int dir=0; //dy[0], dx[0]
			arr[y][x] = Count;
			Count += 1;
			
			while(totalCount >= Count) {
				int ny = y + dy[dir];
				int nx = x + dx[dir];
				
				if(ny < 0 || ny >=N || nx < 0 || nx >=N) {
					dir++; //방향전환
					if(dir == 4) dir = 0;
					continue;
				}
				
				if(arr[ny][nx] != 0) {
					dir++;
					if(dir == 4) dir = 0;
					continue;
				}
				
				arr[ny][nx] = Count;
				Count += 1;
				y = ny;
				x = nx;
			}
			
			System.out.println("#"+testCase);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
		}
	}
	
}
