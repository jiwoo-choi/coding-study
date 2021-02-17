import java.io.*;
import java.util.*;

class Main {
    static int dp[];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dp = new int[n+1];
        dp[0] = 0;
        
        
        for (int i = 1; i <= n; i++) {
        	
        	if ( i < 5) {
        		if ( i % 3 == 0) dp[i] = 1;
        		else dp[i] = -1;
        		continue;
        	}
        	
            dp[i] = Integer.MAX_VALUE;
            if (dp[i-5] != -1) {
                dp[i] = dp[i-5] + 1;
            }
            if (dp[i-3] != -1) {
                dp[i] = Math.min(dp[i], dp[i-3] + 1);
            }
            
            if (dp[i] == Integer.MAX_VALUE) dp[i] = -1;
            
        }
        System.out.println(dp[n]);
    }
}
                        
/*
GREEDY
import java.io.*;
import java.util.*;

class Main {
    static int dp[];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dp = new int[n+1];
        dp[0] = 0;
        
        
        for (int i = 1; i <= n; i++) {
        	
        	if ( i < 5) {
        		if ( i % 3 == 0) dp[i] = 1;
        		else dp[i] = -1;
        		continue;
        	}
        	
            dp[i] = Integer.MAX_VALUE;
            if (dp[i-5] != -1) {
                dp[i] = dp[i-5] + 1;
            }
            if (dp[i-3] != -1) {
                dp[i] = Math.min(dp[i], dp[i-3] + 1);
            }
            
            if (dp[i] == Integer.MAX_VALUE) dp[i] = -1;
            
        }
        System.out.println(dp[n]);
    }
}
                        
                        
*/
