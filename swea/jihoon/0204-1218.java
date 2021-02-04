import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
	
	static int N;
	static String str;

	static Stack<Integer> st = new Stack<>();
	static char[] open = new char[] {'[', '{', '(', '<'};
	static char[] close = new char[] {']', '}', ')', '>'};
	
	static int solution() {
		for (int i = 0; i < N; i++) {
			char ch = str.charAt(i);
			
			boolean isOpen = false;
			for (int j = 0; j < 4; j++) {
				if (ch == open[j]) {
					st.push(j);
					isOpen = true;
					break;
				}
			}
			if (isOpen)
				continue;
			
			if (st.isEmpty())
				return 0;

			int top = st.peek();
			if (close[top] == ch) {
				st.pop();
				continue;
			}
			return 0;
		}
		if (!st.isEmpty())
			return 0;
		
		return 1;
	}
	
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		int testcase = 10;
		for (int tc = 1; tc <= testcase; tc++) {
			st.clear();
			
			N = scanner.nextInt();
			str = scanner.next();
			
			System.out.printf("#%d %d\n", tc, solution());
		}
	}

}
