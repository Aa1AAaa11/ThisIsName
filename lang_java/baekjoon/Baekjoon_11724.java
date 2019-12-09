package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_11724 extends Solution {
	
    static ArrayList[] relation; // ������� ���� (relation[x] �� ����Ǵ� ArrayList<int>�� x ������ ����� ����)
    static boolean[] visited; // �湮 ����(dfs �� ���)
    
    // DFS
    static void dfs(int index) { // index: ���� �湮�� ����
    	visited[index] = true; // index�� �湮��
    	for(int i = 0; i < relation[index].size(); i++) { // index ������ ����� ��� ������ �˻�
    		int next = (int)(relation[index].get(i));
    		if(!visited[next]) { // �湮���� ���� ������ ���
    			dfs(next);
    		}
    	}
    }
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ������ ����
		int M = Integer.parseInt(st.nextToken()); // ������ ����
		
		relation = new ArrayList[N];
		visited = new boolean[N];
		
		for(int i = 0; i< N; i++) {
			relation[i] = new ArrayList();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			//���� ���� �׷����̹Ƿ�
			relation[x].add(y); 
			relation[y].add(x); 
		}
		int answer = 0;
		for(int i = 0; i < N; i++) { //��� ������ ���� ����
			
			if(!visited[i]) {//i�� �湮���� ���� ������ ��� i���� �湮
				answer++;
				dfs(i);
			}
			
			// dfs�� �� ������ �����Ͽ� �� �� �ִ� ��� ������ �㹮
			// �� ����  ���� dfs ������ �湮���� ���� ���� �� �ϳ��� �����Ͽ� dfs -> ��� �湮�� �� ���� �ݺ�
		}
		
		System.out.println(answer);
	}
}
