import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static final int HOUSE = 1;
    static final int CHICKEN = 2;

    static List<int[]> houses = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    static int[][] dist = new int[2500][2500];
    static int res;

    static void calDist() {
        for (int i = 0; i < chickens.size(); i++) {
            int[] curChicken = chickens.get(i);
            for (int j = 0; j < houses.size(); j++) {
                int[] curHouse = houses.get(j);
                dist[i][j] = Math.abs(curChicken[0] - curHouse[0])
                            + Math.abs(curChicken[1] - curHouse[1]);
            }
        }
    }
    
    static void solution() {
        calDist();

        int K = chickens.size();

        int[] perm = new int[K];
        for (int i = 0; i < M; i++) perm[i] = 1;
        Arrays.sort(perm);

        do {
            int[] minDist = new int[houses.size()];
            Arrays.fill(minDist, Integer.MAX_VALUE);

            for (int i = 0; i < K; i++) {
                if (perm[i] == 1)
                    for (int j = 0; j < minDist.length; j++)
                        minDist[j] = Math.min(minDist[j], dist[i][j]);
            }
            int sum = 0;
            for (int j = 0; j < minDist.length; j++)
                sum += minDist[j];

            res = Math.min(res, sum);

        } while (nextPermutation(perm));
    }

    static boolean nextPermutation(int[] perm) {
        int len = perm.length;
        int i = len-1;
        while (i-1 >= 0 && perm[i-1] >= perm[i]) i--;

        if (i <= 0) return false;

        int j = len-1;
        while (perm[i-1] >= perm[j]) j--;
        int tmp = perm[i-1];
        perm[i-1] = perm[j];
        perm[j] = tmp;

        int k = len-1;
        while (i < k) {
            int tmp2 = perm[i];
            perm[i] = perm[k];
            perm[k] = tmp2;
            i++; k--;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        houses.clear();
        chickens.clear();
        res = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == HOUSE)
                    houses.add(new int[]{i, j});
                else if (input == CHICKEN)
                    chickens.add(new int[]{i, j});
            }
        }

        solution();
        System.out.println(res);
    }
}
