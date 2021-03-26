import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_DIST = 50 * 20;
    static int N;
    static int[][] coord;
    // dp[i][j] : i에서 j까지 이동 가능 여부
    static boolean[][] dp;

    static void solution() {
        for (int i = 0; i < N; i++) {
            dp[i][i] = false;
            for (int j = 0; j < i; j++) {
                int dist = Math.abs(coord[i][0] - coord[j][0])
                        + Math.abs(coord[i][1]- coord[j][1]);
                if (dist <= MAX_DIST)
                    dp[j][i] = dp[i][j] = true;
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if (k == i) continue;
                for (int j = 0; j < N; j++) {
                    if (k == j || i == j) continue;

                    dp[i][j] |= (dp[i][k] & dp[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < testcase; tc++) {
            N = 2 + Integer.parseInt(in.readLine());
            coord = new int[N][2];
            dp = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                coord[i][0] = Integer.parseInt(st.nextToken());
                coord[i][1] = Integer.parseInt(st.nextToken());
            }

            solution();

            System.out.println(dp[0][N-1]? "happy" : "sad");
        }
    }
}
