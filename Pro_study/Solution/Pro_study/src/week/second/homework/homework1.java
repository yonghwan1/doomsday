package week.second.homework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class homework1 {

	static int T;
	static int sum;
	static int answer;
	static int N;
	static int[][] data;
	static int[] col;
	static int[] sol;

	public static void main(String[] args) throws FileNotFoundException {
		String path =
		// "F:\\study\\gitHub\\doomsday\\Pro_study\\Solution\\Pro_study\\src\\week\\second\\homework\\homework1.txt";
		"D:\\work\\gits\\gitHub\\doomsday\\Pro_study\\Solution\\Pro_study\\src\\week\\second\\homework\\homework1.txt";
		System.setIn(new FileInputStream(path));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			answer = 0;
			sum = 99999;
			N = sc.nextInt();
			data = new int[N][N];
			col = new int[N];
			sol = new int[N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					data[i][j] = sc.nextInt();
				}
			}
			// printArrary(N);
			backtracking(0);
			System.out.println("#" + test_case + " " + sum);
		}

		if (sc != null) {
			sc.close();
			sc = null;
		}
	}

	public static void printArrary(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void backtracking(int row) {
		if (row == N) {
			int min = 0;
			for (int i = 0; i < N; i++) {
				// System.out.print(sol[i]+" ");
				min += data[sol[i]][i];
			}

			if (sum > min) {
				sum = min;
			}
			// System.out.print("min : "+ min);
			// System.out.println("");
			return;
		}

		for (int i = 0; i < N; i++) {
			// selectct data[row][i]

			if (col[i] == 1) {
				continue;
			}

			sol[row] = i;
			col[i] = 1;

			backtracking(row + 1);

			col[i] = 0;

		}
	}

}
