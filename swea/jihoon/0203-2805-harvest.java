package swea2805;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	
	static int curDir;
    // >, v, <, ^
    static int[] dr = new int[]{0, 1,  0, -1};
    static int[] dc = new int[]{1, 0, -1,  0};
    
    static final int MAX_N = 55;
    static int N;
    static int[][] board = new int[MAX_N][MAX_N];
    
    static class Pair {
    	int first, second;
    	Pair(int first, int second) {
    		this.first = first;
    		this.second = second;
    	}
    }
    
	static int solution() {
		int sum = 0;
		Queue<Pair> q = new LinkedList<>();
		int start = N/2;
		q.add(new Pair(start, start));
		sum += board[start][start];
		board[start][start] = -1;
		
		int limit = N/2;
		int cnt = 0;
		while (cnt < limit) {
			
			int qSize = q.size();
			for (int qs = 0; qs < qSize; qs++) {
				int r = q.peek().first;
				int c = q.peek().second;
				q.poll();
				
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == -1) {
						continue;
					}
					
					sum += board[nr][nc];
					board[nr][nc] = -1;
					
					q.add(new Pair(nr, nc));
				}
			}
			cnt++;
		}
		return sum;
	}
    
	public static void main(String[] args) throws Exception {
       System.setIn(new FileInputStream("in.txt"));
        Scanner sc = new Scanner(System.in);
        
        int testcase = sc.nextInt();
        for (int tc = 1; tc <= testcase; tc++) {
            N = sc.nextInt();
            for (int i = 0 ; i < N; i++) {
            	String str;
            	str = sc.next();
            	for (int j = 0; j < N; j++) {
            		board[i][j] = str.charAt(j) - '0';
            	}
            }
            
            System.out.println("#"+ tc + " " + solution());
        }
	}
}

