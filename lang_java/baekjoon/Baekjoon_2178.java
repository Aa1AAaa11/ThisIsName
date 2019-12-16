package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_2178 extends Solution {

	static boolean[][] visited; //  �湮����
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken()); 
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			for(int j = 0; j <M; j++) {
				if(tmp.charAt(j) == '0')visited[i][j] = true; //0 �� ���� �湮�� �ʿ䰡 ����
			}
		}
		// int[][] dist, ->i,j  ������ ����ġ�� ����
		//ArrayList queue -> �������湮�ؾ��ҳ���.. �䷱������ �������� ����..
		
		int[][] queue = new int[N][M]; // int[][] queue = new int[N*M][3]; // BFS�� ���� ť (index������� >> ť���ִ�� ���尡���� ������ N*M��,ǥ���ϱ� ���ؼ��� 2�� ��ǥ �ʿ�)
		int index = 0; // ����Ž������ ť����ġ
		int end = 1; // �߰��� ť����ġ
		
		queue[index][0] = 0; //i��ǥ
		queue[index][1] = 0; //j��ǥ
		queue[index][2] = 1; // ������� ��ģ ĭ�� �� (�� ó��ĭ ����)
		visited[0][0] = true; // �ʹ� �˻��ߴٴ� ǥ���߰� �ʿ� �ȱ׷��� �ڷ� ���� ť �ε����� �迭 ���̺��� ũ�������ɼ���..
		
		int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		boolean arrive = false;
		
		while(index <= end && !arrive) { // ���������� ��� Ž���� ��� Ž�� ���� index!=end �ƴ� �̷��� ������ ���� Ž�������� -> ������ �������� Ž���ߴµ� ���̻� �߰��� ���� ���� ���(index > end)�� ��������
			
			for(int i = 0; i < 4; i++) { // �������Ͽ� ���� Ȯ��
				int next_i = queue[index][0] + move[i][0];
				int next_j = queue[index][1] + move[i][1];
				if(next_i >= 0 && next_i < N && next_j >= 0 && next_j < M) { // Ž�� ������ ������ ���
					if(!visited[next_i][next_j]) { //�湮���� ���� ������ ���
						queue[end][0] = next_i;
						queue[end][1] = next_j;
						queue[end][2] = queue[index][2]+1;
						visited[next_i][next_j] = true; // �湮�ߴٴ� ǥ�ø� �ؾ���
						end++;
						if(next_i == N-1 && next_j == M-1) { // �������� ������ ���
							arrive = true;
							break; // �������� Ȯ�� �ڵ忡 ���� Ż��
						}
					}
				}
			}
			index++;
		}
		System.out.println(queue[end-1][2]); //������ end�� �߰��Ǿ���ϴ� ��ġ�̹�
		
	}
}
