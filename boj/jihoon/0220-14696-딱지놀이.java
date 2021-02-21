import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int[][] board = new int[5][5];
	static HashMap<Integer, Integer> map = new HashMap<>();
	static int[][] visited = new int[5][5];
	static int[] order = new int[26];
	
	static int N;
	
	static int bingoCount() {
		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			int hSum = 0, vSum = 0, dSum = 0;
			for (int j = 0; j < 5; j++) {
				hSum += visited[i][j];
				vSum += visited[j][i];
			}
			if (hSum == 5) cnt++;
			if (vSum == 5) cnt++;
		}
		int dSum = 0, rSum = 0;
		for (int i = 0; i < 5; i++) {
			dSum += visited[i][i];
			rSum += visited[i][4-i];
		}
		if (dSum == 5) cnt++;
		if (rSum == 5) cnt++;
		return cnt;
	}
	
	static int solution() {
		for (int i = 1; i <= 25; i++) {
			int curOrder = order[i];
			int value = map.get(curOrder);
			int r = value / 5;
			int c=  value % 5;
			visited[r][c] = 1;
			if (bingoCount() >= 3) return i;
		}
		return 25;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < 5; j++)
				map.put(Integer.parseInt(st.nextToken()), i*5 + j);
		}
		
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < 5; j++)
				order[i*5+j+1] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solution());
	}

}
