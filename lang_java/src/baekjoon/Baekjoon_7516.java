package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_7516 extends Solution {

	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken()); 
		int[][] visited = new int[M][N]; 

		ArrayList<int[]> queue = new ArrayList<int[]>(); // �湮�� �������� ����
		int number = 0; // �湮�� ������ ����
		int num_unable = 0;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				String tmp = st.nextToken();
				visited[i][j] = tmp.charAt(0) - '0'; //0 �� ���� �湮�� �ʿ䰡 ����
				if(visited[i][j] == 1) { //1 �� ��� �湮�� ������ �߰�
					queue.add(new int[]{i, j});
				} else if(visited[i][j] < 0) { // '-1' - '0'�� -1�� �ƴ�..
					num_unable++;
				}
			}
		}
		
		// �� �̻� ť�� �湮�� ������ ���� �� ���� 
		// �湮�� ������ N*M - (-1�� ����)���� ���� ��� -1 ��ȯ
		// ��� ������ �湮�� ��� answer ��ȯ

		int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		int index = 0;
		int now_i = queue.get(0)[0], next_i;
		int now_j = queue.get(0)[1], next_j;
		
		while(index < queue.size()) { // �湮�� ������ ������ ���� �ݺ� (ť�� ũ�Ⱑ ��� �����ϹǷ� queue.size�� ��� ���ؾ���)
			// ���� �湮���� ������ ��ġ��������
			now_i = queue.get(index)[0];
			now_j = queue.get(index)[1];
			number++; // �湮�� ������ ���� +1
			
			for(int i = 0; i < 4; i++) {
				next_i = now_i + move[i][0];
				next_j = now_j + move[i][1];
				if(next_i >= 0 && next_i < M && next_j >= 0 && next_j < N) {
					if(visited[next_i][next_j] == 0) { //-1�̰ų� 1�̸� �ȵ� > 1�̸� �̹� �湮, -1�̸� �湮 �Ұ�
						visited[next_i][next_j] = visited[now_i][now_j] + 1;
						queue.add(new int[]{next_i, next_j}); // �湮 
					}
				}
			}
			
			index++;
		}

		if(number + num_unable < N*M) { // �湮�� ������ ���� �湮�ؾ��� ������ ������ ���� ��� ��� �湮�� ���� �ƴ�
			System.out.println(-1);
		} else {
			System.out.println(visited[now_i][now_j] - 1); // visited�� 1���� �����Ͽ����Ƿ� 1�� ���ش�.
		}
	}
}
