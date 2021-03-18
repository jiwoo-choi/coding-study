package startlink.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static boolean[] visit;
	static int input[][];
	static int startT[];
	static int N, half;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		half = N/2;
		StringTokenizer st = null;
		input = new int[N][];
		visit = new boolean[N];
		startT = new int[N/2];
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			input[i] = new int[N];
			for(int j = 0 ; j < N ; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0,0, 0);
		System.out.println(min);
	}
	private static void comb(int start, int pos, int cnt) {
		if( cnt == half ) {
			int idx = 0;
			int link[] = new int[half];
			for(int i = 0 ; i< N; i++) {
				if(!visit[i]) link[idx++] = i;
			}
			int temp = gap(link);
			min = (temp < min) ? temp:min;
			return;
		}
		for(int i = start ; i < N ; i++) {
			startT[pos] = i;
			visit[i] = true;
			comb(i+1, pos+1, cnt+1);
			visit[i] = false;
		}
	}
	private static int gap(int[] link) {
		int sScore = 0;
		int lScore = 0;
		
		for(int i = 0 ; i < half ; i++) {
			for(int j = 0; j < half ; j++) {
				int from = startT[i];
				int to =  startT[j];
				if(from == to) continue;
				sScore += input[from][to];
			}
		}
		for(int i = 0 ; i < half ; i++) {
			for(int j = 0; j < half ; j++) {
				int from = link[i];
				int to =  link[j];
				if(from == to) continue;
				lScore += input[from][to];
			}
		}
		return Math.abs(lScore - sScore);
	}
	
}
