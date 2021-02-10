package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ColoredPaper_2563 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		boolean[][] map = new boolean[100][100];
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int num = Integer.parseInt(br.readLine());
		
		for (int k =0 ; k < num; k++) {
			st = new StringTokenizer(br.readLine());
			int y=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			
			for (int j = y; j < y+10; j++) {
				for (int i = x; i < x+10; i++) {
					map[j][i]=true;
				}
			}
		}
		
		int count=0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j]) {
					count++;
				}
			}
		}
		System.out.println(count);
	}//Main
}//Class
