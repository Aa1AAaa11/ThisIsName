package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_14395 extends Solution {
	static String[] modify_str = {"*", "+"};
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
		System.out.println(get(s, t));
		
	}
	static String get(int s, int t) {
		Queue<long[]> queue = new LinkedList<long[]>(); // ����, move, visited��ġ
		int max = (int) Math.ceil(Math.log10(t) / Math.log10(2)); // 2^a*s^b ���� �� 2^k ���ڸ� ���� �� �����Ƿ�. a,b<= log2(t)
		String[][] visited = new String[max+1][max+1];
		if(s > t) { // ������ �۰� ���� �� ���� �ʿ�
			///if(t == 0) return "-"; // t�� 1 �̻���
			if(t == 1) return "/";
			else {
				queue.add(new long[] {1, 0, 0});
				visited[0][0] = "/";
			}
		} else if(s == t) {
			return "0";
		} else { 
			queue.add(new long[] {s, 0, 1});
			visited[0][1] = "";
			// ���⼭ �ϸ�ȵȴ�. ���� ������ �ٸ��� ���� �� ����
			//queue.add(new long[] {1, 0, 0});
			//visited[0][0] = "/";
		}
		boolean add = false;
		while(!queue.isEmpty()) {
			long[] now = queue.remove();
			// ���� : �׻� 0, ������ : �׻� 1�̹Ƿ� while ���� 0�� 1�� �ʿ��� ���� �̸� �߰��Ͽ��� while������ ������ ����
			for(int i = 0; i < 2; i++) { // ���ϱ�� ���ϱ⸦ �Ѵ�
				long[] next;
				if(i == 0) { // ���ϱ��� ���
					next = new long[]{now[0]*now[0],  now[1]+now[1], now[2]+now[2]};
				} else {//���ϱ��� ���
					next = new long[]{now[0]+now[0],  now[1]+1, now[2]};
				}
				if(next[0] == t) {
					return visited[(int) now[1]][(int) now[2]]+modify_str[i];
				}
				if(next[1] <= max && next[2] <= max && visited[(int) next[1]][(int) next[2]] == null && next[0] < t) { // �湮���� ���� ��� && t ���� ���� ���. ū ���� ���̴� ����� ���� /�ۿ� ����
					visited[(int) next[1]][(int) next[2]] = visited[(int) now[1]][(int) now[2]]+modify_str[i];
					queue.add(next);
				}
			}
			if(!add) {
				add = true;
				queue.add(new long[] {1, 0, 0});
				visited[0][0] = "/";
			}
		}
		return "-1";
	}
}
