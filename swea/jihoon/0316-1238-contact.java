import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static boolean[][] edge;
    static int[] visited;

    static int bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = 1;

        while (!q.isEmpty()) {
            for (int qs = 0, qSize = q.size(); qs < qSize; qs++) {
                int from = q.poll();
                for (int to = 1; to < 101; to++) {
                    if (!edge[from][to] || visited[to] > 0)
                        continue;
                    visited[to] = visited[from] + 1;
                    q.offer(to);
                }
            }
        }
      
        int maxCnt = 1;
        int maxNum = start;
        for (int i = 1; i < 101; i++) {
            if (maxCnt == visited[i])
                maxNum = Math.max(maxNum, i);
            if (maxCnt < visited[i]) {
                maxCnt = visited[i];
                maxNum = i;
            }
        }
        return maxNum;
    }
  
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            edge = new boolean[101][101];
            visited = new int[101];

            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(in.readLine(), " ");
            for (int i = 0; i < N/2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                edge[from][to] = true;
            }
            System.out.println("#" + tc + " " + bfs(start));
        }
    }
}
