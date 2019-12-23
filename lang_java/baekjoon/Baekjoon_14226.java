package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_14226 extends Solution {
	static ArrayList<int[]> queue;
	static int S;
	static boolean[][] visited; // visited������ S��Ŀ���� ������
	
	// 1. �ٿ��ֱ�, 2. ����, 3. ���� >> 3������ ����� ���� ��� �غ���.
	static void bfs(int size, int depth) { // ť������ index ��° ���� Ž�� ����. ����depth�� size���� ����.���� depth���� ����ť������Ǵ°� depth+1 �� ����.
		int now; // ���� �̸�Ƽ���� ����
		int copy; // ����Ǿ��ִ��� ����
	
		// ���� depth�ΰ�� ��� Ž�� �� ���� bfs�� ȣ���ؾ� depth ������� Ž���� bfs��. �ϳ� Ž���� ������ bfs�� ���� �ϳ� �����ϸ� ������ ���� dfs��..
		for(int i = 0; i < size;  i++) {
			now = queue.get(0)[0];
			copy = queue.get(0)[1]; //copy�� isCopy�� boolean�� �ƴ������� ���Ű� �ֱ� ������ ���� ������ ����� ������ �׻� ���ٴº����� ����. ��, ���� �� ������ �׻� 2*now��� ���̾ƴ�.
			// ���� �� ���� �� �ٿ��ֱ⸦ �Ѵٸ� ���� ����now, Ŭ�����忡�¤�now-1�� 
			
			//���� ��带 �湮��.
			queue.remove(0); // ���� Ž���� ��� ���� ..���⼭���ž��ϰ� visited[now][copy]�� false�� ��츸 �����ϸ� true�� ��� ��� �湮�̵�.���Ű��ȵǴϱ�Ȱ����԰�� �湮.
			if(visited[now][copy])continue;  // ���� ������ �湮�� ��� �� �̻��� ���������ʿ�
			
			visited[now][copy] = true;
			if(now == S) {
				System.out.println(depth);
				return;
			}
			if(copy > 0 && now + copy <= S) { // ���� ������ ���
				// �ٿ��ֱ�
				queue.add(new int[]{now + copy, copy});
			}
			if(now > 0) {
				queue.add(new int[]{now, now}); // ���� (copy�� ���� ������ ����°���)
				queue.add(new int[]{now-1, copy}); // ����
			}
		}
		bfs(queue.size(),  depth+1);
	}

	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()); 
		queue = new ArrayList(); // ��������̸�Ƽ�� ������ ����� �̸�Ƽ���ǰ����� ť�� ����
		visited = new boolean[S+1][S+1]; //���� �̸�Ƽ�� ������ ����� ������ ������ ��� �ٽ� �湮�� �ʿ䰡 ����
		queue.add(new int[]{1, 0});
		bfs(1, 0);
	
	}
}
