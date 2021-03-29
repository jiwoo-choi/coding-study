import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] num = {1, 5, 10, 50};
    static boolean[][] visited;

    static void go(int cnt, int start, int sum) {
        if (cnt >= N) {
            visited[sum][cnt] = true;
            return;
        }
        if (visited[sum][cnt]) return;
        visited[sum][cnt] = true;
        for (int i = start; i < 4; i++)
            go(cnt+1, start, sum + num[i]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        visited = new boolean[1001][N+1];
        go(0, 0, 0);

        long res = 0;
        for (int i = 1 ; i < 1001; i++)
            if (visited[i][N]) res++;
        System.out.println(res);
    }
}
