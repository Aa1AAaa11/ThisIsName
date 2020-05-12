package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1495 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ���� 
		int S = Integer.parseInt(st.nextToken()); // ���ۺ���
		int M = Integer.parseInt(st.nextToken()); // �ø� �� �ִ� ���� �ִ�
		int[] V = new int[N]; // ���� ����Ʈ
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(dp(S, M, N, V));
		
	}
	static int dp(int S, int M, int N, int[] V) {
		boolean[][] memo = new boolean[N+1][M+1]; // memo[i][j] : i ��°�� ����Ͽ��� �� ���� j �� �������� ����
		memo[0][S] = true;
		for(int i = 1; i < N; i++) { // i ��°
			for(int j = M; j >= 0; j--) { // ���� j
				if( j-V[i-1] >= 0 && memo[i-1][j-V[i-1]] || j+V[i-1] <= M && memo[i-1][j+V[i-1]] ) { 
					// i-1, j-V[i] ���� ������ �ø��°� ������ ����̰ų�/ i-1, j+V[i] ���� ������ �����°� ����
					memo[i][j] = true;
				}
			}
		}
		for(int j = M; j >= 0; j--) { // ���� j
			if(j-V[N-1] >= 0 && memo[N-1][j-V[N-1]] || j+V[N-1] <= M && memo[N-1][j+V[N-1]]) {
				return j;
			}
		}
		
		return -1;
	}
}