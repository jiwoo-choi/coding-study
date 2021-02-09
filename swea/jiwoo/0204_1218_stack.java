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

    //open일경우  +인덱스
    //close의 경우 -인덱스
    //아무것도 없을경우 0;
    public static int validator(char a) {
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
            Stack<Character> st = new Stack<>();
            int len = sc.nextInt();
            String ss = sc.next();
            if (len % 2 != 0) {
                System.out.println( "#"+test_case + " 0");
            };

            boolean flag = true;
            for (int i = 0 ; i < len ; i++) {
                int index = validator(ss.charAt(i));
                if (index == 0) {
                    flag = false;
                    break;
                }
                if ( index > 0 ) {
                    st.push(open[index]);
                } else if (index < 0) {
                    if (st.empty()) {
                        flag = false;
                        break; // 실패하는경우지.
                    } else {
                        char value = st.pop();
                        int index2 = validator(value);
                        if (index2 != -index) { //짝이 안맞는경우.
                            flag= false;
                            break;
                        }
                    }
                }
            }


            if (!st.empty()) { // 뭔가 남아있으면 안된다.
                flag = false;
            }
            System.out.println( "#"+test_case + " " + (flag ? "1" : "0"));
        }
    }
}
