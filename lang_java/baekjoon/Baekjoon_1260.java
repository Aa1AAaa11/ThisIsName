package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Baekjoon_1260 extends Solution {
	
    static ArrayList[] relation; // ������� ���� (relation[x] �� ����Ǵ� ArrayList<int>�� x ������ ����� ����)
    static boolean[] visited; // �湮 ����(dfs �� ���) (dfs�� ���ڷ� ������ȵ� ��� ������ ���� visited �ʱ�ȭ �Ǿ����..)
    static StringBuilder answer;
    
    static void dfs(int index) { // index: ���� �湮�� ����
    	visited[index] = true; // index�� �湮��
		answer.append(index+1).append(" ");
    	for(int i = 0; i < relation[index].size(); i++) { // index ������ ����� ��� ������ �˻�
    		int next = (int)(relation[index].get(i));
    		if(!visited[next]) { // �湮���� ���� ������ ���
    			dfs(next);
    		}
    	}
    }
    
    static void bfs(int start, int N) { // index: ���� ����
    	int[] nodes = new int[N]; // �湮�� ������
    	boolean[] visited = new boolean[N]; // �湮����
    	int visited_index = 0; // ���� ��� �˻��� ���� ���� ���
    	int add_index = 0; // ��� �߰��� 
    	
    	// �ʱ�ȭ (ó�� ���� �湮)
    	visited[start] = true;
    	nodes[add_index++] = start;
		answer.append(start+1).append(" ");
    	
    	int node;
    			
    	while(N > visited_index) {
        	node = nodes[visited_index];
        	for(int i = 0; i< relation[node].size(); i++) {
        		int next = (int)relation[node].get(i); // �湮 ���
        		if(!visited[next]) { // �湮���� ���� ����� ��� �湮�Ѵ�.
        	    	visited[next] = true;
        	    	nodes[add_index++] = next;
        			answer.append(next+1).append(" ");
        		}
        	}
        	// for���� ���� node�� ������ ���� �� �湮 ������ �������� ��� Ž���ϸ� ���� ����
        	visited_index++;
    	}
    }
    
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	answer = new StringBuilder();
		int N = Integer.parseInt(st.nextToken()); // ������ ����
		int M = Integer.parseInt(st.nextToken()); // ������ ����
		int V = Integer.parseInt(st.nextToken()) - 1; // ������ ����
		
		relation = new ArrayList[N];
		visited = new boolean[N];
		
		for(int i = 0; i< N; i++) {
			relation[i] = new ArrayList();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			//����� �����ϹǷ�
			relation[x].add(y); 
			relation[y].add(x); 
		}
		for(int i = 0; i< N; i++) {
	        Collections.sort(relation[i]); // ���� �� ���� �湮�ؾ��ϹǷ�
		}
		dfs(V);
		answer.append("\n");
		bfs(V, N);
		System.out.println(answer.toString());
	}
}
