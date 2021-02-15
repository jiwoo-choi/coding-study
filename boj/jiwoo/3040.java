import java.io.*;
import java.util.*;

class Main {
	
    static int arr[] = {0, 0, 1, 1, 1, 1, 1, 1, 1};
    static int a[] = {7,8,10,13,15,19,20,23,25};
    static int n = 9;
    
    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
    public static boolean next_permutation() {
        int i = n-1;
        while( i > 0 && arr[i-1] >= arr[i]) i = i - 1;
        if ( i <= 0 ) return false;
        int j = n-1;
        while(arr[j] <=  arr[i-1] ) j = j - 1;
        swap(arr, i-1, j);
        int k = n-1;
        while ( i < k ) {
            swap(arr, i , k);
            i = i + 1;
            k = k - 1;
        }
        return true;
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	for (int i = 0 ; i < 9 ; i++) {
    		a[i] = Integer.parseInt(bf.readLine());
    	}
    	
    	do {
    		int result = 0;
    		for (int i = 0 ; i < n ; i++) {
    			if (arr[i] == 1) {
    				result += a[i];
    			}
    		}
    		if (result == 100) break;
    	} while(next_permutation());
        
    	for (int i = 0 ; i < 9 ; i++) {
    		if (arr[i] == 1) System.out.println(a[i]);
    	}
    }
}
