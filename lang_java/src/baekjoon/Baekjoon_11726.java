package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_11726 extends Solution {
	static int[] memo;
	
	// top-down
	static int dp(int n) {
		if(memo[n-1] == 0) {
			if(n == 1)memo[0] = 1;
			else if (n == 2)memo[1] = 2;
			else  memo[n-1] = (dp(n-1) + dp(n-2))%10007;
		}
		return memo[n-1];
	}
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		memo = new int[N]; // memo[x] = x-1 �� ����� ����� ��
		
		
		// top-down
		// System.out.println(dp(N));
	
		memo[0] = 1; // 1 ����� ����� ��
		if(N > 1) memo[1] = 2;
		
		// bottom-up
		// f(n) = f(n-1) ����� ���η� �� �ִ� ���  + f(n-1) ����� ���η� �� �ִ� ��� �� �����ϹǷ�
		// f(n) = f(n-1) + f(n-2)
		for(int i = 2; i < N; i++) {
			memo[i] = (memo[i-1] + memo[i-2])%10007;
			// (A+B)%C = (A%C + B%C)%C 
			// �̹Ƿ� ((i-1 ����� ����� ��) + (i-2 ����� ����� ��))%10007 
			// = ((i-1 ����� ����� ��)%10007 + (i-2 ����� ����� ��)%10007 )%10007 �̹Ƿ� 
			// memo[] �� (i-1 ����� ����� ��)%10007 �� �����Ͽ��� ����.
		}
		System.out.println(memo[N-1]);
		
	}

}
