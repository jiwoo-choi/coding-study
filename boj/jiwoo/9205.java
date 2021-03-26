package class_p;
import java.util.*;

public class boj_9205 {

	static class Pair {
		int x;
		int y;
		public Pair(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	

	static int distance(Pair p1, Pair p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int test = 0 ; test < t ; test++) {
			List<Pair> p = new ArrayList<Pair>();
			int n = sc.nextInt();
			boolean[][] dist = new boolean[n+2][n+2];
			
			p.add(new Pair(sc.nextInt(), sc.nextInt()));
			for (int j = 0 ; j < n ; j++) {
				p.add(new Pair(sc.nextInt(), sc.nextInt()));
			}
			p.add(new Pair(sc.nextInt(), sc.nextInt()));
			
			for (int i = 0 ;i < n+2 ; i++) {
				for (int j = 0 ;j < n+2; j++) {
					if ( i == j ) continue;
					int distance = distance(p.get(i), p.get(j));
					if (distance <= 1000) {
						dist[i][j] = true;
					}
				}
			}
			
			for (int k = 0 ; k < n+2 ; k++) {
				for (int i = 0 ; i < n+2 ; i++) {
					for (int j = 0 ; j < n+2 ;j++ ) {
						if (i == j || i == k || j == k ) continue;
						if (dist[i][j] == false && dist[i][k] == true && dist[k][j] == true)  {
							dist[i][j] = true;
						}
					}
				}
			}
			
//			System.out.println();
//			for (int i = 0 ; i < n+2; i++) {
//				for (int j = 0 ; j < n+2; j++) {
//					System.out.print(dist[i][j] + " ");
//				}
//				System.out.println();
//				
//			}

			System.out.println(dist[0][n+1] ? "happy" : "sad");
			
//			System.out.println(dist[0][n-1] !== Integer.MAX_VALUE);
			
			
		}
		
	}

}
