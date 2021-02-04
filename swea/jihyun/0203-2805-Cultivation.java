import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		int[] values = new int[T];
		
		for(int cnt = 0; cnt < T ; cnt++) {
			//입력받기 
			int size = Integer.parseInt(in.readLine());
			int half = size/2;
			int end = 0;
			int[][] farm = new int[size][size];
			
			for(int i = 0; i < size ; i++) {
				String row = in.readLine();
				
				for(int j = 0; j < size; j++) 
					farm[i][j]= row.charAt(j) - '0';
				
				for(int idx = half - end ; idx <= half + end ; idx++) 
					values[cnt]+= farm[i][idx];
				
				end = (i < half)? end+1 : end-1;
			}
		}
		//출력 
		for(int i = 0, length = values.length ; i < length; i++) 
			System.out.println("#" + (i+1) + " " + values[i]);
	}
}
