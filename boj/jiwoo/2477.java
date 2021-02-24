import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedList<Integer>[] list = new LinkedList[5];
		Deque<int[]> q = new LinkedList<>();

		
		int t = sc.nextInt();
		
		for (int i = 0 ; i < 6 ; i++) {
			int side = sc.nextInt();
			int value = sc.nextInt();
			if (list[side] == null) list[side] = new LinkedList<>();
			list[side].add(value);
			q.addFirst(new int[]{side,value});
		}
		
		int x = -1;
		int y = -1;
		int result2 = 1;
		for (int i = 1; i <= 4 ; i++) {
			if(list[i].size() >= 2) {
				if (x == -1) {
					x = i;
				} else {
					y = i;
				}
			} else {
				result2 *= list[i].get(0);
			}
		}
		
		while(true) {
			int[] temp = q.peek();
			if (temp[0] != x && temp[0] != y) { break; }
			q.addLast(q.pollFirst());
		}
		
		int cnt = 0;
		int result = 1;
		while(cnt < 3) {
			int[] temp = q.poll();
			if (cnt > 0) {
				result *= temp[1];
				cnt++;
			} else if (temp[0] == x || temp[0] == y) {
				cnt++;
			}
		}
		

		System.out.println((result2-result) * t);

	}
}

