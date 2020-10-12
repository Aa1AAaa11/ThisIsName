package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_15649 extends Solution {

    static StringBuilder sb;
    static int N; // ���� ����
    static int M; //  ���� ����
	static void dfs(int length, boolean[] visited, int[] array) { // length : ������� ������ �迭 ����(�߰��Ǿ���ϴ� �迭�� �ε���), visited : �湮�� �迭, array : ����
		if(length == M) { // ������ ����. �� �̻� �̾����� ������ ����� �ʿ�� ����
			for(int i = 0; i< length; i++)sb.append(array[i]).append(" ");
			sb.append("\n");
		} else { // ���� �湮 ��带 �� ����ؾ���
			for(int i = 0; i < N; i++) { // i�� �湮�� ������ ���
				if(!visited[i]) { // �湮������ ���ٸ� �湮
					visited[i] = true;
					array[length] = (i+1); // ��� �湮
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
		
		dfs(0, new boolean[N], new int[M]);
		System.out.print(sb.toString());
	}
}
