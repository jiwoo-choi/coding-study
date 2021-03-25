import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static double E;
    static int[][] coord;
    static long[][] edge;
    static boolean[] visited;

    static class Node implements Comparable<Node> {
        int to;
        long dist;
        Node(int to, long dist) {
            this.to = to;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    static long solution() {
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                long xdiff = Math.abs(coord[i][0] - coord[j][0]);
                long ydiff = Math.abs(coord[i][1] - coord[j][1]);
                edge[i][j] = edge[j][i] = xdiff*xdiff + ydiff*ydiff;
            }
        }

        long res = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int from = cur.to;
            if (visited[from]) continue;
            visited[from] = true;
            res += cur.dist;

            for (int to = 1; to < N; to++) {
                if (visited[to]) continue;
                pq.offer(new Node(to, edge[from][to]));
            }
        }
        return Math.round(res * E);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            N = Integer.parseInt(in.readLine());
            edge = new long[N][N];
            coord = new int[N][2];
            visited = new boolean[N];

            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < N; j++)
                    coord[j][i] = Integer.parseInt(st.nextToken());
            }
            E = Double.parseDouble(in.readLine());

            sb.append("#").append(tc).append(" ").append(solution()).append("\n");
        }
        System.out.println(sb);
    }
}
