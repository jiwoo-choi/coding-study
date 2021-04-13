import java.io.*;
import java.util.*;

public class Main {
    static final int WALL = 1;
    static int N;
    static int[][] board;

    static final int VERTICAL = 0;
    static final int HORIZONTAL = 1;
    static final int DIAGONAL = 2;

    static class Node {
        int r, c, dir;
        Node (int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    static int bfs() {
        int res = 0;
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(1, 2, HORIZONTAL));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.r == N && cur.c == N) {
                res++;
                continue;
            }

            int nr = 0;
            int nc = 0;
            int sum = 0;
            if (cur.dir == HORIZONTAL || cur.dir == DIAGONAL) {
                nr = cur.r;
                nc = cur.c + 1;
                if (0 < nr && nr <= N && 0 < nc && nc <= N && board[nr][nc] != WALL)
                    q.offer(new Node(nr, nc, HORIZONTAL));
            }
            if (cur.dir == VERTICAL || cur.dir == DIAGONAL) {
                nr = cur.r + 1;
                nc = cur.c;
                if (0 < nr && nr <= N && 0 < nc && nc <= N && board[nr][nc] != WALL)
                    q.offer(new Node(nr, nc, VERTICAL));
            }
            nr = cur.r + 1;
            nc = cur.c + 1;
            if (0 < nr && nr <= N && 0 < nc && nc <= N
                && board[nr][nc] != WALL && board[nr-1][nc] != WALL && board[nr][nc-1] != WALL)
                q.offer(new Node(nr, nc, DIAGONAL));
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        board = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 1; j <= N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.println(bfs());
    }
}
