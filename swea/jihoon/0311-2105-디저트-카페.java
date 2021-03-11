package swea.p2105_디저트_카페;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] board;
    static int res;

    static int[] dr = { 1, 1, -1, -1 };
    static int[] dc = { 1,-1, -1,  1 };
    static boolean[][] visited;
    static HashSet<Integer> set = new HashSet<>();

    static void dfs(int sr, int sc, int r, int c, int dir, int dir0Cnt, int dir1Cnt) {
        if (visited[r][c]) {
            if (sr == r && sc == c)
                res = Math.max(res, set.size());
            return;
        }
        if (set.contains(board[r][c]))
            return;

        set.add(board[r][c]);
        visited[r][c] = true;

        for (int i = dir; i < dir + 2; i++) {
            int ndir = i % 4;
            int nr = r + dr[ndir];
            int nc = c + dc[ndir];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                continue;

            int nDir0Cnt = dir0Cnt;
            int nDir1Cnt =  dir1Cnt;
            if (ndir == 0) nDir0Cnt++;
            else if (ndir == 1) nDir1Cnt++;
            else if (ndir == 2) nDir0Cnt--;
            else nDir1Cnt--;
            if (nDir0Cnt < 0 || nDir1Cnt < 0) continue;

            dfs(sr, sc, nr, nc, ndir, nDir0Cnt, nDir1Cnt);
        }

        set.remove(board[r][c]);
        visited[r][c] = false;
    }

    static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                set.clear();
                dfs(i, j, i, j, 0, 0, 0);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            res = -1;

            N = Integer.parseInt(in.readLine());
            board = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < N; j++)
                    board[i][j] = Integer.parseInt(st.nextToken());
            }
            solution();
            System.out.println("#" + tc + " " + res);
        }
    }
}
