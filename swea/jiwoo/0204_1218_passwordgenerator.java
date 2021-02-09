import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T =10;
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            sc.nextInt();
 
            Queue<Integer> q = new LinkedList<Integer>();
            int t = 8;
            int cnt = 0;
            // sc.nextInt()
            while(t-- > 0) {
                int x = sc.nextInt();
                q.offer(x);
            }
 
            while(true) {
                int val = q.poll();
 
                int curr = (cnt % 5) + 1;
 
                if (val - curr <= 0) {
                    q.offer(0);
                    break;
                } else {
                    q.offer(val- curr);
                }
                cnt++;
            }
 
            StringBuilder sb = new StringBuilder();
 
            sb.append("#").append(test_case).append(" ");
            for (int val : q) {
                sb.append(val);
                sb.append(" ");
            }
            System.out.println(sb);
        }
    }
}
