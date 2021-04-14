import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] h;
    
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        h = new int[N];
        StringTokenizer st = new StringTokenizer(in.readLine());
        long sum = 0;
        long cnt2 = 0;
        for (int i = 0; i < N; i++) {
            h[i] = Integer.parseInt(st.nextToken());
            sum += h[i];
            cnt2 += h[i] / 2;
        }
        boolean possible = true;
        if (sum % 3 == 0) {
            long cnt3 = sum / 3;
            if (cnt3 > cnt2) possible = false;
        } else possible = false;

        System.out.println(possible? "YES" : "NO");
    }
}
