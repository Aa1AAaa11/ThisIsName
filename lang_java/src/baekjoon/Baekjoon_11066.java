package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_11066 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		StringBuilder answer = new StringBuilder();
		for(int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int[][] memo = new int[k][k]; // memo[i][j] : i~j ���� ��ġ�µ� ��� �ּ�
			int[] sum = new int[k]; // sum[i] : i ������ ��

			int before = 0;
			for(int i = 0; i < k; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				memo[i][i] = 0; // i~i �� ��ġ�º���� 0��
				if(i > 0) {
					sum[i] = sum[i-1] + tmp;
					memo[i-1][i] = before + tmp;
					
				} else sum[i] = tmp;
				before = tmp;
			}
			answer.append(dp(k, memo, sum)).append('\n');
		}
		System.out.println(answer);
	}
	
	
	static int dp(int k, int[][] memo, int[] sums) {
		for(int i = 2; i < k; i++) { // i+1�� ��ġ�°�.
			for(int j = 0; j < k-i; j++) { //j ���� i+1�� ��ħ (j ���� j+i ����)
				memo[j][j+i] = Integer.MAX_VALUE; // memo[j][j+i]�� ���ſ� ����� ��� + ��ġ�µ� ����� ���
				for(int l = 0; l < i; l++) {
					memo[j][j+i] = Math.min(memo[j][j+i], memo[j][j+l] +  memo[j+l+1][j+i]); // j, j+l �� ��ġ�µ� �ʿ��� ��� +  j+l+1,j+i �� ��ġ�µ� �ʿ��� ���
				}
				int sum = sums[i]; // j~ j+i ������ �� (i~j+i�� ��ġ�Ƿ� i~j+i �ո�ŭ.��ġ�µ� ����� �ʿ��� ����� �ʿ�)
				if(j > 0) sum = sums[j+i] - sums[j-1]; 
				memo[j][j+i] += sum; // j, j+l�� j+l+1,j+i�� ���ϴµ� ���ſ� ����� ���(sum ���ϱ� �� memo[j][j+i]) + i~i+j ���� ���� ���ϴµ� �ʿ��� ���(sum)
			}
		}
		return memo[0][k-1];
	}
}