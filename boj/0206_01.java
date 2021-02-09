import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int val = sc.nextInt();
        int n = 2 * val - 1;
        char[][] arr = new char[n][n];
        int mid = n / 2;
        int start = mid;
        int end = mid;
        
        for (int i = 0; i < n ; i++) {
            
            for (int j = start ; j <= end; j++) {
                arr[i][j] = '*';
            }
            
            if (i < mid) {
                start--;
                end++;
            } else {
                start++;
                end--;
            }
        }
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0 ; i < n ; i++) {
        	boolean flag = false;
        	for (int j = 0 ; j < n ; j++) {
        		if (arr[i][j] != '*' && flag == false) {
        			sb.append(" ");
        		} else if (arr[i][j] == '*') {
        			flag = true;
        			sb.append("*");
        		}
        	}
        	sb.append("\n");
        }
        
        System.out.println(sb);
    }
    
    
}
