package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

// ������ 2�� �̻� �湮����  �ʰ� depth 4 �������� �˻�
public class Baekjoon_13023 extends Solution {
	
    static ArrayList[] relation; // ������� ���� (relation[x] �� ����Ǵ� ArrayList<int>�� x ������ ����� ����)
    static boolean[] visited; // �湮 ����
    static int N;
    static int answer = 0;
    
    static void search(int depth, int index) { // index : ���� index���� �˻�
    	
    	if(depth == 4) { // �̾ �˻� ������ ������ 5���� ���,,
    		answer = 1;
    		return;
    	}
    	ArrayList linked = relation[index]; // index�� ����� ��� ������..
		for(int i = 0; i < linked.size() && answer != 1; i++) { 
			int next = (int) (linked).get(i); // ������ �湮 ������ ����
			// �ѹ� �湮�� ������ �ƿ� ���� �ȵ� , B ==> A ==> D�� B ==> D�� �ƴϹǷ�
			// ������ �ѹ����� �湮�ؾ��� �˰� �˰� �ƴ϶� �ٷ� �˾ƾ��ϹǷ�
			if(!visited[next]) { // �湮���� ���� ��� �湮 (������ �湮���� ���� ���) -> ������ ������� ���� ��찡 �ƴ�(���� ������� ���� ���� �˻��ϸ� A->B->C->D, B->E �� ��쵵 ������ ����
				visited[next] = true;
				search(depth+1, next);
				visited[next] = false;
			}
			
		}
    }
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // ����� ��
		int M = Integer.parseInt(st.nextToken()); // ������ ��
		
		relation = new ArrayList[N];
		visited = new boolean[N];
		
		for(int i = 0; i< N; i++) {
			relation[i] = new ArrayList();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			relation[x].add(y); 
			relation[y].add(x); // ������� �����ϹǷ�
		}
		// DFS
		for(int i = 0; i< N && answer != 1; i++) {
			visited[i] = true;
			search(0, i);
			visited[i] = false; // ���⼭�� �ʱ�ȭ�������
		}
		System.out.println(answer);
		
	}
}
