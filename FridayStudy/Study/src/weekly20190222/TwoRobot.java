package weekly20190222;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TwoRobot {
	public final static int INF = 987654321;
//	public final static int INF = 99;
	public static int Testcase;
	public static int N, start, end;
	public static int[][] cave = null;
	public static int[] dist = null;
	public static boolean[] visited = null;
	public static int max =0;

	public static void main(String[] args) throws FileNotFoundException {

//		System.setIn(new FileInputStream("res/tworobot_intput2.txt"));
		Scanner sc = new Scanner(System.in);

//		Testcase = sc.nextInt();

		int size = 0;
		int x, y, z;

//		for (int tc = 1; tc <= Testcase; tc++) {
			N = sc.nextInt();
			start = sc.nextInt();
			end = sc.nextInt();

			cave = new int[N][N];
			dist = new int[N];
			visited = new boolean[N];

			Arrays.fill(visited, false);
			Arrays.fill(dist, INF);
			initArrary(N, cave);

			for (int i = 0; i < N - 1; i++) {
				x = sc.nextInt() - 1;
				y = sc.nextInt() - 1;
				z = sc.nextInt();
				cave[x][y] = z;
				cave[y][x] = z;
			}

//			printArrary(N, cave);

			dist[0] = 0;
			size = shortPath(0, N - 1);
			
//			for (int d : dist) {
//				System.out.print(d + " ");
//			}
//			System.out.println();
//			System.out.println("#" + tc + " size: " + size);
			System.out.println(size);
//			System.out.println();

//		}
		if (sc != null) {
			sc.close();
		}
	}

	static void initArrary(int size, int[][] target) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				target[i][j] = INF;
//				if (i == j) {
//					target[i][j] = 0;
//				} else {
//					target[i][j] = INF;
//				}
			}
		}

	}
/*
	static void printArrary(int size, int[][] target) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(target[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
*/
	static int shortPath(int start, int end) {
		
		if (start == end) {
			return dist[end];
		}

		visited[start] = true;

		int min = INF;
		int diff = 0;

		for (int i = 0; i < N; i++) {
			if ((cave[start][i] != INF) && (visited[i] == false)) {
				if(dist[i]> dist[start]+cave[start][i]) {
					dist[i] = dist[start] + cave[start][i];
					diff = cave[start][i];
				}
				min = i;
				
				if(max < diff) {
					max = diff;
				}
			}
		}
		
		shortPath(min, end);

		return dist[end]- max;
	}

}
