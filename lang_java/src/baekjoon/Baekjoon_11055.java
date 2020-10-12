package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_11055 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N]; //A[i] :i ��° ����
        StringTokenizer st;

        st =  new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] memo = new int[N]; // memo[i] = i-1 ��° �迭�� ���ڸ� ����Ͽ��� �� ������ ���� ū ���� ����
        memo[0] = A[0];
        int answer = A[0];
        for(int i = 1; i < N; i++) {
        	memo[i] = A[i]; // �ʱⰪ �ʿ��ϴ�. A[i]�� ������ ���� ���ڵ� ���� ���� ��� memo[i]�� 0�� �ƴ϶� A[i]�̴� (102 101 102 ���� ���)
        	for(int j = i-1; j > -1; j--) {
        		// A[i] > A[j] �� �����ϴ� ���� ū j�� ���� �ƴ�.
        		// 1 100 2 102 �� ��� 102 > 2 �� i = 3�� ��� ���� ū j�� 2������ 1���� �� ������.
        		if(A[i] > A[j] && memo[i] < memo[j] + A[i]) { // 1. A[i] > A[j] �̰� (���������̰�) 2. ������ ���� �亸�� ������ �� �� ���
        			 memo[i] = memo[j] + A[i];
        		}
        	}
        	answer = Math.max(answer, memo[i]);
        }
        System.out.println(answer);
        
	}
	
}