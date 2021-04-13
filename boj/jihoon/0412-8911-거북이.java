import java.io.*;

public class Main {
    static final int MAX_N = 1010;
    
    static int N;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = { 0, 1, 0,-1};
    static int[][] board = new int[MAX_N][MAX_N];
    
    static int solution(char[] orders) {
        int dir = 0;
        int r = 505, c = 505;
        int maxR, maxC, minR, minC;
        maxR = maxC = minR = minC = 505;
        for (int i = 0; i < orders.length; i++) {
            char order = orders[i];
            if (order == 'L')
                dir = (dir + 3) % 4;
            else if (order == 'R')
                dir = (dir + 1) % 4;
            else if (order == 'F') {
                r += dx[dir];
                c += dy[dir];
                
                maxR = Math.max(maxR, r);
                minR = Math.min(minR, r);
                maxC = Math.max(maxC, c);
                minC = Math.min(minC, c);
            }
            else {
                int backDir = (dir + 2) %4;
                r += dx[backDir];
                c += dy[backDir];
                
                maxR = Math.max(maxR, r);
                minR = Math.min(minR, r);
                maxC = Math.max(maxC, c);
                minC = Math.min(minC, c);
            }
        }
        return (maxR - minR) * (maxC - minC);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++)
            System.out.println(solution(in.readLine().toCharArray()));
    }
}
