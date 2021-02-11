import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) {
        int arr[][] = new int[100][100];
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int y = sc.nextInt();
            int x = sc.nextInt();
            for (int i = x ; i < x + 10; i++) {
                for (int j = y ; j < y + 10; j++) {
                    arr[i][j] = 1;
                }
            }
        }
        int result = 0;
        for (int i= 0 ; i < 100 ; i++) {
            for (int j = 0 ; j< 100; j++) {
                result += arr[i][j];
            }
        }
        System.out.println(result);
    }
}
