import java.util.Arrays;
import java.util.Scanner;

/*
 * 0 = zero
 * 1 = one
 * 2 = two
 * 3 = three
 * 4 = four
 * 5 = five
 * 6 = six 
 * 7 = seven
 * 8 = eight
 * 9 = nine
 * */
public class Algo1_서울_06반_박지현 {
	static class Numbers implements Comparable{ //숫자와 영어단어를 저장하기 위한 클래스 
		int num; //숫자 
		String eng; //숫자의 영어단어 
		public Numbers(int num,String eng) { //숫자와 영어단어를 받아와 객체를 생성하는 생성자.
			super();
			this.num = num; //num매개변수로 받아온 정수는 숫자에 저장한다.
			this.eng = eng; //eng매개변수로 받아온 String은 숫자의 영어단어에 저장한다.
		}
		@Override
		public int compareTo(Object o) {
			return eng.compareTo(((Numbers)o).eng); //숫자에 대응하는 영어 단어를 사전순으로 정렬 및 출력하기 위함이다.
		}
		
	}
	//각 숫자에 대응하는 영어단어를 담은 String배열이다.
	static String[] englishNum = {"zero","one","two","three","four","five","siz","seven","eight","nine"};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); //Scanner를 사용하여 입력을 받는다.
		StringBuilder sb = new StringBuilder(); //sb객체를 생성해 출력결과물을 담는다.
		int M = sc.nextInt(); //첫 번째 정수를 받아와 M에 저장한다.
		int N = sc.nextInt(); //두 번째 정수를 받아와 N에 저장한다.
		int length = N-M+1; //N~M사이의 숫자와 영어단어를 담을 객체들의 배열길이이다.
		Numbers[] nums = new Numbers[length]; //N~M사이의 숫자와 영어단어를 담을 객체들의 배열이다.
		int idx = 0; ////숫자와 영어단어를 담는 객체 배열의 인덱스 탐색을 위한 변수이다.
		for(int i = M ; i<= N ; i++, idx++) { //M~N사이 숫자들을 객체 배열에 담는다.
			String temp; //숫자에 해당하는 영단어를 임시 보관할 변수이다.
			if(i < 10) temp = englishNum[i]; //10미만의 숫자인 경우 바로 영단어를 구할 수 있다.
			else { //10이상의 숫자인 경우 
				int ones = i%10; //십의 자리에서의 영단어와 
				int tens = i/10; //일의 자리에서의 영단어를 구한 뒤
			 temp = englishNum[tens] + englishNum[ones]; //이들을 이어 붙인 숫자에 해당하는 영단어를 구한다.
			}
			nums[idx] = new Numbers(i,temp); //숫자와 구한 영단어를 객체배열에 저장한다.
		}
		Arrays.sort(nums); //영어단어 사전순으로 숫자가 정렬되도록 한다.
		
		for(int i = 0; i < length; i++) { //객체 배열을 처음부터 끝까지 돌며 
			sb.append(nums[i].num); //숫자를 출력한다.
			if(i % 10 == 9) sb.append("\n"); //10개의 숫자가 나왔다면 개행을 한다.
			else sb.append(" "); //숫자 뒤에 띄어쓰기를 한다.
		}
		System.out.print(sb); //저네 결과를 출력한다.
	}

}
