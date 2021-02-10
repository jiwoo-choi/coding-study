import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static String[][] input = new String[210][4];

    static int solution() {
        for (int i = 0; i < N; i++) {
            String[] cur = input[i];
            int nodeNum = Integer.parseInt(cur[0]);
            if (Character.isDigit(cur[1].charAt(0))) { // 숫자
                int num = Integer.parseInt(cur[1]);
                if (cur[2] != null || cur[3] != null) {
                    return 0;
                }
            } else { // 연산자
                char operator = cur[1].charAt(0);
                if (cur[2] == null || cur[3] == null) {
                    return 0;
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int testcase = 10;
        for (int tc = 1; tc <= testcase; tc++) {
            for (int i = 0; i < input.length; i++) {
                Arrays.fill(input[i], null);
            }

            N = Integer.parseInt(in.readLine());
            boolean isValid = true;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                int cnt = 0;
                while (st.hasMoreTokens()) {
                    input[i][cnt++] = st.nextToken();
                }
            }

            System.out.println("#" + tc + " " + solution());
        }
    }
}
