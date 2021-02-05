package swea3499퍼펙트_셔플;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static ArrayList<String> list1 = new ArrayList<>();
	static ArrayList<String> list2 = new ArrayList<>();
	
	static int N;
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("in.txt"));
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int testcase = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= testcase; tc++) {
			StringBuilder sb = new StringBuilder();
			
			list1.clear();
			list2.clear();
			
			
			N = Integer.parseInt(in.readLine());
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			
			int half = N/2;
			if (N % 2 == 1) { // 개수가 홀수면 1추가
				half++;
			}
			for (int i = 0; i < half; i++) {
					list1.add(st.nextToken());
			}
			for (int i = half; i < N; i++) {
				list2.add(st.nextToken());
			}
			
			sb.append("#").append(tc).append(" ");
			int len = list2.size();
			for (int i = 0; i < len; i++) {
				sb.append(list1.get(i)).append(" ");
				sb.append(list2.get(i)).append(" ");
			}
			if (list1.size() > len) {
				sb.append(list1.get(list1.size()-1));
			} else
				sb.setLength(sb.length()-1);
			
			System.out.println(sb);
		}
	}
}
