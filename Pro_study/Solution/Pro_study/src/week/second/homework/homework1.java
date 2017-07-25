package week.second.homework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class homework1 {

	static int T;
	static int sum;
	static int answer;
	static int[][] data;

	public static void main(String[] args) throws FileNotFoundException {
		String path = "F:\\study\\gitHub\\doomsday\\Pro_study\\Solution\\Pro_study\\src\\week\\second\\homework\\homework1.txt";
		System.setIn(new FileInputStream(path));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		int N = 0;

		for (int test_case = 1; test_case <= T; test_case++) {
			answer = 0;
			sum = 0;
			N = sc.nextInt();
			data = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					data[i][j] = sc.nextInt();
				}
			}

			printArrary(N);
			backtracking(0);
			System.out.println("#" + test_case + " " + answer);
		}

		if (sc != null) {
			sc.close();
			sc = null;
		}
	}

	public static void printArrary(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(data[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void backtracking(int i){
	}

}
