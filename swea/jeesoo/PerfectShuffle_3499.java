package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PerfectShuffle_3499 {

	static String[] str1;
	static String[] str2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int TestCase = 1; TestCase <= T; TestCase++) {
			int N = Integer.parseInt(br.readLine());
			int point;
			st = new StringTokenizer(br.readLine()," ");
			if(N%2==0) {
				point = N/2;
				str1 = new String[point];
				str2 = new String[point];
			}
			else {
				point = N/2+1;
				str1 = new String[point];
				str2 = new String[point];
				str2[point-1]=" ";
			}
			
			
//			for (int i = 0; i < point; i++) {
//				if(i<point) {
//					str1[i]=st.nextToken();
//				}
//				else {
//					str2[i]=st.nextToken();
//				}
//			}
			
			for (int i = 0; i < point; i++) {
				str1[i]=st.nextToken();
			}
			if(N%2!=0) {
				for (int i = 0; i < point-1; i++) {
					str2[i]=st.nextToken();
				}
			}
			else {
				for (int i = 0; i < point; i++) {
					str2[i]=st.nextToken();
				}
			}
			
			/**하나씩 넣어주기*/
			
			sb.append("#").append(TestCase).append(" ");
			for (int i = 0; i < point; i++) {
				sb.append(str1[i]).append(" ").append(str2[i]).append(" ");
			}
			sb.append('\n');
			
		}//TC
		System.out.println(sb);			
	}//Main
}//Class
