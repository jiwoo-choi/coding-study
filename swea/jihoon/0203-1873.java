package swea1873;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
    static int[] dr = new int[]{0, 1,  0, -1};
    static int[] dc = new int[]{1, 0, -1,  0};
    static char[] dirMarks = new char[]{'>', 'v', '<',  '^'};
    static char[] dirCmds = new char[]{'R', 'D', 'L',  'U'};
    
    static final int MAX_N = 22;
    static int N, M, K;
    static String cmds;
    static char[][] board = new char[MAX_N][];
	static int curDir, r, c;
    
    static void move() {
    	int nr = r + dr[curDir];
    	int nc = c + dc[curDir];
    	
    	if (0 <= nr && nr < N && 0 <= nc && nc < M && board[nr][nc] == '.') {
    		board[r][c] = '.'; // 기존에 전차가 있던 위치를 평지로 변경  
    		board[nr][nc] = dirMarks[curDir]; // 전차 이동
    		r = nr; c = nc; // 전차 위치 업데이트 
    	}
    	else {
    		board[r][c] = dirMarks[curDir];
    	}
    }

    static void shoot() {
    	int nr = r;
    	int nc = c;
    	while (true) {
    		nr += dr[curDir];
    		nc += dc[curDir];
    		
    		if (nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == '#')
    			break;
    		
    		// 벽돌
    		if (board[nr][nc] == '*') { 
    			board[nr][nc] = '.';
    			break;
    		}
    	}
    	
    }
	
    static void doCmd(char cmd) {
		if (cmd == 'S') {
			shoot();
			return;
		} 
		for (int k = 0; k < 4; k++) { 
			if (cmd == dirCmds[k]) {
				curDir = k;
				move();
				return;
			}
		}
    }
    
	static void solution() {
		for (int i = 0; i < cmds.length(); i++)
			doCmd(cmds.charAt(i));
	}
    
	public static void main(String[] args) throws Exception {
       System.setIn(new FileInputStream("in.txt"));
        Scanner sc = new Scanner(System.in);
        
        int testcase = sc.nextInt();
        for (int tc = 1; tc <= testcase; tc++) {
            // init
            N = sc.nextInt(); M = sc.nextInt();
            
            boolean tankFound = false;
            for (int i = 0 ; i < N; i++) {
            	board[i] = sc.next().toCharArray();
            	
            	for (int j = 0; j < M && !tankFound; j++) {
            		char ch = board[i][j];
            		for (int k = 0; k < 4; k++) {
            			if (ch == dirMarks[k]) {
            				curDir = k;
            				r = i; c = j;
            				tankFound = true;
            				break;
            			}
            		}
            	}
            }
            K = sc.nextInt();
            cmds = sc.next();
            
            // simulate
            solution();
            
            // print
            System.out.print("#"+ tc + " ");
            for (int i = 0; i < N; i++) {
            	for (int j = 0; j < M; j++) {
            		System.out.print(board[i][j]);
            	}
            	System.out.println();
            }
        }
	}
}
