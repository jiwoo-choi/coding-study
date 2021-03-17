import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	static int[] input;
	static int N;
	
	static void solution() {
		int res = 1;
		int upCnt = 1;
		for (int i = 1; i < N; i++) {
			if (input[i-1] <= input[i])
				upCnt++;
			else {
				res = Math.max(res, upCnt);
				upCnt = 1;
			}
		}
		res = Math.max(res, upCnt);
		
		int downCnt = 1;
		for (int i = 1; i < N; i++) {
			if (input[i-1] >= input[i])
				downCnt++;
			else {
				res = Math.max(res, downCnt);
				downCnt = 1;
			}
		}
		res = Math.max(res, downCnt);
		System.out.println(res);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		input = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		solution();
	}

}
