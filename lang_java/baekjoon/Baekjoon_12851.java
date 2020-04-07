package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_12851 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] visited = new int[100001];
		int[][] move = {{1, -1}, {1, 1}, {2, 0}};
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(N);
		visited[N] = 1;
		int answer_depth = 0, answer_length = 0;
		while(!queue.isEmpty()) {
			int depth_size = queue.size();
			int[] depth_visited = new int[3*depth_size];
			for(int i = 0; i < depth_size; i++) {
				int now = queue.poll();
				depth_visited[i] = now;
				if(now == K) {
					int depth_remain = depth_size - 1 - i;
					answer_length = 1;
					for(int j = 0; j < depth_remain; j++) {
						if( queue.poll() == K )answer_length++;
					}
					System.out.println(answer_depth+"\n"+answer_length);
					return;
				} else { 
					for(int t = 0; t < 3; t++) {
						int next = now*move[t][0] + move[t][1];
						if(next >=0 && next <= 100000 && (visited[next] == 0 || visited[next] == visited[now] + 1)) { 
							// �湮������ ���ų�, �湮�� ���� ������ ������ depth�� �湮�� ���
							// �湮�� ���� ������ ������ depth�� �湮�� ��� : 1 (*2)-> 2 / 1 (+1) -> 2 �� �� ������� 2������ ��Ʈ�� �ٸ��Ƿ� �ٸ� ���� �ľ��Ѵ�. 
							// ���� ������ depth������ �湮�� �湮 ���θ� ������ �ʰ�
							// ���� depth ������ �湮�� ������ �ߺ� �湮���� �ʵ��� �Ѵ�.
							// 1 2(*2) 4 8 7 14, 1 2(+1) 4 8 7 14, 1 2(*2) 3 6 7 14, 1 2(+1) 3 6 7 14 -> ������ depth������ �湮�� �湮 ���θ� ������ 7�� 1���� �湮�Ͽ�, �߸��� ���� ���ϰ� �ȴ�.
							queue.add(next);
							visited[next] = visited[now] + 1;
						}
						if(next > K) break; // �� ��� -1�� ���ش�.
					}
				}
			}
			answer_depth++;
		
		}
	}
}
