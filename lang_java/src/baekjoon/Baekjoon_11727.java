package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_11727 extends Solution {
	
	static int[] memo;
	
	// top-down
	static int dp(int n) {
		int index = n-1;
		if(memo[index] == 0) {
			if(index == 0) memo[index] = 1;
			else if(index == 1)memo[index] = 3;
			else {
				memo[index] = (dp(n-1) + dp(n-2)*2)%10007; 
				// (memo[index-1] + memo[index-2]*2)%10007; �ƴ�
				// (dp(index-1) + dp(index-2)*2)%10007 �ƴ�.. dp(n) �� ȣ���ϸ� dp ������ memo�� �´� index�� �ٲ� ����ϱ� ������
			}
		}
		return memo[index];
	}
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		memo = new int[N]; // memo[x] = x-1 �� ����� ����� ��
		
		
		// top-down
		 // System.out.println(dp(N));
	
		memo[0] = 1; // 1 ����� ����� ��
		if(N > 1) memo[1] = 3;
		
		// bottom-up
		// f(n) = f(n-1) �� ���� �� 2x1Ÿ�� ���η� ��� ����ϴ� ��� + f(n-2) �� ���� �� 2x2  Ÿ�� ���� ���   + f(n-2) �� ���� ��  2x1Ÿ�� ���η� ��� ����ϴ°��
		for(int i = 2; i < N; i++) {
			memo[i] = (memo[i-1] + memo[i-2]*2)%10007;
			// memo[i-1] + memo[i-2] + 1�� �ƴ�. 
			//  memo[i-2]*2 : f(n-2) �� ���� �� 2x2  Ÿ�� ���� ��� / f(n-2) �� ���� ��  2x1Ÿ�� ���η� ��� ����ϴ°��
			// 2x2 Ÿ�� ���� ��찡 f(n-2) ���� �ְ� 2x1 ��� ����ϴ� ��찡 f(n-2) ������ ����.
		}
		System.out.println(memo[N-1]);
		
	}

}
