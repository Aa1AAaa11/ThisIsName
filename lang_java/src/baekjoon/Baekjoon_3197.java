package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_3197 extends Solution {
	
	static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[R][C];
		int[][] swan_array = new int[2][2];
		Queue<int[]> water = new LinkedList<int[]>();
		int swan_index = 0;
		for(int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < C; j++) {
				char tmpChar = tmp.charAt(j);
				if(tmpChar == 'X') map[i][j] = true;
				else if(tmpChar == 'L') {
					swan_array[swan_index++] = new int[] {i, j};
					water.add(new int[] {i, j});
				} else {
					water.add(new int[] {i, j});
				}
			}
		}
		System.out.println(meet(R, C, swan_array[0], swan_array[1], water, map));
	}
	static int meet(int R, int C, int[] start_swan, int[] end_swan, Queue<int[]> water, boolean[][] map) {
		int answer = 0;
		
		Deque<int[]> swan = new ArrayDeque<int[]>();
		swan.add(start_swan);
		boolean[][] swan_visited = new boolean[R][C];
		swan_visited[start_swan[0]][start_swan[1]] = true;
		while(answer <= R*C) {
			// ���� �̵�
			for(int i = 0; i < swan.size(); i++) { 
				int[] now_swan = swan.remove(); 
				int add_swan = 0;
				for(int direction = 0; direction < 4; direction++) {
					int[] next_swan = new int[] {now_swan[0]+move[direction][0], now_swan[1]+move[direction][1]};
					if(next_swan[0] >= 0 && next_swan[0] < R && next_swan[1] >= 0 && next_swan[1] < C) {
						if(!map[next_swan[0]][next_swan[1]]) { // ���� ���
							if(!swan_visited[next_swan[0]][next_swan[1]] ) { // ������ �湮������ ���ų� ���� ���
								if(next_swan[0] == end_swan[0] && next_swan[1] == end_swan[1]) return answer;
								else {
									swan.addFirst(next_swan);//�׳� add�� i �� �湮���� �ʾƵ� �����ϹǷ� ������ ��� ��츦 �湮���� ����
									swan_visited[next_swan[0]][next_swan[1]] = true;
								}
							}
							add_swan++; // �������� �ֺ��� ���ΰ��
						}
					} else {
						add_swan++; // ���� ���� �ֺ��� ���� ��� 
					}
					
				}
				if(add_swan < 4) { 
					swan.add(now_swan); // ���� �ֺ����� ��� Ȯ���� ���� �ƴϸ� ���� ���� �Ŀ��� ����ؾ� �ϹǷ� ���Ŵ� ���� ����
				} else { // ���� ���� �����¿� Ž���� �ʿ� ���� ��� -> ������ ���ŵǹǷ� i�� ����� size�� i �� �ǵ��� ��� �ۿ��� (swan.size()�� ���� ������ ������ ����� ��ȯ�ϱ� ������ i������ ���� �ʿ�)
					i--;
				}
			}
			
			// ���� ����
			int water_size = water.size();
			for(int i = 0; i < water_size; i++) { // water�� ���� ����Ǿ� �ִ� ��ŭ�� �ؾ���
				int[] now_water = water.remove();
				for(int direction = 0; direction < 4; direction++) {
					int[] next_water = new int[] {now_water[0]+move[direction][0], now_water[1]+move[direction][1]};
					if(next_water[0] >= 0 && next_water[0] < R && next_water[1] >= 0 && next_water[1] < C && map[next_water[0]][next_water[1]]) {
						map[next_water[0]][next_water[1]] = false;
						water.add(next_water);
					}
				}
			}
			
			answer++;
		}
		return -1;
	}
}
