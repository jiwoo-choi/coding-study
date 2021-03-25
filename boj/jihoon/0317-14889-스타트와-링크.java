import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc;
    static final int MAX_N = 25;
    static int[][] board = new int[MAX_N][MAX_N];
    static int[][] score = new int[MAX_N][MAX_N];
    static boolean[] team = new boolean[MAX_N];
    static int N, res;

    static void init() {
        res = Integer.MAX_VALUE;

        N = sc.nextInt();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                board[i][j] = sc.nextInt();
    }

    static void calScore() {
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                score[i][j] = score[j][i] = board[i][j] + board[j][i];
            }
        }
    }
    static int calDiff() {
        int[] sum = new int[]{ 0, 0 };
        for (int i = 0; i < N; i++) {
            boolean firstTeam = team[i];
            for (int j = i+1; j < N; j++)
                if (firstTeam == team[j])
                    sum[firstTeam? 0 : 1] += score[i][j];
        }
        return Math.abs(sum[0] - sum[1]);
    }
    static void dfs(int idx, int cnt) {
        if (idx >= N) {
            if (cnt == N/2)
                res = Math.min(res, calDiff());
            return;
        }

        if (cnt < N/2) {
            team[idx] = true;
            dfs(idx+1, cnt+1);
        }
        team[idx] = false;
        dfs(idx+1, cnt);
    }

    static void solution() {
        calScore();
        dfs(0, 0);
    }

    public static void main(String[] args) throws FileNotFoundException {
        sc = new Scanner(System.in);
        int testcase = 1;
        for (int tc = 1; tc <= testcase; tc++) {
            init();
            solution();
            System.out.println(res);
        }
    }
}
