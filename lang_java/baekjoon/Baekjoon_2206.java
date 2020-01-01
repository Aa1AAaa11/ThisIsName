package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_2206 extends Solution {

	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken()); 
		
		int[][][] number = new int[N][M][2]; // �̵� 
		int[][] wall = new int[N][M]; // ��.
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			for(int j = 0; j < M; j++) {
				wall[i][j] = tmp.charAt(j) - '0';
			}
		}
		
		//�ʱ�ȭ
		ArrayList<int[]> queue = new ArrayList<int[]>();
		number[N-1][M-1][0] = -1; // ������ -1�� �ʱ�ȭ. �������� �ʴ� ��� -1�� > �̰� ���� ��. 1 1�� ��� 1�� ���̹Ƿ�
		number[N-1][M-1][1] = -1; // ������ -1�� �ʱ�ȭ. �������� �ʴ� ��� -1�� > �̰� ���� ��. 1 1�� ��� 1�� ���̹Ƿ�
		number[0][0][0] = 1; // ������ 1 // ���� �� �μ��� �� ��� �ɸ� �Ÿ�
		number[0][0][1] = 1; // ������ 1 // ���� �μ��� �� ��� �ɸ� �Ÿ�
		// �� (i, j)�� ���� �μ��� �� �����ְ� �����μ��� ������������ �����Ƿ� 2���� ��쿡 ���� Ȯ���� �ʿ�.
		// ���� �μ��� �� ��� ���� �Ұ��������� �����Ⱥμ��� ���� �� ���� �����Ҽ��� �����Ƿ�
		
		queue.add(new int[] {0, 0, 0});
		int[][] move = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		int now_break = 0, now_i, now_j;
		int[] index;
		
		while(!queue.isEmpty()) {
			index = queue.remove(0);
			now_i = index[0];
			now_j = index[1];
			now_break = index[2];
			
            if(now_i == N-1 && now_j == M-1)break;
			
			for(int i = 0; i < 4; i++) { //�����¿�
				int next_i = now_i + move[i][0];
				int next_j = now_j + move[i][1];
				
				
				// �湮 ������ �����̰� (next_i >=0 && next_i < N && next_j >=0 && next_j < M)
				if(next_i >=0 && next_i < N && next_j >=0 && next_j < M ) { 
					
					// next_break : ���� �� �μ����� ����.
					int next_break = now_break + wall[next_i][next_j]; //next_break�� 2�� ���� �Ұ����� ��� .. ������ �� �μ��� �� �μ��� �Ŵϱ�..
					
					
					// �湮���� �ʾ��� �� (number[next_i][next_j] < 1) -> ==0�� �ƴ�.�������� -1�� �ʱ�ȭ�Ǿ������Ƿ�
					// ���� �湮�� �ʿ��� ��ġ���湮���� ���� ��ġ�� ���
					if(next_break < 2 && number[next_i][next_j][next_break] < 1) {
						queue.add(new int[] {next_i, next_j, next_break});
						number[next_i][next_j][next_break] = number[now_i][now_j][now_break] + 1;
					}
				}
			}
			
		}
		
		System.out.println(number[N-1][M-1][now_break]);
	
	}
	
}
