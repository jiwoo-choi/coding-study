import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MAX_N = 20010;
    static int V, E, K;
    static boolean[] visited;
    static int[] dists;

    static class Node implements Comparable<Node> {
        int to, dist;
        Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    static List<Node>[] edge = new ArrayList[MAX_N];
    static {
        for (int i = 0; i < MAX_N; i++)
            edge[i] = new ArrayList<>();
    }

    static void dijkstra() {
        Arrays.fill(dists, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(K, 0));
        dists[K] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int from = cur.to;
            int fromDist = cur.dist;
            if (visited[from]) continue;
            visited[from] = true;
            
            for (int i = 0; i < edge[from].size(); i++) {
                int to = edge[from].get(i).to;
                int toDist = edge[from].get(i).dist + fromDist;
                if (visited[to] || toDist > dists[to])
                    continue;

                dists[to] = toDist;
                pq.offer(new Node(to, toDist));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(in.readLine());
        visited = new boolean[V+1];
        dists = new int[V+1];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            edge[from].add(new Node(to, dist));
        }

        dijkstra();

        for (int i = 1; i <= V; i++)
            System.out.println(visited[i]? dists[i] : "INF");
    }
}
