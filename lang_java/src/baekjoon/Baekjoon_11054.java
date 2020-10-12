package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_11054 extends Solution {
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] memo = new int[N][2]; 
		// memo[i][0] : i+1 ��° ���Ҹ� ����ϰ� A[0]���� ������ ��(���ʺ��� -> ����������) ������ ���������� ���� �� ����
		// memo[i][1] : i+1 ��° ���Ҹ� ����ϰ� A[N-1]���� ������ ��(�����ʺ��� -> ��������) ������ ���������� ���� �� ����
		// answer = max(memo[i][0] + memo[i][1] - 1) // 0~ i+1 ���� �κ� ���� , N-1 ~ i+1 �����κм���(-> i+1 ~ N-1 ���� ���� ) => 0 ~ N-1 ������� ����
		int[] subsequence = new int[N+1];
		int length = 1;
		subsequence[length] = A[0];
		memo[0][0] = 1;
		// ���ʺ��� �� (��������)
		for(int i = 1; i < N; i++) {
			memo[i][0] = 1;
			if(subsequence[length] < A[i]) {
				subsequence[++length] = A[i];
				memo[i][0] = length;
			} else {
				int j = 0;
				while(A[i] > subsequence[j]) {
					j++;
				}
				subsequence[j] = A[i];
				memo[i][0] = j;
			}
		}
		length = 1;
		subsequence[length] = A[N-1];
		memo[0][1] = 1;
		// �����ʺ��� �� (���Ҽ���)
		for(int i = N-2; i > -1; i--) {
			memo[i][1] = 1;
			if(subsequence[length] < A[i]) {
				subsequence[++length] = A[i];
				memo[i][1] = length;
			} else {
				int j = 0;
				while(A[i] > subsequence[j]) {
					j++;
				}
				subsequence[j] = A[i];
				memo[i][1] = j;
			}
		}
		int answer = 0;
		for(int i = 0; i < N; i++) {
		    // ������� : �������� + ���Ҽ���
			answer = Math.max(answer, memo[i][0] + memo[i][1] - 1);
		}
		System.out.println(answer);
		
		/*
		int[][] memo = new int[N][2];
		 // memo[i][0] : i+1��° ���Ҹ� ����ϴ� ������� �κм��� �� i+1 ��°���� ������ ��  ������ ���� �� ���� 
		 // memo[i][1] : i+1��° ���Ҹ� ����ϴ� ������� �κм��� �� i+1 ��°���� ������ �� ������ ���� �� ���� 
		  
		memo[0][0] = 1; 
		memo[0][1] = 1; 
		int answer = 1;
		
		for(int i = 1; i < N; i++) {
			memo[i][0] = 1;
			memo[i][1] = 1;
			for(int j = 0; j < i; j++) {
				int tmp_1 = memo[j][0] + 1; // ������ ����->���� �ۿ� ����
				int tmp_2 = Math.max(tmp_1, memo[j][1] + 1); // �����ϴ� ���� ����->����, ����->���� �� �� �����Ƿ�  �� ���� ��� ������.
				if(A[j] < A[i] && memo[i][0] < tmp_1) memo[i][0] = tmp_1; // 
				else if(A[j] > A[i] && memo[i][1] < tmp_2) memo[i][1] = tmp_2; 
			}
			
			// ���� �� �ִ� ������ ���� �� ����
			answer = Math.max(Math.max(answer, memo[i][0]), memo[i][1]);
		}
		System.out.println(answer);*/
        
	}
	
}