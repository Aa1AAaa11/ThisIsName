package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1987 extends Solution {
	static char[][] board;
	static int R, C;
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C]; // ����
		for(int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
		}
		boolean[] visited = new boolean[26];
		visited[board[0][0]-'A'] = true;
		System.out.println(getWay(visited, new int[] {0, 0}, 0));
	}
	
	static int getWay(boolean[] visited, int[] position, int depth) { // visited : �湮�� ���ĺ� ǥ��, position: ���� ��ġ, depth: ������� �湮�� ����
		int answer = 0;
		int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // �����¿�� �̵�
		for(int i = 0; i < 4; i++) {
			int now_i = move[i][0] + position[0];
			int now_j = move[i][1] + position[1];
			// ������ ��ġ�� ���
			if(now_i < R && now_j < C && now_i >= 0 && now_j >= 0)  { 
				if(!visited[board[now_i][now_j]-'A']) { // �湮������ ���
					visited[board[now_i][now_j]-'A'] = true; // ���� ���ĺ��� �湮
					answer = Math.max(answer, getWay(visited, new int[] {now_i,  now_j}, depth+1)); // answer�� ������Ʈ
					visited[board[now_i][now_j]-'A'] = false; // ������ depth ���� �ٸ� ��ġ �湮�� ���̹Ƿ� false ������ �����д�.
				}
			}
		}
		return answer+1; // �׳� answer return�� �ƴ�. ���� ��ġ�� ���������
	}
}