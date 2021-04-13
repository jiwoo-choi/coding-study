import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = { 0, 1, 0,-1};
    
    static int solution(char[] orders) {
        int dir = 0;
        int r = 0, c = 0;
        int maxR, maxC, minR, minC;
        maxR = maxC = minR = minC = 0;
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
                int backDir = (dir + 2) % 4;
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
        for (int i = 0; i < N; i++) {
            System.out.println(solution(in.readLine().toCharArray()));
        }
    }
}
