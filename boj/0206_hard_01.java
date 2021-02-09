import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

class Main {
    
	
	static int dx[] = {0, 0, 1, -1};
	static int dy[] = {1, -1, 0, 0};
	static int value[][];
	static int n;
	static int arr[][];
	
	public static int go(int x, int y) {
		
		if (value[x][y] != -1) return value[x][y];
		
		for (int k = 0 ; k < 4 ; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			
			if (nx >= 0 && ny >= 0 && nx < n && ny < n && arr[nx][ny] > arr[x][y]) {
				value[x][y] = Math.max(go(nx,ny) + 1, value[x][y]);
			} else {
				value[x][y] = Math.max(value[x][y], 1);
			}
		}
		
		return value[x][y];
	}
	
	

    public static void main(String[] args) throws IOException {
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	String ns = bf.readLine();
    	n = Integer.parseInt(ns);
    	arr = new int[n][];
    	value = new int[n][n];

    	//https://github.com/apeach314/study/issues/11
    
    	for (int i = 0 ; i < n ; i++) {
    		arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    		Arrays.fill(value[i], -1);
    	}
    	
    	int result = -1;
    	for (int i = 0 ; i < n ; i++) {
    		for (int j = 0 ; j < n ; j++) {
    			if (value[i][j] == -1) {
    				result = Math.max(result, go(i,j));
    			}
    		}
    	}
    	
    	System.out.println(result);
    	
    }    
    
}
