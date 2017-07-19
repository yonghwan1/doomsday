package first_week;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import first_week.SearchLoad.Point;

public class children {

	static int T;
	static int answer;
	static int[][] data;
	static ArrayList<Point>pdata;

	public static void main(String[] args) throws FileNotFoundException {
		String path = children.class.getResource("").getPath();
		
		System.setIn(new FileInputStream("C:\\Users\\student\\1week\\Solution\\Solution\\res\\조상자손_input.txt"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		int N = 0;

		for (int test_case = 1; test_case <= T; test_case++) {
			answer = 0;
			N = sc.nextInt();
//			data = new int[N][N];
//			pdata = new ArrayList<Point>();
			
			//clear
//			pdata.clear();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					data[i][j] = sc.nextInt();
//					Point p = new Point(i,j,data[i][j]);
//					pdata.add(p);
				}
			}
			
			Stack<Point> s = new Stack<Point>();
			
			System.out.println("#" + test_case + " " + answer);
		}

		if (sc != null) {
			sc.close();
			sc = null;
		}
	}

	public static class data{
		int edge;
		int nodeNumber;
		public data(int edge, int nodeNumber){
			this.edge = edge;
			this.nodeNumber = nodeNumber;
		}
	}
}
