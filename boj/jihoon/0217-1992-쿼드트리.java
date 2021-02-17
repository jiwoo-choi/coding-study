import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] board;

    static boolean allSame(int sr, int sc, int size) {
        int cmp = board[sr][sc];
        for (int r = sr; r < sr + size; r++)
            for (int c = sc; c < sc + size; c++)
                if (cmp != board[r][c]) return false;

        return true;
    }

    static void go(int sr, int sc, int size) {
        if (allSame(sr, sc, size)) {
            System.out.print(board[sr][sc]);
            return;
        }

        System.out.print("(");

        int nextSize = size / 2;
        for (int r = sr; r < sr + size; r += nextSize)
            for (int c = sc; c < sc + size; c += nextSize)
                go(r, c, nextSize);

        System.out.print(")");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            String str = in.readLine();
            for (int j = 0; j < N; j++)
                board[i][j] = str.charAt(j) - '0';
        }
        go(0, 0, N);
    }
}
