import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		Stack<int[]> stack = new Stack<int[]>();
		int laser[] = new int[num+1];
		
		for(int i = 1; i<= num ; i++) {
			int temp = Integer.parseInt(st.nextToken());
			for(int j = 1 ; j <= i ;j++) {
				if(stack.empty()) {
					stack.push(new int[] {i,temp});
					laser[i] = 0;
					break;
				}
				else {
					int[] pre = stack.pop();
					if( pre[1] < temp ) continue;
					else if( pre[1] > temp ) {
						stack.push(pre);
						laser[i] = pre[0];
					}
					stack.push(new int[] {i,temp});
					break;
				}
			}
		}
		for(int i = 1; i<= num ; i++) 
			System.out.print(laser[i] + " ");
		
	
	}

}
