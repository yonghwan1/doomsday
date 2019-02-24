package mst;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

class Node { // �� ��� ������ Ŭ������ ǥ��
	int s, e, v;

	public Node(int s, int e, int v) {
		super();
		this.s = s;
		this.e = e;
		this.v = v;
	}

}

public class MSTByPrim {
	static int N;
	static int E;
	static ArrayList<Node>[] nodeList;
	static boolean visit[];
	static int ans;
	static ArrayList<Node> array = new ArrayList<Node>();

	public static void main(String[] args) throws NumberFormatException, IOException, FileNotFoundException {
		
		System.setIn(new FileInputStream("res/mstbyprim_intput.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ans = 0; // ���� �ּ� ��� ����� ���� ����
		N = Integer.valueOf(br.readLine()); // ������ ����
		E = Integer.valueOf(br.readLine()); // ������ ����
		visit = new boolean[N + 1]; // �湮üũ�� �迭

		nodeList = new ArrayList[N + 1];
		// �� ����� ������¸� �����ϴ� ArrayList :

		for (int i = 1; i <= N; i++) { // �� �迭 �ʱ�ȭ
			nodeList[i] = new ArrayList<Node>();
		}

		String[] tempStr;
		int start;
		int end;
		int value;
		for (int i = 0; i < E; i++) {
			tempStr = br.readLine().split(" ");
			start = Integer.valueOf(tempStr[0]);
			end = Integer.valueOf(tempStr[1]);
			value = Integer.valueOf(tempStr[2]);
			nodeList[start].add(new Node(start, end, value));
			// s:1, e:2, ���:2 �� nodeList[1]�� �߰�
			// 1] 2�����, 3�����, 4�����
			nodeList[end].add(new Node(end, start, value));
			// s:2, e:1, ���:2 �� nodeList[2]�� �߰�
			// 2] 1�����. 3�����, 7�� ���..
			// .....
		}

		MST();
		System.out.println();
	}

	public static void MST() {
		Comp cp = new Comp(); // �켱���� ť�� Ȱ���ؼ� Min Heap �� ����
		PriorityQueue<Node> pq = new PriorityQueue<>(cp); // ����� ���� ���� ������ �ٷ� �̱� ���� �켱���� ť
		Deque<Integer> dq = new ArrayDeque<>(); // ���� ��θ� �湮�ϴµ� �� ť
		dq.add(1); // �������� 1������ ����
		ArrayList<Node> tempList;
		Node tempNode;
		while (!dq.isEmpty()) {
			// ť���� �ϳ� ���� �ֺ��� ��带 �� ����
			int currentNode = dq.poll(); // ���� currentNode �� 1
			visit[currentNode] = true; // �ش� ��� �湮 ó���ؼ� �� �� �湮�ؼ� ������ ����� ���� �ٽ� ó������ ����
			tempList = nodeList[currentNode]; // nodeList[1] = tempList = [2�����,3�����,4�����]
			for (int i = 0; i < tempList.size(); i++) {
				if (!visit[tempList.get(i).e]) {
					pq.add(tempList.get(i)); // ���� ��忡 ����� ��� ������ �켱����ť�� add
				}
			}

			// ���� ���� ���� ���� ���� ������ ���, ���� �湮 ó��
			// ���� �̹� �湮�� �� �� ���� ���� ������ ��� �ѹ� �� ���� �� Ȯ��
			while (!pq.isEmpty()) { //
				tempNode = pq.poll(); // tempNode �� ���� ���� ������ ��忡 ����� ������ ���� ���� ���� ���� ���
				if (!visit[tempNode.e]) {
					// ������ ������ ����� ������ �̹� �湮�� ���̸� �ƹ��͵� ���� �ʰ� ù �湮�̸� ������ �����ϰ� ����� ������ �ּ� ����Ʈ���� �̷��
					// �����̹Ƿ�
					// ������� ������
					visit[tempNode.e] = true;
					ans += tempNode.v;
					dq.add(tempNode.e); // ����� ��带 ť�� �־���
					break;
				}
				// �̹� ���õǾ� �湮�� ��忡�� �ּҰ��� ������ ��� �ƹ��͵� ����
			}

		}
		System.out.println(ans);
	}

}

class Comp implements Comparator<Node> { // ��뿡 ���� Min Heap�� ����� ���� �켱���� ť�� Comparator
	@Override
	public int compare(Node arg0, Node arg1) {
		return arg0.v > arg1.v ? 1 : -1;
	}

}