import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static int arr[][];
    static boolean visited[][];
    static int value[][];
    static int n;
    static int m;

    public static int go(int x, int y, int depth) {

        if (value[x][y] != -1) return value[x][y];

        int max = Integer.MIN_VALUE;

        for(int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < n && ny < m && nx >= 0 && ny >= 0 && !visited[nx][ny] && arr[nx][ny] == arr[x][y] + 1) {
                visited[nx][ny] = true;
                max = Math.max(max, go(nx,ny, depth+1));
                visited[nx][ny] = false;
            } else {
                max = Math.max(max,depth);
            }
        }
        value[x][y] = max;
        return value[x][y];
    }

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        new StringBuilder().append("abc").repl
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            n = sc.nextInt();
            m = n;
            visited = new boolean[n][n];
            arr = new int[n][n];
            value = new int[n][n];

            for (int[] ele : value) {
                Arrays.fill(ele, -1);
            }

            // dp로 풀 수 있지 않을까?
            for(int i = 0 ; i < n ; i++) {
                for (int j = 0 ; j < n ; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            int maxVal = Integer.MIN_VALUE;
            int maxValIdx = Integer.MAX_VALUE;

            for (int i = 0 ; i < n ; i++) {
                for(int j = 0 ; j < m ; j++) {
                    value[i][j] = go(i, j, 1);
                    if (value[i][j] > maxVal) {
                        maxVal = value[i][j];
                        maxValIdx = arr[i][j];
                    } else if (value[i][j] == maxVal &&  arr[i][j] < maxValIdx ) {
                        maxVal = value[i][j];
                        maxValIdx = arr[i][j];
                    }
                }
            }
            System.out.println("#"+ (test_case) + " " + maxValIdx + " " + maxVal);
        }
    }
}
