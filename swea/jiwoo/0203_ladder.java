import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;
 
class Solution{
    static int dx[] = {0, 0, -1}; // 우, 죄, 위
    static int dy[] = {-1, 1, 0};
 
    static char[][] arr;
    static boolean[][] visited;
    public static void main(String args[]) throws Exception
    {
        // x 지점부터 거꾸로.
        // 도착지점에서 부터 시작해서.
        // 위로갔다가 왼쪽으로 간다.
 
        BufferedReader bf = new BufferedReader(new InputStreamReader((System.in)));
 
 
        int x = 0;
        int y = 0;
 
        for(int test_case = 1; test_case <= 10; test_case++)
        {
            arr = new char[100][100];
            visited = new boolean[100][100];
            bf.readLine();
            for (int i = 0 ; i < 100 ; i++) {
                String[] st = bf.readLine().split(" ");
                for (int j = 0 ; j < 100 ; j++) {
                    arr[i][j] = st[j].charAt(0);
                    if (st[j].charAt(0) == '2') {
                        x = i;
                        y = j;
                    }
                }
            }
            int currx = x;
            int curry = y;
            visited[currx][curry] = true;
 
            int dir = 2; // 0: 좌 1:  3: 위.
            while(currx >= 1) {
 
 
                if (curry > 0  && !visited[currx][curry-1] && arr[currx][curry-1] == '1') {
                    dir = 0;
                } else if (curry < 99 && !visited[currx][curry+1] && arr[currx][curry+1] == '1') {
                    dir = 1;
                } else {
                    dir = 2;
                }
 
                currx += dx[dir];
                curry += dy[dir];
                visited[currx][curry] = true;
           }
            System.out.println("#" + test_case + " " + curry );
 
 
        }
    }
}
