package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TurnArray_16935 {

	public static int[][] arr;
	public static int[][] result;
	public static int N;
	public static int M;
	public static int halfR;
	public static int halfC;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken()); // N행
		M = Integer.parseInt(st.nextToken()); // M열
		int R = Integer.parseInt(st.nextToken()); // 몇 번 수행할지
		
		// 쪼개지 않아도 되면 쪼개지 말자!
		arr = new int[N][M];
		
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=0; x < M; x++) {
				arr[y][x]=Integer.parseInt(st.nextToken());
			}
		}

		
		st = new StringTokenizer(br.readLine());
		char op = '1';
			while (st.hasMoreTokens()) {
				op = st.nextToken().charAt(0);
				switch (op) {
				case '1':upDown();break;
				case '2':leftRight();break;
				case '3':right90();break;
				case '4':left90();break;
				case '5':rightC();break;
				case '6':leftC();break;
				}
		} // R번 반복
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					sb.append(arr[i][j]+" ");
				}
				sb.append("\n");
			}
		
		System.out.print(sb);
	}//Main

	
	public static void leftC() {
		
		
		N = arr.length;
		M = arr[0].length;
		
		halfR=N/2;
		halfC=M/2;
		
		result = new int[N][M];
		
		for (int y = 0; y < halfR; y++) {
			for (int x = 0; x < halfC; x++) {
				result[halfR+y][x] = arr[y][x];
			}
		}//1->4
		for (int y = 0; y < halfR; y++) {
			for (int x = halfC; x < M; x++) {
				result[y][x-halfC] = arr[y][x];
			}
		}//2->1
		for (int y = halfR; y < N; y++) {
			for (int x = halfC; x < M; x++) {
				result[y-halfR][x] = arr[y][x];
			}
		}//3->2
		for (int y = halfR; y < N; y++) {
			for (int x = 0; x < halfC; x++) {
				result[y][x+halfC] = arr[y][x];
			}
		}//4->3
		
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.copyOf(result[i], M);
		}		
	}

	public static void rightC() {
		
		N = arr.length;
		M = arr[0].length;
		halfR=N/2;
		halfC=M/2;
		
//		System.out.println(N+" "+M+" "+halfR+" "+halfC);
		
		result = new int[N][M];
		for (int y = 0; y < halfR; y++) {
			for (int x = 0; x < halfC; x++) {
				result[y][halfC+x] = arr[y][x];
			}
		}//1->2
		for (int y = 0; y < halfR; y++) {
			for (int x = halfC; x < M; x++) {
				result[halfR+y][x] = arr[y][x];
			}
		}//2->3
		for (int y = halfR; y < N; y++) {
			for (int x = halfC; x < M; x++) {
				result[y][x-halfC] = arr[y][x];
			}
		}//3->4
		for (int y = halfR; y < N; y++) {
			for (int x = 0; x < halfC; x++) {
				result[y-halfR][x] = arr[y][x];
			}
		}//4->1
		
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.copyOf(result[i], M);
		}
		
// for문 4곳 나눠서 처리
// 전체로 돌리고 if문으로 나눠서 처리
		
	}


	public static void left90() {
		N = arr.length;
		M = arr[0].length;
		result = new int[M][N];
		
		for (int y = 0; y < N; y++) {
			for (int x= 0, temp=M-1; x < M; x++,temp--) {
				result[temp][y]=arr[y][x];
			}
		}
		arr = result;		
	}

	public static void right90() {
		N = arr.length;
		M = arr[0].length;
		result = new int[M][N];
		
		for (int y = 0, temp=N-1; y < N; y++,temp--) {
			for (int x = 0; x < M; x++) {
				result[x][temp]=arr[y][x];
			}
		}

		arr = result;
	}

	public static void leftRight() {
		N = arr.length;
		M = arr[0].length;
		
		result = new int[N][M];
		
		for (int x = 0, temp = M-1; x < M; x++,temp--) {
			for (int y = 0; y < N; y++) {
				result[y][temp] = arr[y][x];
			}
		}
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.copyOf(result[i], M);
		}
	}

	public static void upDown() {
		N = arr.length;
		M = arr[0].length;
		result = new int[N][M];
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				result[N-y-1][x] = arr[y][x];
			}
		}
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.copyOf(result[i], M);
		}
	}
}//Class
