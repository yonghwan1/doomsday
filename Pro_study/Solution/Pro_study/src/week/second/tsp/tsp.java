package week.second.tsp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class tsp {

	static int T;
	static int answer;
//	static int[][] data;
	static int N;
	static int MAX = 999999;
	static Point company, home;
	static ArrayList<Point> arr = new ArrayList<Point>();

	public static void main(String[] args) throws FileNotFoundException {
		String path = 
//				"C:\\Users\\student\\doomgary\\Pro_study\\Solution\\Pro_study\\src\\second_week\\test_input.txt";
				"D:\\work\\gits\\gitHub\\doomsday\\Pro_study\\Solution\\Pro_study\\src\\week\\second\\tsp\\test_input.txt";
		
		System.setIn(new FileInputStream(path));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		N = 0;

		for (int test_case = 1; test_case <= T; test_case++) {
			answer = 999999;
			N = sc.nextInt();
			arr.clear();
//			data = new int[N][N];			
			
//			for(int i=0;i<N;i++){
//				for (int j=0;j<N;j++){
//					if(i==j){
//						data[i][j] =0;
//						continue;
//					}
//					data[i][j] = MAX;
//				}
//			}
						
			company = new Point(sc.nextInt(),sc.nextInt());
			home = new Point(sc.nextInt(),sc.nextInt());
			

			for (int i = 0; i < N; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				Point p = new Point(x,y);
				arr.add(p);
			}

//			for(int i=0;i<arr.size();i++){
//				arr.get(i).print();
//			}
			
			
//			printArrary();
			perm(0,N);
			
//			for (int i=0;i<N;i++){
//				for(int j=0;j<N;j++){
//					data[i][j] = getDistance(arr.get(i),arr.get(j));
//				}
//			}
//			
//			printArrary();
			
			System.out.println("#" + test_case + " " + answer);
		}

		if (sc != null) {
			sc.close();
			sc = null;
		}
	}
	
	static void perm(int k, int n){
		if(k==n){
			int sum = 0;
			for(int i=1;i<arr.size();i++){
				sum +=getDistance(arr.get(i-1),arr.get(i));
//				arr.get(i).print();
			}
			// home
			sum += getDistance(company,arr.get(0));
			// company
			sum += getDistance(home,arr.get(arr.size()-1));
			
			if (sum < answer){
				answer = sum;
			}
//			System.out.print("sum : "+sum);
//			System.out.println();
			
			return;
		}

		for(int i=k;i<n;i++){
			swip(k,i);
			perm(k+1,n);
			swip(k,i);
		}
	}

//	public static void printArrary() {
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(data[i][j]+ " ");
//			}
//			System.out.println();
//		}
//	}
	
	public static class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public void print(){
			System.out.print("x: "+this.x+", y:"+this.y);
			System.out.println();
		}
		
		public void swipe(Point target){
			this.x = target.x;
			this.y = target.y;
		}
		
		public void swipe(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static int getDistance(Point before, Point after){
		return Math.abs(after.x - before.x) + Math.abs(after.y - before.y);
	}


	public static void swip(int i, int j){
		int x = arr.get(i).x;
		int y = arr.get(i).y;
		
		arr.get(i).swipe(arr.get(j));
		arr.get(j).swipe(x,y);
	}
}
