import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MAX_N = 10010;
    static List<Edge>[] edges = new ArrayList[MAX_N];
    static {
        for(int i = 0; i < MAX_N; i++)
            edges[i] = new ArrayList<Edge>();
    }
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static boolean[] visited = new boolean[MAX_N];

    static class Edge implements Comparable<Edge> {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int solution() {
        int sum = 0;

        visited[1] = true;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int from = cur.to;
            if (visited[from]) continue;

            visited[from] = true;
            sum += cur.weight;

            for (int i = 0; i < edges[from].size(); i++) {
                int to = edges[from].get(i).to;
                int weight = edges[from].get(i).weight;
                if (visited[to]) continue;

                pq.offer(new Edge(to, weight));
            }
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Edge toB = new Edge(b, c);
            Edge toA = new Edge(a, c);
            edges[a].add(toB);
            edges[b].add(toA);
            if (a == 1) pq.offer(toB);
            if (b == 1) pq.offer(toA);
        }
        System.out.println(solution());
    }
}
