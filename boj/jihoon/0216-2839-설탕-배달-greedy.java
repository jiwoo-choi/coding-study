import java.util.Scanner;

public class Main {
    static int N;
    
    static int solution() {
        if (N % 5 != 0) {
            int cnt = 0;
            while (N > 0) {
                if (N < 15 && N % 3 == 0) {
                    cnt += N / 3;
                    N = 0;
                    break;
                }
                N -= 5;
                cnt += 1;
            }
            if (N == 0)
                return cnt;
        }
        else
            return N / 5;

        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        System.out.println(solution());
    }
}
