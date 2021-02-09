package SWEA1208;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

    static int N;

    static Queue<Integer> highestPQ = new PriorityQueue<Integer>(Comparator.reverseOrder());
    static Queue<Integer> lowestPQ = new PriorityQueue<Integer>();

    public static int solution() {
        for (int i = 0; i < N; i++) {
            int highest = highestPQ.poll();
            int lowest = lowestPQ.poll();

            if (highest - lowest <= 1) {
                return highest - lowest;
            } 
			else {
                highest--;
                lowest++;
                highestPQ.add(highest);
                lowestPQ.add(lowest);
            }
        }
        return highestPQ.peek() - lowestPQ.peek();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        Scanner sc = new Scanner(System.in);

        int testcase = 10;
        for (int tc = 1; tc <= testcase; tc++) {
            highestPQ.clear();
            lowestPQ.clear();

            N = sc.nextInt();
            for (int i = 0; i < 100; i++) {
                int tmp = sc.nextInt();
                highestPQ.add(tmp);
                lowestPQ.add(tmp);
            }
            System.out.printf("#%d %d\n", tc, solution());
        }
    }
}

