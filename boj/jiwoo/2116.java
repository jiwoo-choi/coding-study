import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2116 {

	static class Dice {
		
		int A;
		int B;
		int C;
		int D;
		int E;
		int F;
		int nextTop;
		
		Dice(int[] inputs) {
			A = inputs[0];
			B = inputs[1];
			C = inputs[2];
			D = inputs[3];
			E = inputs[4];
			F = inputs[5];
		}
		
		public int getMax(int prevTop) {
			// 예전에 올라온 top제외.
			if (A == prevTop) {
				nextTop = F;
				return max(B,C,D,E);
			} else if (B == prevTop) {
				nextTop = D;
				return max(A,C,E,F);
			} else if (C == prevTop) {
				nextTop = E;
				return max(A,B,D,F);
			} else if (D == prevTop) {
				nextTop = B;
				return max(A,C,F,E);
			} else if (E == prevTop) {
				nextTop = C;
				return max(A,D,B,F);
			} else {
				nextTop = A;
				return max(B,C,D,E);
			}
		}
		public int getNextTop() {
			return this.nextTop;
		}
		
		private int max(int x, int y, int z, int a) {
			return Math.max(Math.max(x, y), Math.max(z, a));
		}
	}
	
	public static int max(int a, int b, int c, int d, int e, int f) {
		return Math.max(Math.max(e,f),Math.max(Math.max(a, b), Math.max(c, d)));
	}
	
	public static int go(Dice[] dices, int index, int prevTop, int t, int value) {
		if ( index == t ) return value;
		int val = dices[index].getMax(prevTop);
		return go(dices, index+1, dices[index].getNextTop(), t, value + val);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());

		Dice[] dices = new Dice[t];
		for (int i = 0 ; i < t ;i++) {
			dices[i] = new Dice(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
		}
		
		int a = go(dices, 1, dices[0].A, t, dices[0].getMax(dices[0].A));
		int b = go(dices, 1, dices[0].B, t, dices[0].getMax(dices[0].B));
		int c = go(dices, 1, dices[0].C, t, dices[0].getMax(dices[0].C));
		int d = go(dices, 1, dices[0].D, t, dices[0].getMax(dices[0].D));
		int e = go(dices, 1, dices[0].E, t, dices[0].getMax(dices[0].E));
		int f = go(dices, 1, dices[0].F, t, dices[0].getMax(dices[0].F));
		
		System.out.println(max(a,b,c,d,e,f));
	}
	

}
