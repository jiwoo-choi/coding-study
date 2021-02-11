import java.util.*;
import java.io.*;

class Main {
    
    static int isSelected[];
    static int arr[];
    static StringBuilder sb = new StringBuilder();
    
    static void go(int cnt, int target, int n, int h) {
    	
        // 몇개를 선택해야하는가?
        if (cnt == target) {
            //isSelected에 나와있는 모든것을 개수대로 출력한다
            for(int i = 0; i < n ; i++) {
                for (int j = 0 ; j < isSelected[i] ; j++) {
                	sb.append(arr[i]).append(" ");
                }
            }
            sb.append('\n');
            return;
        }
  
        for(int i = h ; i < n ; i++){
            isSelected[i]++;
            go(cnt+1, target, n, i);
            isSelected[i]--;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        // 실제 값이 들어갈 배열을 의미한다.
        arr= new int[n];
        isSelected = new int[n];
        
        for (int i = 0 ; i < n ; i++) {
            arr[i] = sc.nextInt();
        } 
        
        Arrays.sort(arr);
        go(0,m,n, 0);
        sb.deleteCharAt(sb.length()-1);
        System.out.print(sb);
    }    
    
}
