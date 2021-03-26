import java.io.*;
import java.util.*;

public class Main {
    static final int SAFE = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static class Node {
        int r, c;
        Node (int r, int c) {
            this.r = r; this.c = c;
        }
    }

    static List<Node> virusList;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = { 0,-1, 0, 1};

    static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        for (Node e : virusList) {
            board[e.r][e.c] = VIRUS;
            q.offer(e);
        }

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dx[i];
                int nc = cur.c + dy[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] >= WALL)
                    continue;

                board[nr][nc] = VIRUS;
                q.offer(new Node(nr, nc));
            }
        }
    }

    static int countSafeNInit() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == SAFE)
                    cnt++;
                else if (board[i][j] == VIRUS)
                    board[i][j] = SAFE;
            }
        }
        for (Node e : virusList)
            board[e.r][e.c] = VIRUS;

        return cnt;
    }

    static int solution() {
        int max = 0;
        for (int i = 0; i < N*M; i++) {
            int r1 = i / M, c1 = i % M;
            if (board[r1][c1] >= WALL) continue;
            board[r1][c1] = WALL;
            for (int j = i+1; j < N*M; j++) {
                int r2 = j / M, c2 = j % M;
                if (board[r2][c2] >= WALL) continue;
                board[r2][c2] = WALL;
                for (int k = j+1; k < N*M; k++) {
                    int r3 = k / M, c3 = k % M;
                    if (board[r3][c3] >= WALL) continue;
                    board[r3][c3] = WALL;

                    bfs();

                    max = Math.max(max, countSafeNInit());
                    board[r3][c3] = SAFE;
                }
                board[r2][c2] = SAFE;
            }
            board[r1][c1] = SAFE;
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        virusList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == VIRUS) virusList.add(new Node(i, j));
            }
        }
        System.out.println(solution());
    }
}
