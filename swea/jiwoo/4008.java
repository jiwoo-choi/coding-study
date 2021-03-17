
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	
	static int arr[];
	static int n;
	
	public static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static boolean next_permutation() {
	
		int i = n - 1;
		while( i > 0 && arr[i-1] >= arr[i]) i--;
		if ( i <= 0) return false;
		int j = n - 1;
		while(arr[j] <= arr[i-1]) j--;
		swap(i-1, j);
		
		int k = n - 1;
		while ( i < k) {
			swap(i, k);
			i++;
			k--;
		}
		return true;
	}
	
	public static int eval(int[] numbers, int[] ops) {
		int current = numbers[0];
		
		for (int i = 1; i < numbers.length; i++) {
			if (ops[i-1] == 1) { // +
				current = current + numbers[i];
			} else if (ops[i-1] == 2){ // -
				current = current - numbers[i];
			} else if (ops[i-1] == 3) { // *
				current = current * numbers[i];
			} else { // /
				current = current / numbers[i];
			}
		}
		return current;
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());
		
		for (int test_case=1 ; test_case <= t; test_case++) {
			
			int t1= Integer.parseInt(bf.readLine());
			int[] ops = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] numbers = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int total = Arrays.stream(ops).sum();
			arr = new int[total];
			n = total;
			int idx = 0;
			for (int i = 0 ; i < total; i++) {
				if (ops[idx] <= 0) {
					idx++;
					i--;
					continue;
				}
				arr[i] = idx+1;
				ops[idx]--;
			}
			
			Arrays.sort(arr);
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;


			do {
				int eval = eval(numbers,arr);
				max = Math.max(max, eval);
				min = Math.min(min, eval);
//				System.out.println(eval);
//				System.out.println(Arrays.toString(arr));
			} while (next_permutation());
			
			System.out.println("#" + test_case + " " + (max - min));
		}
	}

}
