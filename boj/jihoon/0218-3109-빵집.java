package boj.p3019_빵집;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] board;
    static final char BUILDING = 'x';
    static final char EMPTY = '.';

    static int[] dx = {-1, 0, 1};

    static int res;
    static boolean backtracking(int r, int c) {
        if(r < 0 || r >= R || board[r][c] == BUILDING)
            return false;

        board[r][c] = BUILDING;

        if (c >= C-1) {
            res++;
            return true;
        }
        int nc = c + 1;
        return backtracking(r-1, nc) || backtracking(r, nc) || backtracking(r+1, nc);
    }

    /*
    static boolean backtracking2(int r, int c) {
        if (c >= C-1) {
            res++;
            return true;
        }

        int nc = c + 1;
        for (int i = 0; i < 3; i++) {
            int nr = r + dx[i];

            if (nr < 0 || nr >= R || board[nr][nc] != EMPTY)
                continue;

            board[nr][nc] = BUILDING;

            if (backtracking2(nr, nc))
                return true;
        }
        return false;
    }
    */

    static void solution() {
        for (int r = 0; r < R; r++)
            backtracking(r, 0);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][];
        for (int i = 0; i < R; i++)
            board[i] = in.readLine().toCharArray();

        solution();
        System.out.println(res);
    }
}
