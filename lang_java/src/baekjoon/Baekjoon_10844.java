package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_10844 extends Solution {
	
	@Override
	public void solution() throws IOException {
		/*
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] memo = new long[N];
        memo[0] = 9; // memo[i] = i+1 �ڸ����� �� ���� �� �ִ� ��ܼ��� ����
        long[] end = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1}; // end[i] = i�� ������ ����
        long[] tmp = new long[10];
        long disabled;
        
        // // f(n) = f(n-1)���� ������ �� -1�� ��� + f(n-1)���� ������ �� +1�� ��� - �������� +1�̳� -1�� �Ұ����� ���
        // f(n) = f(n-1)*2 - f(n-1) ���� 9�� 0���� ������ ���
        for(int i = 1; i < N; i++) {
        	tmp = new long[10];
        	disabled = (end[0] + end[9])%1000000000;
        	tmp[1] = end[0];
        	tmp[8] = end[9];
        	for(int j = 1; j < 9; j++) {
        		tmp[j-1] = (tmp[j-1] + end[j])%1000000000;
        		tmp[j+1] = (tmp[j+1] + end[j])%1000000000;
        	}
        	end = tmp;
        	
        	// memo[i] = (memo[i-1]*2 - disabled + 1000000000)%1000000000;
        	
        	memo[i] = (memo[i-1]*2 - disabled)%1000000000;
        	if(memo[i] < 0) memo[i] += 1000000000; // memo[i-1]�� 1000000000���� Ŀ�� disabled���� �۾��� ��쵵 ����. �׷� ��� ����� memo[i-1]�� 1000000000���� �۾������������� �ϸ� �ȴ�. 
        }
        
		System.out.println(memo[N-1]);
		*/

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] memo = new long[N][10]; // memo[i] = i-1 �ڸ����ڿ����� ��� ���� ����, memo[i][j] = i-1 ��°���� j�� ������ ��ܼ��ǰ���
        
        // �ʱ�ȭ
        memo[0][0] = 0;
        for(int i = 1; i < 10; i++)memo[0][i] = 1;
        long answer = 0;
        
        // // f(n) = f(n-1)���� ������ �� -1�� ��� + f(n-1)���� ������ �� +1�� ��� - �������� +1�̳� -1�� �Ұ����� ���
        // f(n) = f(n-1)*2 - f(n-1) ���� 9�� 0���� ������ ���
        for(int i = 1; i < N; i++) {
        	memo[i][0] = memo[i-1][1];
        	memo[i][9] = memo[i-1][8]; 
        	for(int j = 1; j < 9; j++) {
        		memo[i][j] = (memo[i-1][j-1] + memo[i-1][j+1])%1000000000; // ����� 1000000000�� ���� �� �����Ƿ� % �������
        	}
        }
        for(int i = 0; i < 10; i++) {
        	answer = (answer + memo[N-1][i])%1000000000;
        }
        
		System.out.println(answer);
	}
}