package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_1208 extends Solution {
	
	static int N, S;
	static int[] numbers, left, right;
	static int index = 0;
	
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		numbers = new int[N];
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		int length = 1 << (N >> 1);// (int)Math.pow(2, N >> 1);
		int mod_2 = N & 1; // 2�� ���� ������
		int left_length = length + length*mod_2; // Ȧ���� ��� length*2, ¦���� ��� length
		left = new int[left_length];
		right = new int[length];
		setNumber(0, 0, 0, left, mod_2);
		index = 0;
		setNumber(N/2 + mod_2, 0, 0, right, 0);
		// ����
		Arrays.sort(left);
		Arrays.sort(right);
		int index_left = 0;
		int index_right = length - 1;
		long answer = 0;
		if(S == 0) answer = -1; // �������� ���� ���� (left, right �Ѵ� �ƹ��͵� ������� �ʴ� ��쵵 0�� �Ǿ� �������� ��쵵 ���ǹǷ�)
		while(index_left < left_length && index_right >=0 ) {
			int tmp = left[index_left] + right[index_right];
			if(tmp < S) {
				index_left++;
			} else if(tmp > S) {
		 		index_right--;
			} else {
				int number_same_left = getNext(index_left, 1, left, left_length);
				int number_same_right = getNext(index_right, -1, right, length);
				answer += (long)number_same_left*number_same_right; // same�ִ�� 2^20 (��� ���� ���) �̹Ƿ� 2^20*2^20 �� int ������ �Ѵ´�.
				index_left += number_same_left;
				index_right -= number_same_right;
			}
		}
		System.out.println(answer);
	}
	static int getNext(int index, int direction, int[] array, int length) { // index�� ���� ���� ������ ��ȯ�Ѵ�.
		int next_index = index;
		while(0 <= next_index && next_index < length && array[index] == array[next_index]) {
			next_index += direction;
		}
		return Math.abs(next_index - index);
	}
	static void setNumber(int number_index, int depth, int number, int[] array, int left) {
		if(depth == N/2 + left) { // �������� ��� ����
			array[index++] = number;
		} else if(depth < N/2 + left) { // 
			setNumber(number_index+1, depth+1, number + numbers[number_index], array, left); // numbers[depth]�� �ִ� ���
			setNumber(number_index+1, depth+1, number, array, left); // numbers[depth]�� ���� �ʴ� ���
		}
	}
}

