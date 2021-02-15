package swea.p6808_규영_인영_카드게임;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static final int N = 9;
    static boolean[] isGyu = new boolean[19];

    static int[] gyuCard = new int[N];
    static int[] inCard = new int[N];

    static int iRes;
    static int gRes;

    static void swap(int i, int j) {
        int tmp = inCard[i];
        inCard[i] = inCard[j];
        inCard[j] = tmp;
    }

    static boolean nextPermutation() {
        int i = N-1;
        while (i-1 >= 0 && inCard[i-1] >= inCard[i])
            i--;

        if (i <= 0) return false;

        int j = N-1;
        while (inCard[i-1] >= inCard[j]) {
            j--;
            if (j == 0){
                System.out.println(i-1);
                System.out.println(Arrays.toString(inCard));
                break;
            }
        }

        swap(i-1, j);

        int k = N-1;
        while (i < k)
            swap(i++, k--);

        return true;
    }

    static void solution() {
        do {
            int gSum = 0;
            int iSum = 0;
            for (int i = 0; i < N; i++) {
                if (gyuCard[i] > inCard[i])
                    gSum += (gyuCard[i] + inCard[i]);
                else
                    iSum += (gyuCard[i] + inCard[i]);
            }

            if (gSum > iSum)
                gRes++;
            else
                iRes++;
        } while (nextPermutation());
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            Arrays.fill(isGyu, false);
            Arrays.fill(gyuCard, 0);
            Arrays.fill(inCard, 0);
            gRes = iRes = 0;


            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                isGyu[num] = true;
            }
            int gCnt = 0, iCnt = 0;
            for (int i = 1; i <= 18; i++) {
                if (isGyu[i])
                    gyuCard[gCnt++] = i;
                else
                    inCard[iCnt++] = i;
            }
            Arrays.sort(inCard);

            solution();

            System.out.println("#" + tc + " " + gRes + " " + iRes);
        }
    }
}
