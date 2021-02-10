package hw05_swea1223_계산기2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static Stack<Character> opStack = new Stack<>();
	static Stack<Integer> numStack = new Stack<>();
	static char[] input;
	static StringBuilder pfx = new StringBuilder();

	static void postfix() {
		for (int i = 0; i < N; i++) {
			char curCh = input[i];
			int curNum = curCh - '0';
			if (0 <= curNum && curNum <= 9)	{ // 숫자
				pfx.append(curNum);
				continue;
			}
			
			// 연산자
			while (!opStack.isEmpty()) {
				char topCh = opStack.peek();
				if (topCh <= curCh)  // '*' : 42, '+' : 43  /  '*'가 먼저 삽입될 수 있도록
					pfx.append(opStack.pop());
				else
					break;
			}
			opStack.push(curCh);
			
		}
		while (!opStack.isEmpty())
			pfx.append(opStack.pop());
	}
	
	static int calculate() {
		int len = pfx.length();
		for (int i = 0; i < len; i++) {
			char curCh = pfx.charAt(i);
			int curNum = curCh - '0';
			if (0 <= curNum && curNum <= 9) {  // 숫자
				numStack.push(curNum);
			}
			else {  // 연산자
				int b = numStack.pop();
				int a = numStack.pop();
				numStack.push(curCh == '*'? a * b : a + b);
			}
		}
		return numStack.pop();
	}
	
	static int solution() {
		postfix();
		return calculate();
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("in.txt"));
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1; tc <= 10; tc++) {
			// init
			pfx.setLength(0);
			numStack.clear();
			opStack.clear();
			
			N = Integer.parseInt(in.readLine());
			input = in.readLine().toCharArray();
			
			// print result
			System.out.println("#" + tc + " " + solution());
	    }
	}
}
