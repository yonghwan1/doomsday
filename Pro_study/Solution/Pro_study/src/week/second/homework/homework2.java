package week.second.homework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class homework2 {

	static int T;
	static int answer;
	static int[] data;
	static int sol;

	public static void main(String[] args) throws FileNotFoundException {
		String path = "F:\\study\\gitHub\\doomsday\\Pro_study\\Solution\\Pro_study\\src\\week\\second\\homework\\homework2.txt";
		System.setIn(new FileInputStream(path));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		int N = 0;
		int money;

		for (int test_case = 1; test_case <= T; test_case++) {
			money = sc.nextInt();
			N = sc.nextInt();
			answer = 0;
			data = new int[N];

			for (int i = 0; i < N; i++) {
				data[i] = sc.nextInt();
			}

//			printArrary(N);
			answer = count_coin(money, N);
			System.out.println("#" + test_case + " " + answer);
		}

		if (sc != null) {
			sc.close();
			sc = null;
		}
	}

	public static void printArrary(int N) {
		for (int i = 0; i < N; i++) {
			System.out.print(data[i]+" ");
		}
		System.out.println();
	}
	
	public static int count_coin(int money , int N){
		int count =0;
		
		while (money != 0){
			for(int i=N-1;i>=0;i--){
				if(data[i]>money){
					continue;
				}else{
					money = money - data[i];
					count++;
				}
			}
		}
		return count;
		
	}

}
