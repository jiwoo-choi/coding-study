import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static class Node implements Comparable<Node> {
        int a, b;
        Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.a, o.a);
        }
    }
    static Node[] input;
    static int[] dp;

    static void solution() {
        Arrays.sort(input);
        for (int i = 0; i < N; i++) {
            Node cur = input[i];
            for (int j = 0; j < i; j++) {
                Node prev = input[j];
                if (cur.b > prev.b)
                    dp[i] = Math.max(dp[i], dp[j]);
            }
            dp[i]++;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        input = new Node[N];
        dp = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            input[i] = new Node(a, b);
        }

        solution();

        int max = 0;
        for (int i = 0; i < N; i++)
            max = Math.max(max, dp[i]);
        System.out.println(N - max);
    }
}
