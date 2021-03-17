import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int res = -1;
    static int ER, EC;

    static void go(int sr, int sc, int size, int num) {
        if (size <= 1) {
            if (sr == ER && sc == EC)
                res = num;
            return;
        }

        int newSize = size / 2;
        int square = newSize * newSize;
        for (int r = sr, i = 0; i < 2; i++, r += newSize) {
            for (int c = sc, j = 0; j < 2; j++, c += newSize) {
                if (r <= ER && ER < r + newSize  && c <= EC && EC < c + newSize) {
                    go(r, c, newSize, num);
                    return;
                }
                num += square;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = 1 << Integer.parseInt(st.nextToken());
        ER = Integer.parseInt(st.nextToken());
        EC = Integer.parseInt(st.nextToken());

        go(0, 0, N, 0);
        System.out.println(res);
    }
}
