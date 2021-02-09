package hw06_swea9229_현빈이와_Spot_Mart;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	static final int MAX_N = 1010;
	static int N, M;
	static int[] input = new int[MAX_N]; 
	
	static int res; 
	static void solution() {
		for (int i = 0; i < N; i++) {
			int a = input[i];
			for (int j = i+1; j < N; j++) {
				int sum = a + input[j];
				if (res < sum && sum <= M) {
					res = sum;
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("in.txt"));
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
		int testcase = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= testcase; tc++) {
			res = -1;
			
			
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}

			solution();
			
			System.out.println("#" + tc + " " + res);
		}
		
	}

}
