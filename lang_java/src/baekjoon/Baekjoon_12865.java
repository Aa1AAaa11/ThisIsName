package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_12865 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ������ ���� 
		int K = Integer.parseInt(st.nextToken()); // ��ƿ �� �ִ� ����
		int[][] things = new int[N][2];
		int[][] memo1 = new int[N][K+1]; // memo[i][j] : i ��° ������ ���� �� ���� j ���ѿ� ���� 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			things[i][0] = Integer.parseInt(st.nextToken()); // ����
			things[i][1] = Integer.parseInt(st.nextToken()); // ��ġ
			if(things[i][0] <= K)memo1[i][things[i][0]] = things[i][1];
		}
		
		//System.out.println(dp1(things, memo1, N, K));
		System.out.println(dp(things, N, K));
	}
	static int dp(int[][] things, int N, int K) {
		int[] memo = new int[K+1]; // memo[i]: ���� i�� ��� �ִ� ��ġ
		// i ��° ������ ����Ͽ��� �� ���� K~0 ���� ����
		for(int i = 0; i < N; i++) { // 0~i ��° ���� ����� ��� (������ �ϳ��� ������ �ѹ��ۿ�  ����� �� �����Ƿ�)
			for(int j = K; j-things[i][0] >= 0; j--) { // ���԰�  K�� �Ǵ� ��� (���Ը� K~0 ���� ����� ���� ���⼭ ������ �ְ��� ��ġ�� ��������.)( ������ �� �ִ� ������ ������ Ŭ���� �̵浵�� Ŀ���Ƿ� ū������ ���ص� ��. 
				// j �� + 1���� K ���� ������������ �Ի��� ��� ���� K ���� ������ ���� ���� ���� �� �����Ƿ� i ��° ���� �ߺ� ���Ǵ� ������ �߻��Ѵ� ( // 1 2 / 1 1) )
				
				memo[j] = Math.max(memo[j], memo[j-things[i][0]] + things[i][1]); //i �� ���ų�, i ������� �ʰų� (memo[i-1]���ٴ� �׻� ŭ.)
			}
		}

		print(memo);
		return memo[K];
	}
	static int dp1(int[][] things, int[][] memo, int N, int K) {
		for(int i = 0; i < N; i++) { // i ��° ���ǿ� ���� ����
			for(int j = 0; j <= K; j++) { // �� ���� j �� ���ؼ� ����
				// i ��° ������ ��� ������ ��� (j-things[i][0] >= 0)
				if(i == 0 && j > things[i][0]) memo[i][j] = memo[i][j-1];
				if(i > 0) {
					if(j-things[i][0] >= 0)memo[i][j] = Math.max(memo[i][j], memo[i-1][j-things[i][0]] + things[i][1]);
					memo[i][j] = Math.max(memo[i][j], memo[i-1][j]);
				}
				
			}
		}
	//	print(memo);
		return memo[N-1][K];
	}
}