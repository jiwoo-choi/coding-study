import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

public class Main {	
	
	/// target row
	static int tr;
	/// target col
	static int tc;
	
	public static int go(int r, int c, int len2, int startValue) {

        if (len2 == 0) {
			return startValue;
		}
		
		int len = (int) Math.pow(2, len2);
		int midRow = r + len/2;
		int midCol = c + len/2;		
		int each = (int) Math.pow(4, len2-1);
		if ( tr < midRow && tc < midCol ) {
			return go(r, c, len2-1 , startValue);
		} else if ( tr < midRow && tc >= midCol) {
			return go(r, midCol, len2-1, each * 1 + startValue);
		} else if ( tr >= midRow && tc < midCol) {
			return go(midRow, c, len2-1, each * 2 + startValue);
		} else {
			return go(midRow, midCol, len2-1, each * 3 + startValue);
		}		
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		tr = sc.nextInt();
		tc = sc.nextInt();
		System.out.println(go(0, 0,n, 0));
	}

}
