import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main {
	static int N,r,c,num;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int size = (int) Math.pow(2, N);
		divide(size, 0,0,0);
		System.out.println(num);
	}
	private static void divide(int size, int startR, int startC, int first) {
		if(N == 1) {
			if(r == startR) {
				if(c == startC) num = first;
				else num = first+1;
			}
			else {
				if(c == startC) num = first+2;
				else num = first+3;
			}
			return;
		}
		int half = size/2, nextSR = startR, nextSC = startC;
		int element = (int) Math.pow(4, --N);
		if(r < startR+ half ) { //사각형을 4등분했을 때 위에 두개 
			if(startC+half <= c) {
				nextSC += half;
				first += element;
			}
		}
		else { //4등분 밑에 두 사각형 
			if(c<startC+half) { //왼쪽 밑 사각형   
				nextSR += half;
				first += 2 * element;
			}
			else { //오른쪽 밑 사각형 
				nextSR += half;
			    nextSC += half;
				first += 3 * element;
			}
		}
		divide(half, nextSR, nextSC, first);
	}
}
