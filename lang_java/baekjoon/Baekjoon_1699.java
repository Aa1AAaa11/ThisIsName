package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_1699 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] memo = new int[N+1]; // memo[i] : ���� i�� �������� ������ ǥ���� ��, ������ ���� �ּ� ����
        
        memo[1] = 1; // memo[0] = 0�̿�����
        
        // i-1�� ����� 1�� ���ϴ� ��� + i-2�� ����� 2�� ���ϴ� ��� +.... +
        // i-1 ����� ���� ���� 1�� �ִ� ���°������ �ʾƵ� ��. ������ �� �ȿ��� ���� �ٲ�¸�� ����� ���� memo[i-1]�� ���ԵǾ��ֱ� ���� . 
        // �������� ������ �տ��������� ���� ������ �ʾƵ� ��. ������ i-1 + 0,i-2 + 1, .... �ϸ鼭 �������� ���� ��� ��츦������ ������. 1�� �տ��ְ� i-1�� �ϳ� �� ���������� ���°� �� ���� +�� �� i-1 �� �� �ϳ��� �������� ��쵵 ����.
    	// memo[i] = min(memo[i-1] + memo[0], memo[i-2] + memo[1], ... )
        for(int i = 2; i < N+1; i++) {
        	memo[i] = i; // ������ �ִ� ��. ��� 1�� ǥ���ϴ� ���
        	for(int j = 1; j*j <= i; j++) { // i ���� ���� �������� ���� ���غ�
        		// �������� ����� ���ϴ� ���
            	memo[i] = Integer.min(memo[i-j*j] + 1, memo[i]); // memo[0] = 0�̹Ƿ� i�� �������̸� memo[0] + 1 �� 1�� �ȴ�.
        	}
        }
        System.out.println(memo[N]);
	}
	
}