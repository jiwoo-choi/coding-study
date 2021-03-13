package swea_a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class swea_2112 {


	static String map[][];
	static int min_cnt;
	static boolean selected[];
	
	
	static boolean eval(int D, int W, int K) {
		for (int j = 0 ; j < W; j++) {
			int cnt = 1;
			char prev = map[0][j].charAt(0);
			for (int i = 1 ; i < D ; i++) {
				if (prev == map[i][j].charAt(0)) cnt++; 
				else {
					prev = map[i][j].charAt(0);
					cnt = 1;
				}
				
				if (cnt >= K) break;;
				
			}
			if (cnt < K) {
				return false;
			}
		}
		return true;
	}
	

	
	static void go(int idx, int limit, int D, int W, int K) {
		
		
		if (limit >= min_cnt) {
			// 최소로 정한게 발곃졌는데 굳이 더 큰 limit를 탐색할 이유는 없다.
			return;
		}
		
		if (limit >= K) {
			//통과 기준보다 약품을 더 쓰는경우는 무조건 통과된다.
			min_cnt = limit;
			return;
		}
		
		if (idx >= D) {

			// A와 B를 선택해줘야합니다.
			List<Integer> list = new LinkedList<>();
			List<String[]> stored = new LinkedList<>();
//			System.out.print("선택된 것 : ");
			for (int i = 0 ; i < D ; i++) {
				if (selected[i] == true) {
//					System.out.print(i + " " );
					list.add(i);
					String[] ele = new String[W];
					for (int j = 0 ; j < W ; j++) {
						ele[j] = map[i][j];
					}
					stored.add(ele);
				}
			}
//			System.out.println();
					    
			
			for (int i = 0 ; i < 1 << list.size(); i++) {
				List<Integer> AList = new LinkedList<>();
				List<Integer> BList = new LinkedList<>();
	
				for (int j = 0; j < list.size();j++) {
					//A와 B를 픽함.

					if ((i & 1 << j) > 0) {
						AList.add(list.get(j));
					} else {
						BList.add(list.get(j));
					}
				}
				
				for (Integer row : AList) {
					for (int k = 0 ; k < W; k++) {
						map[row][k] = "0";
					}
				}
				
				for (Integer row : BList) {
					for (int k = 0 ; k < W; k++) {
						map[row][k] = "1";
					}
				}
				
				if (eval(D, W, K)) {
					min_cnt = Math.min(min_cnt, limit);
				};

				
				for (Integer row : AList) {
					for (int k = 0 ; k < W; k++) {
						map[row][k] = "1";
					}
				}
				
				for (Integer row : BList) {
					for (int k = 0 ; k < W; k++) {
						map[row][k] = "0";
					}
				}
				
				if (eval(D, W, K)) {
					min_cnt = Math.min(min_cnt, limit);
				};
				// min을 가지고 다투자.			
			}
			
			for (int k = 0 ; k < list.size(); k++) {
				map[list.get(k)] = stored.get(k);
			}
			
//			for (int a = 0; a < D ; a++) {
//				for (int b= 0 ; b < W; b++) {
//					System.out.print(map[a][b] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			return;
		}
		
		
		go(idx+1, limit, D, W, K);
		selected[idx] = true;
		go(idx+1, limit+1, D, W, K);
		selected[idx] = false;
		
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());

		for (int test_case=1 ; test_case <= t; test_case++) {
			int inputs[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int D = inputs[0];
			int W = inputs[1];
			int K = inputs[2];
			
			map = new String[D][];
			for (int i = 0 ; i < D ; i ++) {
				map[i] = bf.readLine().split(" ");
			}

			
			if ( K <= 1 ) {
				// 가지치기 1: k==1일때 무조건 0반납.
				System.out.println("#" + test_case + " " + 0);
				continue;
			} else {
				
				selected = new boolean[D];
				min_cnt = Integer.MAX_VALUE;
				
				go(0,0, D,W,K);
				//K 이하로 뽑길 원함.				
				System.out.println("#" + test_case + " " + min_cnt);
				
			}
			
		}
		
		
	}


}
