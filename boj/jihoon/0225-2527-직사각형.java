import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] recs = new int[2][4];
    
    static final int LEFT = 0;
    static final int RIGHT = 1;
    
    static final int X1 = 0;
    static final int Y1 = 1;
    static final int X2 = 2;
    static final int Y2 = 3;

    
    static String solution() {
        int x1 = Math.max(recs[LEFT][X1], recs[RIGHT][X1]);
        int y1 = Math.max(recs[LEFT][Y1], recs[RIGHT][Y1]);

        int x2 = Math.min(recs[LEFT][X2], recs[RIGHT][X2]);
        int y2 = Math.min(recs[LEFT][Y2], recs[RIGHT][Y2]);

        if (y2 - y1 > 0 && x2 - x1 > 0)
            return "a";
        else if (x1 == x2 && y1 == y2)
            return "c";
        else if (x1 == x2 || y1 == y2)
            return "b";
        else
            return "d";
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            String[] input = in.readLine().split(" ");

            for (int j = 0; j < 2; j++) {
                int offset = j*4;
                recs[j][X1] = Integer.parseInt(input[offset+0]);
                recs[j][Y1] = Integer.parseInt(input[offset+1]);
                recs[j][X2] = Integer.parseInt(input[offset+2]);
                recs[j][Y2] = Integer.parseInt(input[offset+3]);
            }

            System.out.println(solution());
        }
    }
}
