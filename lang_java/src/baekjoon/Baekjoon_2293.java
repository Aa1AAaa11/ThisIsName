package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2293 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coins = new int[n];
		int[][] memo = new int[k][n]; // memo[i] : �������� i���� ����� ����� �� (memo[i][j] : �������� i+1���� ����� ����� ��(��, coins[0]~coins[j] ������ ������ �����.))
		for(int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
			if(coins[i]-1 < k) memo[coins[i]-1][i] = 1;
		}
		// System.out.println(dp1(memo, coins, k, n));
		System.out.println(dp2(coins, k, n));
	}
	
	static int dp1(int[][] memo, int[] coins, int k, int n) {
		for(int i = 1; i < k; i++) { // i ���� ���� ����� ���� ��� (2������ ����Ѵ�.)
			for(int j = 0; j < n; j++) { // ������ ������ ����Ͽ�����
				if(i-(coins[j]) >= 0) {
					for(int l = 0; l <=j; l++) {
						memo[i][j] += memo[i-(coins[j])][l]; 
					}
				}
			}
		}
		int answer = 0;
		for(int i = 0; i <n; i++) {
			answer += memo[k-1][i];
		}
		return answer;
	}
	static int dp2(int[] coins, int k, int n) { // 1��° ������ ���, 1��+2��° ���� ���, ... �̷������� memo�� ä��������. (k���� ���� ����� ���� ���� ��... ����� ���� ���� �������� �Է� ������� �����Ͽ� �߰��Ǵ� ������ �ڿ� ���̴� ����̹Ƿ� ���� �ٸ��ֵ��� �ߺ����� ������ ����.)
		int[] memo = new int[k];
		for(int i = 0; i < n; i++) { // ������ ������ ������� 
			if(coins[i]-1 < k) memo[coins[i]-1] += 1;
			for(int j = 0; j < k; j++) { // k���� ���� ����
				if(j-coins[i] >= 0)memo[j] += memo[j-coins[i]];
			}
			
		}
		return memo[k-1];
	}
}