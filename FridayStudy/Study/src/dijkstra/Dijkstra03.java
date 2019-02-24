package dijkstra;

import java.util.Arrays;
import java.util.Vector;
 
public class Dijkstra03 {
    public final static int INF = 987654321;
    public final static int N = 5;
    
    // [from_node][to_node] = distance
    static int[][] LEN = new int[N][N];
    
    static {
        for(int i=0; i<N; i++)
            Arrays.fill(LEN[i], INF);
        LEN[0][1] = LEN[1][0] = 6;
        LEN[0][2] = LEN[2][0] = 2;
        LEN[1][2] = LEN[2][1] = 3;
        LEN[2][3] = LEN[3][2] = 3;
        LEN[1][3] = LEN[3][1] = 6;
        LEN[1][4] = LEN[4][1] = 4;
        LEN[3][4] = LEN[4][3] = 11;
    }
    
    // start node���� i node������ �ִܰŸ�
    public static int[] dist = new int[N];
    static {
        Arrays.fill(dist, INF);
    }
    
    // �� ��庰�� �湮�� ���� �ִ��� ǥ��
    public static boolean[] visited = new boolean[N];
    static {
        Arrays.fill(visited, false);
    }
    
    // ������� ���� ����ȣ
    public static int[] prev = new int[N];
    static {
        Arrays.fill(prev, 0);
    }
    
    // ��δ�� vector
    static Vector<Integer> steps = new Vector<Integer>();
    
    
    public static int shortestPath(int start, int end){
        
        // ����
        if(start == end){
            return dist[end];
        }
        
        // �湮�� ������ ���ʴ�� ��´�
        visited[start] = true;
        
        // ����� ���� �� ª�� �Ÿ� ����� �ε���
        int min=INF;
        
        for(int i=0; i<N; i++){
            
            // start node�� ���� Ʈ�� Ž��
            // ����1 : �湮�̷�x
            // ����2 : start node�� �����
            if(LEN[start][i]!=INF && visited[i]==false){
                
                // �������� start node�� �����ؼ� ���� �Ÿ� �� �� �ּҰŸ��� ������Ʈ
                if(dist[i] > dist[start] + LEN[start][i]){
                    dist[i] = dist[start] + LEN[start][i];
                    prev[i] = start;    // �ּҰ�θ� �����ϴ� �����
                }
                    
                
                // ���� ��� node ����
                // ���� : �ּҰŸ����� ���� ���
                if(min==INF || dist[min] > dist[i]){
                    min = i;
                }
                
            }
            
        }
        
        // �ּҰ�θ� �������� �ٽ� recursive �ϰ� �湮�Ѵ�.
        shortestPath(min, end);
        
        return 0;
    }
    
    public static void makingPath(int start, int end, int node){
        if(node == start){
            steps.add(node);
            return;
        }
        
        makingPath(start, end, prev[node]);
        steps.add(node);
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        dist[0] = 0;
        
        shortestPath(0,N-1);
        makingPath(0,N-1,N-1);
        
        System.out.print("������� = ");
        for(int node : steps){
            System.out.print(node + " ");
        }
        
        System.out.println();
        
        System.out.print("�� ����� ���� �����Ÿ� = ");
        for(int d : dist){
            System.out.print(d + " ");
        }
        
    }
 
}
 
