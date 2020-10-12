package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_15658 extends Solution {
	
	
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] numbers;
	static int[] operator_array; // �����ڰ� ��� �ִ���
	static int N;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		operator_array = new int[4]; // �����ڸ� �Է¹���
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		numbers[0] = Integer.parseInt(st.nextToken());
		for(int i = 1; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++ ) {
			operator_array[i] = Integer.parseInt(st.nextToken()); // �� ������� +, -, x, / �� �Է�
		}
		get_operator(operator_array, 0, numbers[0]);
		System.out.println(max+"\n"+min);
	}
	//dfs�� n!�� �ð����⵵ ������ +, -,  *, / �� �ش� depth���� ���� ����ϴ°�츦 ������ �ִ� 4^n �� �ð����⵵(������ ������ 0 �ϰ��� ��� ����..�ִ��� ���� �������� ������ ��� ���� ��)
	// dfs�� +++ �� ��� 123, 132, ... �ؼ� ������ ������� 6���� ����� �ʿ�
	// depth���� � ������ ����ϴ��� �� �غ��� ����  +++ �� ���� +++ �ϳ��� ����غ�
	static void get_operator(int[] operators, int depth, int result) {// operators : �����ڵ�, depth : ������ ����, result : ������� ���� ��
		if(depth == N-1) { // ������ ���̰� N-1�� ��� ���̻� ���ص���
			if(min > result) min = result;
			if(max < result) max = result;
			return;
		}
		// +, -, *, / �� ���ʴ�� �־��.
		for(int operator = 0; operator < 4; operator++) {
			if(operators[operator] > 0) { // ������ ����� �ִ� ���
				operators[operator]--; // operator ����Ͽ����Ƿ�
				int next_result = 0;
				switch(operator) {
					case 0: 
						next_result = result + numbers[depth+1];
						break;
					case 1: 
						next_result = result - numbers[depth+1];
						break;
					case 2:
						next_result = result * numbers[depth+1];
						break;
					default: 
						next_result = result / numbers[depth+1];
				}
				get_operator(operators, depth+1, next_result);
				operators[operator]++; // ���� for���� ������ �ָ� �ȵ�, ��, + �� �� ���� ������ -�� ���� ��� -> ������ �� + �� ���⼭ ����ó�� �Ǹ� �ȵȴ�.
			}
		}
	}
	
}
