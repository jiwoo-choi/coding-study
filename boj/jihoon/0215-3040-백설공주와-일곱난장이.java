package boj.p3040;


import java.util.Scanner;

public class Main {
    static final int N = 9;
    static int[] input = new int[N];
    static int[] perm = new int[N];

    static void swap(int i, int j) {
        int tmp = perm[i];
        perm[i] = perm[j];
        perm[j] = tmp;
    }

    static boolean nextPermutation() {
        int i = N-1;
        while (i-1 >= 0 && perm[i-1] >= perm[i]) i--;

        if (i <= 0) return false;

        int j = N-1;
        while (perm[i-1] >= perm[j]) j--;

        swap(i-1, j);

        int k = N-1;
        while (i < k) swap(i++, k--);

        return true;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = N-1; i > N-3; i--)
            perm[i] = 1;

        int totalSum = 0;
        for (int i = 0; i < N; i++) {
            input[i] = scanner.nextInt();
            totalSum += input[i];
        }

        do {
            int sum = 0;
            for (int i = 0; i < N; i++)
                if (perm[i] == 1)
                    sum += input[i];

            if (totalSum - sum == 100)
                break;
        }
        while (nextPermutation());

        for (int i = 0; i < N; i++)
            if (perm[i] == 0)
                System.out.println(input[i]);
    }
}
