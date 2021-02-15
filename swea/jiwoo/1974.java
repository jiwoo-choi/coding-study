import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Solution {

	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		
		for (int test_case = 1; test_case <= t; test_case++) {
			
			int arr[][] = new int[9][];
			for (int i = 0 ; i < 9 ; i++) {
				arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			boolean answer = false;
			
			FAKELOOP:
			while(true) {
				
				// 열 점검.
				for (int i = 0 ; i < 9 ; i++) {
					int flag = 0;
					for (int j = 0 ; j < 9 ; j++) {
						 flag |= 1 << (arr[i][j] -1);
					}
					
					int test = 0;
					for (int j = 0 ; j < 9 ; j++) {
						test |= (1 << j);
					}

					
					if ((test & flag) != test) break FAKELOOP;
				}
				
				
				// 행 점검.
				for (int i = 0 ; i < 9 ; i++) {
					int flag = 0;
					for (int j = 0 ; j < 9 ; j++) {
						 flag |= 1 << (arr[j][i] - 1);
					}
					int test = 0;
					for (int j = 0 ; j < 9 ; j++) {
						test |= (1 << j);
					}
					
					if ((test & flag) != test) break FAKELOOP;
				}
				
				// 3*3 점검.
				for (int i = 0 ; i < 3 ; i++) {
					for (int j = 0 ; j < 3 ; j++) {
						int flag = 0;
						for (int k = (i*3) ; k < (i*3)+3 ; k++) {
							for (int l = (j*3) ; l < (j*3)+3 ; l++) {
								flag |= 1 << (arr[k][l] - 1);
							}
						}
						int test = 0;
						for (int u = 0 ; u < 9 ; u++) {
							test |= (1 << u);
						}
						if ((test & flag) != test) break FAKELOOP;
					}
				}
				
				answer = true;
				break;
			}
			System.out.println("#" + test_case + " " + (answer ? "1" : "0"));
		}
	}

}
