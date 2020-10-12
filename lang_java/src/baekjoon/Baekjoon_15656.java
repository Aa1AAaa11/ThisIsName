package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_15656 extends Solution {

    static StringBuilder sb;
    static int N; // ���� ����
    static int M; //  ���� ����
    static int[] numbers;
	static void dfs(int length, int[] array) { // length : ������� ������ �迭 ����(�߰��Ǿ���ϴ� �迭�� �ε���), array : ����
		if(length == M) { // ������ ����(array[i]�� numbers�� �ε��� ������)
			for(int i = 0; i< length; i++)sb.append(numbers[array[i]]).append(" ");
			sb.append("\n");
		} else { // ���� �湮 ��带 �� ����ؾ���
			for(int i = 0; i < N; i++) { // i�� �湮�� ������ ��� (number�� �湮�Ͽ����Ƿ� number ���ĺ��� �湮�Ѵ�.)
				array[length] = i; // ��� �湮 .. (index) //������ �湮�����ϹǷ� visited�� ����
				dfs(length+1, array); // �� dfs�� ��������� ȣ���ϸ鼭 ������ �̵���
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
