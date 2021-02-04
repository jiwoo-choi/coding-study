package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Flatten {
	
	static int dumpNum;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		for(int T=1;T<=10;T++) {
			/** 최대 덤프 횟수 입력 받기*/
			dumpNum = Integer.parseInt(br.readLine());
			
			/** 상자 정보를 받아와서 배열 초기화 해주기*/
			st = new StringTokenizer(br.readLine());

			int[] boxInfo = new int[100];
			int k=0;
			while(st.hasMoreTokens()) {
				boxInfo[k] = Integer.parseInt(st.nextToken());
				k++;
			}		
			
			while(dumpNum>0) {
				Arrays.sort(boxInfo);
				if(boxInfo[0]>=boxInfo[99]) {
					break;
				}
				--boxInfo[99];
				++boxInfo[0];
				--dumpNum;
			}
			
			Arrays.sort(boxInfo);
//			for(int i : boxInfo) {
//				System.out.print(i+" ");
//			} // 확인용
			
			System.out.println("#"+T+" "+(boxInfo[99]-boxInfo[0]));
		}

	
	}//end of Main

}
