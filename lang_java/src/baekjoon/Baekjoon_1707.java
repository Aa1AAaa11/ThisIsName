package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1707 extends Solution {
	
    static ArrayList[] relation; // ������� ���� (relation[x] �� ����Ǵ� ArrayList<int>�� x ������ ����� ����)
    static boolean[] visited; // �湮 ����(dfs �� ���)
    static boolean[] graph; // ���� index�� �׷� A�� ���ԵǴ��� ���� (�׷� A�� ���� 0�� ���ԵǴ� )
    static boolean is_binary;// �̺б׷������� ����
    static StringBuilder answer;
    
    // DFS
    static void dfs(int index, boolean A) { // index: ���� �湮�� ����, A : �������� ���� �׷����� ���Ե� �� �ִ��� ���� (�׷� A/B�� �������� �� A�� ���ԵǴ��� (�������� A))
    	visited[index] = true; // index�� �湮��
    	graph[index] = A; // index�� A �׷쿡 ���Ե�
    	
    	for(int i = 0; i < relation[index].size(); i++) { // index ������ ����� ��� ������ �˻�
    		int next = (int)(relation[index].get(i)); // index�� ����� ������ �湮�� ����
    		
    		if(!visited[next]) { // next�� �湮���� ���� ������ ���
    			dfs(next, !A); // index�� �ٷ� ����� �����̹Ƿ� index�� �ٸ� �׷�����
    		} else { // next�� �湮�� ������ ��� // dfs�� �ٽ� �� �ʿ�� ���� -> �湮 �ߴٴ°� �ش��������� dfs �غ��Ŵϱ�
    			if(graph[next] == A) { // ����� ���, index�� ���ϴ� �׷���A �ε� ������ next�� �׷��� A�� ���,, > �̺б׷��� �ƴ� // graph[next] != (!A) -> !graph[next] == (!A) -> graph[next] == A
    				is_binary = false;
    			}
    		}
    		
    		if(!is_binary)break;
    	}
    }
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken()); // �׽�Ʈ ����
		int V;
		int E;
    	answer = new StringBuilder();
		
		for(int test_case = 0; test_case < K; test_case++) {
			is_binary = true; // �Ź� �ʱ�ȭ����
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // ������ ����
			E = Integer.parseInt(st.nextToken()); // ������ ����
			
			relation = new ArrayList[V];
			visited = new boolean[V];
			graph = new boolean[V];
			
			for(int i = 0; i < V; i++) {
				relation[i] = new ArrayList();
			}
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				//���� ���� �׷����̹Ƿ�
				relation[x].add(y); 
				relation[y].add(x); 
			}
			for(int i = 0; i < V; i++) {
				// i�� ����׷������� ������ (����׷����� �ƴҼ��� �����Ƿ� ��� ������ ���� Ȯ��)
				if(!visited[i])dfs(i, true);
				// ���⼭�� is_binary�� false�� ��� ���� -> ���� Ž���� ���ʿ��ϹǷ� 
				if(!is_binary)break;
			}
			if(is_binary) {
				answer.append("YES").append("\n");
			} else {
				answer.append("NO").append("\n");
			}
		}
		System.out.print(answer.toString());
	}
}
