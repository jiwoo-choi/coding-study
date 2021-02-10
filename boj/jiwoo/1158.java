import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

class Main {
    public static void main(String[] args) {
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	Scanner sc = new Scanner(System.in);
    	int n = sc.nextInt();
    	int m = sc.nextInt();
    	
    	Deque<Integer> dq = new LinkedList<>();
    	for(int i = 0 ; i < n ; i++) {
    		dq.add(i+1);
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("<");
    	while(!dq.isEmpty()) {
    		for(int i = 1 ; i <= m-1 ;i++) {
    			int last = dq.removeFirst();
        		dq.addLast(last);	
    		}
    		sb.append(dq.removeFirst()).append(", ");
    	}
    	sb.delete(sb.length()-2, sb.length());
    	sb.append(">");
    	System.out.println(sb);
    }
}

