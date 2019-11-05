package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_15666 extends Solution {

    static StringBuilder sb;
    static int N; // ���� ����
    static int M; //  ���� ����
    static int[] numbers;

    //������ ���ð����ϹǷ� visited�� ���ʿ���
    static void dfs(int length, int[] array) { // length : ������� ������ �迭 ����(�߰��Ǿ���ϴ� �迭�� �ε���), array : ����,
		if(length == M ) { //���� &&compare�����ϸ� �ȵ�
			for(int i = 0; i < length; i++) {
				sb.append(numbers[array[i]]).append(" ");
			}
			sb.append('\n');
		} else {
			int start = length==0? 0 : array[length-1];
            int prev_num = 0; // ������ �߰���,, 
			for(int i = start; i < N; i++) { 
				if(prev_num < numbers[i]) { // ���� ���ڰ� ������ ���� ��ε� ��� ���� ���´�.(for�� ���°� �ǹ̰� ���� ������ ��ε����� �ǹ��ϹǷ� �� ������ ��� -> ���� ���ڰ� �����Ƿ� �������ߺ��̤Ӥ�..)
					array[length] = i; 
					dfs(length+1, array);
					prev_num = numbers[i];
				}
			}
		}
	}

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numbers);

		// index�� ������ ������ش�.
		dfs(0, new int[M]); 
		System.out.print(sb.toString());

	}
}