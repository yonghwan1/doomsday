package week.second.tsp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class tsp {

	static int T;
	static int answer;
	static int[][] data;
	static int N;
	static int MAX = 999999;
	static ArrayList<Point> arr = new ArrayList<Point>();

	public static void main(String[] args) throws FileNotFoundException {
		String path = "C:\\Users\\student\\doomgary\\Pro_study\\Solution\\Pro_study\\src\\second_week\\test_input.txt";
		System.setIn(new FileInputStream(path));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		N = 0;

		for (int test_case = 1; test_case <= T; test_case++) {
			answer = 0;
			N = sc.nextInt();
			data = new int[N][N];
			
			for(int i=0;i<N;i++){
				for (int j=0;j<N;j++){
					if(i==j){
						data[i][j] =0;
						continue;
					}
					data[i][j] = MAX;
				}
			}
						
			Point company = new Point(sc.nextInt(),sc.nextInt());
			Point home = new Point(sc.nextInt(),sc.nextInt());
			

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
			
			for (int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					data[i][j] = getDistance(arr.get(i),arr.get(j));
				}
			}
			
			printArrary();
			
			System.out.println("#" + test_case + " " + answer);
		}

		if (sc != null) {
			sc.close();
			sc = null;
		}
	}
	
	static void perm(int k, int n){
		if(k==n){
			for(int i=0;i<arr.size();i++){
				arr.get(i).print();
			}
			System.out.println();
			return;
		}

		for(int i=k;i<n;i++){
			swipe(k,i);
			perm(k+1,n);
			swipe(k,i);
		}
	}

	public static void printArrary() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(data[i][j]+ " ");
			}
			System.out.println();
		}
	}
	
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


	public static void swipe(int i, int j){
		int x = arr.get(i).x;
		int y = arr.get(i).y;
		
		arr.get(i).swipe(arr.get(j));
		arr.get(j).swipe(x,y);
	}
}
