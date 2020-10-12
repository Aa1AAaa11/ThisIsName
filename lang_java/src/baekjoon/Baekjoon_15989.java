package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_15989 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        int[] answer_list = new int[T];
        int maxN = 0;
        for(int test_case = 0; test_case < T; test_case++) {
        	int n = Integer.parseInt(br.readLine());
        	answer_list[test_case] = n-1;
        	maxN = Math.max(n, maxN);
        }

    	int[][] memo = new int[maxN][2]; // memo[i][j] : i+1�� ����� ����� �� ([i][0] : 1, 2�� ����, [i][1] : 1, 2, 3 �� ����)
    	memo[1][0] = 1;
    	memo[2][0] = 1;
    	memo[2][1] = 1;
    	for(int i = 3; i < maxN; i++) {
    		memo[i][0] =  1 + memo[i-2][0]; // i-2 + 2 (i-2 (��� 1�� ��� + 1�� 2�� �̷���� ���) + 2) // i-3 +3 �� �ϹǷ� 1,2,3 ��� ����� ���� i-3���� �������Ƿ� i-2������ 1�� 2�� ����� ��츸 
    		memo[i][1] = 1 + memo[i-3][0] + memo[i-3][1]; // i-3 + 3 (i-3 (��� 1�� ��� + 1�� 2�� �̷���� ��� + 1,2,3 ��� ����� ���) + 3)	

    	}
        for(int test_case = 0; test_case < T; test_case++) {
        	answer.append(1+memo[answer_list[test_case]][0]+memo[answer_list[test_case]][1]).append('\n');
        }
    	
        System.out.println(answer);
        
	}
}