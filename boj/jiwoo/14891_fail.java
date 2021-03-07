import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class boj_14891 {

	static int[] rotatenum;
	
	public static int get2nd(int idx) {
		int getCur2nd = (rotatenum[idx] + 2) % 8;
		if (getCur2nd < 0) { getCur2nd = 8 + getCur2nd; }
		return getCur2nd;
	}
	
	public static int get6th(int idx) {
		int val = get2nd(idx);
		if (val < 4) {
			return val + 4;
		} else {
			return 7 - val;
		}
	}
	
	public static int get0th(int idx) {
		int getCur0th = (get2nd(idx) - 2) % 8;
		if (getCur0th < 0) { getCur0th = 8 + getCur0th; }
		return getCur0th;
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		char[] t1 =  bf.readLine().toCharArray();
		char[] t2 =  bf.readLine().toCharArray();
		char[] t3 =  bf.readLine().toCharArray();
		char[] t4 =  bf.readLine().toCharArray();
		
		char[][] ts = new char[4][];
		
		ts[0] = t1;
		ts[1] = t2;
		ts[2] = t3;
		ts[3] = t4;
		
		rotatenum = new int[]{0, 0, 0, 0};
		
		// 3번과 7번이 바로 맞닿는 부분임.
		
		int t = Integer.parseInt(bf.readLine());
		for (int i = 0 ; i < t ; i++) {
			
			int[] test = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			int toeIdx = test[0] - 1;
			int dmove = -test[1];
						
			// 업데이트 내용 반영..
			List<int[]> updateList = new LinkedList<>();
			
			int update = dmove;
			
			updateList.add(new int[] {toeIdx, update});
			
			for (int j = test[0]-1; j > 0 ; j--) {
				char leftSide = ts[j][get6th(j)]; // 나의 left를 비교.
				char rightSide = ts[j-1][get2nd(j-1)]; // 너의 right side
				if (leftSide != rightSide) {
					System.out.println("left");
					updateList.add(new int[] {j-1, -update});
					update = -update;
				} else {
					break;
				}
			}
						
			update = dmove;

			for (int j = test[0]-1; j < 3 ; j++) {
				char leftSide = ts[j][get2nd(j)]; // 나의 right와
				char rightSide = ts[j+1][get6th(j+1)]; // 너의 left를 
				if (leftSide != rightSide) {
					System.out.println("right");
					updateList.add(new int[] {j+1, -update});
					update = -update;
				} else {
					break;
				}
			}
			updateList.forEach( (v) -> {
				rotatenum[v[0]] += v[1];
			});		
			System.out.println(Arrays.toString(rotatenum));

		}
		
		int answer = 0;
		int upd = 1;
		for(int j = 0 ; j < 4 ; j++) {
			System.out.println(rotatenum[j]);
			if (ts[j][get0th(j)] == '1') {
				answer += upd;
			}
			upd *= 2;
		}
		System.out.println(answer);
	}

}
