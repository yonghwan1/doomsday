package week.second.mst;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class mst {

	static int T;
	static int N;
	static int answer;
	static int[][] data;
	static ArrayList<Point> arr = new ArrayList<Point>();
	static int MAX = 777;
	static int[] visit;
	static int sum;

	public static void main(String[] args) throws FileNotFoundException {
		String path = "C:\\Users\\student\\doomgary\\Pro_study\\Solution\\Pro_study\\src\\second_week\\mst\\test_input.txt";
		System.setIn(new FileInputStream(path));
		Scanner sc = new Scanner(System.in);
		
		// Testcase count
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			//clear
			answer = 0;
			arr.clear();
			
			N = sc.nextInt();
			data = new int[N][N];
			visit = new int [N];

			for (int i=0;i<N;i++){
				visit[i] = 0;
			}
			sum = 0;

			resetArrary();

			int[] xArrary = new int[N];
			int[] yArrary = new int[N];
			
			for (int i = 0; i < N; i++) {
				xArrary[i] = sc.nextInt();
			}
			
			for (int i = 0; i < N; i++) {
				yArrary[i] = sc.nextInt();
			}

			
			for (int i = 0; i < N; i++) {				
				Point p = new Point(xArrary[i],yArrary[i]);
				arr.add(p);
			}
			
			for (int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					if (i == j){
						data[i][j] = 0;
					} else{
						data[i][j] = getCost(arr.get(i), arr.get(j));
					}
				}
			}
			
//			printArr();
//			printArrary();
			visit[0] = 1;
			answer = mst();

			System.out.println("#" + test_case + " " + answer);
		}

		if (sc != null) {
			sc.close();
			sc = null;
		}
	}
	
	public static int mst(){
		int check = 0;
		for(int i=0;i<N;i++){
			check +=visit[i];
		}
		if (check == N){
			return sum;
		}
		
		int min_index = getMinIndex();
		if (min_index == N+1){
			return sum;
		}
		visit[min_index] = 1;
		return mst();
	}

	public static int getMinIndex(){
		int min=99999999;
		int min_index = N+1;
		
		for (int i=0;i<N;i++){
			if (visit[i]== 1){
				for(int j=i;j<N;j++){
					if( i==j){
						continue;
					}
					
					if(visit[j]==1){
						continue;
					}
					
					int data = getCost(arr.get(i),arr.get(j));
					if (min > data){
						min = data;
						min_index = j;
					}
				}
			}
		}
		
		sum += min;
		return min_index;
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
	}
	
	public static void printArr(){
		for(int i=0;i<arr.size();i++){
			arr.get(i).print();
		}
	}
	
	public static int getCost(Point beforePoint, Point AfterPoint){
		return (beforePoint.x - AfterPoint.x)*(beforePoint.x - AfterPoint.x) 
				+ (beforePoint.y-AfterPoint.y)*(beforePoint.y-AfterPoint.y);
	}
	
	public static void resetArrary(){
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				data[i][j] = MAX;
			}
		}
		
	}
	
	public static void printArrary() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(data[i][j]+ "\t");
			}
			System.out.println();
		}
	}

}
