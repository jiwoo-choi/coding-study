import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;

    static final int WALL = 1;
    
    static final int VERTICAL = 0;
    static final int HORIZONTAL = 1;
    static final int DIAGONAL = 2;

    static int[][][] memo;
    static int go(int r, int c, int s) {
        if (r < 1 || r > N || c < 1 || c > N || board[r][c] == WALL)
            return 0;

        if (memo[r][c][s] != -1) return memo[r][c][s];
        memo[r][c][s] = 0;

        if (s == VERTICAL) {
            if (r-1 >= 1 && board[r-1][c] != WALL) {
                memo[r][c][s] += go(r-1, c, VERTICAL);
                memo[r][c][s] += go(r-1, c, DIAGONAL);
            }
        } else if (s == HORIZONTAL) {
            if (c-1 >= 1 && board[r][c-1] != WALL) {
                memo[r][c][s] += go(r, c-1, HORIZONTAL);
                memo[r][c][s] += go(r, c-1, DIAGONAL);
            }
        } else {
            if (r-1 >=1 && c-1 >=1 && board[r-1][c] != WALL && board[r][c-1] != WALL) {
                memo[r][c][s] += go(r-1, c-1, VERTICAL);
                memo[r][c][s] += go(r-1, c-1, HORIZONTAL);
                memo[r][c][s] += go(r-1, c-1, DIAGONAL);
            }
        }
        return memo[r][c][s];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        board = new int[N+1][N+1];
        memo = new int[N+1][N+1][3];
        for (int[][] d2 : memo)
            for (int[] d1 : d2) Arrays.fill(d1, -1);

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 1; j <= N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        memo[1][2][HORIZONTAL] = 1;

        int res = 0;
        for (int s = 0; s < 3; s++) res += go(N, N, s);
        System.out.println(res);
    }
}
