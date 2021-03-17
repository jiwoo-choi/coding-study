import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static boolean[][] edge;
    static boolean[] visited;

    static void dfs(int from) {
        visited[from] = true;
        System.out.print(from + " ");

        for (int to = 1; to <= N; to++) {
            if (!edge[from][to] || visited[to])
                continue;

            visited[to] = true;
            dfs(to);
        }
    }

    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int from = q.poll();
            System.out.print(from + " ");
            for (int to = 1; to <= N; to++) {
                if (!edge[from][to] || visited[to])
                    continue;

                visited[to] = true;
                q.offer(to);
            }
        }
    }
  
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        edge = new boolean[N+1][N+1];
        visited = new boolean[N+1];
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            edge[from][to] = edge[to][from] = true;
        }
        dfs(V);
        System.out.println();

        Arrays.fill(visited, false);
        bfs(V);
    }
}
