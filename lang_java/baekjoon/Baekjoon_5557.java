package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_5557 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ������ ���� 
		int[] numbers = new int[N]; // ���� ����Ʈ
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(dp(N, numbers));
	}
	static long dp(int N, int[] numbers) {
		long answer = 0;
		long[][] memo = new long[N-1][21]; // memo[i][j] : i ��° ���� ����Ͽ��� �� ���� j�� ����� ����� ��
		memo[0][numbers[0]] = 1;
		for(int i = 1; i < N-1; i++) {
			for(int j = 0; j < 21; j++) {
				int plus = j + numbers[i]; // ���� ���
				int minus = j - numbers[i]; // �� ���
				if(plus < 21) {
					memo[i][plus] += memo[i-1][j]; // ++ �ƴ�
				}
				if(minus >= 0 ) {
					memo[i][minus] += memo[i-1][j]; // ++ �ƴ�
				} 
			}
		}
		answer = memo[N-2][numbers[N-1]]; // numbers[N-1]�� �Ǵ� ����� ��
		return answer;
	}
}