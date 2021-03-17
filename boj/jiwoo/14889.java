package class_p;
import java.io.*;
import java.util.*;

public class boj_14899 {

	public static void swap(int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public static boolean next_permutation() {
		int i = N - 1;
		while( i > 0 && arr[i-1] >= arr[i]) i--;
		if ( i <= 0 ) return false;
		int j = N - 1;
		while ( arr[i-1] >= arr[j] ) j--;
		swap(i-1, j);
		
		int k = N - 1;
		while ( i < k ) {
			swap(i, k);
			i++;
			k--;
		}
		return true;
	}
	
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		for (int i = 0 ; i < N/2 ; i++) {
			arr[i] = 1;
		}
		Arrays.sort(arr);
		
		int val[][] = new int[N][];
		for (int i = 0; i < N ; i++) {
			val[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		int answer = Integer.MAX_VALUE;
		
		do {
			int result1 = 0;
			int result2 = 0;

//			System.out.println(Arrays.toString(arr));
			for (int i = 0 ; i < N ; i++) {
				for (int j = i ; j < N; j++) {
					if (i == j) continue;
					
					if (arr[i] == 1 && arr[j] == 1) {
						result1 += val[i][j] + val[j][i];
					}
					
					if (arr[i] == 0 && arr[j] == 0) {
						result2 += val[i][j] + val[j][i];
					}
				}
			}
			answer = Math.min(answer, Math.abs(result2 - result1));
		} while(next_permutation());
		
		System.out.println(answer);
	}

}
