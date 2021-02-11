mport java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	private static ArrayList<String> mat[];
	private static int ret;
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = 10;
		
		for(int i = 1; i <= T ; i++) {
			int N = Integer.parseInt(in.readLine());
			mat = new ArrayList[N+1];
			ret = 1;
			int idx = 0;
			for(int j = 1 ; j <= N ;j++) {
				st = new StringTokenizer(in.readLine(), " ");
				idx = Integer.parseInt(st.nextToken());
				mat[idx] = new ArrayList<String>();
				while(st.hasMoreTokens()) {
					mat[idx].add(st.nextToken());
				}
			}
			dfs(1);
			sb.append("#");
			sb.append(i);
			sb.append(" ");
			sb.append(ret);
			sb.append("\n");
		}
		System.out.print(sb);
		
	}
	private static void dfs(int node) {
		int size = mat[node].size();
		String middle = mat[node].get(0);
		if(size == 1) {
			if(!isNumber(middle)) ret = 0;
			return; 
		}
		if( middle.equals("+") ||  middle.equals("-")|| middle.equals("*")|| middle.equals("/")) {
			dfs(node*2);
			dfs(node*2+1);
		}
		else {
			ret = 0;
			return;
		}
	}
	private static boolean isNumber(String st) {
		try{
			Integer.parseInt(st);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
}

