package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_6064 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int M, N, x, y;
		int gap, tmp, k, j;
		StringBuilder answer = new StringBuilder();
		boolean[] visited;
		
		for(int i = 0; i < T; i++) {
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken()) - 1; // ������ ������ 1~M�̹Ƿ� 0 ~ M-1�� �ٲٱ� ����
			y = Integer.parseInt(st.nextToken()) - 1;// ������ ������ 1~N�̹Ƿ� 0 ~ N-1�� �ٲٱ� ����  (�߰��� (tmp-1)%N +1 �� �ƴ϶� tmp%N�� ö���ֱ� ����)
			visited = new boolean[N];
			gap = N > M ? M : M-N; 
		   	tmp = x % N; // ó����. M > N �� ��쵵 �����Ƿ� �׳� x�� �ƴϴ�. N�� ������
			k = -1; // start ~ start �� �� ���� y�� �Ұ����� ��� k = -1  (�Ұ���)
			j = 0; // 0���� ���� (��ȸ ����Ŭ ���Ҵ���)
			
			do {
				visited[tmp] = true;
				if(tmp == y) {
					k =  x + M*j + 1; // �ʹݿ� x��ŭ ������ �� M �� �ݺ��Ǵ� ����Ŭ�� j �� ����. // 0���� �������� ������Ƿ� +1�� ���ش�.
					break;
				} else {
					tmp = (tmp+gap) % N; //M �����̹Ƿ� N���� ������.
					// visited[tmp-1] = true; ���������� �ȵ�. �׻� true�� �ǹǷ�
					j++;
				}
			} while(!visited[tmp]);// �湮�� ���� ���� ��� ����.

			answer.append(k).append("\n");
			
		}
		
		System.out.println(answer.toString());
	}
}