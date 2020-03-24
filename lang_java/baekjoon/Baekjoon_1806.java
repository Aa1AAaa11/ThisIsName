package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1806 extends Solution {
	
	static int N, M;
	static int[] numbers;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(get());
		
	}
	static int get() {
		int start = 0, end = 0;
		int sum = 0;
		int answer = N+1;
		
		while(end < N || sum >= M) { // end < N �̰ų� sum >= M �̸� ����. (1. end < N �� ��� / 2. end >= N �� ��� sum >= M �̸� start �����ص� �������� �غ�����)
			if(sum < M) { // ���� �� ���� ��� (�� ���� �� �ִ� ���) end �߰�
				 sum += numbers[end++];
			} else { // ���� �� ū ���(�� ���� �� ���� ���) start ����
				sum -= numbers[start++];
				answer = Math.min(answer, end - start + 1); // ���� ���� ���
			} 
		}
		
		if(answer == N+1) return 0;
		return answer;
	}
}

