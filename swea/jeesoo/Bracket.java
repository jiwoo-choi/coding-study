package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Bracket {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 이 문장을 아래 TC for문에 넣으면 왜 안되는거지?
		
		for(int t = 1;t<=10;t++) {
			int length = Integer.parseInt(br.readLine());
			String str = br.readLine();
			int check=1;
			
			Stack<Character> stack = new Stack<Character>();
			HashMap<Character, Character> map = new HashMap<Character, Character>();
			
			map.put('(', ')');
			map.put('[', ']');
			map.put('{', '}');
			map.put('<', '>');
			
			for (int i = 0; i < length; i++) {
				char c = str.charAt(i);
				if(map.keySet().contains(c)) {
					stack.push(c);
				}
				else {
					if(stack.isEmpty()) { 
						stack.push(c);
						check=0;
						break;
					}
					if(map.get(stack.pop())==c) {
						continue;
					}
					else{
						stack.push(c);
						check=0;
						break;
					}
				}
			}

//			System.out.println(check);
//			System.out.println(stack.toString());
			
			System.out.println("#"+t+" "+check);
			
		}//end of TestCase
	}//end of Main
}
