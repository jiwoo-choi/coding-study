package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ValidityCheck_1233_error {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int testcase = 1; testcase <= 10 ; testcase++) {
			int N = Integer.parseInt(br.readLine());
			
			int check = 1;
			int i=0;
			
			for (i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				st.nextToken();
				String s = st.nextToken();
				
				// 연산자
				if (!Character.isDigit(s.charAt(0))) {
					if (!st.hasMoreTokens()) {
						check = 0;
						break;
					}
					st.nextToken();
					if (!st.hasMoreTokens()) {
						check = 0;
						break;
					}
				}
				// 숫자
				else {
					if (st.hasMoreTokens()) {
						check = 0;
						break;
					}
				}
			}
			i++;
			for (; i <= N; i++) {
				br.readLine();
			}
			sb.append("#").append(testcase).append(" ").append(check).append("\n");
		}//TC
		System.out.println(sb);
	}//Main
}//Class
