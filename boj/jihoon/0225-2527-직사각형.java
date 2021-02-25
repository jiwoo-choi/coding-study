package boj.p2527_직사각형;

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

        if (x1 < x2 && y1 < y2)
            return "a";
        else if (x1 == x2 && y1 == y2)
            return "c";
        else if (x1 == x2 || y1 == y2)
            return "b";
        else
            return "d";
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < 4; k++)
                    recs[j][k] = Integer.parseInt(st.nextToken());

            System.out.println(solution());
        }
    }
}
