package dijkstra;

import java.util.Arrays;
import java.util.Vector;

public class Dijkstra04 {
	public final static int INF = 987654321;
	public final static int N = 9;

	// [from_node][to_node] = distance
	static int[][] LEN = new int[N][N];

	static {
		for (int i = 0; i < N; i++)
			Arrays.fill(LEN[i], INF);
//		LEN[0][1] = LEN[1][0] = 1;
//		LEN[1][2] = LEN[2][1] = 2;
//		LEN[2][3] = LEN[3][2] = 3;
//		LEN[3][4] = LEN[4][3] = 4;
				
		LEN[0][1] = LEN[1][0] = 8;
		LEN[1][2] = LEN[2][1] = 6;
		LEN[1][3] = LEN[3][1] = 5;
		LEN[1][4] = LEN[4][1] = 10;
		LEN[4][8] = LEN[8][4] = 6;
		LEN[4][5] = LEN[5][4] = 14;
		LEN[5][6] = LEN[6][5] = 7;
		LEN[5][7] = LEN[7][5] = 7;
		
	}

	// start node부터 i node까지의 최단거리
	public static int[] dist = new int[N];
	static {
		Arrays.fill(dist, INF);
	}

	// 각 노드별로 방문한 적이 있는지 표시
	public static boolean[] visited = new boolean[N];
	static {
		Arrays.fill(visited, false);
	}

	// 각노드의 이전 노드번호
	public static int[] prev = new int[N];
	static {
		Arrays.fill(prev, 0);
	}

	// 경로담는 vector
	static Vector<Integer> steps = new Vector<Integer>();

	public static int shortestPath(int start, int end) {

		// 기저
		if (start == end) {
			return dist[end];
		}

		// 방문한 노드들을 차례대로 담는다
		visited[start] = true;

		// 연결된 노드들 중 짧은 거리 노드의 인덱스
		int min = INF;

		for (int i = 0; i < N; i++) {

			// start node의 하위 트리 탐색
			// 조건1 : 방문이력x
			// 조건2 : start node와 연결됨
			if (LEN[start][i] != INF&& visited[i] == false) {

				// 기존값과 start node를 경유해서 오는 거리 비교 후 최소거리로 업데이트
				if (dist[i] > dist[start] + LEN[start][i]) {
					dist[i] = dist[start] + LEN[start][i];
					prev[i] = start; // 최소경로를 제공하는 전노드
				}

				// 다음 경로 node 갱신
				// 조건 : 최소거리값이 작은 노드
//				if (min == INF || dist[min] > dist[i]) {
					min = i;
//				}

			}

		}

		// 최소경로를 시작으로 다시 recursive 하게 방문한다.
		shortestPath(min, end);

		return 0;
	}

	public static void makingPath(int start, int end, int node) {
		if (node == start) {
			steps.add(node);
			return;
		}

		makingPath(start, end, prev[node]);
		steps.add(node);

	}
	public static int getMax() {
		int max = 0;
		for(int i=0;i<steps.size()-1;i++) {
			int x = steps.get(i);
			int y = steps.get(i+1);
			int length =  LEN[x][y];
			
			if(max<length) {
				max = length;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		dist[0] = 0;

		shortestPath(0, N - 1);
		makingPath(0, N - 1, N - 1);

		System.out.print("최종경로 = ");
		for (int node : steps) {
			System.out.print(node + " ");
		}

		System.out.println();

		System.out.print("각 노드의 최적 누적거리 = ");
		for (int d : dist) {
			System.out.print(d + " ");
		}
		System.out.println();
		int max = getMax();
		System.out.println(dist[N-1]-max);

	}
}
