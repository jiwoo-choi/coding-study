import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        for (int i = 0 ; i < n ; i++) {
            q.add(i+1);
        }
        
        if (q.size() == 1) {
            System.out.println(q.poll());
        } else if (q.size() == 2) {
            q.poll();
            System.out.println(q.poll());
        } else {
            int back = 0;
            while(q.size() > 1) {
                 q.poll();
                 back = q.poll();
                 q.offer(back);
            }
            System.out.println(q.poll());
        }

    }
}
