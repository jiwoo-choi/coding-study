
import java.io.*;
import java.util.*;

public class swea_4021 {

	
	static int n;
	static int arr[];
	static int s[][];
	public static void swap(int idx, int idx2) {
		int temp = arr[idx];
		arr[idx] = arr[idx2];
		arr[idx2] = temp;
		
	}
	
	public static boolean next_permutation() {
		int i = n-1;
		while( i > 0 && arr[i-1] >= arr[i]) i--;
		if (i <= 0) return false;
		int j = n-1;
		while (arr[i-1] >= arr[j]) j--;
		swap(i-1, j);
		
		int k = n-1;
		while( i < k) {
			swap(i, k);
			i++;
			k--;
		}
		return true;
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		
		for (int test_case = 1 ; test_case <= t; test_case++) {
			n = Integer.parseInt(bf.readLine());
			arr = new int[n];
			for (int i = 0 ; i < n/2 ; i++) {
				arr[i] = 1;
			}
			
			Arrays.sort(arr);
			
			s = new int[n][];
			int ans = Integer.MAX_VALUE;
			for (int i = 0 ; i < n ; i++) {
				s[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			int a = 0;
			int b = 0;
			
			do {
				a = 0;
				b = 0;
				for (int i = 0 ; i < n ; i++) {
					for (int j = i+1 ; j < n ; j++) {
						if (arr[i] != arr[j]) continue;
						if (arr[i] == 0) {
							a += s[i][j];
							a += s[j][i];
						} else {
							b += s[i][j];
							b += s[j][i];
						}
					}
				}
				ans = Math.min(Math.abs(a - b), ans);
				
			} while(next_permutation());
			System.out.println("#" + test_case + " " + ans);
		}

	}
	
	
}
