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
	}
	static String dp(int N, int K) {
		String[][][][][] memo = new String[31][][][][];
		int[][] add_num = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
		String[] add_string = {"A", "B", "C"};
		
		
		memo[1] = new String[1][2][2][2];
		memo[1][0][1][0][0] = "A";
		memo[1][0][0][1][0] = "B";
		memo[1][0][0][0][1] = "C";
		
		
		for(int i = 1; i <= N-1; i++) { // i�� i+1�� ���ϹǷ� 1���� 30-1���� �����ϴ�
			int next_i = i+1;
			int max_k = (i*i-i)/2;
			int max_next_k = (next_i*next_i-next_i)/2;
			memo[next_i] = new String[max_next_k+1][next_i+1][next_i+1][next_i+1]; // 0���� �����ϹǷ� length+1(i+1) ���� �����ϴ�.
			boolean[][][][] visited = new boolean[max_next_k+1][next_i+1][next_i+1][next_i+1];
			for(int j = 0; j <= max_k; j++) {
				for(int a = 0; a <= i; a++) { 
					for(int b = 0; b <= i-a; b++) {
						int c = i-a-b; // c�� a, b, i�� ������ �������Ƿ� 0~ c <= i-a-b; ���� for�� ���� �ʾƵ� �ȴ�.
						if(memo[i][j][a][b][c]!=null) {
							int[] add_j = {0, a, a+b};
							for(int add = 0; add < 3; add++) { // A, B, C�� ����
								int next_j = j+add_j[add];
								int next_a = a+add_num[add][0];
								int next_b = b+add_num[add][1];
								int next_c = c+add_num[add][2];
								if(next_a <= next_i && next_b <= next_i && next_c <= next_i && next_j <= max_next_k && !visited[next_j][next_a][next_b][next_c]) {
									visited[next_j][next_a][next_b][next_c] = true;
									memo[next_i][next_j][next_a][next_b][next_c] = memo[i][j][a][b][c]+add_string[add];
									if(i == N-1 && next_j == K) { 
										return memo[next_i][next_j][next_a][next_b][next_c];
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
}

