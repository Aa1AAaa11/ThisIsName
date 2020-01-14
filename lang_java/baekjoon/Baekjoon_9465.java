/*package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_9465 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] sticker;
        StringTokenizer st;
        int[][] memo;
	    StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            sticker = new int[N][2];
            memo = new int[N][2]; 
            // �Է�
            for(int sticker_j = 0; sticker_j < 2; sticker_j++) {
                st =  new StringTokenizer(br.readLine());
            	for(int sticker_i = 0; sticker_i < N; sticker_i++) {
            		sticker[sticker_i][sticker_j] = Integer.parseInt(st.nextToken());
            	}
            }
            if(sticker[0][0] > sticker[0][1]) {
            	memo[0][0] = sticker[0][0];
            	memo[0][1] = 0;
            } else  {
            	memo[0][0] = sticker[0][0];
            	memo[0][1] = 1;
            }

            memo[1][1] = 1-memo[0][1];
            memo[1][0] = memo[0][0] + sticker[1][memo[1][1]];
            //
            if(memo[1][0] < sticker[1][1-memo[1][1]] + sticker[0][memo[1][1]]) {  // 1��° ���� ������ ���� �ȶ��� �ٸ� �������� ���
            	memo[1][1] = 1-memo[0][1];
                memo[1][0] = sticker[1][1-memo[1][1]] + sticker[0][memo[1][1]];
            } 
            
            // f(n) = f(n-1) + �밢������ ���ų� �̰ų� f(n-2) + �� �� �ϳ� ���ų� -> max(f(n-1) + n-1�� �밢�� ��ġ, f(n-2) + 0��°, f(n-2) + 1��° )
            for(int memo_i = 2; memo_i < N; memo_i++) {
            	int max_j = 1 - memo[memo_i-1][1]; // 1�̸� 0, 0�̸� 1
            	int max = memo[memo_i-1][0] + sticker[memo_i][max_j];
            	
            	// memo[i-1][j] �� �߰��� ������ ����?
            	
            	//i-2, i��°���� j��  ���� ���
               if(max < memo[memo_i-2][0] + sticker[memo_i][memo[memo_i-2][1]] + sticker[memo_i-1][1-memo[memo_i-2][1]]) {// i������ ��ƼĿ��j �� i-2������ j�� ���� ��
                	max_j = memo[memo_i-2][1]; 
                	max = memo[memo_i-2][0] + sticker[memo_i][memo[memo_i-2][1]] + sticker[memo_i-1][1-memo[memo_i-2][1]];
            	}
            	
            	//i-2, i��°���� j��  �ٸ� ���
            	if(max < memo[memo_i-2][0] + sticker[memo_i][1-memo[memo_i-2][1]]) {
                	max_j = 1-memo[memo_i-2][1]; 
                	max = memo[memo_i-2][0] + sticker[memo_i][1-memo[memo_i-2][1]];
            	}
            	memo[memo_i][0] = max;
            	memo[memo_i][1] = max_j;
            }
            sb.append(memo[N-1][0]).append('\n');
	            
        }
        System.out.println(sb.toString());
        
	}
}*/
package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_9465 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] sticker;
        StringTokenizer st;
        int[][] dp;
        
	    StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            sticker = new int[N][2];
            dp = new int[N][3]; 
            // �Է�
            for(int sticker_j = 0; sticker_j < 2; sticker_j++) {
                st =  new StringTokenizer(br.readLine());
            	for(int sticker_i = 0; sticker_i < N; sticker_i++) {
            		sticker[sticker_i][sticker_j] = Integer.parseInt(st.nextToken());
            	}
            }
            dp[0][0] = 0;
            dp[0][1] = sticker[0][0];
            dp[0][2] = sticker[0][1];
            
            for(int k = 1; k < N; k++) {
                // ���� �ൿ�� ���� ������ ������ �ൿ�� ������. ���� ���� ��� �ൿ�ǰ�쿡 ���� ���� �� ���� �ൿ�� �����غ���.
            	// �׳� dp�� �ϸ� ��� �ô��� �� ���� ����
            	// dp�� i���� ������ ������ �����϶� ��� �������� ���� x
            	// 
                // 1. k���� ��ƼĿ�� ���� �ʴ� ���
                dp[k][0] = Integer.max(Integer.max(dp[k-1][0], dp[k-1][1]), dp[k-1][2]);
                
                // 2. k���� ��ƼĿ������ ���
                // 2-1. 1��° ��ƼĿ ���� ���
                dp[k][1] = Integer.max(sticker[k][0] + dp[k-1][2], sticker[k][0] + dp[k-1][0]);
                
                // 2. i���� ��ƼĿ������ ���
                // 2-2. 2��° ��ƼĿ ���� ���
                dp[k][2] = Integer.max(sticker[k][1] + dp[k-1][1], sticker[k][1] + dp[k-1][0]);
            }
        	
            sb.append(Integer.max(Integer.max(dp[N-1][0], dp[N-1][1]), dp[N-1][2])).append('\n');
	            
        }
        System.out.println(sb.toString());
        
	}
}