package practice.card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int[] G = new int[9];
	static int[] I = new int[9];
	static int N = G.length;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// input 입력 
		int T = Integer.parseInt(in.readLine());
		for(int i = 1; i <=  T ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int win = 0,loss = 0, flag = 0;
			// 규영's
			for(int j = 0; j < 9; j++) {
				G[j] = Integer.parseInt(st.nextToken());
				flag = flag | 1 << G[j];
			}
			// 인영's
			int k = 0;
			for(int j = 1 ; j <= 18 ; j++) {
				if((flag & 1<< j)== 0) I[k++] = j;
			}
			// 계산 
			Arrays.sort(I);
			do {
				int gScore = 0,iScore = 0;
				for(int cnt = 0; cnt < N ; cnt++) {
					int sum = I[cnt] + G[cnt];
					if(I[cnt] < G[cnt]) gScore+= sum;
					else if(I[cnt] > G[cnt]) iScore+= sum;
				}
				if(gScore < iScore) loss++;
				else if(gScore > iScore) win++;
			}while(np());
			sb.append("#");
			sb.append(i);
			sb.append(" ");
			sb.append(win);
			sb.append(" ");
			sb.append(loss);
			sb.append("\n");
		}
		System.out.print(sb);
	}
	private static boolean np() {
		int temp = 0;
		int i = N-1;
		while(0 < i && I[i-1] >= I[i]) i--;
		
		if(i == 0) return false;
		
		int j = N-1;
		while(I[j] <= I[i-1]) j--;
		temp = I[j];
		I[j] = I[i-1];
		I[i-1] = temp;
		
		int k = N-1;
		while(k > i) {
			temp = I[k];
			I[k] = I[i];
			I[i] = temp;
			i++; k--;
		}
		return true;
	}
}
