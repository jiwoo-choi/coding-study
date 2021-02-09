package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Tower_2493_2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 결과값들을 저장하기 위해
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N+1][2]; 
		// 행: 탑 순서
		// 열: 탑의 높이
		// N+1을 해주는 이유는 index를 1부터 쓸거라서(편의상) 
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int index=0;
		
		/**타워 개수인 N만큼 반복 작업*/
		for (int i = 1; i < N+1; i++) {
			
			int x=Integer.parseInt(st.nextToken());
			/*
			[][] index:3
			[2][9] index:2
			[0][6] index:1 // push는 여기부터 할거야
			[][] index:0 // 있지만 안쓸거다
			*/
			/**index가 0 이하거나(아래 행이 없다는 의미) || 새로 들어온 탑의 높이가 기존 탑의 높이 보다 크면 pop*/
			
			while(index>0 && arr[index][1]<x) {
			// 1) index가 0보다 커야 현재 내가 만든 가상의 스택 배열 안에 탑이 최소 하나라도 있다는 것
		    // 2) 현재 들어온 탑의 높이가 가장 최근 탑의 높이보다 크다면 가장 최근 탑(기존 탑)을 부셔주기
				arr[index--][0]=0; // 기존 탑 부수기				
			}
			
			/**새로 들어오는 탑을 쌓아줌*/
			arr[++index][0]= i;
			// 탑의 순서 정보 저장. ++을 해주는 이유는 처음에 index는 0을 가리키고 있기 때문에 그 윗 칸부터 넣어주려고
			arr[index][1] = x;
			// 탑의 높이 저장
			sb.append(arr[index-1][0]+" ");
		}
		System.out.println(sb);
	}//Main
}//Class


// 부등호 조심!!
