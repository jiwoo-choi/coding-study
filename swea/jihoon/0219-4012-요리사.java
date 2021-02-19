import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
    static int N;
    static int[][] input;

    static int[] left = new int[16];
    static final int LEFT = 1;
    static final int RIGHT = 0;
    static int res;
    
    static void calSum() {
        int[] sum = new int[2];
        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                if (left[i] == left[j])
                    sum[left[i]] += (input[i][j] + input[j][i]);

        res = Math.min(res, Math.abs(sum[1] - sum[0]));
    }
    
    static void backtracking(int idx, int leftCnt) {
        if (idx >= N) {
            if (leftCnt == N/2)
                calSum();
            return;
        }

        if (leftCnt > N/2) return;

        left[idx] = LEFT;
        backtracking(idx+1, leftCnt + 1);

        left[idx] = RIGHT;
        backtracking(idx+1, leftCnt);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int testcase = sc.nextInt();
        for (int tc = 1; tc <= testcase; tc++) {
            res = Integer.MAX_VALUE;

            N = sc.nextInt();
            input = new int[N][N];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    input[i][j] = sc.nextInt();

            backtracking(0, 0);
            System.out.println("#" + tc + " " + res);
        }
    }
}
