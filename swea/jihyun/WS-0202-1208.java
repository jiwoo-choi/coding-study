import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int min, max, row = 100;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] height = new int[10];
		
		for(int ct = 0, size = height.length ; ct < size ; ct++) {
			
			min = 0; max = 0;
			int num = Integer.parseInt(in.readLine());
			int[] box = new int[100];
			
			StringTokenizer st = new StringTokenizer(in.readLine()," ");
			for(int i = 0; i < row ; i++) {
				 box[i] = Integer.parseInt(st.nextToken());
				 min = (box[i] < box[min])? i : min;
				 max = (box[i] > box[max])? i : max;
			}
			
			dump(box, num);
			height[ct] = box[max] - box[min];  
			
		}
		//출력 
		for(int i = 0, size = height.length ; i < size ; i++) {
			System.out.println("#"+ (i+1) + " " + height[i]);
		}
		
	}
	public static void dump(int[] box, int num) {
		if(box[max] - box[min] <= 1 || num == 0)
			return ;
		box[max]--;
		box[min]++;
		num--;
		for(int i = 0; i < row ; i++) {
			 min = (box[i] < box[min])? i : min;
			 max = (box[i] > box[max])? i : max;
		}
		dump(box,num);
	}
}
