import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;
 
class Solution{
    static int dx[] = {-1, 1, 0, 0}; // 우, 죄, 위
    static int dy[] = {0, 0, -1, 1};
    static char tank[] = {'^' , 'v' , '<', '>'};
    static int[][] arr;
 
    public static void main(String args[]) throws Exception
    {
        BufferedReader bf = new BufferedReader(new InputStreamReader((System.in)));
        int t = Integer.parseInt(bf.readLine());
 
 
        for(int test_case = 1; test_case <= t; test_case++){
 
            int n = Integer.parseInt(bf.readLine());
            arr = new int[n][n];
            for (int i = 0 ; i < n ; i ++) {
                String s = bf.readLine();
                for (int j = 0 ; j < n; j++) {
                    arr[i][j] = s.charAt(j) - '0';
                }
            }
 
            int mid = n/2;
            int start  = mid;
            int end = mid;
            int adder = 1;
            int result = 0;
 
            for (int i = 0 ; i < n ; i++) {
 
                for(int j = start; j <= end; j++) {
                    result += arr[i][j];
                }
                if (start == 0) {
                    adder = -1;
                }
                start -= adder;
                end += adder;
            }
            System.out.println("#" + test_case + " " + result);
        }
    }
}
