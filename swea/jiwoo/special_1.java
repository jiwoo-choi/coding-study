import java.io.*;
import java.util.*;

public class im_1 {

	
	static int arr[][];
	static int[] group;
	static int R, C;
	
	private static void init() {
		arr = new int[R][C];
		group = new int[11];
	}
	
	private static boolean isPaintable(int sx, int sy, int fx, int fy, int val) {
		for (int i = sx; i <= fx ; i++) {
			for (int j = sy; j <= fy ; j++) {
				if (arr[i][j] > val) return false;
			}
		}
		return true;
	}
	
	private static void paint(int sx, int sy, int fx, int fy, int val) {	
//		
		if (!isPaintable(sx,sy,fx,fy,val)) {
//			System.out.println(sx + " " + sy + " " + fx + " " + fy);
			return;
		}
//		
		for (int i = sx; i <= fx ; i++) {
			for (int j = sy; j <= fy ; j++) {
				arr[i][j] = val;
			}
		}
	}
	
	static class Pair {
		int x;
		int y;
		Pair (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	
	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		
		for (int test_case = 1 ; test_case <= t ; test_case++) {
			
			int[] inputs = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int n = inputs[0];
			int m = inputs[1];
			int k = inputs[2];
			R = n;
			C = m;
			
			init();
			
			for (int i = 0 ; i < k ; i++) {
				int[] inputs2 = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				paint(inputs2[0], inputs2[1], inputs2[2], inputs2[3], inputs2[4]);
			}
			
			for (int i = 0 ; i < R ; i++) {
				for (int j = 0 ; j < C ; j++) {
					group[arr[i][j]]++;
				}
			}
			
			System.out.println("#" + test_case + " " + Arrays.stream(group).max().getAsInt());

		}
		
	}

}
