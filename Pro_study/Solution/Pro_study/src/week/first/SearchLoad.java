package week.first;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class SearchLoad {

	static int T;
	static int answer;
	static int[][] data;
	static ArrayList<Point> pdata;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream(
				"D:\\work\\gits\\gitHub\\doomsday\\Pro_study\\Solution\\Pro_study\\res\\maze_input.txt"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		int N = 0;

		for (int test_case = 1; test_case <= T; test_case++) {
			answer = 0;
			N = sc.nextInt();
			data = new int[N][N];
			pdata = new ArrayList<Point>();

			// clear
			pdata.clear();
			clear(N);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					data[i][j] = sc.nextInt();
					Point p = new Point(i, j, data[i][j]);
					pdata.add(p);
				}
			}
			// printArrary(N);

			Stack<Point> s = new Stack<Point>();
			Point start = findData(2);

			answer = search(s, start, N);

			System.out.println("#" + test_case + " " + answer);
		}

		if (sc != null) {
			sc.close();
			sc = null;
		}
	}

	public static void clear(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				data[i][j] = 0;
			}
		}
	}

	public static void printArrary(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(data[i][j]);
			}
			System.out.println();
		}
	}

	public static int search(Stack<Point> s, Point start, int size) {		

		s.push(start);
		while (!s.isEmpty()) {
			Point p = s.pop();
			if (p.visit == true)
				continue;

			if (p.data == 1) {
				continue;
			}

			if (p.data == 3) {
				return 1;
			}

			p.visit = true;
			{
				int index = findDataIndex(p);
				if (index == -1) {
					return 0;
				}

				if (pdata.get(index - 1).visit != true) {
					s.push(pdata.get(index - 1));
				}

				if (pdata.get(index + 1).visit != true) {
					s.push(pdata.get(index + 1));
				}

				if (pdata.get(index - size).visit != true) {
					s.push(pdata.get(index - size));
				}
				if (pdata.get(index + size).visit != true) {
					s.push(pdata.get(index + size));
				}

			}

		}
		return 0;
	}

	public static void dfs() {

		return;
	}

	public static class Point {
		int x;
		int y;
		int data;
		boolean visit;

		public Point(int x, int y, int data) {
			this.x = x;
			this.y = y;
			this.data = data;
			this.visit = false;
		}
	}

	public static Point findData(int searchData) {
		for (int i = 0; i < pdata.size(); i++) {
			if (pdata.get(i).data == searchData) {
				return pdata.get(i);
			}
		}
		return null;
	}

	public static int findDataIndex(Point p) {
		for (int i = 0; i < pdata.size(); i++) {
			if (pdata.get(i).equals(p)) {
				return i;
			}
		}
		return -1;
	}

}
