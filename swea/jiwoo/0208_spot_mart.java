import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

class Solution {
    
	static int n;
	static int m;
	static int numbers[];
	static int a[];
	static int answer;
	
	public static void comb(int cnt, int idx) {
		
		if (cnt == 2) {
			
			int result = Arrays.stream(numbers).sum();
			if (result <= m) answer = Math.max(answer, result);
			return;
		} 
		
		for (int i = idx ; i < n ; i++) {
			numbers[cnt] = a[i];
			comb(cnt+1, i+1);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		for (int test_case = 1 ; test_case <= t ; test_case++) {
			String[] inputs = bf.readLine().split(" ");
			n = Integer.parseInt(inputs[0]);
			m = Integer.parseInt(inputs[1]);
			numbers = new int[2];
			a = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			answer = 0;
			comb(0,0);
			System.out.println("#" + test_case + " " + ((answer == 0)? -1 : answer));
		}
	}
}
