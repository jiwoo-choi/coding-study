import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <=t ; test_case++) {
			int inputs[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int n = inputs[0];
			int k = inputs[1];

			char[] arr = bf.readLine().toCharArray();
			Deque<Character> arrd = new LinkedList<Character>();
			for (char ch : arr) {
				arrd.addLast(ch);
			}
		
			Set<Integer> set = new TreeSet<Integer>();
			
			// 총 3회전을 돌린다.
			for (int i = 0 ; i < (n/4) ; i++) {
				int idx = 0;
				String s = "";
				for (char ch : arrd) {
					if (idx % (n / 4) == 0) {
						if (!s.equals("")) {
							set.add(Integer.parseInt(s, 16));
							s = "";
						}
					}
					s += ch;
					idx++;
				}
				set.add(Integer.parseInt(s, 16));
				arrd.addFirst(arrd.pollLast());
			}

			
			System.out.println("#" + test_case + " " + set.toArray()[set.size() -  k ]);
			
		}

	}

}
