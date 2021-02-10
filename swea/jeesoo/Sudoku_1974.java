/**
 * 입력처리
 * 한 행에 1~9 숫자가 한 번씩만 사용되었는지 확인 (스도쿠가 정상적인지 체크 해야함)
 * 3*3 구획
 */



package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sudoku_1974 {

	private static int[][] m;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		m = new int[9][9]; // 한 번만 만들어서 재사용 하자. 어차피 덮어 쓸거니까
		for (int testcase = 1; testcase <= TC; testcase++) {
			for (int i = 0; i < 9; i++) {
				String s = br.readLine();
				for (int j = 0, index=0; j < 9; j++,index+=2) {
					m[i][j]=s.charAt(index)-'0';
				}
			}
			sb.append("#").append(testcase).append(" ").append(check()).append("\n");
		}//TC
		System.out.print(sb);
	}//Main

	/**해당 스도쿠가 정상인지 오류인지 확인하는 메서드*/
	public static int check() {
		for (int i = 0; i < 9; i++) {
			if(!checkRow(i)||!checkCol(i)) {// 정상이면 true/오류면 false
				 return 0; // 오류
			}
		}
		for (int i = 0; i < m.length; i+=3) { // 0 3 6
			for (int j = 0; j < m.length; j+=3) { // 0 3 6
				if (!checkBlock(i,j)) {
					return 0;
				}
			}
		}
		return 1; // 정상
	}
	
	static boolean[] flag = new boolean[10]; // 0번방은 안씀
	/**3*3블럭의 값이 유효한지 체크, 정상이면 true 리턴, 오류면 false 리턴*/
	public static boolean checkBlock(int r, int c) {
		Arrays.fill(flag, false);
//		for (int i = 0; i < flag.length; i++) {
//			flag[i] = false;
//		} // 이게 Arrays.fill 보다 빠르긴함
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (flag[m[r+i][c+j]]) {
					return false; // 오류
				}
				flag[m[r+i][c+j]] = true;
			}
		}
		return true; // 정상
	}

	/**c 열의 값이 유효한지 체크, 정상이면 true 리턴, 오류면 false 리턴*/
	public static boolean checkCol(int c) {
		boolean[] flag = new boolean[10]; // 0번방은 안씀
		for (int i = 0; i < 9; i++) {
			if (flag[m[i][c]]) {
				return false; // 오류
			}
			flag[m[i][c]] = true;
		}
		
		return true; //정상
	}

	/**r 행의 값이 유효한지 체크, 정상이면 true 리턴, 오류면 false 리턴*/
	public static boolean checkRow(int r) {
		boolean[] flag = new boolean[10]; // 0번방은 안씀
		for (int i = 0; i < 9; i++) {
			if (flag[m[r][i]]) {
				return false; // 오류
			}
			flag[m[r][i]] = true;
		}
		
		return true; //정상
	}
}//Class
