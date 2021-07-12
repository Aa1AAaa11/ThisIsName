package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_11051 extends Solution {
	
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] memo = new int[N][N+1];
		memo[0][0] = 1;
		memo[0][1] = 1;
		
		for(int i = 1; i < N; i++) {
			memo[i][0] = 1;// i+1�� �� 0���� ������ �� ����� ��
			memo[i][1] = i+1; // i+1�� �� 1���� ������ �� ����� ��

			System.out.print("("+(i)+", "+0+") "+memo[i][0]+"   ");
			System.out.print("("+(i)+", "+1+") "+memo[i][1]+"   ");
			for(int j = 2; j <= i+1; j++) {
				memo[i][j] = (memo[i-1][j-1] + memo[i-1][j])%10007; // i�� �� j-1�� ���� + i�� �� j�� ���� = i+1�� �� j�� �����ϴ� ����� ��

				System.out.print("("+(i)+", "+j+") "+memo[i][j]+"   ");
			}
			System.out.println();
		}
		
		System.out.println(memo[N-1][K]);
		

	}
}