package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_11057 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] memo = new int[N][10]; // memo[i] = i-1 �ڸ����ڿ����� ���������� ����, memo[i][j] = i-1 ��°���� j�� ������ ���������ǰ���
        int answer = 0;
        int tmp = 0;
        // �ʱ�ȭ
        for(int i = 0; i < 10; i++) {
        	memo[0][i] = 1;
        }

    	// f(n) = f(n-1)*2 - �������� +1�� �Ұ����� ��� (9�� ������ ���)
        for(int i = 1; i < N; i++) {
    		memo[i][0] = memo[i-1][0];
        	for(int j = 1; j < 10; j++) {
        		tmp = 0;
        		for(int k = 0; k < j+1; k++) // j �� ������ ����  0~ j �� ������ ���� ���� �� �ִ�.
        			tmp += memo[i-1][k];
        		memo[i][j] = tmp%10007;
        	}
        }
        
    	for(int j = 0; j < 10; j++) {
    		answer += memo[N-1][j]; // ���⼭ �׻� ������ ���� �ʰ� ����ϱ� �� �ѹ��� % ���ָ� �ȴ�. (10007*10�� int ǥ�� ���� ���� �̹Ƿ� �� ���ؼ� �������� �����൵ ��. ���� int ���� ���̿����� ������ �Ǿ���� �̻��� ���� ��µ� �� ����.)
    	}
        
		System.out.println(answer%10007);
	}
}