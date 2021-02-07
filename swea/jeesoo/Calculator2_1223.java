/**
 * 1. 중위표기식 -> 후위표기식 변환
 * 2. 후위표기식 계산
 */

package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Calculator2_1223 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {

			int N = Integer.parseInt(br.readLine());

			/** 문자열 받아와서 char 배열에 넣어주기 */
			char[] c = br.readLine().toCharArray();

			/** 후위표기식으로 변경 */
			// 변경한 char 배열을 담을 ArrayList 생성
			ArrayList<Character> cal = new ArrayList<Character>();
			Stack<Character> op = new Stack<Character>(); // 연산자만 담는 스택

			for (int i = 0, size = c.length; i < size; i++) {
				if (c[i] == '+') {
					if (op.isEmpty())
						op.push(c[i]);
					else {
						while (!op.isEmpty()) {
							cal.add(op.pop());
						}
						op.push(c[i]);
					}
				} else if (c[i] == '*') {
					if (!op.isEmpty()) {
						if (op.peek() == '+') {
							op.push(c[i]);
						} else {
							cal.add(op.pop());
							op.push(c[i]);
						}
					}
					else {
						op.push(c[i]);
					}
				} else {
					cal.add(c[i]);
				}
			}
			while (!op.isEmpty()) {
				cal.add(op.pop());
			}

//			 System.out.println(cal);

			/** 후위 표기식 계산 */

			Stack<Integer> result = new Stack<Integer>();

			int n1 = 0;
			int n2 = 0;
			for (int i = 0, size = cal.size(); i < size; i++) {
				if (cal.get(i) == '+') {
					// n1 = result.pop()-'0'; // 이렇게 하면 오류. 이미 내가 push 할 때 int로 바꿔서 넣어줬음
					n1 = result.pop();
					n2 = result.pop();
					result.push(n2 + n1);
				} else if (cal.get(i) == '*') {
					n1 = result.pop();
					n2 = result.pop();
					result.push(n2 * n1);
				} else {
					result.push(cal.get(i) - '0');
				}
			}
			
			sb.append("#").append(tc).append(" ").append(result.pop()).append("\n");
		}//TC
		
		System.out.println(sb);
	}//Main
}//Class
