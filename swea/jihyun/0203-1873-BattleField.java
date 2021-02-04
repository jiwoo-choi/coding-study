import java.io.IOException;
import java.util.Scanner;

public class Solution {

	static int count;
	static int row, col;
	static int posx,posy;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] values = new int[T];
		char[][][] map = new char[T][][];
		
		for(int cnt = 0; cnt < T ; cnt++) {
			//맵 입력받기 
			row = sc.nextInt();
			col = sc.nextInt();
			
			map[cnt] = new char[row][];
			
			for(int i = 0; i < row ; i++) {
				map[cnt][i] = new char[col];
				String eachRow= sc.next();
				for(int j = 0 ; j < col ; j++) {
					map[cnt][i][j] = eachRow.charAt(j);
					if(map[cnt][i][j] == '^'|| map[cnt][i][j] == 'v' 
							||map[cnt][i][j] == '<' || map[cnt][i][j] == '>') {
						posx = i; posy = j;
					}
				}
			}
			count = sc.nextInt();
			game(map[cnt],sc.next());
		}
		for(int cnt = 0; cnt < T ; cnt++) {
			System.out.print("#"+(cnt+1)+" ");
			for(char[] e : map[cnt])
				System.out.println(e);
		}
		
	}
	private static void game(char[][] map, String inputs) {
		for(int i = 0, num = inputs.length() ; i < num ; i++) {
			char ctrl = inputs.charAt(i);

			switch(ctrl) {
				case 'U' : {
					if(0 <= posx-1 && map[posx-1][posy] == '.') {
						map[posx][posy]= '.';
						posx--;
					}
					map[posx][posy] = '^';
					break;
				}
				case 'D' : {
					if(posx+1 < row && map[posx+1][posy] == '.') {
						map[posx][posy]= '.';
						posx++;
					}
					map[posx][posy] = 'v';
					break;
				}
				case 'L' : {
					if(0 <= posy-1 && map[posx][posy-1] == '.') {
						map[posx][posy]= '.';
						posy--;
					}
					map[posx][posy] = '<';
					break;
				}
				case 'R' : {
					if(posy+1 < col && map[posx][posy+1] == '.') {
						map[posx][posy]= '.';
						posy++;
					}
					map[posx][posy] = '>';
					break;
				}
				case 'S' : {
					int d = 0;
					if(map[posx][posy] == '^') d = 0;
					else if(map[posx][posy] == 'v') d = 1;
					else if(map[posx][posy] == '<') d = 2;
					else d = 3;
					
					int tempx = posx + dx[d];
					int tempy = posy + dy[d];
					for(int j = 1 ; 0 <= tempx && tempx < row && 0 <= tempy && tempy < col;j++) {
						if(map[tempx][tempy] == '*') {
							map[tempx][tempy] = '.'; break;
						}
						else if(map[tempx][tempy] == '#') break;
						tempx += dx[d]; 
						tempy += dy[d];
					}
					break;
				}
			}
		}
	}
}
