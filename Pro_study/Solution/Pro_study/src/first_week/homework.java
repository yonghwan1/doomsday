package first_week;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class homework {
	static int T;
	static int answer;
	static int data[][];
	static ArrayList<LineData> lineData = new ArrayList<LineData>();
	static ArrayList<Integer> distance = new ArrayList<Integer>();
	static ArrayList<Integer> selected = new ArrayList<Integer>();
	static int MAX = 999;

	public static void main(String[] args) throws FileNotFoundException {
		String file_path = 
				//"D:\\work\\gits\\gitHub\\doomsday\\Pro_study\\Solution\\Pro_study\\res\\first_week_homework.txt";
				"F:\\study\\gitHub\\doomsday\\Pro_study\\Solution\\Pro_study\\res\\first_week_homework.txt";
		System.setIn(new FileInputStream(file_path));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		int N = 0;

		for (int test_case = 1; test_case <= T; test_case++) {
			// init
			answer = 0;
			
			lineData.clear();
			distance.clear();
			selected.clear();
			
			int target = sc.nextInt();
			N = sc.nextInt();

			for (int i = 0; i < N; i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();
				int value = sc.nextInt();
				
				LineData point = new LineData(start, end, value);
				lineData.add(point);
			}

			data = new int[target][target];
			resetArrary(target, target, MAX);

			System.out.println("target : " + target);
			setArraryFromLineData();
			printArrary(target, target);
			
			//algorithm

			dkt(1, target);
			
			System.out.println("#" + test_case + " " + answer);
		}

		if (sc != null) {
			sc.close();
			sc = null;
		}
	}

	public static void dkt(int x, int size){
		selected.add(x);
		int target;
		
		for (int i=0; i<size;i++){
			target = data[x-1][i];
			if ((target > 0)&&(target < MAX)){
				distance.add(target);
			}
		}
		
		while u not equal v
			d[w] 
		    u <- 
		
	}

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
