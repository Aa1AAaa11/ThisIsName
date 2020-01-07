package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_1463 extends Solution {
	static int[] memo;
	
	static int dp(int n) { 
		if(n == 1) return 0; // �̰� ������ �ȵ�. 1�� 0�̱� ������ 1�� �Է����� ������ ��� ��� ������.
		else if(memo[n] == 0) { // memo�� ����� ���� ���� ��쿡�� ���
			// memo (cache)�� ���� �� �ٽ� ���� ��� memo����������
			if(n%6 == 0) { // n%2 == 0 && n%3 == 0
				memo[n] = Math.min(Math.min(dp(n/3), dp(n/2)), dp(n-1));
			} else if(n%3 == 0) { // 3���θ� ������ �������� ��� 
				memo[n] = Math.min(dp(n/3), dp(n-1)); //  Math.min(memo[n/3], memo[n-1]); �̰� �ƴ�... n�� �� memo�� n/3�� n-1�̱������ٴ� ������ �����Ƿ� Ȯ�� �� �����ϴ� dp�Լ��� ȣ���ؾ���
			} else if(n%2 == 0) { // 2�θ� ������ �������� ���
				memo[n] = Math.min(dp(n/2), dp(n-1));
			} else { //2,3 ��� ������ �������� �ʴ� ���
				memo[n] = dp(n-1);
			}
			memo[n]++; // n-1 �̳� n/2 �̳� n/3�� �ѹ� ��ģ �� n�� �� �� �����Ƿ� 1�� ������
		}
		
		return memo[n];
	}
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		int N = (int)sc.nextInt();
		memo = new int[N+1];
		memo[1] = 0; // 1 ����µ� �ɸ���
		
		// f(n) = min (f(n/3), f(n/2), f(n-1)) + 1;
		for(int i = 1; i <= N; i++) {
			if(memo[i] == 0) { // n�� �������� ���� ���
				if(i%6 == 0) { // n%2 == 0 && n%3 == 0
					memo[i] = Math.min(Math.min(memo[i/3], memo[i/2]), memo[i-1]);
				} else if(i%3 == 0) { 
					memo[i] = Math.min(memo[i/3],memo[i-1]);
				} else if(i%2 == 0) {
					memo[i] = Math.min(memo[i/2], memo[i-1]);
				} else {
					memo[i] = memo[i-1];
				}
				memo[i]++;
			}
		}
		System.out.println(memo[N]);
		
		//System.out.println(dp(N));
		
	}

}
