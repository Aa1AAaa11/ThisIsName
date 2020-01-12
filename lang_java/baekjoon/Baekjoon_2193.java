package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_2193 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] memo = new long[N][2]; // ���� ����!
        // memo[i] = i+1�ڸ��� ��ģ���� ����.
        // memo[i][j] = i+1 �ڸ����� j�� ������ ��ģ���� ����
        memo[0][1] = 1;
        
        // f(n) = f(n-1)���� 1�� ���̴� ��� + f(n-1)���� 0�� ���̴� ��� - ��ģ���� �ƴ� ���
        // f(n) = f(n-1)*2 - 1�� ������ ���
        // f(n) = memo[i-1][0] + memo[i-1][1]��...
        for(int i = 1; i < N; i++) {
        	memo[i][0] = memo[i-1][0] + memo[i-1][1]; // 0�� ���̴� ���
        	memo[i][1] = memo[i-1][0]; // 1�� ���̴� ���
        }
        System.out.println(memo[N-1][0] + memo[N-1][1]);
        
	}
}