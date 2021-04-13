import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] subjects = new int[1001][];
    static boolean[][] students = new boolean[10001][51];

    static void solution() {
        for (int i = 0; i < M; i++) {
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                int NN = subjects[j].length;
                boolean pass = true;
                for (int k = 0; k < NN; k++) {
                    if (!students[i][subjects[j][k]]) {
                        pass = false;
                        break;
                    }
                }
                if (pass) cnt++;
            }
            System.out.println(cnt);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int NN = Integer.parseInt(st.nextToken());
            subjects[i] = new int[NN];
            for (int j = 0; j < NN; j++)
                subjects[i][j] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(in.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int MM = Integer.parseInt(st.nextToken());
            for (int j = 0; j < MM; j++) {
                int num = Integer.parseInt(st.nextToken());
                students[i][num] = true;
            }
        }
        solution();
    }
}
