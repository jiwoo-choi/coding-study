import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] board;
    static int res;

    static int[] dx = {  0, 1, 0, -1 };
    static int[] dy = { -1, 0, 1,  0 };

    static int[] percents = {1, 1, 2, 7, 7, 2, 10, 10, 5};
    static int[][] sdx = {
        {-1,1,-2,-1,1,2,-1,1,0,0},
        {-1,-1,0,0,0,0,1,1,2,1},
        {-1,1,-2,-1,1,2,-1,1,0,0},
        {1,1,0,0,0,0,-1,-1,-2,-1}
    };
    static int[][] sdy = {
        {1,1,0,0,0,0,-1,-1,-2,-1},
        {-1,1,-2,-1,1,2,-1,1,0,0},
        {-1,-1,0,0,0,0,1,1,2,1},
        {-1,1,-2,-1,1,2,-1,1,0,0}
    };

    static void cal(int sr, int sc, int sdir) {
        int sand = board[sr][sc];
        for (int i = 0; i < 10; i++) {
            int nr = sr + sdx[sdir][i];
            int nc = sc + sdy[sdir][i];

            if (i == 9) {
                if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                    res += sand;
                else
                    board[nr][nc] += sand;
                break;
            }

            int cur = board[sr][sc] * percents[i] / 100;
            sand -= cur;
            if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                res += cur;
            else
                board[nr][nc] += cur;
        }
    }

    static void solution() {
        int r = N/2;
        int c = N/2;
        int dir = 0;

        int depth = 2;
        int cnt = 0;
        for (int level = 1; level < N * N; level++) {
            r += dx[dir];
            c += dy[dir];

            cal(r, c, dir);

            cnt++;
            if (cnt >= depth/2) {
                cnt = 0;
                dir = (dir+1) % 4;
                depth++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        res = 0;

        N = Integer.parseInt(in.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        solution();
        System.out.println(res);
    }
}
