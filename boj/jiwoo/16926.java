import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

class Main {
	
    static int[] dx = {0, 1,  0, -1}; // 우 -> 하 -> 좌 -> 상.
    static int[] dy = {1, 0, -1,  0};
    static int[][] arr;

	// 현재 가장 겉부분에 있는 element들을 모두 swap해준다.
	public static void swapEdges(int x, int y, int width, int height) {

		if (width <= 0 || height <= 0) {
			return;
		}
		// limit 
		// width 
		int temp = arr[x][y]; // swap하기전에 저장해둘것들.
		int sx = x;
		int sy = y;
		int minx = x;
		int miny = y;
		int maxx = x+height;
		int maxy = y+width;
		int curdir = 0;

		for(int i = 0 ; i < (width+height)*2 - 4; i++) {
			int nx = x + dx[curdir];
			int ny = y + dy[curdir];
						
			if (nx < minx || ny < miny || nx >= maxx || ny >= maxy) {
				curdir = (++curdir) % 4;
				i--;
				continue;
			}

			arr[x][y] = arr[nx][ny];
			x = nx;
			y = ny;
		}
		
		arr[sx+1][sy] = temp;
		swapEdges(sx+1, sy+1, width-2, height-2);
	}
	
	
    public static void main(String[] args) throws IOException {
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	int inputs[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	int n = inputs[0];
    	int m = inputs[1];
    	int r = inputs[2];
    	
    	arr = new int[n][];
    	for (int i = 0 ; i < n ; i++) {
    		arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	}
    	
    	for (int i = 0 ; i < r ; i++) {
        	swapEdges(0, 0, m, n);
    	}

    	for(int i = 0 ; i < n ; i++) {
    		for (int j = 0 ; j < m ;j ++) {
    			System.out.print(arr[i][j] + " " );
    		}
    		System.out.println();
    	}
    }
}

