import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.*;
import java.io.*;

public class Main {

	/// 총 길이
	static int N;
	/// 제한길이
	static int L;
	/// 주어진 길이.
	static char[] list;
	
	static void dfs(int idx, int jaumCnt, int moumCnt, String concated, int cnt) {

		if (cnt == L) {
//			System.out.println(idx + " " + cnt + " " + jaumCnt + " " + moumCnt);
			if (jaumCnt <= 0 && moumCnt <= 0) {
				System.out.println(concated);
			}
			return;
		}
		
		if (idx == N) {
			return;  // 아무수확없이 도달만했다.
		}
		
		// 현재가 자음이면..
		if (list[idx] == 'a' || list[idx] == 'e' || list[idx] == 'i' || list[idx] == 'o' || list[idx] == 'u' ) {
			dfs(idx+1, jaumCnt-1, moumCnt, concated + list[idx], cnt+1);
		} else { // 현재가 모음이면.. 
			dfs(idx+1, jaumCnt, moumCnt-1, concated + list[idx], cnt+1);
		} 
		dfs(idx+1, jaumCnt, moumCnt, concated, cnt);
	}
	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		//4 6
		//a t c i s w
		int inputs[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		L = inputs[0];
		N = inputs[1];
		
		list = Arrays.stream(bf.readLine().split(" "))
				.reduce("" , (p, c) -> {
					return p + c;
				}).toCharArray();
				
		
		Arrays.sort(list);
		
		dfs(0, 1, 2, "", 0);
	}

}
