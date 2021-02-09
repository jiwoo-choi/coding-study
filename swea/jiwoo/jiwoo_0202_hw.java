import java.util.Scanner;
import java.io.FileInputStream;
 
class Solution
{
     
    static int[][] arr;
 
     public static void recurisve(int x, int y, int start, int limit) {
     
        if (limit < 1) {
            return;
        }
        // 반복
        for (int i = y ; i < y + limit ; i++) {
            arr[x][i] = start++;
        }
 
        for (int i = x + 1 ; i < x + limit ; i++) {
            arr[i][y + limit - 1] = start++;
        }
        // 끝 좌표에서 -1 -> 첫좌표까지
        for (int i = (y + limit -1 ) -1 ; i >= y ; i--) {
            arr[x + limit - 1][i] = start++;
        }
 
        for (int i = (x+limit-1)-1 ; i > x ; i--) {
            arr[i][y] = start++;
        }
 
        recurisve(x+1,y+1,start, limit - 2);
    }
    
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
         
         
        for(int test_case = 1; test_case <= T; test_case++)
        {
                int limit = sc.nextInt();
                arr = new int[limit][limit];
                recurisve(0,0, 1, limit);
               System.out.println("#" + test_case);
                for (int i = 0 ; i < limit ; i++) {
                    for(int j = 0 ; j < limit ; j++) {
                        System.out.print(arr[i][j] + " ");
                    }
                    System.out.println();
                }
 
        }
    }
}
