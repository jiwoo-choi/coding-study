import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

class Solution
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T;
        T=sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++) {

            int n = sc.nextInt();
            String[] arr = new String[n];

            for (int i = 0; i < n; i++) {
                arr[i] = sc.next();
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n / 2; i++) {
                sb.append(arr[i]).append(" ").append(arr[i + n / 2 + (n%2)]).append(" ");
            }

            if ((n%2) > 0) {
                sb.append(arr[n / 2]);
            }

            System.out.println("#" + test_case + " " + sb);
        }

    }
}
