import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[] visited;

    static int[] dr = {-1,  0, 1, 0};
    static int[] dc = { 0, -1, 0, 1};

    // 퇴각 진행
    static int backtracking(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= M)
            return 0;

        int curAlphabet = board[r][c];
        if (visited[curAlphabet]) return 0;

        visited[curAlphabet] = true;

        int ret = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            ret = Math.max(ret, backtracking(nr, nc) + 1);
            if (ret >= 26)
                break;
        }

        visited[curAlphabet] = false;
        return ret;
    }

    /* 미리 가지 치기
    static int backtracking2(int r, int c) {
        int ret = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                continue;
            int nextAlphabet = board[nr][nc];
            if (visited[nextAlphabet])
                continue;

            visited[nextAlphabet] = true;
            ret = Math.max(ret, backtracking2(nr, nc) + 1);
            visited[nextAlphabet] = false;
            if (ret >= 26)
                break;
        }
        return ret;
    }
    */

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[26];

        for (int i = 0; i < N; i++) {
            String str = in.readLine();
            for (int j = 0; j < M; j++)
                board[i][j] = str.charAt(j) - 'A';
        }

        System.out.println(backtracking(0, 0));

        /*
        int curAlphabet = board[0][0];
        visited[curAlphabet] = true;
        System.out.println(backtracking2(0, 0));
        */
    }
}

