import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int dp[] = new int[Math.max(4, n+1)];
		dp[2] = 1;
		dp[3] = 1;
		
		for (int i = 4 ; i <= n ; i++) {
			dp[i] = dp[i-1] + 1;
			if ( i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i/3] + 1);
			}
			if ( i % 2 == 0 ) {
				dp[i] = Math.min(dp[i], dp[i/2] + 1);
			}
		}
		
		System.out.println(dp[n]);
	}

}
