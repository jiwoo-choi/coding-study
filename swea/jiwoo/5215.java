import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	
    static int[][] arr;
    static boolean isSelected[];
    
    static int n;
    static int m;
    
    static int answer;
    
    public static void go(int idx, int cal) {
        
        if (idx == n) { 
            if (cal <=  m) {
                int result = 0;
                for (int i = 0 ; i < n ; i++ ) {
                 	if (isSelected[i] == true) {
                        result += arr[i][0];
                    }
                }
                answer = Math.max(answer, result);
            }
            return;
        }
        
        isSelected[idx] = true;
        go(idx+1, cal + arr[idx][1]);
        isSelected[idx] = false;
        go(idx+1, cal);
    }
    
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            n = sc.nextInt();
            m = sc.nextInt();
            arr = new int[n][2];
            isSelected = new boolean[n];
            answer = 0;
            for (int i = 0 ; i < n ; i++ ) {
                int val = sc.nextInt();
                int cal = sc.nextInt();
                arr[i][0] = val;
                arr[i][1] = cal;
            }
            go(0, 0);
            System.out.println("#"+test_case+ " " + answer);
        }
    }
}
