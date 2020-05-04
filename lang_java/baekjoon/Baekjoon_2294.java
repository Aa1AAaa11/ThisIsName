package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_2294 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coins = new int[n];
		for(int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		System.out.println(dp2(coins, k, n));
	}
	static int dp2(int[] coins, int k, int n) { // 1��° ������ ���, 1��+2��° ���� ���, ... �̷������� memo�� ä��������. (k���� ���� ����� ���� ���� ��... ����� ���� ���� �������� �Է� ������� �����Ͽ� �߰��Ǵ� ������ �ڿ� ���̴� ����̹Ƿ� ���� �ٸ��ֵ��� �ߺ����� ������ ����.)
		int[] memo = new int[k];
		for(int i = 0; i < n; i++) { // ������ ������ ������� 
			if(coins[i]-1 < k) memo[coins[i]-1] = 1;
			for(int j = 0; j < k; j++) { // k���� ���� ����
				if(j-coins[i] >= 0 && memo[j-coins[i]] > 0) {
					if(memo[j] == 0) memo[j] = memo[j-coins[i]] + 1; // memo[j]�� ������ ���� �����
					else memo[j] = Math.min(memo[j-coins[i]] + 1, memo[j]);
				}
			}
			
		}
		if(memo[k-1] == 0) return -1; // �Ұ����� ���
		return memo[k-1];
	}
}