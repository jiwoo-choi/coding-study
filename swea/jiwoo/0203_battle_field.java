import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;
 
class Solution{
    static int dx[] = {-1, 1, 0, 0}; // 우, 죄, 위
    static int dy[] = {0, 0, -1, 1};
    static char tank[] = {'^' , 'v' , '<', '>'};
    static char[][] arr;
    static int currx;
    static int curry;
    static int dir;
 
    static void move(int dir, int h, int w) {
        Solution.dir = dir;
        arr[currx][curry] = tank[dir];
 
        int nx = currx + dx[dir];
        int ny = curry + dy[dir];
 
        if (nx < 0 || ny < 0 || nx >= h || ny >= w || arr[nx][ny] == '#' || arr[nx][ny] == '*' || arr[nx][ny] == '-') return;
        arr[currx][curry] = '.';
        currx = nx;
        curry = ny;
        arr[currx][curry] = tank[dir];
    }
 
    static void shoot(int h, int w) {
        // 현재 방향과 현재 좌표를 기준으로.
        // 끝까지 쏴준다.
        // 바라보는 방향이 위면은..
        int nx = currx;
        int ny = curry;
 
        while(true){
 
            nx = nx + dx[dir];
            ny = ny + dy[dir];
 
            if (nx < 0 || ny < 0 || nx >= h || ny >= w) break;
 
            if (arr[nx][ny] == '*') {
                arr[nx][ny] = '.';
                break;
            }
 
            if (arr[nx][ny] == '#') {
                break;
            }
 
        }
 
 
    }
 
    public static void main(String args[]) throws Exception
    {
        // x 지점부터 거꾸로.
        // 도착지점에서 부터 시작해서.
        // 위로갔다가 왼쪽으로 간다.
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader((System.in)));
 
        int t = Integer.parseInt(bf.readLine());
 
        int x = 0;
        int y = 0;
 
        for(int test_case = 1; test_case <= t; test_case++){
 
            System.out.print("#" + test_case + " ");
            int w = 0;
            int h = 0;
            String[] st = bf.readLine().split(" ");
 
            h = Integer.parseInt(st[0]);
            w = Integer.parseInt(st[1]);
            arr = new char[h][w];
            for (int i = 0 ; i < h; i++) {
                String sub = bf.readLine();
                for (int j = 0 ; j < w;  j++ ) {
                    arr[i][j] = sub.charAt(j);
                    if (arr[i][j] == '<' || arr[i][j] == '^' || arr[i][j] == '>' || arr[i][j] == 'v' ) {
                        currx = i;
                        curry = j;
                        dir = arr[i][j] == '^' ? 0 : arr[i][j] == 'v' ? 1 : arr[i][j] == '<' ? 2 : 3;
                    }
                }
            }
//            for (int q = 0 ; q < h ; q ++) {
//                for (int z = 0 ; z < w ; z++) {
//                    System.out.print(arr[q][z]);
//                }
//                System.out.println();
//            }
 
            bf.readLine();
            String commands = bf.readLine();
            for (int i = 0 ; i < commands.length() ; i++){
                switch(commands.charAt(i)) {
                    case 'U':
                        move(0, h, w);
                        break;
                    case 'D':
                        move(1, h, w);
                        break;
                    case 'L':
                        move(2, h, w);
                        break;
                    case 'R':
                        move(3, h, w);
                        break;
                    case 'S':
                        shoot(h,w);
                        break;
                }
 
 
            }
            for (int q = 0 ; q < h ; q ++) {
                for (int z = 0 ; z < w ; z++) {
                    System.out.print(arr[q][z]);
                }
                System.out.println();
            }
 
        }
    }
}
