package weekly20190222;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Arrow2 {

	public static class Point implements Comparable<Point> {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void print() {
			System.out.println("X : " + this.x + ", Y:" + this.y);
		}

		@Override
		public int compareTo(Point target) {
			if (this.x > target.x) {
				return 1;
			} else if (this.x == target.x) {
				return 0;
			}
			return -1;
		}
	}

	static ArrayList<Point> points = new ArrayList<Point>();

	public static void main(String[] args) throws FileNotFoundException {

//		System.setIn(new FileInputStream("res/arrow_input2.txt"));
		Scanner sc = new Scanner(System.in);

//		int Testcase = 0;
//		Testcase = sc.nextInt();

		int sum = 0;
		int count, x, y;
		//for (int tc = 1; tc <= Testcase; tc++) {
			count = sc.nextInt();
			for (int i = 0; i < count; i++) {
				x = sc.nextInt();
				y = sc.nextInt();
				points.add(new Point(x, y));

			}
			Collections.sort(points);
//			for (Point p : points) {
//				p.print();
//			}
			sum = getLength();
			//System.out.println("#" + tc + " sum: " + sum);
            System.out.println(sum);
			//System.out.println();
			points.clear();

		//}
		if (sc != null) {
			sc.close();
		}
	}
	
	static int getLength() {
		int sum = 0;
		for (Point p : points) {
			sum+=getMin(p);
		}

		return sum;
	}
	
	static int getMin(Point target) {
		int min=1000000;
		for (Point p : points) {
			if(target.y==p.y) {
				//same color
				if(target.x == p.x) {
					// me
					continue;
				} else {
					int length =Math.abs(target.x-p.x); 
					if(min > length) {
						min=length;
					}
				}
				
			} else {
				//other color
			}
		}
		
		return min;
	}

}
