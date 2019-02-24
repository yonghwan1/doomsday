package dijkstra;

import java.util.*;

public class Dijkstra02 {

	static int[][] ad;
	static int[] dist;
	static boolean[] visit;
	static int nE, nV;
	static final int inf = 1000000;

	public static void ssp(int start, int end) {
		dist[start] = 0; // 최초 시작 값 distance 초기화
		String s = "";
		for (int j = 0; j < nV; j++) // dist 값을 한번 초기화 했으므로 n-1번만 진행
		{
			int min = inf + 1; // dist 최소값 찾기 위한 임시 값 초기화
			int index = -1;
			for (int k = 1; k <= nV; k++) {
				if (visit[k] == false && min > dist[k]) {
					min = dist[k];
					index = k;
				}
			}
			visit[index] = true;
			s += index + " "; // 경로를 체크하는 방법

			for (int l = 1; l <= nV; l++) {
				if (ad[index][l] != 0 && dist[l] > dist[index] + ad[index][l]) { // 인접 행렬을 검사하여 최적의 값을 찾아
					dist[l] = dist[index] + ad[index][l];
				}

			}
		}

		for (int i = 1; i <= nV; i++) {
			System.out.print(dist[i]);
		}
		System.out.println();
		System.out.println(s);

	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		nV = scan.nextInt();
		nE = scan.nextInt();

		ad = new int[nV + 1][nV + 1];
		dist = new int[nV + 1];
		visit = new boolean[nV + 1];

		for (int i = 1; i <= nV; i++) {
			dist[i] = inf; // 임의의 무한한 값으로 거리값 초기화
		}

		for (int i = 0; i < nE; i++) {
			int t1, t2, t3;
			t1 = scan.nextInt();
			t2 = scan.nextInt();
			t3 = scan.nextInt();

			ad[t1][t2] = t3;
		}

		ssp(1, 5);
		scan.close();
	}

}
