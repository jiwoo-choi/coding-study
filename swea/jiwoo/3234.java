import java.io.*;
import java.util.*;

public class Solution {

	static int arr[];
	static int n;
	static int capacity;
	
	public static void swap(int idx, int idx2) {
		int temp = arr[idx];
		arr[idx] = arr[idx2];
		arr[idx2] = temp;
	}
	
	public static boolean next_permutation() {
		// 1. 거꾸로 가면서 가장 큰 거 찾는다.
		int i = n - 1;
		while(i > 0 && arr[i-1] >= arr[i]) i--;
		if ( i <= 0) return false;
		
		int j = n-1;
		while( arr[j] <= arr[i-1] ) j--;
		
		swap( i-1, j);
		
		int k = n -1;
		while( i <  k) {
			swap(i, k);
			i++;
			k--;
		}
		return true;
	}
	
	// 비트 1 : 왼쪽에 담은 경우.
	// 비트 0 : 오른쪽에 담은 경우.
	static int cnt;
	public static void set(int idx, int leftSum, int rightSum) {
		if (idx == n) {
			
			System.out.println(leftSum + " " + rightSum);
            if (leftSum >= rightSum) {
				cnt++;
            }
			return;
		}		
				
		set(idx+1, leftSum + arr[idx], rightSum);
		if (leftSum >= rightSum + arr[idx])
	        set(idx+1, leftSum, rightSum + arr[idx]);

		return;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		for (int test = 1 ; test <= t; test++) {
			cnt=0;

			n = Integer.parseInt(bf.readLine());
			int[] arrs = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			arr = arrs;
			Arrays.sort(arr);		
			do {
				set(0, 0, 0);
			} while (next_permutation());
			
			System.out.println("#" + test + " " + cnt);

		}
	}

}
