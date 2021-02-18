package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import common.Solution;

public class Baekjoon_1767 extends Solution {
	
	static int N, M, K;
	static long[][][] memo;
    static long[][][] d = new long[101][101][101];
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		memo = new long[N+1][M+1][K+1]; // 
		// ���� ���ٸ� �ִ� ��� �ʱ�ȭ
		for(int i = 0; i <= N; i++) {
			for(int j = 0; j <= M; j++) memo[i][j][0] = 1; // �ʱⰪ ����
			// �Ϻη� i, j 0 ���� ����. 1 �� �۰� ���� x -> 1�� �۰��ϸ� �ε��� ���� 1�� �� ����� 2ĭ�̶� 2�� �߰��� �����ѵ�.. ������ �׷��� �߰��ϸ� ������ ����
		}
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) { // ���� ���ٸ� �ִ� ��� �ʱ�ȭ �Ϸ��Ͽ���
				for(int k = 1; k <= K; k++) {
					
					// 1. j ��° �����ٿ� �ƹ��͵� �߰����� �ʴ� ���
					memo[i][j][k] += memo[i][j-1][k];
					
					// 2. j ��° �����ٿ� 1�� �߰��ϴ� ���
					//memo[i][j][k] += memo[i][j-1][k-1];  �̷��� �ϸ� �߰��� �Ϳ� �ִ� ���� 2�� ���ݹ޴� ��찡 ���� �� ���� -> ������ �ްų�/ �ִ� �ѹ��� �ްų��� ��������
					// 2-1. j ��° �����ٿ� 1�� �߰��ϴµ� �ȸ´� ���
					if(k >= 1) memo[i][j][k] += memo[i-1][j-1][k-1]*(i);
					if(k > 1) {
						// 2-2. j ��° �����ٿ� 1�� �߰��ϴµ� �´� ���
						if(j >= 2) memo[i][j][k] += memo[i-1][j-2][k-2]*(i)*(j-1);
						// 3. j ��° �����ٿ� 2�� �߰��ϴ� ���
						if(i >= 2)memo[i][j][k] += memo[i-2][j-1][k-2]*(i*(i-1))/2;
					}
					
					memo[i][j][k] %= 1000001;
				}
			}
		}
		System.out.println(memo[N][M][K]);
		
		
	}
}

