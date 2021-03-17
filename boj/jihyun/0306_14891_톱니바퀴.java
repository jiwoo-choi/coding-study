import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static char[][] gears;
	static int cnt = 4;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		gears = new char[cnt][];
		for(int i = 0 ; i < cnt ; i++) {
			gears[i] = in.readLine().toCharArray();
		}
		int pos, dir, score = 0, K = Integer.parseInt(in.readLine());
		StringTokenizer st;
		for(int i = 0 ; i < K ; i++) {
			st = new StringTokenizer(in.readLine()," ");
			pos = Integer.parseInt(st.nextToken())-1;
			dir = Integer.parseInt(st.nextToken());
			rotate(pos,dir);
		}
		for(int i = 0 ; i < cnt ; i++) {
			if(gears[i][0] == '1')
				score += (int)Math.pow(2, i);
			else score += 0;
		}
		System.out.println(score);
	}
	static void rotate(int pos,int dir) {
		int size = gears[pos].length;
		int left_dir = (dir == 1)? -1:1; 
		int right_dir = (dir == 1)? -1:1; 
		char first = gears[pos][0], last=gears[pos][size-1];
		char pairs[][] = {
				{gears[0][2], gears[1][6]},
				{gears[1][2], gears[2][6]},
				{gears[2][2], gears[3][6]}
		};
		//배열 당기기 - 톱니 돌리기
		if(dir == -1) {
			for(int j = 0; j < size-1 ; j++) 
				gears[pos][j] = gears[pos][j+1];
			gears[pos][size-1] = first;
		}
		else {
			for(int j = size-1; 0 < j  ; j--) 
				gears[pos][j] = gears[pos][j-1];
			gears[pos][0] = last;
		}
		//왼쪽 톱니...
		for(int left = pos-1 ; 0 <= left; left--) {
			
			if(pairs[left][0] == pairs[left][1]) break;
			if(left_dir == -1) { //반시계일 경우 
				first = gears[left][0];
				for(int j = 0; j < size-1 ; j++) 
					gears[left][j] = gears[left][j+1];
				gears[left][size-1] = first;
			}	
			else {
				//시계방향 
				last = gears[left][size-1];
				for(int j = size-1; 0 < j  ; j--) 
					gears[left][j] = gears[left][j-1];
				gears[left][0] = last;
				
			}
			left_dir = (left_dir == 1)? -1:1; 
		}
		for(int right = pos+1; right < cnt; right++) {
			if(pairs[right-1][0] == pairs[right-1][1]) break;
			if(right_dir == -1) { //반시계일 경우 
				first = gears[right][0];
				for(int j = 0; j < size-1 ; j++) 
					gears[right][j] = gears[right][j+1];
				gears[right][size-1] = first;
			}	
			else {
				//시계방향 
				last = gears[right][size-1];
				for(int j = size-1; 0 < j  ; j--) 
					gears[right][j] = gears[right][j-1];
				gears[right][0] = last;
				
			}
			right_dir = (right_dir == 1)? -1:1; 
		}
	}
}
