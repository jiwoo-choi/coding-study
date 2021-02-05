import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    static char[] open = {' ','{' , '(', '<', '[' } ;
    static char[] close = {' ','}', ')' , '>', ']'};


    /**
     * open close 배열의 짝 맞춘 위치에 따라서
     * @param a
     * @return 0 이면 해당하는 케이스가 없음 , 음수면 close, 양수면 open 괄호의 인덱스.
     */
    public static int getIdxInfo(char a) {
        for(int i = 1 ; i <= 4 ; i++ ) {
            if (a == open[i]) return i;
        }
        for (int i = 1 ; i <= 4 ; i++) {
            if (a == close[i]) return -i;
        }
        return 0;
    }

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = 10;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            Stack<Integer> st = new Stack<>();
            int len = sc.nextInt();
            String ss = sc.next();

            // 홀수 일때는 무조건 짝이 안맞음.
            if (len % 2 != 0) {
                System.out.println( "#"+test_case + " 0");
                continue;
            };

            boolean flag = true;
            for (int i = 0 ; i < len ; i++) {
                int index = getIdxInfo(ss.charAt(i));
                if (index == 0) {
                    flag = false;
                    break;
                }
                if ( index > 0 ) {
                    st.push(index);
                } else if (index < 0) {
                    if (st.empty()) {
                        flag = false;
                        break; // 스택이 비어있는 경우.
                    } else {
                        int idx2 = st.pop();
                        if (idx2 != -index) { //짝이 안맞는경우.
                            flag= false;
                            break;
                        }
                    }
                }
            }

            if (!st.empty()) { // ㅅ택에 남아있으면 안된다.
                flag = false;
            }

            System.out.println( "#"+test_case + " " + (flag ? "1" : "0"));
        }
    }
}
