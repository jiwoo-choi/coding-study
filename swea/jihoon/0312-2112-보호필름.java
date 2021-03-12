package swea.p2112_보호필름;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int[][] board, backup;
    static int D, W, K;
    static final int A = 0;
    static final int B = 1;
    static final int NONE = -1;
    static int res;

    static boolean isFinish() {
        for (int j = 0; j < W; j++) {
            int maxCnt = 1;
            int sameCnt = 1;
            int comparison = board[0][j];
            for (int i = 1; i < D; i++) {
                if (comparison == board[i][j]) sameCnt++;
                else {
                    maxCnt = Math.max(maxCnt, sameCnt);
                    sameCnt = 1;
                    comparison = board[i][j];
                }
            }
            maxCnt = Math.max(maxCnt, sameCnt);
            if (maxCnt < K) return false;
        }
        return true;
    }

    static void dfs(int cnt, int idx) {
        if (isFinish()) {
            res = Math.min(res, cnt);
            return;
        }
        if (cnt >= K) {
            res = Math.min(res, K);
            return;
        }
        if (idx >= D) return;

        for (int i = 0; i < W; i++) board[idx][i] = A;
        dfs(cnt+1, idx+1);

        for (int i = 0; i < W; i++) board[idx][i] = B;
        dfs(cnt+1, idx+1);

        for (int i = 0; i < W; i++) board[idx][i] = backup[idx][i];
        dfs(cnt, idx+1);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            res = Integer.MAX_VALUE;
            Arrays.fill(arr, NONE);

            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            board = new int[D][W];
            backup = new int[D][W];

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < W; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    backup[i][j] = board[i][j];
                }
            }
            if (K <= 1) {
                System.out.println("#" + tc + " " + 0);
                continue;
            }

            dfs(0, 0);
            System.out.println("#" + tc + " " + res);
        }
    }
}
