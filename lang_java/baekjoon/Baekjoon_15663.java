package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_15663 extends Solution {

    static StringBuilder sb;
    static int N; // ���� ����
    static int M; //  ���� ����
    static int[] numbers;
    static int[] prev;
    
	static void dfs(boolean compare, int length, boolean[] visited, int[] array) { // compare: �񱳰� �ʿ�����(length-1 ���� ������ �� ��� ���ų�������). length : ������� ������ �迭 ����(�߰��Ǿ���ϴ� �迭�� �ε���), array : ����, visited: i��° ��� �湮�Ͽ����� Ȯ��
		if(length == M ) { //���� &&compare�����ϸ� �ȵ�
			if(!compare) { // compare�� true�� ���� ������ �߰��� ��ο� ���� �߰��� ��ο� ������ �߰��� ��ΰ� ��� ���� ����̹Ƿ� compare�� false�� ��찡 ���ο� ���.
				for(int i = 0; i < length; i++) {
					prev[i] = numbers[array[i]]; //�׳� array �ƴ�. �׳� array�� �翬�� �� �ٸ�. a < b < c �϶� numbers[a] <= numbers[b] <= numbers[c] �� �����ϹǷ� bc ca ��ü�� bc < ca������ ��η� �� ��� numbers[b]==numbers[c]�̰� numbers[c] > numbers[a] �̸� ����� ���ڰ� ca ���� Ŀ�� 
					sb.append(prev[i]).append(" ");
				}
				sb.append('\n');
			}
		} else {
			for(int i = 0; i < N; i++) { 
				if(!visited[i]) { //��� i�� �湮���� ���� ��츸 �湮
					array[length] = i; 
					visited[i] = true;
					if(length == 0) { // ���� ����� ������ �߰��� ��� 
						// ������ �߰��� ������ ��. ���� ������ �߰��� ������ ù��° �ڸ����� Ŭ��� �� ���� ���� ����.(���� ���� ������ ���� ����� �ݺ��̹Ƿ� ȣ������ ����.. ���� ���� ���� �� ����� ��θ� ����� �Ŵϱ�)
						if(prev[length] < numbers[array[length]])dfs(false, length+1, visited, array); 						
					} else {
						if(compare) { // length -1������ ������ ��� ������ ���, ȣ���� �ʿ���  ��� ���� ��� �������� �� Ŭ�����ְ�.
							
							//prev[length] > numbers[array[length]] �� ���� ������ �߰��ȼ������� �ƿ� �۾���. �� ���Ŀ������ֵ�. ���� ȣ������ ����
							// ���� ��쵵 ȣ���� �ʿ��ѵ�, ���� ������ ���� �ڸ����� ���ڰ� ���� ��� ���� ���ڿ��� ũ�⸦ �Ǻ��ؾ��ϱ� ����
							if(prev[length] <= numbers[array[length]])dfs(prev[length] == numbers[array[length]], length+1, visited, array); // length �� ������ �� �� same �缳��	
							
						} else { // length - 1������ ������ ��� �������� ���� ���
							dfs(false, length+1, visited, array); 	// �� �翬�� ���ʿ䰡 ����. ���� ������ ��ũ�ٴ°��� �׵ڷ� ���� ���� �� ũ�ٴ°�..
						}
					}
					visited[i] = false;
					compare  = true; // for���� �ѹ� ���� �״����� length - 1 ������ ��� ���� ����̹Ƿ� �񱳰� �ʿ��ϴ�.
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
		prev = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numbers);
		
		// index�� ������ ������ش�.
		dfs(false, 0, new boolean[N], new int[M]); 
		System.out.print(sb.toString());
		
	}
}
