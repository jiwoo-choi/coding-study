import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));       
        // input 입력 
        int input = Integer.parseInt(in.readLine());
        int fiveLimit = input/5;
        int i = fiveLimit, sum = -1;
        for( ; 0 <= i ; i--) {
        	int temp = input - i*5;
        	if(temp%3 == 0) {
        		sum = i + temp/3; break;
        	}
        }
        System.out.println(sum);
	}
}
