package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2234 extends Solution {
	static int[][] map;
	static int[][] move = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
	static int[][] ableMove;
	static int[][] visited;
	static int n, m;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		visited = new int[m][n];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ableMove = new int[16][5];
		for(int i = 0; i < 15; i++) {
			int number = i;
			int move_index = 0;
			ableMove[i][move_index] = 0;
			for(int j = 8, t = 3; j > 0; j /= 2, t--) {
				if(number >= j) { // j�� 1�ΰ� (j �� ��������)
					number -= j;
				} else { // j�� �� �� ����
					ableMove[i][0]++;
					ableMove[i][++move_index] = t;
				}
			}
		}
		
		// 
		int[] rooms = new int[n*m]; //���� ���̸� ����
		int max = 0;
		int room_number = 1;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(visited[i][j] == 0) { // �湮�� ���� ���� ��� (���ȣ�� �Ҵ���� ����)
					rooms[room_number-1] = bfs(room_number, i, j);
					max = Math.max(rooms[room_number-1], max);
					room_number++;
				} 
			}
		}
		// ���� �ϳ� �μ����� �� 
		int breakMax = 0;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				for(int d = 0; d < 4; d++) {
					int next_i = i+move[d][0], next_j = j+move[d][1]; // next�� ������
					if(next_i >= 0 && next_i < m && next_j >= 0 && next_j < n && visited[next_i][next_j] != visited[i][j]) { // �����ϰ� ���ȣ�� �ٸ� ���
						breakMax = Math.max(breakMax, rooms[visited[i][j]-1] + rooms[visited[next_i][next_j]-1]);
					}
				}
			}
		}
		System.out.println(room_number-1+"\n"+max+"\n"+breakMax);
	}
	static int bfs(int room_number, int index_i, int index_j) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {index_i, index_j});
		visited[index_i][index_j] = room_number;
		int number = 0;
		while(!queue.isEmpty()) {
			int[] now = queue.remove();
			number++;
			int move_num = ableMove[map[now[0]][now[1]]][0];
			for(int d = 0; d < move_num; d++) {
				int[] next = new int[] {now[0]+move[ableMove[map[now[0]][now[1]]][d+1]][0], now[1]+move[ableMove[map[now[0]][now[1]]][d+1]][1]};
				if(next[0] >= 0 && next[0] < m && next[1] >= 0 && next[1] < n && visited[next[0]][next[1]] == 0) {
					queue.add(next);
					visited[next[0]][next[1]] = room_number;
				}
			}
		}
		return number;
	}
}
