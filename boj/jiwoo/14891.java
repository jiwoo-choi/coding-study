import java.util.*;
import java.io.*;

public class boj_14891_2 {

	
	public static int getSecond(int idx) {
		return (idx + 2) % 8;
	}
	
	public static int getSixth(int idx) {
		return (idx + 6) % 8;
	}
	
	public static void main(String[] args) throws IOException {
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
		
		
		Deque<Integer> dq1 = new LinkedList<>();
		Deque<Integer> dq2 = new LinkedList<>();
		Deque<Integer> dq3 = new LinkedList<>();
		Deque<Integer> dq4 = new LinkedList<>();
		List<Deque<Integer>> dqlist = new LinkedList<>();

		// index를 넣어준다.
		// index를 가장 첫번째 있는거 기준으로 알면 되니까 속편함 ㅎㅎ;
		for (int i = 0 ; i < 8 ; i++) {
			dq1.add(i);
			dq2.add(i);
			dq3.add(i);
			dq4.add(i);
		}
		dqlist.add(dq1);
		dqlist.add(dq2);
		dqlist.add(dq3);
		dqlist.add(dq4);

		int t = Integer.parseInt(bf.readLine());
		for (int i = 0 ; i < t ; i++) {
			
			int[] inputs = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int curidx = inputs[0]-1;
			int direction = inputs[1];
			
			List<int[]> updateList = new LinkedList<>();
			updateList.add(new int[] {curidx, direction});
			
			int tempDirection = direction;
			// 왼쪽으로 가면서 업데이트 대상인지 체크.
			for (int j = curidx; j > 0 ; j --) {
				// 나의 왼쪽 
				int curme = getSixth(dqlist.get(j).getFirst());
				// 상대방의 오른쪽 
				int curyou = getSecond(dqlist.get(j-1).getFirst());
				// 나의 왼쪽과 상대방의 오른쪽이 같다면..
				if (ts[j][curme] != ts[j-1][curyou]) {
					updateList.add(new int[] {j-1, -tempDirection});
					tempDirection = -tempDirection;
				} else {
					break;
				}
			}
			
			tempDirection = direction;
			
			// 오른쪽으로 가면서 업데이트 대상인지 체크.
			for (int j = curidx; j < 3 ; j++) {
				// 나의 오른쪽
				// 상대방의 왼쪽 
				int curme = getSecond(dqlist.get(j).getFirst());
				// 상대방의 오른쪽 
				int curyou = getSixth(dqlist.get(j+1).getFirst());
				// 나의 왼쪽과 상대방의 오른쪽이 같다면..
				// 
							
				if (ts[j][curme] != ts[j+1][curyou]) {
					updateList.add(new int[] {j+1, -tempDirection});
					tempDirection = -tempDirection;
				} else {
					break;
				}
			}
			
			
			// 업데이트 대상일경우. 
			for (int[] update : updateList) {
				Deque<Integer> dq = dqlist.get(update[0]);
				if (update[1] == 1) {
					// 반시계일경우.
					dq.addFirst(dq.pollLast());
				} else {
					dq.addLast(dq.pollFirst());
				}
			}
			
	
			
		}
		
		
		
//		int ii = 0;
//		for (Deque<Integer> dq : dqlist) {
//			final int ti = ii;
//			dq.stream().forEach((v) -> {
//				System.out.print(ts[ti][v] == '1' ? "S " : "N ");
//			});
//			System.out.println(" ");
//			ii++;
//		}
//		
		
		int adder = 1;
		int idx = 0;
		int result = 0;
		for (Deque<Integer> dq : dqlist) {
			char list = ts[idx][dq.getFirst()];
			if (list == '1') {
				result += adder;
			}
			adder = adder*2;
			idx++;
		}

		System.out.println(result);
	
	}

}
