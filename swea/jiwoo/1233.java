import java.util.Scanner;
import java.io.*;

public class Solution {

	static char[] arr;
	static int n;
	public static boolean go(int x) {
		// 만약 범위 이상이라면 
		if (x > n) return false;
		if (Character.isDigit(arr[x])) {
			// 만약 숫자라면 둘다 leaf노드여야할것.
			return 2*x > n;
		} else {
			// 만약 연산자라면 둘다 valid해야할것.
			return go(2*x) && go(2*x+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-gㅇenerated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		for(int test_case = 1; test_case <= 10; test_case++) {
			n = Integer.parseInt(bf.readLine());
			arr = new char[n+1];
			for(int i = 1 ; i <= n ;i++) {
				String[] temp = bf.readLine().split(" ");
				char val = temp[1].charAt(0);
				arr[i] = val;
			}
			System.out.println("#" + test_case + " " + (go(1) ? 1 : 0));
		}
		
	}

}
