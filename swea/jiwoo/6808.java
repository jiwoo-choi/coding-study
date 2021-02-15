import java.io.*;
import java.util.Arrays;

public class Solution {
	
	static int[] opp;
	static int[] me;
	static boolean[] isSelected;
	static int[] num;
	static int count;
	static int totalcount;
	
	public static void perm(int cnt) {
		if (cnt == 9) {
			int meval = 0;
			int oppval = 0;
			for (int i = 0 ; i < 9 ; i++) {
				if (me[i] > num[i]) {
					meval += (num[i] + me[i]);
				} else {
					oppval += (num[i] + me[i]);
				}
			}
			
			if (meval > oppval) count++;
			totalcount++;
		} else {
			for(int i = 0 ; i < 9 ; i++) {
				if(isSelected[i] == true) continue;
				isSelected[i] = true;
				num[cnt] =opp[i];
				perm(cnt+1);
				isSelected[i] = false;
			}
		}
	}
	public static void main(String args[]) throws Exception
	{
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		
		for (int test_case = 1; test_case <= t; test_case++) {
			me = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			opp = new int[9];
			num = new int[9];
			isSelected = new boolean[9];
			
			int selected[] = new int[18];
			int pos = 0;
			for( int ele : me) {
				selected[ele-1] = 1;
			}
			
			for (int i = 1 ; i <= 18 ; i++) {
				if (selected[i-1] == 0) {
					opp[pos++] = i;
				}
			}
			perm(0);
			System.out.println("#" + (test_case) + " " + count + " " + (totalcount-count));
			count = 0 ;
			totalcount = 0;
		}
	}
}
