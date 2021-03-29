import java.io.*;
import java.util.*;

public class Main {
    static String src, dest;

    static int solution() {
        StringBuilder sb = new StringBuilder(dest);
        while (sb.length() > src.length()) {
            if (sb.charAt(sb.length()-1) == 'B') {
                sb.setLength(sb.length()-1);
                sb.reverse();
            } else
                sb.setLength(sb.length()-1);
        }
        return sb.toString().equals(src)? 1 : 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        src = in.readLine();
        dest = in.readLine();

        System.out.println(solution());
    }
}
