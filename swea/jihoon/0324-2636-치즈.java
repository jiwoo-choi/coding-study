import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int EMPTY = 0;
    static final int CHEESE = 1;
    static final int MARKED = 2;
    static int N, M, K;
    static int[][] board;
    static boolean[][] visited;
    // > v < ^
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {1, 1, 0,-1,-1, -1,  0,  1};
    static List<Integer> res = new ArrayList<>();

    static int[] dx2 = {0, -1,  0, 1};
    static int[] dy2 = {1,  0, -1, 0};

    static void bfs(int sr, int sc) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(sr);
        q.offer(sc);
        visited[sr][sc] = true;
        while (!q.isEmpty()) {
            int r = q.poll();
            int c = q.poll();
            for (int i = 0; i < 8; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || board[nr][nc] == EMPTY)
                    continue;

                visited[nr][nc] = true;
                q.offer(nr);
                q.offer(nc);
            }
        }
    }

    static void markBoundary(int sr, int sc) {
        int r = sr, c = sc, dir = 0;

        board[r][c] = 2;
        int dirCnt = 0;
        while (true) {
            int nr = r + dx[dir];
            int nc = c + dy[dir];
            if (nr == sr && nc == sc)
                break;

            if (nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == EMPTY) {
                if (++dirCnt >= 8) break;
                dir = (dir + 1) % 8;
                continue;
            }

            board[nr][nc] = 2;
            r = nr;
            c = nc;
            dir = (dir + 6) % 8;
            dirCnt = 0;
        }
    }
    static int cleanMarks() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == MARKED) {
                    board[i][j] = EMPTY;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void init() {
        for (int i = 0; i < N; i++)
            Arrays.fill(visited[i], false);
    }

    static void solution() {
        while (K > 0) {
            init();
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == CHEESE && !visited[i][j]) {
                        bfs(i, j);
                        markBoundary(i, j);
                        cnt += cleanMarks();
                    }
                }
            }
            res.add(cnt);
            K -= cnt;
        }
    }

    static int bfs2(int sr, int sc) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(sr);
        q.offer(sc);
        visited[sr][sc] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            int r = q.poll();
            int c = q.poll();
            if (board[r][c] == CHEESE) {
                board[r][c] = EMPTY;
                cnt++;
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nr = r + dx2[i];
                int nc = c + dy2[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc])
                    continue;

                visited[nr][nc] = true;
                q.offer(nr);
                q.offer(nc);
            }
        }
        return cnt;
    }

    static void solution2() {
        while (K > 0) {
            init();
            int cnt = bfs2(0, 0);
            res.add(cnt);
            K -= cnt;
        }
    }

    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == CHEESE) K++;
            }
        }
//        solution();
        solution2();
        System.out.println(res.size());
        System.out.println(res.get(res.size()-1));
    }
}


