package first_week;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class homework {
	static int T;
	static int answer;
	static int data[][];
	static ArrayList<LineData> lineData;

	public static void main(String[] args) throws FileNotFoundException {
		String file_path = "D:\\work\\gits\\gitHub\\doomsday\\Pro_study\\Solution\\Pro_study\\res\\first_week_homework.txt";
		System.setIn(new FileInputStream(file_path));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		int N = 0;

		for (int test_case = 1; test_case <= T; test_case++) {
			answer = 0;
			int target = sc.nextInt();
			int max = 777;
			N = sc.nextInt();

			lineData = new ArrayList<LineData>();

			for (int i = 0; i < N; i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();
				int value = sc.nextInt();
				if (max < value) {
					max = value;
				}
				LineData point = new LineData(start, end, value);
				lineData.add(point);
			}

			data = new int[target][target];
			resetArrary(target, target, max);

			System.out.println("target : " + target);
			setArraryFromLineData();
			printArrary(target, target);
			
			//algorithm

			System.out.println("#" + test_case + " " + answer);
		}

		if (sc != null) {
			sc.close();
			sc = null;
		}
	}

//	public static void dkt(int x,int data[][],  ){
//		
//	}

	public static void resetArrary(int x, int y, int resetValue) {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (i == j) {
					data[i][j] = 0;
				} else {
					data[i][j] = resetValue;
				}
			}
		}
	}

	public static void setArraryFromLineData() {
		for (int i = 0; i < lineData.size(); i++) {
			LineData target = lineData.get(i);
			data[target.start - 1][target.end - 1] = target.value;
		}
	}

	public static void printArrary(int x, int y) {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				System.out.print(data[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static class LineData {
		int start;
		int end;
		int value;

		public LineData(int start, int end, int value) {
			this.start = start;
			this.end = end;
			this.value = value;
		}

		public void print() {
			System.out.println("start: " + this.start + "\t,end: " + this.end + "\t,value:" + this.value);
		}
	}

	public static void printLineData() {
		for (int i = 0; i < lineData.size(); i++) {
			lineData.get(i).print();
		}
	}
}
