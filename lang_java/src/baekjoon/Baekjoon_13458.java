package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_13458 extends Solution {
	
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		long answer = N; // �� ������
		for(int i = 0; i < N; i++) {
			double tmp = A[i] - B; // �ΰ������� �����ؾ� �ϴ� �л��� ��
			answer += tmp > 0 ? Math.ceil(tmp/C) : 0;
		}
		System.out.println(answer);
	}
}