import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N,M;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		int[] testcases = new int[T+1];
		
		for(int cnt = 1 ; cnt <= T ; cnt++) {
			StringTokenizer st = new StringTokenizer(in.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			//파리 채우기 
			int[][] flies = new int[N][];
			for(int i = 0 ; i < N ; i++) {
				flies[i] = new int[N];
				st = new StringTokenizer(in.readLine()," ");
				for(int j = 0; j < N ;j++) {
					flies[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			testcases[cnt]= killMaxFlies(flies);
		}
		for(int i = 1; i <= T ; i++)
			System.out.println("#" + i + " " + testcases[i]);
		
	}
	private static int killMaxFlies(int[][] flies) {
		int max = 0;
		for(int i = 0; i + M <= N ; i++) {
			for(int j = 0; j + M <= N ; j++) {
				int temp = 0;
				for(int dx = 0 ; dx < M; dx++) {
					for(int dy = 0 ; dy < M; dy++) {
						temp += flies[i+dx][j+dy];
					}
				}
				max = (max < temp)? temp:max;
			}
		}
		return max;
	}

}
