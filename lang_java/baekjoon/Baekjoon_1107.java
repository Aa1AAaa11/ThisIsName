package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1107 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
        int[] trouble = new int[10]; // ���峭 ��ư
        int N = Integer.parseInt(br.readLine()); // ��ǥ��
        int M = Integer.parseInt(br.readLine()); // ���峭 ��ư�� ����
        int answer; // �����е� ���� �� ���Ǥ������� �̵��ϴ� ���
		int answer_100 = Math.abs(N-100); // 100���� �����Ͽ� ���ڹ�ư�� ������ �ʰ� ����� ���
		
        if(M > 0) { // ���峭 ��ư�� ������ 0���� ū ���
        	st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) {
            	trouble[Integer.parseInt(st.nextToken())] = 1; // ���峭 ��ư�̸� 1�� ����
            }
            int minus = N, plus = N;
            boolean plus_len = isContain(plus, trouble); // ���ϴ� ��� 
            boolean minus_len = isContain(minus, trouble); // ���� ���

            for(answer = 0; answer < answer_100 && !(plus_len || minus_len); answer++) {
        		plus_len = isContain(++plus, trouble);
        		minus_len = isContain(--minus, trouble);
        		// �Ѵ� false�� ���(-> ���峭 ��ư�� �ִ� ���)�� ����.
            }
        	int len = 6; // ���������� �Է��ϴ� ������ ���� (�ִ� len = 6)
        	if(plus_len) len = length(plus); // plus�� ������ ���
        	if(minus_len) len = Math.min(len, length(minus)); // minus�� ������ ���
    		answer += len; // ��ư �Է�Ƚ�� : ������ �Է� ���� + ������ ��,�� ��ư �Է� Ƚ��
        } else { // ���峭 ��ư�� �ϳ��� ���� ���
        	answer = N==0? 1: (int)Math.log10(N) + 1; // ���������� �ٷ� N�� ���� �� �ִ�
        }
		answer = Math.min(answer_100, answer);
        System.out.println(answer);
	}
	static boolean isContain(int number, int[] trouble) { // number�� ���� �� �ִ� ��� true/ ���� �� ���� ���(���峭 ��ư�� �ִ� ���) false
		if(number < 1) return trouble[0] == 0; // 0�� ���峭 ��ư�̸� false, ���峪�� ���� ��ư�̸� true
		else {
			while(number > 0) { // number�� ��� �ڸ����� ���� ����
				if(trouble[number%10] == 1) return false; // ���峭 ��ư�� �ִ� ���
				number /= 10;
			}
			return true; // ���峭 ��ư�� ���� ��� (�ڸ���)
		}
	}
	static int length(int number) { // number�� �ڸ����� ����
		return number <= 0? 1: (int)Math.log10(number) + 1; // number < 0 �� ���� number = 0���� �ľ��� (number < 0�̸� --�� �ϸ� �ȵǴµ� �ϴϱ�)
	}
}