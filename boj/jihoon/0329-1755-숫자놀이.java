import java.util.*;
import java.io.*;

public class Main {
    static int M, N;

    static class Node implements Comparable<Node> {
        String str;
        int num;
        Node(String str, int num) {
            this.str = str;
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            return this.str.compareTo(o.str);
        }
    }

    static void solution() {
        List<Node> list = new ArrayList<>();

        for (int i = M; i <= N; i++) {
            StringBuilder sb = new StringBuilder();

            char[] numStr = Integer.toString(i).toCharArray();
            for (int j = 0; j < numStr.length; j++)
                sb.append(map[numStr[j] - '0']).append(" ");
            sb.setLength(sb.length()-1);

            list.add(new Node(sb.toString(), i));
        }

        Collections.sort(list);

        StringBuilder out = new StringBuilder();
        for (int i = 0, cnt = 0; i < list.size(); i++, cnt++) {
            String last = " ";
            if (cnt == 9) {
                last = "\n";
                cnt = -1;
            }
            out.append(list.get(i).num).append(last);
        }
        System.out.print(out);
    }

    static String[] map = {"zero", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine"};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        solution();
    }
}
