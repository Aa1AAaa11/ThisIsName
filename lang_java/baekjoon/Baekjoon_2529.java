package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_2529 extends Solution {

	static StringBuilder answer;
	static boolean isEnd;
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
    	int length = k+1;
    	int[] min_numbers = new int[k+1];
    	int[] max_numbers = new int[k+1];
    	char[] inequalitys = br.readLine().replaceAll(" ", "").toCharArray();
    	for(int i = 0; i < k+1; i++) {
    		max_numbers[i] = 9-i;// max�� 9~ k+1�� ���ڷ� ����� -> ū ���ں��� �������� ������.
    		min_numbers[i] = i;// min�� 0~ k+1�� ���ڷ� ����� -> �������ں��� �������� ������.
    	}
    	isEnd = false;
    	answer = new StringBuilder();
    	getNumbers(length, 0, max_numbers, new boolean[10], inequalitys, new int[k+1]);
    	answer.append("\n");
    	isEnd = false;
    	getNumbers(length, 0, min_numbers, new boolean[10], inequalitys, new int[k+1]);
    	System.out.println(answer);
    	
	}
	static void getNumbers (int length, int depth, int[] numbers, boolean[] visited, char[] inequalitys, int[] arrays) {
		if(depth == length) {
			for(int i = 0; i < length; i++) {
				answer.append(arrays[i]);
			}
			isEnd = true;
			return;
		}else if(depth > 0) { // ù��° ���ڰ� �ƴ� ���
			for(int i = 0; i < length && !isEnd; i++) { // isEnd �� �ƴ� ��� 
				int next_number = numbers[i]; // ������ �� ����
                
                // ������ �� ���ڰ� �湮������ ���� �����̰� ���� ���� ���� �� �� �ִ� ���ڶ��
				if(!visited[next_number] && isAble(arrays[depth-1], next_number, inequalitys[depth-1])) {
					visited[next_number] = true; // �湮
					arrays[depth] = next_number; //
					getNumbers(length, depth+1, numbers, visited, inequalitys, arrays);
					visited[next_number] = false;
				}
			}
		} else { // ù��° ������ ���
			for(int i = 0; i < length; i++) {
				visited[numbers[i]] = true;
				arrays[depth] = numbers[i];
				getNumbers(length, depth+1, numbers, visited, inequalitys, arrays);
				visited[numbers[i]] = false;
			}
		}
		
	}
	static boolean isAble(int present, int next, char inequality) { // present ������ next ���ڰ� �� �� �ִ��� �Ǻ�
		if(inequality == '<') return present < next;
		else return present > next;
	}
	
}