import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	static int size;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(in.readLine());
		int[][][] snailArr = new int[num][][];
		
		for(int i = 0 ; i < num ; i++) {
			size = Integer.parseInt(in.readLine());
			snailArr[i] = new int[size][size];
			snail(snailArr[i], 0, 0, 1);
		}
		for(int i = 0 ; i < num ; i++) {
			System.out.println("#"+(i+1));
			print(snailArr[i]);
		}
	}
	private static void snail(int[][] snailArr, int iter, int dir, int idx) {
		if(idx > size*size) return;
		int fix = size-1-iter;
		switch(dir) {
			case 0 : { //왼 -> 오
				for( int j = iter ; j < fix; j++,++idx) 
					snailArr[iter][j] = idx;
				if(idx == (size * size)) snailArr[iter][iter] = idx++; //홀수일 경우 빠져나오기 위함 
				dir = 1;
				break;
			}
			case 1 : { //위 -> 아래 
				for( int i = iter; i < fix; i++,++idx) 
					snailArr[i][fix] = idx;
				dir = 2;
				break;
			}
			case 2 : { //오 -> 왼    
				for( int j = fix ; iter < j ; j--,++idx) 
					snailArr[fix][j] = idx;
				dir = 3;
				break;
			}
			case 3 :{ //아 -> 위  
				for( int i = fix ; iter < i ; i--,++idx) 
					snailArr[i][iter] = idx;
				dir = 0;
				iter++;
				break;
			}
		}
		snail(snailArr, iter, dir, idx); 
	}
	private static void print(int[][] snailArr) {
		int size = snailArr[0].length;
		
		for(int i = 0 ; i < size ; i++) {
			for(int j = 0 ; j < size ; j++) 
				System.out.print(snailArr[i][j] + " ");
			System.out.println();
		}
	}

}
