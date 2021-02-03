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
    static int arr[][];
    static int sum[][];
 
    public static int getSum(int x1, int y1, int x2, int y2) {
        return sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
    }
 
 
 
    public static void main(String args[]) throws Exception
    {
        BufferedReader bf = new BufferedReader(new InputStreamReader((System.in)));
        int t = Integer.parseInt(bf.readLine());
 
 
        for(int test_case = 1; test_case <= t; test_case++){
 
            String[] ss = bf.readLine().split(" ");
 
            int n = Integer.parseInt(ss[0]);
            int m = Integer.parseInt(ss[1]);
 
            arr = new int[n+1][n+1];
            sum = new int[n+1][n+1];
 
            for (int i = 1 ; i <= n ; i ++) {
                String[] s = bf.readLine().split(" ");
                for (int j = 1 ; j <= n; j++) {
                    arr[i][j] = Integer.parseInt(s[j-1]);
                }
            }
 
            for (int i = 1 ; i <= n ; i ++) {
                for (int j = 1 ; j <= n; j++) {
                    sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + arr[i][j];
                }
            }
 
            int result = Integer.MIN_VALUE;
            for (int i = 1 ; i <= n-m+1; i ++) {
                for (int j = 1 ; j <= n-m+1; j++) {
                    result = Math.max(result, getSum(i,j,i+m-1, j+m-1));
                }
            }
            System.out.println("#" + test_case + " " +  result);
        }
    }
}
