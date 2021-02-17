import java.io.*;
import java.util.*;
 
public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[][] = new int[n][2];
         
        for (int i = 0 ; i < n ; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            arr[i][0] = a;
            arr[i][1] = b;
        }
         
        Arrays.sort(arr, (v1, v2) -> {
            return v1[1] - v2[1];
        });
         
        int curpoint = arr[0][1];
        int count = 1;
        for (int i = 1; i < n ; i++) {
            if (arr[i][0]> curpoint) {
                curpoint = arr[i][1];
                count++;
            } else {
                i++;
            }
        }
        System.out.println(count);
    }
 
}
