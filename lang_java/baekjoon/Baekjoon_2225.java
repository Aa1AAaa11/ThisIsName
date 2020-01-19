package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_2225 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] memo = new int[N+1][K]; // memo[i][k] : ���� i�� ���� k+1�� ���� �������� ǥ���ϴ� ����� ��
        
         
        for(int i = 0; i <= N; i++) {
        	memo[i][0] = 1; // �� �ϳ��� ǥ�� ������ ���
        }
        
    	// memo[i][k] = memo[i][k] + memo[i-1][k-1] + memo[i-1][k-1] + ... + memo[0][k-1] // memo[i][k]�� 0�� ���ϴ� ���
        for(int k = 1; k < K; k++) {
        	for(int i = 0; i < N+1; i++) {
        		for(int j = 0; j <= i; j++) {
        			memo[i][k] = (memo[i][k] + memo[j][k-1])%1000000000;
        		}
        	}
        }
        // �Ʒ��� ��ȭ�����ε� ���� �� �ִ�.
        // memo[i][k] = memo[i-1][k] + memo[i][k-1] 
        // 1. i-1�� k�� ���̷� ���� ���� ���� ���ڿ� 1�� ���Ѵ�. // 2. i�� ���� �� �� �տ� 0�� �߰��� �ٿ��ش�.
        // 1�� 2�� ��ĥ�� x > 1���� �� �տ� +1�̶� �� �� ���ڰ� 0�� ���� �����Ƿ�
        // i-1 �� k�� ���̷� ���� �� �� �׿� ���� ���� +1�� ���ִ� ���°���� �ʿ䰡 ����. �ᱹ �� �� �ִ� ������ ����̱� ����
        
        System.out.println(memo[N][K-1]);
	}
	
}