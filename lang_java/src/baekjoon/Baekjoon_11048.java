package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_11048 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] input = new int[N][M];
		int[][] memo = new int[N+1][M+1];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < N+1; i++) {
			memo[i][M] = -1;
		}
		for(int j = 0; j < M; j++) {
			memo[N][j] = -1;
		}
		System.out.println(dp_recursion(N, M, N, M, input, memo));
		

		/*
			int[][] memo = new int[N][M]; // memo[i][j] : ��ǥ (i+1,j+1) ���� ���� �� �ִ� ������ �ִ밪
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					if(j > 0 && i == 0) memo[i][j] = memo[i][j-1] + Integer.parseInt(st.nextToken());  // ���� ������ (1,1)���� �� ���� ����ۿ� ����
					else if(i > 0 && j == 0) memo[i][j] = memo[i-1][j] + Integer.parseInt(st.nextToken());// ���� �������� (1,1)���� �� ���� ����ۿ� ����
					else  memo[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println(dp(N, M, memo));
		 */

	}
	
	static int dp_recursion(int N, int M, int i, int j, int[][] input, int[][] memo) {// i, j ������ �ִ� ��ġ�� ��ȯ
		if(memo[i][j] != -1) return memo[i][j]; // �̹� ������ ���� ��� ����Ѵ�. (�̰� ������ �ߺ����� ���Ǵ� ��ġ�� ���ؼ� ��� ����ؾ��ϹǷ� �ð��� �����ɸ�.)
		
		// �ٷ� return�� �ƴϰ� memo �迭�� �����Ѵ�.
		if(i == 1 && j == 1) memo[i][j] = input[i-1][j-1]; // memo�� input ���� ���μ��� 1�� �� ���.
		else if(i == 0 || j == 0) memo[i][j] = 0;
		else memo[i][j] = Math.max(dp_recursion(N, M, i-1, j, input, memo), dp_recursion(N, M, i, j-1, input, memo)) + input[i-1][j-1]; // memo�� i, j �� -1�� ��쵵 �����Ͽ� 0���� �����ϱ� ������ �Ǥ��� ��ġ�� input ���� 1�� ũ��.
		
		return memo[i][j]; // input[][] �ƴ�
	}
	
	static int dp(int N, int M, int[][] memo) {
		for(int i = 1; i < N; i++) {
			for(int j = 1; j < M; j++) {
				memo[i][j] = Math.max(Math.max(memo[i-1][j], memo[i][j-1]), memo[i-1][j-1]) + memo[i][j]; // ����, �� �� ū ��ġ���� ����Ѵ�. (�밢���� ������ ����, ���� ���ؼ��� ������ �����Ƿ� �� x)
			}
		}
		return memo[N-1][M-1];
	}
	
}
