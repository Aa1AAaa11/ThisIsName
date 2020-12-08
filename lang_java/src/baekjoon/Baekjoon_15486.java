package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_15486 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+2]; // N�Ͽ� ����� ���� �� ���� �� �ִ� 
		int[][] counsel = new int[N+1][2]; // 
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			counsel[i][0] = Integer.parseInt(st.nextToken()); // �ð�
			counsel[i][1] = Integer.parseInt(st.nextToken()); // ����
		}
		print(dp);
		for(int i = 1; i <= N; i++) {
			System.out.println(i);
			// i�Ͽ� ����� �ϴ� ���
			int next = i+counsel[i][0]; // i�Ͽ� ����� �� ��� ����� �� �� �ִ� ���� ��¥
			System.out.println(next+" "+(dp[i] + counsel[i][1]));
			if(next <= N+1) {
				dp[next] = Math.max(dp[next], dp[i] + counsel[i][1]); //dp[i] : i�� ���� ������� �� ���� �� �ִ� �ִ� ����
			}
			// i�Ͽ� ����� ���� �ʴ� ���
			dp[i+1] = Math.max(dp[i+1], dp[i]);
			print(dp);
		}
		System.out.println(dp[N+1]);
	}
}

