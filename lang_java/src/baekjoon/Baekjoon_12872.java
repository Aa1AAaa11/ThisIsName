package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_12872 extends Solution {
	
	static int N, M, P;
	static long[][] memo;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		memo = new long[P+1][N+1]; // [P][N] : N���� ������ ���� P �÷��̸���Ʈ�� ���� �� ���� �� �ִ� ����� ��
		memo[0][0] = 1; // memo[1][1] = N��
		for(int i = 1; i < P+1; i++) {
			for(int j = 0; j < N+1; j++) {
				// j <= N �̸� ������� �ʾҴ� �뷡 ��� ����. 
				if(j > 0) memo[i][j] =  (memo[i][j] + memo[i-1][j-1]*(N-j+1))%1000000007; 
				// memo[i-1][j-1] �� ������� �ʾҴ� ���ο� �뷡�� i ��° �뷡�� ����ϴ� ��� : i ��°�� ���� �뷡�� memo[i-1][j-1]���� ������� ���� N-(j-1)������ �����ϴ�. 
				
				// ����Ͽ��� �뷡 ���
				if(j > M) memo[i][j] = (memo[i][j] + memo[i-1][j]*(j-M))%1000000007; 
				// memo[i-1][j-1] �� ����Ͽ��� �뷡�� i ��° �뷡�� ����ϴ� ��� : i ��°�� ���� �뷡�� memo[i-1][j] �� �÷��̸���Ʈ���� �������� M ���� ������ i-1-M ���� ������ �ƴϰ� �÷��̸���Ʈ�� ���� �� ����� j������ �뷡 �� ���� M �� �����ϰ� ��� �����ϹǷ� j-M �� ���� ����
			}
		}
		System.out.print(memo[P][N]+" ");
		
	}
}

