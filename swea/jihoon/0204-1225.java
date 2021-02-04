import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N = 8;
	static Queue<Integer> q = new LinkedList<>();
	
	static void solution() {
		
		int cnt = 1;
		while (true) {
			int front = q.poll();
			front -= cnt;
			if (front <= 0) {
				q.offer(0);
				break;
			}
			q.offer(front);
			if (cnt % 5 == 0)
				cnt = 0;
			cnt++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1; tc <= 10; tc++) {
			in.readLine();
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				int input = Integer.parseInt(st.nextToken());
				q.offer(input);
			}
			solution();
			
			System.out.print("#" + tc + " ");
			while (!q.isEmpty())
				System.out.print(q.poll() + " ");
			System.out.println();
		}
	}
}
