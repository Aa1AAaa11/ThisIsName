package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2933 extends Solution {
	static int[][] move = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
	static boolean[][] visited;
	static char[][] cave;
	static int[] low;
	static int lowest = 0;
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		cave = new char[R][C];
		ArrayList<int[]> cluster;
		visited = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			cave[i] = br.readLine().toCharArray();
		}
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			int[] break_position = breakMineral(i, height, C, R); // �μ����� �̳׶� ��ġ�� ����
			if(break_position[0] >= 0 && break_position[0] < R && break_position[1] >=0 && break_position[1] < C) {
				cave[break_position[0]][break_position[1]] = '.'; // �̳׶��� �ʴ�.
				visited = new boolean[R][C];
				visited[break_position[0]][break_position[1]] = true;
				for(int t = 0; t < 4; t++) { // �� �̳׶� �����¿�� ��
					int next_i = break_position[0]+move[t][0], next_j = break_position[1] + move[t][1];
					low = new int[C];
					lowest = -1;
					Arrays.fill(low, -1);
					if(next_i >= 0 && next_i < R && next_j >=0 && next_j < C && cave[next_i][next_j] == 'x'  && !visited[next_i][next_j]) {
						cluster = getCluster(next_i, next_j, cave, R, C);
						if(lowest < R-1) { // Ŭ������ �� ���� �Ʒ� �ִ� �̳׶��� ���� ���ִ� ���
							move(cluster, R, C);
							break; // ���ÿ� �������� ���� �����Ƿ�
						} 
						// cluster = null;
					}
				}
			}
		}
		
		for(int i = 0; i < cave.length; i++) {
			for(int j = 0; j < cave[i].length; j++) {
				System.out.print(cave[i][j]);
			}
			System.out.println();
		}
	}
	static int[] breakMineral(int i, int height, int C, int R) {
		int[] position = new int[2];
		int mod = i%2; // 0: ���ʿ��� ������, 1: �����ʿ��� ����
		int add = 1-mod*2; // mod == 0?1:-1
		position[1] = C*mod - mod; // mod == 0?0:C-1;
		position[0] = R-height;
		while(position[0] >= 0 && position[0]< R && position[1] >=0 && position[1] < C && cave[position[0]][position[1]] == '.') { // �̳׶��� ã�� �� ���� �̵�
			position[1] += add;
		}
		return position;
	}
	static ArrayList<int[]> getCluster(int i, int j, char[][] cave, int R, int C) { // Ŭ������ ��ġ�� ����
		ArrayList<int[]> cluster = new ArrayList<>();
		cluster.add(new int[] {i, j});
		visited[i][j] = true;
		low[j] = Math.max(low[j], i);
		lowest = low[j];
		int index = 0, size = 1;
		while(size > index) {
			int[] now = {cluster.get(index)[0], cluster.get(index)[1]};
			for(int t = 0; t < 4; t++) {
				int[] next = {now[0] +move[t][0], now[1] +move[t][1]};
				if(next[0] >= 0 && next[0] < R && next[1] >=0 && next[1] < C && !visited[next[0]][next[1]] && cave[next[0]][next[1]] == 'x') {
					low[next[1]] = Math.max(low[next[1]], next[0]);
					lowest = Math.max(lowest, low[next[1]]);
					visited[next[0]][next[1]] = true;
					cluster.add(next);
					size++;
				}
			}
			index++;
		}
		return cluster;
	}
	static int getDown(ArrayList<int[]> cluster, int R, int C) { // �󸶳� �Ʒ��� ���������� ����
		int down = 101;
		for(int i = 0; i < C; i++) {
			int tmp_down = 1;
			int next = low[i] + tmp_down; // Ŭ�����Ϳ��� ���� �Ʒ� �ִ� �̳׶����� ��ĭ�� �Ʒ��� �����δ�.
			if(low[i] > -1) {
				while(next != R-1 && cave[next+1][i]!='x') { // �ٴڿ� ��ų� ������ �̳׶��� ���
					next = low[i]+ ++tmp_down;
				}
				down = Math.min(tmp_down, down);
			}
		}
		return down;
	}
	static void move(ArrayList<int[]> cluster, int R, int C) { // �Ʒ��� �̵�..  
		int index = getDown(cluster, R, C); // ��� Ŭ�����Ϳ� ���� ��ĭ�� �Ʒ��� �̵� �� �̵� �������� �Ǻ��ϸ� �ʹ� �����ɸ��Ƿ� low �迭�� �̿��Ͽ� ��ĭ �Ʒ��� �������ϴ��� ����
		Iterator<int[]> it = cluster.iterator();
		boolean[][] fill = new boolean[R][C];
		while(it.hasNext()) {
			int[] now = it.next();
			if(!fill[now[0]][now[1]])cave[now[0]][now[1]] = '.'; // ������ ������ �̳׶��� ��� ����� �ʰ� �̳׶��̿�����
			cave[now[0] + index][now[1]] = 'x';
			fill[now[0] + index][now[1]] = true;
		}
	}
}
