package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_11722 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N]; //A[i] :i ��° ����
       
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	A[i] = Integer.parseInt(st.nextToken());
        }
        
        // 2.  memo[i] = ���Ҽ����� �� i ��° ���ҿ��� ���� �� �ִ� ���� ������ ��
        int[] memo = new int[N + 1];
        int length = 1;
        memo[0] = Integer.MAX_VALUE;
        memo[length] = A[0];
        for(int i = 1; i < N; i++) {
        	int j = length;
        	while(memo[j] <= A[i]) { // memo[j] <= A[i]�� ��� ��� ����.
        		j--;
        	}
			memo[++j] = A[i]; // i, j�� ���� memo[j] > A[i]�� �����ϴ� �ּ� j�� ��� memo[j+1]�� A[i]�̴�. -> memo[j+1] <= A[i]�� �����ϴ� ���� ���� j 
			length = Integer.max(j, length); // length �� j �� ū ���� ����...
        }
        System.out.println(length);
        
        /*
        // 1. memo[i] = �迭�� i+1 ��° ���Ҹ� ����Ͽ��� �� ������ ���� ��(������) ���Ҽ����� ���
        int[] memo = new int[N];
        memo[0] = 1;
        int answer = 1;
        for(int i = 1; i < N; i++) {
        	memo[i] = 1; // �ʱⰪ�� 1 (������ �Ұ����� ���)
        	for(int j = 0; j < i; j++) { // i ���� ���� ��� j�� ���� Ȯ���ؾ���
        		if(A[i] < A[j]) memo[i] = Math.max(memo[i], memo[j] + 1);
        	}
        	answer = Math.max(answer, memo[i]); // memo[N-1]�� ������ �ƴ�
        }*/
	}
	
}