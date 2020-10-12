package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1261 extends Solution {

	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken()); 
		
		int[][] number = new int[M][N]; // ���� �μ��� ���� (���� : -1�� �ʱ�ȭ������ �ʾұ� ������ 0�� �̹湮 �������� ���� ������ ��� �� -1�� ���ش�.)
		int[][] wall = new int[M][N]; // ��.
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			for(int j = 0; j < N; j++) {
				wall[i][j] = tmp.charAt(j) - '0';
			}
		}
		
		ArrayList<int[]> queue = new ArrayList<int[]>();
		queue.add(new int[] {0, 0});
		number[0][0] = 1;
		int[] index;
		int[] move_i = {-1, 0, 0, 1};
		int[] move_j = {0, 1, -1, 0};
		
		while(!queue.isEmpty()) {
			// index �湮
			index = queue.get(0);
			queue.remove(0);
			int i = index[0]; 
			int j = index[1];
			int num = number[i][j];
			
			// ���� �μ��� �ּ� �����̹Ƿ� 
			// �߰��� ���� �μ��� ť�� �޺κп� �߰����ְ�
			// �߰��� ���� �μ��� �ʾƵ� �� �� �տ� �߰����ش�.
			for(int k = 0; k < 4; k++) {
				// ���� �湮�ϴ� ����
				int next_i = i + move_i[k];
				int next_j = j + move_j[k];
				if(next_i >= 0 && next_i < M && next_j >=0 && next_j < N) { // �湮 ������ ���� ������ ���
					if(number[next_i][next_j] == 0) { // �湮���� ���� ���
						number[next_i][next_j] = num + wall[next_i][next_j];
						if(wall[next_i][next_j] == 0) { //�߰��� ���� �μ��� �ʾƵ� �Ǵ°��
							queue.add(0, new int[] {next_i, next_j}); 
						} else {// �߰��� ���� �μ��� ���
							queue.add(new int[] {next_i, next_j}); 
						}
					}
				}
			}
		}
		
		System.out.println(number[M-1][N-1]-1); // 1���� �����Ͽ����Ƿ�
		
	}
	
}
