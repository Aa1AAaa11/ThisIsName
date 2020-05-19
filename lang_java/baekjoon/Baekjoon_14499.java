package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14499 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ����ũ��
		int M = Integer.parseInt(st.nextToken()); // ����ũ��
		int x = Integer.parseInt(st.nextToken()); // �ֻ��� x ��ǥ
		int y = Integer.parseInt(st.nextToken()); // �ֻ��� y ��ǥ
		int K = Integer.parseInt(st.nextToken()); // ��� ����
		int[][] map = new int[N][M]; //����
		int[] dice = new int[6]; // �ֻ����� ���� ����
		int[][] move_change = {{3, 5, 2, 0}, {3, 0, 2, 5}, {1, 0, 4, 5}, {1, 5, 4, 0}}; // �̵��� ���� �ٲ� index ��ġ��. �ش� index ���� ���ڵ��� ��ġ�� �̵��Ѵ�.
		int[][] move_position = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // x, y �� �󸶳� �ٲ����
		StringBuilder answer = new StringBuilder();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			int move = Integer.parseInt(st.nextToken()) - 1;
			int tmp = dice[move_change[move][0]];
			x += move_position[move][0]; // �ϴ� �̵� �� �̵� �������� �Ǵ�. �̵� �Ұ����� ��� �̵� �� ��ǥ�� �ٲ۴�.
			y += move_position[move][1];
			if(0 <= x && x < N && 0 <= y && y < M) { // �̵� ������ ���
				for(int j = 0; j < 3; j++) {
					dice[move_change[move][j]] = dice[move_change[move][j + 1]];
				}
				dice[move_change[move][3]] = tmp;
				answer.append(dice[0]).append('\n');
				if(map[x][y] == 0) map[x][y] = dice[5]; // �̵��� ĭ�� ���� �ִ� ���� 0�� ���
				else { // �̵��� ĭ�� ���� �ִ� ���� 0�� �ƴ� ���
					dice[5] = map[x][y];
					map[x][y] = 0;
				}
			} else { // �̵� �Ұ����� ��� �̵����� �ʴ´�.
				x -= move_position[move][0];
				y -= move_position[move][1];
			}
		}
		System.out.print(answer);
	}
}

/* ���� ) �ֻ��� �̵� �� �ε��� ��ġ ����
 * if(move == 1 && x < M-1) { // ����
	int tmp = dice[3];
	dice[3] = dice[5];
	dice[5] = dice[2];
	dice[2] = dice[0];
	dice[0] = tmp;
	x++;
} else if(move == 2 && x > 0) { //��
	int tmp = dice[3];
	dice[3] = dice[0];
	dice[0] = dice[2];
	dice[2] = dice[5];
	dice[5] = tmp;
	x--;
} else if(move == 3 && y < N-1) { // ��
	int tmp = dice[1];
	dice[1] = dice[0];
	dice[0] = dice[4];
	dice[4] = dice[5];
	dice[5] = tmp;
	y--;
} else if(move == 4 && y > 0){ // ��
	int tmp = dice[1];
	dice[1] = dice[5];
	dice[5] = dice[4];
	dice[4] = dice[0];
	dice[0] = tmp;
}
 * */