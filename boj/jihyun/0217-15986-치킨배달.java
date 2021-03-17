import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int[] comb;
	static int h, c, num;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] house = new int[2*N+1][2];
		int[][] chicken = new int[13+1][2];
		int[][] distance;
		int min = Integer.MAX_VALUE; //실제 집과 치킨집이 얼마나 있는지 담는 변수 & 치킨거리 합.
		boolean flag = false;
		//입력받기 
		for(int i = 0; i < N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0; j < N; j++) {
				 int input = Integer.parseInt(st.nextToken());
				 if( input == 1) {
					 house[h][0] = i; 
					 house[h][1] = j;
					 h++;
				 }
				 else if(input ==2) {
					 chicken[c][0] = i; 
					 chicken[c][1] = j;
					 c++;
				 }
			}
		}
		//처리 
		comb = new int[c]; 
		num = c-M;
		for(int i = c-1 ; num <= i; i--) comb[i] = 1;
		distance = new int[h][c];	
		for(int i = 0; i < h ; i++) {
			for(int j = 0; j < c; j++) {
				 distance[i][j] = ( Math.abs(house[i][0] - chicken[j][0]) 
						 			+ Math.abs(house[i][1] - chicken[j][1]));
			}
		}
		do {
			int temp = 0;
			for(int i = 0; i < h ; i++) {
				int min_d = Integer.MAX_VALUE;
				for(int j = 0 ; j< c ; j++) {
					if(comb[j] == 1) {
						min_d = (min_d < distance[i][j])? min_d: distance[i][j];
						flag = true;
					}
				}
				temp = (flag)? temp + min_d : 0; 
			}
			min = (temp < min)? temp: min;
		}while(comb());
		
		System.out.println(min);
	}
	private static boolean comb() {
		int i = c-1;
		while(0 < i && comb[i] <= comb[i-1]) i--; 
		
		if(i == 0) return false;
		
		int j = c-1;
		while(comb[i-1] >= comb[j]) j--; 
		int temp = comb[j];
		comb[j] = comb[i-1];
		comb[i-1] = temp;
		
		int k = c-1;
		while(i < k) {
			 temp = comb[i];
			 comb[i] = comb[k];
			 comb[k] = temp;
			 i++;
			 k--;
		}
		return true;
	}
}
