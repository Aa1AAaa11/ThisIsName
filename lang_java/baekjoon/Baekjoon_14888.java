package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14888 extends Solution {
	
	
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
	
	/*
	 * 
	
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] numbers;
	static int[] operator_array; // �����ڰ� ��� �ִ���
	static int N;
	static boolean[] visited;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		visited = new boolean[N];
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
		get_operator(0, new int[N-1]);
		System.out.println(max+"\n"+min);

	}
	// dfs ������� �������� ��ġ�� ���Ѵ�, �� �����ڴ� N-1�� -> �̷��� ������ ��ġ�� ���ϰ� �ش� ��ġ���� +, -, *, / �� ������� ������ �ϴ°� �� ����, (4^n �� ���⵵�� �����Ƿ�).. ����� ������ �����ϴ� �ڵ�� n! �� ���⵵�� ����.
	static void get_operator(int depth, int[] operators) { //depth�� ������ ��� ����, operators : ������� ������ ������
		
		// ���ڰ� N���� �� �����ڴ� N-1�� ���
		if(depth == N-1) {
			int tmp = calculate(operators, numbers);
			if(tmp < min) min = tmp;
			if(tmp > max) max = tmp;
			}
		else { // �߰��� ������ ��ġ ����� �ʿ��� ���
			for(int i = 0; i < N-1; i++) {
				if(!visited[i]) {
					visited[i] = true; // visited[depth] �ƴ�. visited[]
					operators[depth] = i; //operators[i] �� �ƴ�. //depth ��° ������ i ����߰�
					get_operator(depth+1, operators); 
					visited[i] = false;
				}
				
			}
		}
		
	}
	
	// ������ �迭�� ���ڸ� �Է¹޾� ���
	static int calculate(int[] operators, int[] numbers) { //int[] operators : �������� ��ġ�� ��� �迭, int[] numbers: ���ڵ��� ��� �迭
		// operators ���� �����ڰ� +, - x, / �߿� ���� �˾ƾ���
		
		int[] operators_limit = new int[4]; // �� �����ڰ� ������ ��������
		operators_limit[0] = operator_array[0]-1; //index�̹Ƿ� -1, 0�� ���� -1�� �Ǿ���Ѵ�.
		for(int i = 1; i < 4; i++) {
			operators_limit [i] = operators_limit [i-1] + operator_array[i];
		}
		
		//��� ���
		int result = numbers[0];
		for(int i = 1; i < N; i++) {
			
			// �����ڰ�  + , - , x , / �߿� ����� ���
			int operator = 0;
			
			while(operators[i-1] > operators_limit[operator] && operator < 3){ //operator �ִ밪�� 3
				operator++;
			}
			switch(operator) {
				case 0: 
					result += numbers[i];
					break;
				case 1: 
					result -= numbers[i];
					break;
				case 2:
					result *= numbers[i];
					break;
				default: 
					result /= numbers[i];
			}
		}
		
		return result;
	}*/

	/*
	 * 
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
    // ��� ���� Ȱ��
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
	 */
}
