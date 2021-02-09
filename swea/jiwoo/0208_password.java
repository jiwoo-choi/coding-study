import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

class Solution {
    	
	public static void add(List<Integer> list, String[] command) {
		int x = Integer.parseInt(command[1]);
		int y = Integer.parseInt(command[2]);
		list.addAll(x, Arrays.stream(Arrays.copyOfRange(command, 3, 3+y)).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t =10;
		for (int test_case = 1 ; test_case <= t ; test_case++) {
			int n = Integer.parseInt(bf.readLine());
			String[] origns = bf.readLine().split(" ");
			
			List<Integer> nodes = new LinkedList<Integer>();
			
			for (int i = 0 ; i < n ;i ++) {
				nodes.add(Integer.parseInt(origns[i]));
			}
			
			int m = Integer.parseInt(bf.readLine());		
			
			String[] commands = bf.readLine().split("I");

			int len = commands.length;

			for(int i = 1; i < len ; i++) {
				String[] micors = commands[i].split(" ");
				add(nodes, micors);
			}
			System.out.print("#" + test_case + " ");
			nodes.stream().limit(10).forEach((val) -> System.out.print(val + " "));
			System.out.println();
			
		}
	}
}
