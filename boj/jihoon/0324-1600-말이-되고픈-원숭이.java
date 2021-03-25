import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static final int WALL = 1;
    static final int EMPTY = 0;
    static int N, M, K;
    // 0 ~ 7 : horse, 8 ~ 11 : monkey
    static int[] dx = new int[]{-1, 1, 2,  2, -1,  1, -2, -2, -1,  0, 1, 0};
    static int[] dy = new int[]{ 2, 2, 1, -1, -2, -2, -1,  1,  0, -1, 0, 1};
    static int[][] board;
    static boolean[][][] visited;

    static class Node {
        int r, c, cnt;
        Node(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    static int solution() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0, 0));
        visited[0][0][0] = true;
        int cnt = 0;
        while (!q.isEmpty()) {
            for (int qs = 0, qSize = q.size(); qs < qSize; qs++) {
                Node cur = q.poll();
                if (cur.r == N-1 && cur.c == M-1)
                    return cnt;

                for (int i = 0; i < 12; i++) {
                    int nr = cur.r + dx[i];
                    int nc = cur.c + dy[i];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == WALL)
                        continue;

                    if (i < 8) {
                        int nCnt = cur.cnt + 1;
                        if (cur.cnt >= K || visited[nr][nc][nCnt]) continue;

                        visited[nr][nc][nCnt] = true;
                        q.offer(new Node(nr, nc, nCnt));
                    } else {
                        if (visited[nr][nc][cur.cnt])
                            continue;

                        visited[nr][nc][cur.cnt] = true;
                        q.offer(new Node(nr, nc, cur.cnt));
                    }
                }
            }
            cnt++;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M][K+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < M; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }
}
