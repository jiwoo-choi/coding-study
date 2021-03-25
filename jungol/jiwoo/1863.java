
import java.util.*;
import java.io.*;
 
public class Main {
 
    static int parents[];
    static int N;
    static int M;
     
    static void union(int x, int y) {
         
        int aRoot = find(x);
        int bRoot = find(y);
        if (aRoot == bRoot) return;
        parents[bRoot] = aRoot;
//
//      if (rank[aRoot] > rank[bRoot]) {
//          parents[bRoot] = aRoot;
//      } else {
//          parents[aRoot] = bRoot;
//          if (rank[aRoot] == rank[bRoot]) {
//              rank[bRoot]++;
//          }
//      }
    }
     
    static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
     
    static void make() {
        parents = new int[N+1];
//      rank = new int[N+1];
        for (int i = 1 ; i <= N ; i++) {
            parents[i] = i;
        }
    }
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
 
         
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
         
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
 
        make();
         
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x, y);
        }
         
        int result = 0;
        for(int i = 1 ; i <= N ; i++) {
            if (parents[i] == i) result++;
        }
         
        System.out.println(result);
    }
 
}
