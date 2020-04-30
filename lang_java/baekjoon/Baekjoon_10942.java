package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_10942 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[] input = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][] memo = new boolean[N][N]; // memo[i][j] = i-1 ~ j-1 �Ӹ��������
		for(int i = 0; i < 3; i++) { // 1�ڸ�~3�ڸ� �ʱ�ȭ
			int max = N-i;
			for(int j = 0; j < max; j++) { // �������� 0 ~ max ����
				memo[j][j+i] = isSame(j, j+i, input); // j, j+i �� ������ ������ ��� �Ӹ����
			}
		}
		for(int i = 3; i < N; i++) { // 4�ڸ� ~ N �ڸ� �� �Ӹ�������� ����
			int max = N-i;
			for(int j = 0; j < max; j++) { // �������� 0 ~ max ����
				memo[j][j+i] = memo[j+1][j+i-1] && isSame(j, j+i, input); // ������ ���� ���� �̿��Ͽ� ������ ���� // j+1 ~ j+i-1�� �Ӹ�����̰� j, j+i �� ������ �����̸�  j ~ j+i�� �Ӹ�����̴�.
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			answer.append(memo[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]? 1: 0).append('\n');	
		}
		System.out.print(answer);

	}
	static boolean isSame(int i, int j, int[] input) {
		return input[i] == input[j];
	}
	
}
