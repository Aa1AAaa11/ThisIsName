package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_2251 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] cup = new int[3];
		boolean[][][] visited = new boolean[201][201][201]; // ������ �� ������ ���� �����ϹǷ� AB�� ������ C�� ������ boolean[][] visited = new boolean[201][201] �̷��� �ص� �ɵ�.
		cup[0] = Integer.parseInt(st.nextToken());
		cup[1] = Integer.parseInt(st.nextToken());
		cup[2] = Integer.parseInt(st.nextToken());
		visited[cup[0]][cup[1]][cup[2]] = true;
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[] answer = new boolean[cup[2]+1];
		queue.offer(new int[] {0, 0, cup[2]}); // ����Ǿ��� �ʹ��� C�� ������ �����̹Ƿ� ���� �ѷ��� C ���ٴ� ������ ����
		answer[cup[2]] = true;
		// A->B, C, B-> A,C C->A,B
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int i = 0; i < 3; i++) { //A, B, C ���� �̵�
				for(int k = 1; k <= 2; k++) {
					// i -> move��  ���� �̵�
					int move = (i + k)%3;
					int[] change = new int[3];
					// �̵� ������ �ſ� �ִ� ���� �̵��� �ſ� �� �� �ִ� �纸�� ū ��� 
					change[i] = now[i] > cup[move] - now[move] ? -(cup[move] - now[move]): -now[i];
					change[move] = -change[i];
					int[] next = {now[0] + change[0], now[1] + change[1], now[2] + change[2]};
					if(now[i] > 0 && !visited[next[0]][next[1]][next[2]]) { //change[move] > 0 && 
						visited[next[0]][next[1]][next[2]] = true;
						queue.offer(next);
						if(next[0] == 0)answer[next[2]] = true; // A�� ������� �� 
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i <= cup[2]; i++) {
			if(answer[i]) sb.append(i).append(' ');
		}
		System.out.println(sb.toString());
	}
}
