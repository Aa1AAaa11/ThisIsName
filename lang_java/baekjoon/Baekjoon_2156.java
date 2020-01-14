package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_2156 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int number; // i ��° �ܿ� ����ִ� �������Ǿ�
        int prev_number; // ���� ��(i-1��°)�� ����ִ� �������� ��
        
        int[][] dp = new int[n][3]; // dp[n][i] = n+1��° ���� ���� i�� ���ż� ���ô� ��� �ִ�� ���� �� �ִ� ��
        // ���� �� �� �ִ� �ൿ : �ȸ��ñ�, 1�ܸ��ñ�, 2�ܸ��ñ�
        // dp[i][0] = max(dp[i-1][0],[1],[2]) (������ �ʴ´�.)
        // dp[i][1] = dp[i-1][0] + number // �������� �ȸ��ð� ���� ���� ���Ŵ� (���� 1��)
        // dp[i][2] = dp[i-2][0] + number + prev_number // ������,������ ��� ���Ŵ� (���� 2��)
        
        
        // 2���� �迭�� �̷��� ���ص� dp[i] = i ��° �����������̸�
        // dp[i] = Max(i �ȸ���(i-1����), ���ܸ��ż� i (i-2���� + i ������ ��), 2�ܸ��ż� i(i-3���� + i-1 �����־�+i-2������ ��))
        //�ص� �Ǵ°� ������ ���̱� ������ ���� ���� = ���� ���� + ���� ������ �ൿ
        // �ൿ ������ �Ǻ��ϴ� ������ Ư���� ���� ���������ϰ� ���ϸ� �ȴ�. (�׳� �����ָ� ���ðų� ���ų��ǻ�����)
        // ���� �ൿ  ���� ���ΰ� Ư�� �������� �Ǻ��� �ʤ������ٸ� �߰��� ���� �ʿ���.

        number = Integer.parseInt(br.readLine());
    	dp[0][1] = number;
    	prev_number = number;
    	if(n >= 2) {
    		number = Integer.parseInt(br.readLine());
        	dp[1][0] = dp[0][1]; // 1��° �� ���ð� 2��° �� �ȸ��ô� ���
        	dp[1][1] = number; // 1��°�� �ȸ��ð� 2��° �� ���ô� ���
        	dp[1][2] = number + prev_number; // 1��°�� ���ð� 2��° �� ���ô� ���
        	prev_number = number;
    	}
        for(int i = 2; i < n; i++) {
        	number = Integer.parseInt(br.readLine());
        	dp[i][0] = Integer.max(Integer.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);
        	dp[i][1] = dp[i-1][0] + number;
        	dp[i][2] = dp[i-2][0] + number + prev_number;
        	prev_number = number;
        }
        
        System.out.println(Integer.max(Integer.max(dp[n-1][0], dp[n-1][1]), dp[n-1][2]));
	}
}