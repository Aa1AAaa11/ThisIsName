package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_10422 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken()); 
		long[] memo = new long[5001];
		memo[0] = 1;
		memo[2] = 1;
		StringBuilder answer = new StringBuilder();
		for(int i = 4; i <= 5000; i += 2) {
			for(int j = 0; j <= i-2; j +=2) {
				memo[i] = (memo[i] + memo[i-j-2]*memo[j])%1000000007;
				// n���� �ùٸ� ��ȣ���ڿ� : (n-2��)0��/(n-4��)2��/(n-6��)4�� /... /(0��)n-2��
				// n���� �ùٸ� ��ȣ���ڿ� : (n-2���� �ùٸ� ��ȣ���ڿ�)0���� �ùٸ� ��ȣ���ڿ�/(n-4���� �ùٸ� ��ȣ���ڿ�)2���� �ùٸ� ��ȣ���ڿ�/(n-6���� �ùٸ� ��ȣ���ڿ�)4���� �ùٸ� ��ȣ���ڿ� /... /(0���� �ùٸ� ��ȣ���ڿ�)n-2���� �ùٸ� ��ȣ���ڿ�
			}
		}
		for(int i = 0; i < T; i++) {
			answer.append(memo[Integer.parseInt(br.readLine())]).append('\n');
		}
		System.out.println(answer);
	}
}