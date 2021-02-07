package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrintStars_2444 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		for (int i = 1; i <= N; i++) {
			for (int j = N-i-1; j >= 0 ; j--) {
				System.out.print(" ");
			}
			int k = 2*i-1;
			for (int j = 1; j <= k; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		int l = N-1;
		for (int i = 1; i <= l; i++) {
			for (int j = 1; j <= i ; j++) {
				System.out.print(" ");
			}
			int k = 2*(N-i)-1;
			for (int j = 1; j <= k; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}//Main
}//Class
