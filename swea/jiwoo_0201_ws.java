import java.io.*;
import java.util.Arrays;
import java.util.*;
 
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
       // PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
       // PriorityQueue<Integer> minHeap = new PriorityQueue<>();
 
        for(int test_case = 1; test_case <= 10; test_case++) {
         
            Integer dump = Integer.parseInt(bf.readLine());
            String[] data = bf.readLine().split(" ");
            int[] mapped = Arrays.stream(data).mapToInt(Integer::parseInt).toArray();
                        for (int i = 0; i < dump; i++) {
 
                int maxIdx = 0;
                for (int j = 0; j < mapped.length; j++) {
                    if (mapped[j] > mapped[maxIdx]) {
                        maxIdx = j;
                    }
                }
 
                int minIdx = 0;
                for (int j = 0; j < mapped.length; j++) {
                    if (mapped[j] < mapped[minIdx]) {
                        minIdx = j;
                    }
                }
                mapped[maxIdx]--;
                mapped[minIdx]++;
            }
 
            int maxIdx = 0;
            for (int j = 1; j < mapped.length; j++) {
                if (mapped[j] > mapped[maxIdx]) {
                    maxIdx = j;
                }
            }
 
            int minIdx = 0;
            for (int j = 1; j < mapped.length; j++) {
                if (mapped[j] < mapped[minIdx]) {
                    minIdx = j;
                }
            }
 
            System.out.println("#" + (test_case) + " " + (mapped[maxIdx] - mapped[minIdx]));
        }
    }
}
