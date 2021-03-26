package boj.p17472_다리_만들기2;

import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_K = 7;
    static final int LAND = -1;
    static final int SEA = 0;
    static int N, M, K;
    static int curLabel;
    static int[][] board;
    static boolean[][] visited;
    static List<Boundary> boundaryList;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = { 0,-1, 0, 1};

    static List<Node>[] edge = new ArrayList[MAX_K];
    static {
        for (int i = 0; i < MAX_K; i++)
            edge[i] = new ArrayList<>();
    }

    static class Boundary {
        int r, c, dir;
        Boundary(int r, int c, int dir) {
            this.r = r; this.c = c; this.dir =dir;
        }
    }

    static class Node implements Comparable<Node> {
        int to, dist;
        Node(int to, int dist) {
            this.to = to; this.dist = dist;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r; this.c = c;
        }
    }

    static void bfs(int sr, int sc) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(sr, sc));
        board[sr][sc] = curLabel;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dx[i];
                int nc = cur.c + dy[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                    continue;

                if (board[nr][nc] == SEA)
                    boundaryList.add(new Boundary(cur.r, cur.c, i));
                else if (board[nr][nc] == LAND) {
                    board[nr][nc] = curLabel;
                    q.offer(new Point(nr, nc));
                }
            }
        }
    }

    static void label() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == LAND) {
                    curLabel++;
                    bfs(i, j);
                }
            }
        }
    }

    static void makeEdge() {
        for (Boundary boundary : boundaryList) {
            int r = boundary.r;
            int c = boundary.c;
            int from = board[r][c];
            int dir = boundary.dir;

            int dist = 0;
            while (true) {
                r += dx[dir];
                c += dy[dir];
                if (r < 0 || r >= N || c < 0 || c >= M) break;

                if (board[r][c] == SEA) {
                    dist++;
                    continue;
                }
                int to = board[r][c];
                if (from != to && dist >= 2)
                    edge[from].add(new Node(to, dist));
                break;
            }
        }
    }

    static int prim() {
        int res = 0;
        boolean[] visited = new boolean[MAX_K];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int from = cur.to;
            if (visited[from]) continue;
            visited[from] = true;
            res += cur.dist;

            for (int i = 0; i < edge[from].size(); i++)
                pq.offer(edge[from].get(i));
        }
        for (int i = 1; i <= curLabel; i++)
            if (!visited[i]) return -1;

        return res;
    }

    static int solution() {
        label();
        makeEdge();
        return prim();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        boundaryList = new ArrayList<>();
        curLabel = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) board[i][j] = LAND;
            }
        }
        System.out.println(solution());
    }
}
