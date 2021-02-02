package hw02_swea1954;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    static int curDir; // 현재 방향

    // >, v, <, ^
    static int[] dr = new int[]{0, 1,  0, -1};
    static int[] dc = new int[]{1, 0, -1,  0};
    
    static final int MAX_N = 11;
    static int N;
    static int[][] board = new int[MAX_N][MAX_N];
    static boolean[][] visited = new boolean[MAX_N][MAX_N];
    
    static void solution() {
        int r = 0, c = -1; // (0, 0) 부터 시작하기 위한 초기화

        for (int k = 1; k <= N*N; k++) {
            int nr = r + dr[curDir];
            int nc = c + dc[curDir];
            
            if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) {
                curDir = (curDir + 1) % 4;
                k--;
                continue;
            }

            visited[nr][nc] = true;
            board[nr][nc] = k;
            r = nr;
            c = nc;
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        Scanner sc = new Scanner(System.in);
        
        int testcase = sc.nextInt();
        for (int tc = 1; tc <= testcase; tc++) {
            curDir = 0;
            for (int i = 0; i < MAX_N; i++)
                Arrays.fill(visited[i], false);
            
            N = sc.nextInt();
            solution();
            
            System.out.println("#"+ tc);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                    System.out.print(board[i][j] + " ");
                System.out.println();
            }
        }
    }
}
