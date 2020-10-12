package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_13707 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] memo = new int[N][K]; // memo[i][j] = ���� i+1�� K+1���� ���ڷ� ����� ����� ��
        // memo[i][j] += memo[i-p][j-1] (0 <= p <= i), 0< j < K
        // memo[i-1][j] += memo[i-1-p][j-1] (0 <= p <=i-1) (p= 0�� �����Ѱ� i�� j-1���� ����� �ڿ� 0 ���ϸ� �Ǵϱ�)
        // memo[i][j] = memo[i-1][j] + memo[i][j-1]
        
        memo[0][0] = 1; 
        for(int j = 1; j < K; j++) {
        	memo[0][j] = j+1; // 1 �� j+1���� ����� (������ �ٸ��� �ٸ� ���̹Ƿ� 1�� �ƴ� j ���� ���� (0�� 1 ���� �ٲ㰡�鼭 �����.))
        }
        for(int i = 1; i < N; i++) {
        	memo[i][0] = 1;
        	for(int j = 1; j < K; j++) {
            	memo[i][j] = (memo[i-1][j] + memo[i][j-1])%1000000000;
            	//System.out.println(i+", "+j+" // "+memo[i][j]);
        	}
        }   
        
        System.out.println(memo[N-1][K-1]);
	}
}