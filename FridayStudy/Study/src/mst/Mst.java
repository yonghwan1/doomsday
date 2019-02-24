package mst;

/*
 * URL : http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=340&sca=3020
 * */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mst {
	private static Scanner sc;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/mst_intput.txt"));
		sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[][] map = new int[N][N];
		int len = 0;
		int[] label = new int[N];
		Vertex[] points = new Vertex[5050];

		for (int i = 0; i < N; ++i) {
			label[i] = i;
			for (int j = 0; j < N; ++j) {
				map[i][j] = sc.nextInt();
				if (i < j) {
					points[len++] = new Vertex(i, j, map[i][j]);
				}
			}
		}
		// 1. 정렬
		quickSort(points, 0, len - 1);

		// 2. 어사이클
		int nodes = 0, index = 0;
		int cost = 0, tmp = 0;
		while (nodes < N - 1) {
			if (label[points[index].x] != label[points[index].y]) {
				tmp = label[points[index].y];
				for (int i = 0; i < N; ++i) {
					if (tmp == label[i])
						label[i] = label[points[index].x];
				}
				cost += points[index].cost;
				nodes++;
			}
			index++;
		}

		System.out.println(cost);
	}

	public static void quickSort(Vertex[] array, int s, int e) {
		if (s >= e) {
			return;
		}
		int i = s + 1;
		int j = e;
		Vertex pivot = array[s];

		while (i <= j) {
			while (i <= e && array[i].cost <= pivot.cost)
				i++;
			while (s + 1 <= j && pivot.cost <= array[j].cost)
				j--;

			if (i <= j) {
				Vertex temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			} else {
				array[s] = array[j];
				array[j] = pivot;
			}
		}
		quickSort(array, s, j - 1);
		quickSort(array, j + 1, e);
	}
}

class Vertex {
	public int x;
	public int y;
	public int cost;

	Vertex(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
}
