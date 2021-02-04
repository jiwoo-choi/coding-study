package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KillFly {

	public static int[][] flies;
	static int N;
	static int M;
	static int maxSum;
	
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TestCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TestCase; t++) {
			
			maxSum=0; 
			
			st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			flies = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < N; j++) {
					flies[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
	//		for (int[] i : flies) {
	//			System.out.println(Arrays.toString(i));
	//		} // flies 배열 출력 확인
			
			for (int y = 0; y < N-M+1; y++) {
				for (int x = 0; x < N-M+1; x++) {
					if(sum(M,y,x) > maxSum) {
						maxSum = sum(M, y, x);
					}
				}
			}
			sb.append("#").append(t).append(" ").append(maxSum);
			System.out.println(sb);
			sb.setLength(0);
		
		}//end of TestCase
	}//end of Main

	
	public static int sum(int M, int y, int x) {
		int sum=0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				sum += flies[y+i][x+j];
			}
		}
		return sum;
	}

}
