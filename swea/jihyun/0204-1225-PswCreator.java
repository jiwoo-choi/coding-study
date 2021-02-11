import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = 10;
		String[] ret = new String[T+1];
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for(int i = 0; i < T;i++) {
			int tcase = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine(), " ");
			ret[tcase] = "";
			
			Queue<Integer> queue = new LinkedList<Integer>();
			for( int ct = 0 ; ct < 8 ; ct++)
				queue.add(Integer.parseInt(st.nextToken()));
			
			int curr, N = 1;
			
			do{
				curr = queue.poll()-N;
				if(curr <= 0) curr= 0;
				queue.offer(curr);
				N = (N == 5)? 1 : N+1;
			}while( curr > 0); 
			
			for(int e : queue) {
				ret[tcase] += (" " + e);
			}
		}
		for(int i = 1; i <= T ; i++)
			System.out.println("#" + i + ret[i]);
		
	}
}
