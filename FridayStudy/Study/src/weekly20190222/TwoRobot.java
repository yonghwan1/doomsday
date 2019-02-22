package weekly20190222;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Graph implements Comparable<Graph> {
	int x;
	int y;
	int length;

	Graph(int x, int y, int length) {
		this.x = x;
		this.y = y;
		this.length = length;
	}

	public void print() {
		System.out.println("X : " + this.x + ", Y:" + this.y+", length :"+this.length);
	}
	
	@Override
	public int compareTo(Graph target) {
		if (this.length > target.length) {
			return 1;
		} else if (this.length == target.length) {
			return 0;
		}
		return -1;
	}
}

class Destination{
	int start;
	int end;
	Destination(int start, int end){
		this.start = start;
		this.end =end;
	}
}

public class TwoRobot {

	static ArrayList<Graph> Graphs = new ArrayList<>();
	
	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("res/tworobot_intput.txt"));
		Scanner sc = new Scanner(System.in);

		int Testcase = 0;
		Testcase = sc.nextInt();

		int sum = 0;
		int count, x, y, z;
		int start,end;
		Destination des;
		
		for (int tc = 1; tc <= Testcase; tc++) {
			count = sc.nextInt()-1;
			start = sc.nextInt();
			end = sc.nextInt();
			
			des = new Destination(start, end);	
			for (int i = 0; i < count; i++) {
				x = sc.nextInt();
				y = sc.nextInt();
				z = sc.nextInt();
				
				Graphs.add(new Graph(x,y,z));
			}
			
			Collections.sort(Graphs);
			for (Graph g : Graphs) {
				g.print();
			}
			sum = getLength();
			System.out.println("#" + tc + " sum: " + sum);
			System.out.println();
			Graphs.clear();

		}
		if (sc != null) {
			sc.close();
		}
	}
	
	static int getLength() {
		int sum = 0;

		return sum;
	}

}
