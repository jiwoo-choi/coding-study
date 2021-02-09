import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

class Main {
    
	
	static HashMap<Character, Integer> map = new HashMap<>();
	static int multiplier = 1;
	static {
		map.put('+', 1);
		map.put('-', 1);
		map.put('*', 2);
		map.put('/', 2);
	}

	public static boolean isOperator(String s) {
		char ch = s.charAt(0);
		if (ch - 'A' < 0 || ch - 'A' > 26) {
			return true;
		} else {
			return false;
		}
	}
	
    public static void main(String[] args) throws IOException {
    	
    	Scanner sc = new Scanner(System.in);
    	String s = sc.next();
    	StringTokenizer st = new StringTokenizer(s, "+-/*()", true);
    	Stack<Character> opst = new Stack<>();
    	String[] list = new String[st.countTokens()];
    	int pos = 0;
    	
    	while(st.hasMoreTokens()) {
    		
    		String token = st.nextToken();
    		
    		if (isOperator(token)) {
    			char operator = token.charAt(0);
    			
    			if (operator == '(') {
        			opst.add(operator);
        			continue;
    			}
    			
    			// 끝났으면 무조건 해줘야한다. (서브루틴)
    			if (operator == ')') {
    				while(!opst.empty() && opst.peek() != '(') {
    					list[pos++] = opst.pop().toString();
    				}
    				if (!opst.empty()) {
        				opst.pop(); // '(' 빼는 용도.
    				}
    				continue;
    			}
    			
    			int priority = map.get(operator);
    			// 여기서는 빠지는게 아니라 유지하는거기 때문에 (나오면 처음이란 뜻이 됨.
    			while(!opst.empty() && opst.peek() != '(' && map.get(opst.peek()) >= priority) {
        			list[pos++] = opst.pop().toString();
    			}
    			opst.add(operator);
    		} else {
    			list[pos++] = token;
    		}    		
    	}
    	while(!opst.empty()) {
			list[pos++] = opst.pop().toString();
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for(String s1 : list) {
    		if (s1 != null) {
    			sb.append(s1);
    		}
    	}
    	System.out.println(sb);
    	
    }    
    
}
