package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Harvesting {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		/**테스트 케이스 횟수 입력 받기*/
		int T = Integer.parseInt(br.readLine());
		
		// StringTokenizer 쓰면 안됨! 숫자가 다 붙어서 오니까
		

		for(int testCase=1;testCase<=T;testCase++) {
			
			/**농장 가로(세로)크기 N입력 받기*/
			int N = Integer.parseInt(br.readLine());
			
			
			/**농장 2차원 배열에 넣어주기*/
			int[][] farm = new int[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					farm[i][j] = str.charAt(j)-'0';
				}
			}
			
			int point = N/2;
			int sumValue = 0; // 농작물 가치 합
			
			for (int i = 0; i < N; i++) {
				if(i<=point) {
					sumValue += farm[i][point];
					int move = 1;
					for (int j = 0; j < i; j++) {
						sumValue += farm[i][point-move];
						sumValue += farm[i][point+move];
						move++;
					}
				}
				else {
					sumValue += farm[i][point];
					int move = 1;
					for (int j = N-i-1; j > 0; j--) {
						sumValue += farm[i][point-move];
						sumValue += farm[i][point+move];
						move++;
					}
				}
				
			}// 행 고정해서 N까지 아래로 내려가는 형태
			
			/**각 테스트 케이스 마다의 결과값 출력*/
			sb.append("#").append(testCase).append(" ").append(sumValue);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
