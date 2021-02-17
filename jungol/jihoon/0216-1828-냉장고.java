package jungol.p1828_냉장고;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static int[][] input;

    static int solution() {
        Arrays.sort(input, ((o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            else                return o1[1] - o2[1];
        }));

        int cnt = 0;
        int prevEnd = -271;
        for (int i = 0; i < N; i++) {
            int start = input[i][0];
            int end = input[i][1];
            if (prevEnd < start) {
                cnt++;
                prevEnd = end;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        input = new int[N][2];
        for (int i = 0; i < N; i++) {
            input[i][0] = sc.nextInt();
            input[i][1] = sc.nextInt();
        }
        System.out.println(solution());
    }
}
