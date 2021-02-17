import java.io.*;
import java.util.*;

public class Main {

	
	static char arr[][];
	
	static class SB {
		boolean result = true;
		String prev = null;
		
		SB equals(String s) {
			
			if (s.length() != 1) { this.result = false; }
			
			if ( result ) {
				if (prev == null) {
					prev = s;
				} else {
					result = prev.equals(s);
					prev = s;
				}
			}
			return this;
		}
		boolean eval() {
			return result;
		}
	}
	
	public static String go(int x, int y, int len) {
				
		
		if (len == 1) {
			return Character.toString(arr[x][y]); 
		}		

		int midx = x + len/2;
		int midy = y + len/2;
		
		
		String a = go(x, y, len/2);
		String b = go(x, midy, len/2);
		String c = go(midx, y, len/2);
		String d = go(midx, midy, len/2);
		
		boolean evaled = (new SB()).equals(a).equals(b).equals(c).equals(d).eval();
		
		if (evaled) {
			return a;
		} else {
			return "(" + a + b + c + d + ")";
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());
		arr = new char[t][];
		for(int i = 0 ; i < t ; i++) {
			arr[i] = bf.readLine().toCharArray();
		}
		
		System.out.println(go(0, 0, t));
	}

}
