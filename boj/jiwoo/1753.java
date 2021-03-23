import java.util.*;
import java.io.*;

public class Main {

	static class Vertex implements Comparable<Vertex>{
		int no, length;

		public Vertex(int no, int length) {
			this.no = no;
			this.length = length;
		}

		@Override
		 public int compareTo(Vertex that) {
	        if (this.length < that.length) {
	            return -1;
	        } else if (this.length == that.length) {
	            if (this.no < that.no) return -1;
	            else if (this.no > that.no) return 1;
	            else return 0;
	        } else {
	            return 1;
	        }
	    }	
	}
	
	public static int getInt(String s) {
		return Integer.parseInt(s);
	}
    static final int inf = 1000000000;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int V = getInt(st.nextToken());
		int E = getInt(st.nextToken());
		int root = getInt(bf.readLine());
		List<Vertex>[] graph = (List<Vertex>[]) new List[V+1];

		int distance[] = new int[V+1];
        boolean visited[] = new boolean[V+1];
		for (int i = 1 ; i <= V ; i++) {
			graph[i] = new ArrayList<Vertex>();
			distance[i] = inf;
		}
        
		for (int i = 0; i < E ; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int from = getInt(st.nextToken());
			int to = getInt(st.nextToken());
			int w = getInt(st.nextToken());
			graph[from].add(new Vertex(to, w));
		}
        
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.add(new Vertex(root, 0));
		distance[root] = 0;
		
	    while(!pq.isEmpty()) {
	    	
	    	Vertex here = pq.poll();
	    	int no = here.no;
            if (visited[no] == true) continue;
            visited[no] = true;
	    	
	    	for (int i = 0 ; i < graph[no].size(); i++) {
	    		Vertex there = graph[no].get(i);
	    		int thereno = there.no;
	    		int therelen = there.length;
	    		if (distance[thereno] > distance[no] + therelen) {
	    			distance[thereno] = distance[no] + therelen;
	                pq.add(new Vertex(thereno,distance[thereno]));
	    		}
	    	}
	    }
	    
	   	    
        StringBuilder sb = new StringBuilder();
        
	    
	    for (int i = 1 ; i <= V ; i++) {
            if (distance[i] == inf) {
                sb.append("INF");
                sb.append("\n");
            } else {
                sb.append(distance[i]);
                sb.append("\n");
            }
	    }
	    System.out.println(sb);
	}
}
