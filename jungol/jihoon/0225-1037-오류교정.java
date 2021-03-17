import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Main {
 
    static class Node {
        int r, c;
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node other = (Node) obj;
                if (this.r == other.r && this.c == other.c)
                    return true;
            }
            return false;
        }
        @Override
        public int hashCode() {
            int hash = 31;
            hash *= this.r;
            hash *= this.c + 7;
            return hash;
        }
    }
    static int N;
    static int[][] board;
     
    static String solution() {
        List<Integer> rowsCnt = new ArrayList<>();
        List<Integer> colsCnt = new ArrayList<>();
         
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += board[i][j];
            }
            if (sum % 2 == 1)
                rowsCnt.add(i);
        }
         
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += board[j][i];
            }
            if (sum % 2 == 1)
                colsCnt.add(i);
        }
         
        if (rowsCnt.size() == 1 && colsCnt.size() == 1) {
            return "Change bit (" + (rowsCnt.get(0)+1) + "," + (colsCnt.get(0)+1) + ")";
        } else if (rowsCnt.size() == 0 && colsCnt.size() == 0) {
            return "OK";
        } else {
            return "Corrupt";
        }
    }
     
    public static void main(String[] args) throws Exception {   
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
         
        N = Integer.parseInt(in.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
             
        }
        System.out.println(solution());
    }
}
