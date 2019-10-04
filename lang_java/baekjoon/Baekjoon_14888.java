package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14888 extends Solution {

	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] numbers;
	static int[] operators;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		operators = new int[4];
		//+, -, x, / ����
		

		// 0~N-1 ������ �����Ѵ�. // ������ ������ ��Ÿ���� ����
		int[] operator_array = new int[N-1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		numbers[0] = Integer.parseInt(st.nextToken());
		for(int i = 1; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			operator_array[i-1]  = i-1;
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		int result = calculate(operator_array);

		if(result > max) max = result;
		if(result < min)min = result;
		change(operator_array, 0);
		System.out.println(max+"\n" +min);

	}
	static int calculate(int[] operator_array) {
		int result = numbers[0];
		int N = numbers.length;
		
		int[] operators_limit = {operators[0]-1, operators[0]+operators[1]-1,  operators[0]+operators[1]+operators[2]-1, operators[0]+operators[1]+operators[2]+operators[3]-1 };
		
		for(int i = 0; i < N-1; i++) {
			int operator = 0;
			for(;operator < 4; operator++) {
				if(operator_array[i] <= operators_limit[operator])break;
			}
			switch(operator) {
				case 0: 
					result += numbers[i+1];
					break;
				case 1: 
					result -= numbers[i+1];
					break;
				case 2:
					result *= numbers[i+1];
					break;
				default: 
					result /= numbers[i+1];
			}
		}

		return result;
	}
	static int[] change(int[] array, int change_index) {
			
			int N = array.length;
			int tmp;
			for(int k = 0; k < N-1 - change_index ; k++ ) {//k�� ���� Ƚ�� (change_index�� ���� N-1 - change_index �� �ݺ� �ʿ�)
				if(change_index < N-2 )array = change(array, change_index+1); //change_index�� N-2 ���� �����ϹǷ�.
				int change = array[change_index];
				
				//change_index�� ��� �ٲ����� �ϴ��� Ȯ��
				for(int i = N-1; i >= change_index+1; i-- ) {
					if(array[i] > change) { 
						array[change_index] = array[i]; 
						array[i] = change;
						
						int sort_limit = ((N-1)-(change_index+1))/2; 
						
						//���ڸ� �ٲ۴�. 
						for(int j = 0; j <= sort_limit; j++) {
							tmp = array[change_index+1+j];
							array[change_index+1+j] = array[N-1-j];
							array[N-1-j] = tmp;
						}
						
						break;
					}
				}
				int result = calculate(array);
				if(result > max) max = result;
				else if(result < min)min = result;
			}
			if(change_index < N-2)array = change(array, change_index+1); // ���������� �ٲ�� �ڸ����� �ö󰡱� ���� ������ �ѹ� �� ������Ѵ�.
			return array;
		}
}
