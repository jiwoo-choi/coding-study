import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int t = Integer.parseInt(bf.readLine());
        int a[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 거리 저장.
        int d[] = new int[n+1];
        Arrays.fill(d, -1);

        // 안전 거리 : 비트의 추가/삭제연산을 해서 그 해당 1비트씩 변환한다고 가정했을 , 몇번의 변환 과정을 거쳐야하는지.
        // 안전도 : 안전거리를 변환하면서 가장 적게 비트 변환을 해서 도달하는 경우  =>  BFS.
        // 문제에서 구하라고 하는것 : 모든 가능한 값들을 상대로, 가장 distance가 높은걸 찾는다.

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0 ; i < t ; i++) {
            d[a[i]] = 0;
            q.add(a[i]);
        }

        while(!q.isEmpty()) {
            int curr = q.peek(); q.poll();
            // 최대값이 100만이므로 log2(1_000_000) = 19.93
            for (int i = 0 ; i <= 20 ; i++) {
                // 가용할 수 있는 모든 비트에 대해서 비트연산을 해본다.
                // 비트 추가/삭 연산은 XOR을 활용한다.
                int value = curr ^ (1 << i);
                if (value <= n && d[value] == -1) {
                    d[value] = d[curr] + 1;
                    q.add(value);
                }
            }
        }
        System.out.println(Arrays.stream(d).max().getAsInt());
    }    
}
