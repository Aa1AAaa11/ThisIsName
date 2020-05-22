package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14503 extends Solution {
	
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // ��, ��, ��, ���� �ٶ󺸰� ���� �������� ������ �� �̵��ϴ� ��ǥ
	static int[][] map; // 2 : ��, 1 : ���� û�ҵ��� ���� ���, 0: û�ҵ� ���
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ����ũ��
		int M = Integer.parseInt(st.nextToken()); // ����ũ��
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()); // ��ǥ (����)
		int c = Integer.parseInt(st.nextToken()); // ��ȿ (����)
		int d = Integer.parseInt(st.nextToken()); // ����
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken()) + 1;
		}
		
		int answer = 0;
		int[] position = new int[] {r, c, d};
		while(position[0] >= 0) { // ���̻� �̵��� �Ұ����� �� ���� �ݺ��Ѵ�.
			// ���� ��ġ�� û��
			answer += map[position[0]][position[1]];
			map[position[0]][position[1]] = 0; 
			// �̵�
			position = move(position[0], position[1], position[2], map, N, M);
		}
		System.out.println(answer);
	}
	static int[] move(int r, int c, int d, int[][] map, int N, int M) { // ���� ��ġ�� ����
		for(int i = 0; i < 4; i++) { // �� ���⿡ ���� ���غ�
			// ���� �������� ȸ�� �� �̵�
			d = (d+3)%4; //(move_d+4-1)%4;
			int x = r+move[d][0];
			int y = c+move[d][1];
			if(0 <= x && x < N && 0 <= y && y < M && map[x][y] == 1) return new int[] {x, y, d}; // ���� �������� �̵� �����ϰ� ���� û������ ���� ������ ������ ���
		}
		// �� ���� ��� û�Ұ� �̹� �Ǿ��ְų� ���� ���
		// ����
		int x = r-move[d][0];
		int y = c-move[d][1];
		if(0 <= x && x < N && 0 <= y && y < M && map[x][y] != 2) return new int[] {x, y, d};// ���� ������ ��� (���⼭ û�ҵ� ���, ���� ��� �и� �ʿ�) û�ҵǾ����� ���� �ƴϸ� �̵� �����ϴ�.
		else return new int[] {-1, -1, -1}; // ���� �Ұ��� ���
	}
}
