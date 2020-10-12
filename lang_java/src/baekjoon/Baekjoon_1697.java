package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1697 extends Solution {

	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int K = Integer.parseInt(st.nextToken()); 
		int[] visited = new int[100001]; // �̰� ���ָ� ������ �ǰ��� �ؼ� �ð� �����ɸ�.. visited�� ������ �ִ� 10������ Ž��

		ArrayList<Integer> queue = new ArrayList<Integer>(); // �湮�� �������� ����
		queue.add(N);
		int now, next;//, next = new int[2];
		visited[N] = 1; // �ʱ⸦ 0�� �������� ���� ���� �����Ͽ����Ƿ� ������ -1�������
		// time ���������� while�� ���� ���� �ø��� ���� �ð��� �����ϴ� �����̿��� �ð��̴ٸ��԰���
		// while�� �ȿ� for => ���� ť�ǻ����� ��ŭ,for�ȿ��� add �ϰ� .. while �ֻ�ܿ����� ť ������ ���ϰ�, �ð�++ ���ָ� �Ǳ�� �Ѵ�.
		
		do {
			now = queue.remove(0); // ť�� �ִ� ������ �湮
			// ���� ��ġ�� �湮 ������ ������ ��� ����
			if(now > 0 && visited[now-1] == 0) { // now_x - 1 >= 0 >>> now_x - 1 > -1
				next = now-1;
				visited[next] = visited[now] + 1; // �� �ȿ��� visited[]�� �湮�ߴٰ� �ؾ���..while �ֻ�ܿ��� �湮�ߴٰ� �ϸ� �ߺ� �湮�� ���� �� ����. (1-> 1,0,2 -> 2:�ߺ�..)
				queue.add(next);
			}
			if(now < K) {// now < K �� ��츸 now�� ������Ŵ. now�� �۾����� ���� -1�ۿ����µ� ũ�� �ߴٰ� ���ҽ�Ű���� -1�� �� Ƚ���� ������
				if(now < 50001 && visited[now*2] == 0) { // now_x*2 <= 100,000 >>> now_x*2 < 100001
					next = now*2;
					visited[next] = visited[now] + 1;
					queue.add(next);
				}
				if(now < 100000 && visited[now+1] == 0) { // now_x + 1 <= 100,000 >>> now_x + 1 < 100001
					next = now+1;
					visited[next] = visited[now] + 1;
					queue.add(next);
				}
			}
		} while(!queue.isEmpty() && now != K); // ť�� ������� �ʰ� ���� ������ K�� �ƴ� ��� Ž��

		System.out.println(visited[K] - 1);
		
	
		
	}
}
