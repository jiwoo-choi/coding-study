package class_p;
import java.util.*;

public class boj_2565 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a[] = new int[501];
		int d[] = new int[501];
		
//		Arrays.fill(a, Integer.MAX_VALUE);
		
		for (int i = 0 ; i < n ; i++) {
			a[sc.nextInt()] = sc.nextInt();
		}
		
		
		for (int i = 1 ; i < 501 ; i++) {
			d[i] = 1;
			if (a[i] == 0) {
				d[i] = 0;
				continue;
			}
			for (int j = 1 ; j < i ;j++) {
				if (a[j] < a[i] && d[i] < d[j] + 1) {
					d[i] = d[j] + 1;
				}
			}
		}
		
		int result = n - Arrays.stream(d).max().getAsInt();
		System.out.println(result);
	}

}
