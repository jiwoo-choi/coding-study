package swea.p8382_방향_전환;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static final int MAX_N = 201;
    static final int OFFSET = 0;
    static boolean[][][] visited = new boolean[MAX_N][MAX_N][2];
  
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = { 0,-1, 0, 1};

    static int sr, sc, er, ec;

    static class Node {
        int r, c;
        int isVertical;
        Node(int r, int c, int isVertical) {
            this.r = r;
            this.c = c;
            this.isVertical = isVertical;
        }
    }


    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(sr, sc, 0));
        q.offer(new Node(sr, sc, 1));
        visited[sr][sc][0] = true;
        visited[sr][sc][1] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            for (int qs = 0, qSize = q.size(); qs < qSize; qs++) {
                Node cur = q.poll();
                if (cur.r == er && cur.c == ec)
                    return cnt;

                int nextDir = 1 - cur.isVertical;
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dx[i];
                    int nc = cur.c + dy[i];
                    if (cur.isVertical == (i % 2)) continue;
                    if (nr < 0 || nr >= MAX_N || nc < 0 || nc >= MAX_N) continue;
                    if (visited[nr][nc][nextDir]) continue;

                    visited[nr][nc][nextDir] = true;
                    q.offer(new Node(nr, nc, nextDir));
                }
            }
            cnt++;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            for (int i = 0; i < MAX_N; i++)
                for (int j = 0; j < MAX_N; j++)
                    visited[i][j][0] = visited[i][j][1] = false;

            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            sr = Integer.parseInt(st.nextToken()) + OFFSET;
            sc = Integer.parseInt(st.nextToken()) + OFFSET;

            er = Integer.parseInt(st.nextToken()) + OFFSET;
            ec = Integer.parseInt(st.nextToken()) + OFFSET;

            System.out.println("#" + tc + " " + bfs());
        }
    }
}
