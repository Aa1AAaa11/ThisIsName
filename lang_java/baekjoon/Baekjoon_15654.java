package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_15654 extends Solution {

    static StringBuilder sb;
    static int N; // ���� ����
    static int M; //  ���� ����
    static int[] numbers;
	static void dfs(int length, boolean[] visited, int[] array) { // number: ������ �߰��� ��尡 ���°���� (0���� ����), length : ������� ������ �迭 ����(�߰��Ǿ���ϴ� �迭�� �ε���), visited : �湮�� �迭, array : ����
		if(length == M) { // ������ ����(array[i]�� numbers�� �ε��� ������)
			for(int i = 0; i< length; i++)sb.append(numbers[array[i]]).append(" ");
			sb.append("\n");
		} else { // ���� �湮 ��带 �� ����ؾ���
			for(int i = 0; i < N; i++) { // i�� �湮�� ������ ��� (number�� �湮�Ͽ����Ƿ� number ���ĺ��� �湮�Ѵ�.)
				if(!visited[i]) { // �湮������ ���ٸ� �湮 (1����� �湮 �� visited[0] = true, visited�� �ε����� ���� �湮 ��庸�� 1 ����)
					visited[i] = true;
					array[length] = i; // ��� �湮 .. (index)
					dfs(length+1, visited, array); // �� dfs�� ��������� ȣ���ϸ鼭 ������ �̵���
					visited[i] = false; //���� for�������� �ٸ� ����̹Ƿ� ���� ��ΰ� ������ �ָ� �ȵ�
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
		dfs(0, new boolean[N], new int[M]); 
		System.out.print(sb.toString());
	}
}
