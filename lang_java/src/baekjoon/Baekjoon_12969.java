package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_12969 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());

		System.out.println(dp(N, K));
		String answer = dp(new char[N], 0, 0, 0, 0, 0, new boolean[K+1][N+1][N+1][N+1], N, K);
		if(answer == null) answer = "-1";
		System.out.println(answer);
		
	}
	// bfs������ Ž���ϱ� ������ memo
	static String dp(int N, int K) {

		String[][][][] memo = new String[436][31][31][31]; // a,b,c�� ������ i�� ���ϹǷ� i���� �迭�����ʿ�� ����..
		boolean[][][][] visited = new boolean[436][31][31][31];
		int[][] add_num = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
		String[] add_string = {"A", "B", "C"};
		
		
		memo[0][1][0][0] = "A";
		memo[0][0][1][0] = "B";
		memo[0][0][0][1] = "C";
		
		
		for(int i = 1; i <= N-1; i++) { // i�� i+1�� ���ϹǷ� 1���� 30-1���� �����ϴ�
			int next_i = i+1;
			int max_k = (i*i-i)/2;
			int max_next_k = (next_i*next_i-next_i)/2;
			for(int j = 0; j <= max_k; j++) {
				for(int a = 0; a <= i; a++) { 
					for(int b = 0; b <= i-a; b++) {
						int c = i-a-b; // c�� a, b, i�� ������ �������Ƿ� 0~ c <= i-a-b; ���� for�� ���� �ʾƵ� �ȴ�.
						if(memo[j][a][b][c]!=null) {
							int[] add_j = {0, a, a+b};
							for(int add = 0; add < 3; add++) { // A, B, C�� ����
								int next_j = j+add_j[add];
								int next_a = a+add_num[add][0];
								int next_b = b+add_num[add][1];
								int next_c = c+add_num[add][2];
								if(next_a <= next_i && next_b <= next_i && next_c <= next_i && next_j <= max_next_k && !visited[next_j][next_a][next_b][next_c]) {
									visited[next_j][next_a][next_b][next_c] = true;
									memo[next_j][next_a][next_b][next_c] = memo[j][a][b][c]+add_string[add];
									if(i == N-1 && next_j == K) { 
										return memo[next_j][next_a][next_b][next_c];
									}
								}
							}
						}
					}
				}
			}
		}
		return "-1";
	}
	// dfs������ Ǯ� ������ answer�� �����ϸ��
	static String dp(char[] answer, int n, int k, int a, int b, int c, boolean[][][][] visited, int N, int K) { // n : ���� ���ڿ��� ����, k : ���� �����ϴ� ����, a: ���� A ����, b: ���� B ����, c : ���� C ����
		
		if(n==N) { // ���� ���� ���ڿ��� ���̰� ������ ���ǿ� �����ϴ� ���
			if(k == K) return String.copyValueOf(answer); // ������ ���Ѱ���
			else return null; // ������ ������.. �Ƿ�..���� ���̴� �´µ� K �� �ٸ�..
		}
		if(k > K) return null; // ������ ���ɼ��� ����.. k�� ���ų� �����ϹǷ�
		
		if(!visited[k][a][b][c]) {
			visited[k][a][b][c] = true;
		} else return null;
		
		answer[n] = 'A';
		if(dp(answer, n+1, k, a+1, b, c, visited, N, K)!=null) return String.copyValueOf(answer);
		answer[n] = 'B';
		if(dp(answer, n+1, k+a, a, b+1, c, visited, N, K)!=null) return String.copyValueOf(answer);
		answer[n] = 'C';
		if(dp(answer, n+1, k+a+b, a, b, c+1, visited, N, K)!=null) return String.copyValueOf(answer);
		
		
		return null;
		
	}
}

