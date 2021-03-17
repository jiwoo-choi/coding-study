import java.io.*;
import java.util.*;

public class boj_2999 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int n = s.length();
		
		int r = 0;
		int c = 0;
		
		for (int i = 1 ; i <= n ; i++) {
			
			if (n / i < i) {
				break;
			}
			
			if (n % i == 0) {
				r = i;
				c = n / i;
			}
			
		}
		
		char arr[][] = new char[r][c];
		
		int idx = 0;
		for (int i = 0 ; i < c; i++) {
			for (int j = 0; j < r ; j++) {
				arr[j][i] = s.charAt(idx++);
			}
		}
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0 ; i < r; i++) {
			for (int j = 0; j < c ; j++) {
				sb.append(arr[i][j]);
			}
		}
		System.out.println(sb.toString());
	}
}
