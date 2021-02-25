import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main {
    static int N;
    static int[] input;
    static int jokerCnt;
    static int solution() {
        Arrays.sort(input);
 
        int res = jokerCnt + (N - jokerCnt > 0? 1 : 0);
 
        for (int i = jokerCnt; i < N; i++) {
            int zeroCnt = jokerCnt;
            int j = i;
            int sameCnt = 0;
            for (; j < N-1; j++) {
                int betweenCnt = input[j+1] - input[j] - 1;
                if (betweenCnt == 0) continue;
                else if (betweenCnt == -1) {
                    sameCnt++;
                    continue;
                }
                if (betweenCnt <= zeroCnt)
                    zeroCnt -= betweenCnt;
                else
                    break;
            }
            res = Math.max(res, j - i + 1 + jokerCnt - sameCnt);
        }
        return res;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 
        N = Integer.parseInt(in.readLine());
        input = new int[N];
 
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            if (input[i] == 0) jokerCnt++;
        }
 
        System.out.println(solution());
    }
}
