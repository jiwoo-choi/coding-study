import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

class Main {
	
	static private void swap(int s1, int s2, int f1, int f2) {
		int temp = arr[s1][s2];
		arr[s1][s2] = arr[f1][f2];
		arr[f1][f2] = temp; 
	}
	
	static private void horizontalFlipped() {
		for (int i = 0 ; i < m; i++) {
			for(int j = 0 ; j < n/2 ; j++) {
				swap(j,i, j + ( n- (2 * j + 1)), i );
			}
		}
	}
	
	static private void verticalFlipped() {
		for (int i = 0 ; i < n; i++) {
			for(int j = 0 ; j < m/2 ; j++) {
				swap(i,j, i, j + ( m - (2 * j + 1)));
			}
		}
	}

	static private void rotateClockWise() {
		// 1. 대각선 공식 으로 풀기
		// 2. 그냥 행열바꿔서 복사하기.
		
		int temp[][] = new int[m][n];
		for (int i = 0; i < n ;i++) {
			for (int j = 0 ; j < m ; j++) {
				temp[j][n-i-1] = arr[i][j];
			}
		}
		arr = temp;
		int tmp = n;
		n = m;
		m = tmp;
		
	}
	
	static private void rotateAntiClockWise() {
		
		int temp[][] = new int[m][n];
		for (int i = 0; i < n ;i++) {
			for (int j = 0 ; j < m ; j++) {
				temp[m-j-1][i] = arr[i][j];
			}
		}
		arr = temp;
		int tmp = n;
		n = m;
		m = tmp;
	}
	
	static private void split() {
		// 원본을 가지고 tmp에 만들어가기
		int temp[][] = new int[n][m];

		for (int i = 0; i < n/2 ;i++) {
			for (int j = 0 ; j < m/2 ; j++) {
				temp[i][j+m/2] = arr[i][j];
			}
		}
		for (int i = 0; i < n/2 ;i++) {
			for (int j = m/2 ; j < m ; j++) {
				temp[i+n/2][j] = arr[i][j];
			}
		}
		for (int i = n/2; i < n ;i++) {
			for (int j = m/2 ; j < m ; j++) {
				temp[i][j-m/2] = arr[i][j];
			}
		}
		for (int i = n/2; i < n ;i++) {
			for (int j = 0 ; j < m/2 ; j++) {
				temp[i-n/2][j] = arr[i][j];
			}
		}
		arr = temp;
		
	}
	static private void splitReversed() {
		int temp[][] = new int[n][m];

		for (int i = 0; i < n/2 ;i++) {
			for (int j = 0 ; j < m/2 ; j++) {
				temp[i+n/2][j] = arr[i][j];
			}
		}
		for (int i = 0; i < n/2 ;i++) {
			for (int j = m/2 ; j < m ; j++) {
				temp[i][j-m/2] = arr[i][j];
			}
		}
		for (int i = n/2; i < n ;i++) {
			for (int j = m/2 ; j < m ; j++) {
				temp[i-n/2][j] = arr[i][j];
			}
		}
		for (int i = n/2; i < n ;i++) {
			for (int j = 0 ; j < m/2 ; j++) {
				temp[i][j+m/2] = arr[i][j];
			}
		}
		arr = temp;
	}

	static void perform(int n){

		
		switch(n) {
		case 1:
			horizontalFlipped();
			break;
			
		case 2:
			verticalFlipped();
			break;
		case 3:
			rotateClockWise();
			break;
		case 4:
			rotateAntiClockWise();
			break;
		case 5:
			split();
			break;
		case 6:
			splitReversed();
			break;
		}
	}
	
	static int arr[][];
    static int n;
    static int m;
    static int r;
	public static void main(String[] args) throws IOException {
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	int inputs[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	n = inputs[0];
    	m = inputs[1];
    	r = inputs[2];
    	
    	arr = new int[n][];
    	for (int i = 0 ; i < n ; i++) {
    		arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	}
    	
    	Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).forEach( (value) -> {
    		perform(value);
    	});

		for(int i = 0 ; i < Main.n ; i++) {
    		for (int j = 0 ; j < m ;j ++) {
    			System.out.print(arr[i][j] + " " );
    		}
    		System.out.println();
    	}
	}
}

