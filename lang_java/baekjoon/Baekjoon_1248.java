package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_1248 extends Solution {

	static int[][] marks;
	static int N;
	static int[] numbers;
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
    	String tmp_marks = br.readLine();
    	marks = new int[N][N]; // marks[i][j] : i+1 ~ j+1 ��° ������ ��
    	for(int i = 0, char_index = 0; i < N; i++) {
    		for(int j = i; j < N; j++) {
    			char tmp = tmp_marks.charAt(char_index++);
    			if(tmp == '0') marks[i][j] = 0;
    			else if(tmp == '-') marks[i][j] = -1;
    			else marks[i][j] = 1;
    		}
    	}
    	numbers = new int[N];
    	
    	getNumbers(0);
    	
    	StringBuilder answer = new StringBuilder();
		for(int i = 0; i < N; i++) {
			answer.append(numbers[i]).append(' ');
		}
    	System.out.println(answer);
	}
	
	static boolean isAble(int depth) {
		// number[depth]�� ������ �������� ����
		int sum = 0;
		for(int i = depth; i >= 0; i--) {
			int mark = marks[i][depth]; // i+1 ~ depth+1 ��° ������ ���� ��ȣ
			// ������ ���� Ȯ��
			// depth, (depth-1 ~ depth)��° ��, (depth-2 ~ depth)��° �� .... �̷������� ���Ѵ�.
			sum += numbers[i]; // �� 
			if(mark == 0 && sum!=0) {
				return false;
			} else if(mark == -1 && sum >= 0) {
				return false;
			} else if(mark == 1 && sum <= 0) {
				return false;
			}
		}
		return true;
	}
	
	// �������� ���θ� �˱� ���� boolean �� return
	static boolean getNumbers(int depth) {
		if(depth == N) {
			return true; // 
		} else {
			int mark = marks[depth][depth];
			int start = Math.min(1*mark, 10*mark); // ����
			int end = Math.max(1*mark, 10*mark); // ��
			for(int i = start; i <= end; i++) {
				numbers[depth] = i;
				if(isAble(depth) && getNumbers(depth+1)) return true;
				// if(isAble(depth)) return getNumbers(depth+1);
				// �̷��� �ȵ�. ù��° �������� �� ������ ������ ������ ���ص� ��������
			}
		}
		return false;
	}
}