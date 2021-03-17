import java.io.*;
import java.util.*;

public class boj_2527 {

	
	static class Square {
		int sx;
		int sy;
		int fx;
		int fy;
		public Square(int sx, int sy, int fx, int fy) {
			this.sx = sx;
			this.sy = sy;
			this.fx = fx;
			this.fy = fy;
		}
	}
	
	static public void isContained(Square s1, Square s2) {
		
		// 선분1이,target 사각형에 포함되는지 안포함되는지 체크하기.
		int startx = s1.sx;
		int endx = s1.fx;
		
		int tsx = s2.sx;
		int tex = s2.fx;
		
		int starty = s1.sy;
		int endy = s1.fy;
		int tsy = s2.sy;
		int tey = s2.fy;
		
		boolean dotPossibility = false;
		boolean dotPossibility2 = false;
		boolean linePosibility = false;
		boolean linePosibility2 = false;
		
		// start가 더 큰경우. (포개지는)
		if (startx <= tsx && tex <= endx) {
			linePosibility = true;
		}
		
		// end가 더 큰 경우. (포개지는)
		if (startx >= tsx && endx <= tex) {
			linePosibility = true;
		}
		
		// 겹치는 경우.
		// startX에 겹치는 경우.
		if (startx >= tsx && startx <= tex && endx >= tex) {
			if (startx == tex) {
				dotPossibility = true;
			} else {
				linePosibility = true;
			}
		}
		
		// 겹치는 경우 2.
		// endx애 겹치는 경우
		if (endx >= tsx && endx <= tex && tsx >= startx) {
			if (tsx == endx) {
				dotPossibility = true;
			} else {
				linePosibility = true;
			}
		}
		//---------------------
		
		// start가 더 큰경우. (포개지는)
		if (starty <= tsy && tey <= endy) {
			linePosibility2 = true;
		}
		
		// end가 더 큰 경우. (포개지는)
		if (starty >= tsy && endy <= tey) {
			linePosibility2 = true;
		}
		
		// 겹치는 경우.
		// startX에 겹치는 경우.
		if (starty >= tsy && starty <= tey && endy >= tey) {
			if (starty == tey) {
				dotPossibility2 = true;
			} else {
				linePosibility2 = true;
			}
		}
		
		// 겹치는 경우 2.
		// endx애 겹치는 경우
		if (endy >= tsy && endy <= tey && tsy >= starty) {
			if (tsy == endy) {
				dotPossibility2 = true;
			} else {
				linePosibility2 = true;
			}
		}
		
		if (dotPossibility & dotPossibility2) {
			System.out.println("c");
		} else if (dotPossibility & linePosibility2 || dotPossibility2 & linePosibility ) {
			System.out.println("b");
		} else if (linePosibility2 & linePosibility){
			System.out.println("a");
		} else {
			System.out.println("d");
		}

	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		for (int i = 0 ; i < 4 ; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			int d = sc.nextInt();
			int e = sc.nextInt();
			int f = sc.nextInt();
			int g = sc.nextInt();
			int h = sc.nextInt();

			isContained(new Square(a,b,c,d), new Square(e,f,g,h));
		}
	}
}
