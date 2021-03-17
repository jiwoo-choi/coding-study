import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] input;
	
	static int solution() {
		Arrays.sort(input);
		int maxDiff = 0;
		
		int cur1 = input[N-1];
		int cur2 = input[N-1];
		for (int i = N-2; i >= 0; i--) {
			int next1 = input[i];
			if (i == 0) {
				maxDiff = Math.max(maxDiff, Math.max(cur1 - next1, cur2 - next1));
				break;
			}
			
			int next2 = input[i-1];
			
			int diff11 = cur1 - next1;
			int diff22 = cur2 - next2;
			int aa = Math.max(diff11, diff22);
			
			int diff12 = cur1 - next2;
			int diff21 = cur2 - next1;
			
			int bb = Math.max(diff12, diff21);
			if (aa < bb) {
				cur1 = next1;
				cur2 = next2;
				maxDiff = Math.max(maxDiff, aa);
			} else if (aa > bb) {
				cur1 = next2;
				cur2 = next1;
				maxDiff = Math.max(maxDiff, bb);
			} else {
				if (Math.min(diff11, diff22) > Math.min(diff21, diff21)) {
					cur1 = next1;
					cur2 = next2;
					maxDiff = Math.max(maxDiff, aa);
				} else {
					cur1 = next2;
					cur2 = next1;
					maxDiff = Math.max(maxDiff, bb);
				}
			}
			i--;
		}
		maxDiff = Math.max(maxDiff, Math.abs(cur2 - cur1));
		return maxDiff;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int testcase = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < testcase; tc++) {
			N = Integer.parseInt(in.readLine());
			input = new int[N];
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			
//			solution();
			System.out.println(solution());
		}
	}


}
