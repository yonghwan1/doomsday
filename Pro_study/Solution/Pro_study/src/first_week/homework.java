package first_week;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class homework {
	static int T;
	static int answer;
	static int data[][];

	public static void main(String[] args) throws FileNotFoundException {
		String file_path = "D:\\work\\gits\\gitHub\\doomsday\\Pro_study\\Solution\\Pro_study\\res\\first_week_homework.txt";
		System.setIn(new FileInputStream(file_path));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		int N = 0;

		for (int test_case = 1; test_case <= T; test_case++) {
			answer = 0;
			int target = sc.nextInt();
			N = sc.nextInt();			
			
			data = new int[N][3];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 3; j++) {
					data[i][j] = sc.nextInt();
				}
			}

			System.out.println("target : "+ target);
			printArrary(N,3);
			System.out.println("#" + test_case + " " + answer);
		}

		if (sc != null) {
			sc.close();
			sc = null;
		}
	}

	public static void printArrary(int x, int y) {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				System.out.print(data[i][j]+"\t");
			}
			System.out.println();
		}
	}
}
