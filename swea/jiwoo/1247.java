import java.io.*;
import java.util.*;

public class swea_1247 {

	
	static int arr[];
	static int n;
	
	static void swap(int idx, int idx2) {
		int temp = arr[idx];
		arr[idx] = arr[idx2];
		arr[idx2] = temp;
	}
	static boolean next_permutation() {
		int i = n - 1;
		while ( i > 0 && arr[i-1] >= arr[i]) i--;
		if ( i <= 0 ) return false;
 		int j = n - 1;
		while ( arr[j] <= arr[i-1]) j--;
		swap(i-1, j);
		int k = n-1;
		while( i < k ) {
			swap(i, k);
			i++;
			k--;
		}
		return true;
	}
	
	static class Pair {
		int x;
		int y;
		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public String toString() {
			return x + " " + y;
		}
	}
	
	public static int distance(Pair p1, Pair p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		
		for (int test_case = 1 ; test_case <= t ; test_case++) {
			
			n = Integer.parseInt(bf.readLine());
			
			int[] inputs = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			Pair dest = new Pair(inputs[0], inputs[1]);
			Pair home = new Pair(inputs[2], inputs[3]);
			Pair[] pairs = new Pair[n];
			for (int i = 0 ; i < n*2; i += 2) {
				pairs[i/2] = new Pair(inputs[i+4], inputs[i+5]);
			}
			
			arr = new int[n];
			for (int i = 0; i < n ; i++) {
				arr[i] = i;
			}
			int answer = Integer.MAX_VALUE;
			
			do {
				int result = distance(dest, pairs[arr[0]]);
				for (int i = 0 ; i < n-1 ;i++) {
					result += distance(pairs[arr[i]],pairs[arr[i+1]]);
				}
				result += distance(home, pairs[arr[n-1]]);
				answer = Math.min(answer, result);
			} while(next_permutation());
			
			System.out.println("#" + test_case + " " + answer);
		}
		
	}

}
