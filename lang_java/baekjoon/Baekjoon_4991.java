package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_4991 extends Solution {
	
	static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		while (h+w > 0) {
			char [][] map = new char[h][w];
			boolean[][] visited = new boolean[h][w];
			int[][] node = new int[11][2];
			int dirty = 0;
			for(int i = 0; i < h; i++) {
				StringBuilder sb = new StringBuilder(br.readLine());
				for(int j = 0; j < w; j++) {
					map[i][j] = sb.charAt(j);
					if(map[i][j] == 'x') visited[i][j] = true;
					else if(map[i][j] == 'o') {
						map[i][j] = (char) 0;
						node[0] = new int[] {i, j};
					} else if(map[i][j] == '*') {
						dirty++;
						map[i][j] = (char) dirty;
						node[dirty] = new int[] {i, j};
					}
				}
			}
			int[][] lengths = new int[dirty+1][dirty+1]; // [i][j]  : ��� i ���� j ���� �̵��ϴµ� �ɸ��� �ּ� ����
			boolean isOk = true;
			for(int i = 0; i < dirty+1; i++) {
				lengths[i] = bfs(node[i], dirty+1, h, w, map); // ��� ���� + ������ ���� ���� bfs ���ϰ�
				if(lengths[i] == null) { // �湮���� ���ϴ� ��尡 �ִ� ��� .. (A <-> B ����, B <-> C �Ұ�, A <-> C ������ ���� ����..A <-> B ����, A <-> C ���� �̸�  B <-> C �� ������ (B->A->C))
					answer.append(-1).append('\n');
					isOk = false;
					break;
				}
			}
			if(isOk) answer.append(dfs(1, dirty+1, new boolean[dirty+1], new int[dirty+1], lengths)).append('\n');
			// ������ ����Ͽ� ��ġ
			
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
		}
		System.out.println(answer);
	}
	static int getLen(int[][] lengths, int[] array, int size) {
		int answer = 0;
		for(int i = 1; i < size; i++) {
			answer += lengths[array[i-1]][array[i]];
		}
		
		return answer == 0? -1 : answer;
	}
	static int dfs(int depth, int number, boolean[] visited, int[] array, int[][] lengths) {
		if(depth == number) {
			return getLen(lengths, array, number);
		} 
		int answer = Integer.MAX_VALUE;
		for(int i = 1; i < number; i++) { // ������ �׻� 0 �����̹Ƿ�
			if(!visited[i]) {
				visited[i] = true;
				array[depth] = i;
				answer = Math.min(answer, dfs(depth+1, number, visited, array, lengths));
				visited[i] = false;
			}
		}
		return answer;
		
	}
	static int[] bfs(int[] start, int number, int height, int width, char[][] map) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(start);
		boolean[][] visited = new boolean[height][width];
		visited[start[0]][start[1]] = true;
		int depth = 0;
		int[] lengths = new int[number]; // start���� number ��° ������ �˻� �� �ɸ��� �ð�
		while(!queue.isEmpty() && number > 0) { // ��� ��������� ������ ������ ���ؼ��� Ȯ���ϸ� ��
			depth++;
			int size = queue.size();
			for(int t = 0; t < size && number > 0; t++) {
				int[] now = queue.poll();
				//it.remove();
				for(int i = 0; i < 4; i++) {
					int[] next = new int[] {now[0]+move[i][0], now[1]+move[i][1]};
					if(0 <= next[0] && next[0] < height && 0 <= next[1] && next[1] < width && !visited[next[0]][next[1]]) {
						visited[next[0]][next[1]] = true;
						if(map[next[0]][next[1]] == 'x') continue; //���� ������ �� ����
						if(0 <= map[next[0]][next[1]] && map[next[0]][next[1]] < 10) { //  ��� �����̰ų� ������ ���
							number--;
							lengths[map[next[0]][next[1]]] = depth;
						} 
						queue.add(next);
					}
				}
			}
		}
		if(number > 1) return null; // ������ ��忡�� �湮���� ���ϴ� ��尡 �ִ� ��� (���� ��� ����)
		
		return lengths;
	}
}
