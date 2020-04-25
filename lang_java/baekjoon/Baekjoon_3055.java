package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_3055 extends Solution {

	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()); 
		int C = Integer.parseInt(st.nextToken()); 

		boolean[][] visited = new boolean[R][C];
		int[] end = new int[2];
		ArrayList<int[]> queue = new ArrayList<int[]>();
		ArrayList<int[]> water = new ArrayList<int[]>();
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			for(int j = 0; j < C; j++) {
				if(tmp.charAt(j) == 'S') {
					visited[i][j] = true;
					queue.add(new int[] {i, j});
				} else if(tmp.charAt(j) == 'D') {
					visited[i][j] = true;
					end = new int[] {i, j};
				} else if(tmp.charAt(j) == '*') {
					visited[i][j] = true;
					water.add(new int[] {i, j});
				} else if(tmp.charAt(j) == 'X') visited[i][j] = true;
			}
		}
		
		int[][] move = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		int answer = 0;
		int[] now = new int[2];
		boolean isPrint =  false;

		
		while(!queue.isEmpty()) {
			// for���� ������ ���� depth�� ��
			int water_size = water.size(); // for �ȿ� �ٷ� ������ �ȿ��� add�� remove ������ size�� ��� �ٱʹϱ�.
			for(int i = 0; i < water_size; i++) {
				int[] now_water = water.remove(0);
				for(int j = 0; j < 4; j++) {
					int next_i = now_water[0] + move[j][0];
					int next_j = now_water[1] + move[j][1];
					if(next_i >= 0 && next_i < R && next_j >=0 && next_j < C && !visited[next_i][next_j]) {
						visited[next_i][next_j] = true;
						water.add(new int[]{next_i, next_j});
					}
				}
			}
			int queue_size = queue.size();
			// System.out.println(queue_size+" test");
			for(int i = 0; i < queue_size; i++) {
				now = queue.remove(0);
				for(int j = 0; j < 4; j++) {
					int next_i = now[0] + move[j][0];
					int next_j = now[1] + move[j][1];
					if(next_i == end[0] && next_j == end[1]) { // �� ���� water ������ ���� ���� D�� visited = true�� �����Ǿ��ֱ� ������ ��� ���� Ȯ����.
						System.out.println(++answer);
						// ť�������. ������� ���� ��� �� ���� �Ǵµ� �������� �����ϴ°��� visited���ο� ���� ���� end ���� ������ �ǹǷ� �ѹ� �� ��µɼ��� ����.
						while(!queue.isEmpty()) { // queue_size - i�����ɷ� ���ϴ°� �Ʒ����� ť�� �߰��� �� �����Ƿ�
							queue.remove(0);
						}
						queue_size = 0;
						isPrint = true;
						break;
					}
					if(next_i >= 0 && next_i < R && next_j >=0 && next_j < C && !visited[next_i][next_j]) {
						visited[next_i][next_j] = true;
						queue.add(new int[]{next_i, next_j});
					}
				}
			}
			answer++;
		}
		if(!isPrint) { // ���� ���ϴ��� answer�� 0�� ���� �ƴ�. ������� Ž���� �ϴ� ���� ���ϴ°ű⶧����
			System.out.println("KAKTUS");
		} 
	
	}
	
}
