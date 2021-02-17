import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	private static int N;
	private static char[][] input;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		input = new char[N][];
		
		for(int i = 0; i < N ;i++) {
			String s = in.readLine();
			input[i] = s.toCharArray();
		}
		String ret = zip(N, 0, 0);
		System.out.println(ret);
	}
	private static String zip(int size, int startR, int startC) {
		if(size == 1) {
			if( input[startR][startC] == '1') return "1";
			return "0";
		}
		int half = size/2, nextR = startR, nextC = startC;
		String ret = "";
		ret += zip(half,nextR,nextC);
		ret += zip(half,nextR,nextC + half);
		ret += zip(half,nextR+half,nextC);
		ret += zip(half,nextR+half,nextC+half);
		
		if(ret.equals("0000")) return "0";
		else if(ret.equals("1111")) return "1";
		else return ret = "(" + ret + ")";
	}
}
