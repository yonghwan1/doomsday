package dijkstra;

public class DijkstraSP {
	final static int infi = 999; // ������ ���� ����, ����ġ ���Ѵ븦 ����.

	public static void dijkstra(int[][] c, int n, int[][] t, int vertex) {
		// c�迭�� �׷����� �������, n�� ������ ����, t�迭�� ����� �����迭, vertex�� ���� ����
		int[] from = new int[n]; // �� ������ ��� �������κ��� ����Ǿ����� ǥ���ϴ� �迭
		int[] dist = new int[n]; // ������� ������ ���������� �� ���������� ����ġ�� ǥ��

		for (int i = 0; i < n; i++) { // ���� ���������� ù��° ���������� vertex���� �ʱ�ȭ
			from[i] = vertex;
			dist[i] = c[vertex][i];
		}

		int best = 0;
		for (int i = 0; i < n - 1; i++) { // n-1�� ����� ������ ��
			best = isBest(dist); // dist�迭�� 0�� ������ �ּҰ��� ���� ������ �ε���
			t[0][i] = from[best]; // ����ġ�� ���� ���� ������ �����ؼ� �־��ش�.
			t[1][i] = best;

			for (int j = 0; j < n; j++) { // dist�迭�� from�迭�� �ֽ�ȭ
				if (dist[best] + c[best][j] < dist[j]) { // �ֱٿ� ���õ� ������ ������ ������ ����ġ�� ���� dist�迭�� ��
					from[j] = best;
					dist[j] = dist[best] + c[best][j];
				}
			}
			dist[best] = 0; // �κ� �׷����� ���õ� ���������� ����ġ�� 0���� ����
		}
	}

	public static int isBest(int[] dist) { // dist�迭�� 0�� ������ �ּҰ��� ���� ������ �ε����� ��ȯ�ϴ� �޼ҵ�
		int best = 0;

		for (int i = 0; i < dist.length; i++) {
			if (dist[i] > 0) {
				best = i;
				break;
			}
		}

		for (int j = 0; j < dist.length; j++) { // dist���� 0�̾ƴ� �� �� ���� ���� ���� �ε����� ã�´�.
			if (dist[j] != 0 && dist[j] < dist[best]) {
				best = j;
			}
		}

		return best;
	}

	public static void main(String[] args) {
		int[][] graph = { 
				{ 0, 8, 7, 20, 14, infi }, 
				{ infi, 0, infi, 13, infi, infi },
				{ infi, infi, 0, infi, 5, infi }, 
				{ 12, infi, infi, 0, infi, infi }, 
				{ 11, infi, infi, 6, 0, 4 },
				{ infi, infi, infi, 10, infi, 0 }, };
		int[][] t = new int[2][5];

		dijkstra(graph, 6, t, 0);

		for (int i = 0; i < t[0].length; i++) {
			System.out.println(t[0][i] + 1 + " " + (t[1][i] + 1));
		}
	}
}
