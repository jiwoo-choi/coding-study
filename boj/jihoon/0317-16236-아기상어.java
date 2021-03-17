import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] board;
    static boolean[][] visited;
    static int cr, cc;
    static int curLevel, levelUpCnt;
    static int[] dx = {-1, 0 , 1, 0};
    static int[] dy = { 0,-1 , 0, 1};
    
    static class Point implements Comparable<Point> {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
        @Override
        public int compareTo(Point o) {
            if (this.r == o.r)
                return this.c - o.c;
            else
                return this.r - o.r;
        }
    }
    
    static int bfs() {
        for (int i = 0; i < N; i++)
            Arrays.fill(visited[i], false);
        
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(cr, cc));
        visited[cr][cc] = true;
        
        int cnt = 0;
        List<Point> eatList = new ArrayList<>();
        while (!q.isEmpty()) {
            for (int qs = 0, qSize = q.size(); qs < qSize; qs++) {
                Point cur = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dx[i];
                    int nc = cur.c + dy[i];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || curLevel < board[nr][nc])
                        continue;
                    
                    visited[nr][nc] = true;
                    
                    Point next = new Point(nr, nc);
                    if (board[nr][nc] > 0 && curLevel > board[nr][nc])
                        eatList.add(next);
                    
                    q.offer(next);
                }
            }
            cnt++;
            if (eatList.size() > 0)
                break;
        }

        if (eatList.size() <= 0)
            return -1;
        
        Collections.sort(eatList);
        
        levelUpCnt--;
        if (levelUpCnt == 0) {
            curLevel++;
            levelUpCnt = curLevel;
        }
        cr = eatList.get(0).r;
        cc = eatList.get(0).c;
        board[cr][cc] = 0;
        return cnt;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        for (int tc = 1; tc <= 1; tc++) {
            curLevel = 2;
            levelUpCnt = 2;
            
            N = Integer.parseInt(in.readLine());
            board = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 9) {    
                        cr = i; cc= j; 
                        board[i][j] = 0; 
                    }
                }
            }
            int res = 0;
            int time = -1;
            while ((time = bfs()) > 0)
                res += time;
            
            System.out.println(res);
        }
    }
}
