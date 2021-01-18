package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_12869 extends Solution {
	
	//boolean[][][] visited = new boolean[61][61][61]; // �ʿ����. memo�� ����ȣȯ��
	static int[][][] memo;
	static int[][] attack_list = {{1, 3, 9}, {1, 9, 3}, {3, 1, 9}, {3, 9, 1}, {9, 1, 3}, {9, 3, 1}};
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] SCV = new int[3];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			SCV[i] = Integer.parseInt(st.nextToken());
		}
		memo = new int[SCV[0]+1][SCV[1]+1][SCV[2]+1];
		for(int i = 0; i < SCV[0]+1; i++) {
			for(int j = 0; j < SCV[1]+1; j++) {
				for(int k = 0; k < SCV[2]+1; k++) {
					memo[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		memo[0][0][0] = 0; // �ʱⰪ ����. ���� ���� �� �� �ִ� ������ ��
		System.out.println(dp(SCV[0], SCV[1], SCV[2]));
	}
	static int dp(int scv0, int scv1, int scv2) {
		 if(memo[scv0][scv1][scv2] != Integer.MAX_VALUE) return memo[scv0][scv1][scv2]; 
		 
		// memo[scv0][scv1][scv2] = length;  // �̷��� dfs�̱� ������ �����´ٰ� ������ �ƴ� �� ����. dfs�� ��Ա�� ���� ������ 123�� ù��° ������ 5��° depth �̰� 2��° ������ 3 depth�� �� 5�� ���� �����Ƿ� ������ �ƴ� �� ����
		for(int i = 0; i < 6; i++) {
			// memo�� ����� ���� �����̶� �����ϰ� ����� �������� ���� ������ ���� ����. ������ ���� ������ ������ Ȱ���Ͽ� ���� ���� ����
			// ��ȯ���� +1�� �ϹǷ� 0 ���� ���°�� ������
			memo[scv0][scv1][scv2] = Math.min(memo[scv0][scv1][scv2], dp(Math.max(0, scv0-attack_list[i][0]), Math.max(0, scv1-attack_list[i][1]), Math.max(0, scv2-attack_list[i][2]))+1);
		}
		return memo[scv0][scv1][scv2];
	}
}

