package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import common.Solution;

public class Baekjoon_2151 extends Solution {
	static int[][] move = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		int[][] init = new int[2][2];
		int init_index = 0;
		boolean[][] wall = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = tmp.charAt(j);
				if(map[i][j] == '#') init[init_index++] = new int[] {i, j, 0};
				else if(map[i][j] == '*') wall[i][j] = true;
			}
		}
		System.out.println(bfs(N, map, init, wall));
		
	}
	static int bfs(int N, char[][] map,  int[][] init, boolean[][] wall) {
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][N];
		queue.add(init[0]);
		visited[init[0][0]][init[0][1]] = true;
		int answer = 0;
		while(!queue.isEmpty()) {
			int[] now = queue.remove(); // ��ġ�� �ſ��� ��ġ�� ������
			for(int i = 0; i < 4; i++) { // 4���� ���� �̵� �ſ��� 45�� �������� ��ġ�ϸ� ���� �����¿�� �̵��ϱ� ����
				int next_i = now[0]+move[i][0], next_j = now[1]+move[i][1];
				while(next_i >= 0 && next_i < N && next_j >=0 && next_j < N && !wall[next_i][next_j]) {
					if(!visited[next_i][next_j]) {
						if(next_i == init[1][0] && next_j == init[1][1]) return now[2]; // ��
						else if(map[next_i][next_j] == '!') { // �ſ��� ��ġ ������ ��
							visited[next_i][next_j] = true;
							queue.add(new int[] {next_i, next_j, now[2]+1}); // ���� ��ġ���� �ſ��� ��ġ �����ϸ� ���� ��ġ�� ok
						}
					}
					 next_i += move[i][0]; 
					 next_j += move[i][1];
				}
				
			}
		}
		return answer;
	}
}
