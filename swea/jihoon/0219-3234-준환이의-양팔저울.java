import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int res;
    static int[] input;
    static int totalSum;


    static void backtracking(int idx, int leftSum, int rightSum) {
        if (idx >= N) {
            if (leftSum >= rightSum)
                res++;
            return;
        }
        if (leftSum < rightSum) return;

        backtracking(idx+1, leftSum + input[idx], rightSum);
        backtracking(idx+1, leftSum, rightSum + input[idx]);
    }

    static void solution() {
        Arrays.sort(input);
        do {
            backtracking(0, 0, 0);
        } while(np());

    }

    static boolean np() {
        int i = N-1;
        while (i-1 >= 0 && input[i-1] >= input[i]) i--;
        if (i <= 0) return false;

        int j = N-1;
        while (input[i-1] >= input[j]) j--;
        swap(i-1, j);

        int k = N-1;
        while (i < k) swap(i++, k--);
        return true;
    }
    
    static void swap(int i, int j) {
        int tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            totalSum = 0;
            res = 0;

            N = Integer.parseInt(in.readLine());
            input = new int[N];
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int i = 0; i < N; i++) {
                input[i] = Integer.parseInt(st.nextToken());
                totalSum += input[i];
            }

            solution();
            System.out.println("#" + tc + " " + res);
        }
    }

}
