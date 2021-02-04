package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BattleField {
	
	static char[][] map;
	static int x;
	static int y;

	
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("in.txt")); // 제출시 지우기
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		
		for(int t = 1;t<=TestCase;t++) {
		
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
	
			/**전차의 위치 표시*/
			y=0;
			x=0;
			
			map = new char[H][W];
			
			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W ; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j]=='<'||map[i][j]=='>'||map[i][j]=='^'||map[i][j]=='v'){
						y=i;
						x=j;
					}
				}
			}
			
			/** 사용자가 동작할 문자열 저장*/
			int N = Integer.parseInt(br.readLine());
	
			char[] actArr = new char[N];
			String actString = br.readLine();
			for (int i = 0; i < N; i++) {
				actArr[i] = actString.charAt(i);
			}	
			
			for (int i = 0; i < N; i++) {
				char c = actArr[i];
				switch (c) {
				case 'U':up();break;
				case 'D':down(H);break;
				case 'L':left();break;
				case 'R':right(W);break;
				case 'S':shoot(H, W);break;
				default:break;
				}
//				System.out.println(i);
//				System.out.println(c);
//				for (int k = 0; k < H; k++) {
//					for (int j = 0; j < W; j++) {
//						System.out.print(map[k][j]);
//					}
//					System.out.println();
//				
//				}
//				
//				System.out.println();
			} // 움직임
			
			sb.append("#"+t+" ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
			// 입출력은 작을수록 좋다.
//			for (char[] cs : map) {
//				System.out.println(Arrays.toString(cs));
//			} //입력 확인용
		}//end of TestCase
		System.out.println(sb);
	}//end of Main
	
	public static void up() {
		map[y][x]='^';
		if(y-1>=0 && map[y-1][x]=='.') {
			map[y][x]='.';
			map[y-1][x]='^';
			y--;
		}
	}
	
	public static void down(int H) {
		if(y+1<H && map[y+1][x]=='.') {
			map[y][x]='.';
			map[y+1][x]='v';
			y++;
		}
		map[y][x]='v';
	}
	
	public static void left() {
		map[y][x]='<';
		if(x-1>=0 && map[y][x-1]=='.') {
			map[y][x]='.';
			map[y][x-1]='<';
			x--;
		}
	}
	
	public static void right(int W) {
		map[y][x]='>';
		if(x+1<W && map[y][x+1]=='.') {
			map[y][x]='.';
			map[y][x+1]='>';
			x++;
		}
	}
	
	public static void shoot(int H, int W) {
		char c = map[y][x];
		switch (c) {
		case '<':
			for (int i = x-1; i>=0 ; i--) {
				if(map[y][i]=='#') {
					break;
				}
				if(map[y][i]=='*') {
					map[y][i]='.';
					break;
				}
			}
			break;
			
		case '>':
			for (int i = x+1; i < W; i++) {
				if(map[y][i]=='#') {
					break;
				}
				if(map[y][i]=='*') {
					map[y][i]='.';
					break;
				}
			}
			break;
		case '^':
			for (int i = y; i >=0 ; i--) {
				if(map[i][x]=='#') {
					break;
				}
				if(map[i][x]=='*') {
					map[i][x]='.';
					break;
				}
			}
			break;
		case 'v':
			for (int i = y; i < H; i++) {
				if(map[i][x]=='#') {
					break;
				}
				if(map[i][x]=='*') {
					map[i][x]='.';
					break;
				}
			}
			break;

		default:
			break;
		}

	}

}
