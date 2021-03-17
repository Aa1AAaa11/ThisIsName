package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_12970 extends Solution {
	
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(getString1(N, K));
	}
	static StringBuffer getString1(int N, int K) {
		StringBuffer answer = new StringBuffer();
		if(K == 0) {
			for(int i = 0; i < N; i++) answer.append('A');
			return answer;
		} else if (K < N){
			answer.append('A');
			for(int i = 0; i < K; i++) answer.append('B');
			for(int i = K; i < N-1; i++) answer.append('A');
			return answer;
		} else {
			int max_a = N/2; // A �� ������ N/2 �϶� �ִ�
			for(int a = 2; a <= max_a; a++) { //i �� A�� ���� ..1 ���� ���� K <N ���� Ȯ������
				int b = N-a;
				int maxab = a*b; // A a��, B b ���� �ִٰ� �� �� �����ϴ� �ִ� ��
				if(maxab >= K) { 
					int move = maxab - K;
					for(int i = 0; i < a-1; i++) {
						answer.append('A');
					}
					for(int i = 0; i < move; i++) {
						answer.append('B');
					}
					answer.append('A');
					for(int i = 0; i < b-move; i++) {
						answer.append('B');
					}
					return answer;
				}
			}
		}
		return new StringBuffer("-1");
	}
	static StringBuffer getString(int N, int K) {
		StringBuffer answer = new StringBuffer();
		if(K == 0) {
			answer.append('B');
			for(int i = 1; i < N; i++) answer.append('A');
			return answer;
		} else {
			int tmp = (int)Math.sqrt(K);
			for(int i = 1; i <= tmp; i++) {
				int a = i, b = K/i;
				if(K%i == 0 && a + b <= N) {
					for(int k = 0; k < a; k++) answer.append('A');
					for(int k = 0; k < b; k++) answer.append('B');
					for(int k = a+b; k < N; k++) answer.append('A');
					return answer;
				}
			}
		}
		
		return new StringBuffer("-1");
	}
}