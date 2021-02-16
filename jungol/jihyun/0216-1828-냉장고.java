import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
 
class Main {
    static int totalCnt = 1;
    static class chemical implements Comparable<chemical> {
        int lowest, highest;
 
        public chemical(int lowest, int highest) {
            super();
            this.lowest = lowest;
            this.highest = highest;
        }
 
        @Override
        public int compareTo(chemical o1) {
            int diff = this.highest - o1.highest;
            return (diff != 0)? diff : this.lowest - o1.lowest;
        }
         
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringTokenizer st;
         
        chemical[] c = new chemical[T];
        for(int i = 0; i < T ; i++) {
             st = new StringTokenizer(in.readLine());
            c[i] = new chemical(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(c);
        int tempH = c[0].highest;
        for(int j = 1; j < c.length ; j++) {
            if( tempH < c[j].lowest) {
                totalCnt++;
                tempH = c[j].highest;
            }
        }
        System.out.println(totalCnt);
    }
 
}
