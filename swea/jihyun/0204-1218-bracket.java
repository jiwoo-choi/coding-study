import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1 {

	static char[] stack;
	static int top; 
	static char[] open_pair = {'(', '[', '{','<'};
	public static void main(String[] args) throws IOException {
		
		int T = 10;
		int[] valid = new int[T+1];
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for( int i = 1 ; i <= T ; i++) {
			int num = Integer.parseInt(in.readLine());
			stack = new char[num];
			top = -1;
			valid[i] = 1;
			char[] input = in.readLine().toCharArray();
			
			for(int j = 0, size = input.length ; j < size ; j++) {
				if( input[j] == open_pair[0] || input[j] == open_pair[1]
					|| input[j] == open_pair[2] ||input[j] == open_pair[3]) 
					push(input[j]);
				else {
					char out = top();
					pop();
					if( input[j]-out <= 0 || 2 < input[j]-out) {
						valid[i] = 0; break;
					}
				}
			}
			if(!empty()) valid[i] = 0;
		}
		StringBuilder sb = new StringBuilder();
		for( int i = 1 ; i <= T ; i++) {
			sb.append("#");
			sb.append(Integer.toString(i));
			sb.append(" ");
			sb.append(Integer.toString(valid[i]));
			sb.append("\n");
		}
		System.out.println(sb);
	}
	private static char top() {
		return stack[top];
	}
	private static void push(char e) {
		stack[++top] = e;
	}
	private static void pop() {
		stack[top--] = 0;
	}
	private static boolean empty() {
		return (top == -1)? true:false;
	}
}
