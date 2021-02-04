import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	//3방으로...
	static int row = 100, col = 100;
	static int[] dy = {0,-1,0};
	static int[] dx = {-1,0,+1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int[] start = new int[11];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int destx= 0, desty= 0;
		for(int ct = 1, size = start.length; ct < size ; ct++) {

			int num = Integer.parseInt(in.readLine());
			int[][] ladder = new int[row][col];
			int dir = 1;
			for(int i = 0; i < row ; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine()," ");
				for(int j = 0; j < col ; j++ ) {
					ladder[i][j]  = Integer.parseInt(st.nextToken());
					if(ladder[i][j] == 2) {
						desty = i;
						destx = j;
					}
				} 
			}
			while( 0 < desty ) 
			{
				if( dir == 0 ) { //왼쪽 방향 
					if(0 < destx+dx[0] && ladder[desty + dy[0]][destx + dx[0]] == 1) {
						desty += dy[0];
						destx += dx[0];
					}
					else {
						desty += dy[1];
						destx += dx[1];
						dir = 1;
					}
				}
				else if(dir ==2 ) { //오른쪽 방향 
					if( destx+dx[2] < 100 && ladder[desty + dy[2]][destx + dx[2]] == 1) {
						desty += dy[2];
						destx += dx[2];
					}
					else {
						desty += dy[1];
						destx += dx[1];
						dir = 1;
					}
				}
				else { //정방향 
					if( 0 < destx+dx[0] && ladder[desty + dy[0]][destx + dx[0]] == 1) dir = 0;
					else if( destx+dx[2] < 100 && ladder[desty + dy[2]][destx + dx[2]] == 1) dir = 2;
					else {
						desty += dy[1];
						destx += dx[1];
					}
				}
			}
			start[ct] = destx;			
		}
		//출력 
		for(int i = 1, size = start.length ; i < size ; i++) {
			System.out.println("#"+ i+ " " + start[i]);
		}	
	}
}
