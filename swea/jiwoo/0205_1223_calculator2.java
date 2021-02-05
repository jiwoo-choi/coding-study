import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

class Solution {

    private static Integer calculate(List<String> arg) {
        Stack<Integer> st = new Stack<Integer>();
        for (String str : arg) {
            int validator = str.charAt(0) - '0';
            if (validator < 0 || validator > 9) {
                //스택에서 두개를 꺼낸다.
                int f = st.pop();
                int s = st.pop();
                switch(str) {
                    case "+" :
                        st.push(f + s);
                        break;
                    case "-" :
                        st.push(f - s);
                        break;
                    case "*" :
                        st.push(f * s);
                        break;
                    case "/" :
                        st.push( f / s);
                        break;
                }
            } else {
                st.push(Integer.parseInt(str));
            }
        }
        return st.pop();
    }

    private static List<String> convert(String arg) {

        Stack<Character> st = new Stack<Character>();
        StringTokenizer toks = new StringTokenizer(arg, "+-*/", true);
        List<String> result = new ArrayList<>();

        while(toks.hasMoreTokens()) {
            String tok= toks.nextToken();
            int validator = tok.charAt(0) - '0';
            if (validator < 0 || validator > 9) {
                char operator = tok.charAt(0);
                int currp = map.get(operator);
                if (st.empty()) {
                    st.push(operator);
                } else {
                    while(!st.empty() && map.get(st.peek()) >= currp) {
                        result.add(st.pop().toString());
                    }
                    st.push(operator);
                }
            } else {
                result.add(tok);
            }
        }

        while(!st.empty()) {
            result.add(st.pop().toString());
        }

        return result;
    }

    static HashMap<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            sc.nextInt();
            List<String> result = convert(sc.next());
            Integer val = calculate(result);
            System.out.println("#" + test_case + " " + val);
        }
    }
}
