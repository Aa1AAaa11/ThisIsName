package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_7453 extends Solution {

	static int n;
	static int[] A, B, C, D, left, right;

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		A = new int[n];
		B = new int[n];
		C = new int[n];
		D = new int[n];
		int length = n*n;
		left = new int[length];
		right = new int[length];
		long answer = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0, index = 0; i < n; i++, index++) {
			for(int j = 0; j < n; j++) {
				left[index] = A[i] + B[j]; // A �� B ���� �ϳ��� ������ ��� ����� ��
				right[index] = C[i] + D[j]; // C �� D ���� �ϳ��� ������ ��� ����� ��
			}
		}
		// ����
		Arrays.sort(left);
		Arrays.sort(right);

		int left_index = 0;
		int right_index = length - 1;
		while(left_index < length && right_index >= 0) {
			int tmp = left[left_index] + right[right_index];
			if(tmp > 0) { // ���� �� ū ��� right �� �� �۰� �����.
				right_index--;
			} else if(tmp < 0) {
				left_index++;
			} else {
				int left_same = getSameNumber(left_index, left, length, 1);
				int right_same = getSameNumber(right_index, right, length, -1);
				answer += (long)left_same* (long)right_same;
				right_index -=right_same;
				left_index +=left_same;
			}
		}
		System.out.println(answer);

	}
	static int getSameNumber(int index, int[] array, int length, int direction) {
		int tmp = index + direction;
		while(0 <= tmp && tmp < length && array[tmp] == array[index]) {
			tmp += direction;
		}
		return Math.abs(tmp - index);
	}
}
