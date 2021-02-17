import java.io.FileInputStream;
import java.util.*;

public class Main {

    static final int MAX_N = 20;
    static final int MONSTER = 1;

    static int N, M, D;
    static List<Integer>[] board = new ArrayList[MAX_N];
    static List<Integer>[] backup = new ArrayList[MAX_N];
    static int[][] input = new int[MAX_N][MAX_N];
    static {
        for (int i = 0; i < board.length; i++) {
            board[i] = new ArrayList<>();
            backup[i] = new ArrayList<>();
        }
    }

    // ^ > v
    static int[] dx = new int[] { -1, 0, 1 };
    static int[] dy = new int[] {  0, 1, 0 };
    static boolean[][] visited = new boolean[MAX_N][MAX_N];
    static int res;
    static Set<Integer> dead = new HashSet<>();

    static void dfs(int sr, int sc) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc});
  
        int cnt = 0;
        while (cnt < D) {
            int qSize = q.size();
            for (int qs = 0; qs < qSize; qs++) {
                int r = q.peek()[0];
                int c = q.poll()[1];

                for (int i = 0; i < 3; i++) {
                    int nr = r + dx[i];
                    int nc = c + dy[i];

                    if (nr < 0 || nr >= M || nc < 0 || nc >= N)
                        continue;

                    if (board[nr].get(nc) == MONSTER) {
                        dead.add(nr * N + nc);
                        q.clear();
                        return;
                    }
                    if (visited[nr][nc])
                        continue;

                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
            cnt++;
        }
        q.clear();
    }

    static void shift() {
        for (int i = 0; i < M; i++) {
            board[i].remove(0);
            board[i].add(0);
        }
    }

    static void simulation(int a, int b, int c) {
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                board[i].set(j, backup[i].get(j));

        int killCount = 0;
        for (int rotate = 0; rotate < N; rotate++) {
            dead.clear();

            for (int i = 0; i < M; i++) {
                if (i == a || i == b || i == c) {
                    for (int k = 0; k < MAX_N; k++)
                        Arrays.fill(visited[k], false);

                    dfs(i, -1);
                }
            }

            killCount += dead.size();
            for (int e : dead)
                board[e/N].set(e%N, 0);

            shift();
        }
        res = Math.max(res, killCount);
    }

    static void solution() {
        for (int a = 0; a < M; a++)
            for (int b = a+1; b < M; b++)
                for (int c = b+1; c < M; c++)
                    simulation(a, b, c);
    }

    static void printBoard() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(board[i].get(j) + " ");
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        D = sc.nextInt();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                input[i][j] = sc.nextInt();

        for (int j = 0; j < M; j++)
            for (int i = N-1; i >= 0; i--) {
                board[j].add(input[i][j]);
                backup[j].add(input[i][j]);
            }

        solution();
        System.out.println(res);
    }
}
