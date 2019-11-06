package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_15650 extends Solution {

    static StringBuilder sb;
    static int N; // ���� ����
    static int M; //  ���� ����
	static void dfs(int number, int length, boolean[] visited, int[] array) { // number: ������ �߰��� ��尡 ���°���� (0���� ����), length : ������� ������ �迭 ����(�߰��Ǿ���ϴ� �迭�� �ε���), visited : �湮�� �迭, array : ����
		if(length == M) { // ������ ����. �� �̻� �̾����� ������ ����� �ʿ�� ����
			for(int i = 0; i< length; i++)sb.append(array[i]).append(" ");
			sb.append("\n");
		} else { // ���� �湮 ��带 �� ����ؾ���
			for(int i = number+1; i < N; i++) { // i�� �湮�� ������ ��� (number�� �湮�Ͽ����Ƿ� number ���ĺ��� �湮�Ѵ�.)
				//visited�� ��� ���ʿ� ������ array[length] >array[length-1]�� �׻� �����ϱ� ����
				if(!visited[i]) { // �湮������ ���ٸ� �湮 (1����� �湮 �� visited[0] = true, visited�� �ε����� ���� �湮 ��庸�� 1 ����)
					visited[i] = true;
					array[length] = (i+1); // ��� �湮 .. ���� ����̹Ƿ� +1�� ���ش�... �̰��� i�� ���� ������ �ٲ��ְ� ���������� ���Ǵ� i�� 0���� �����ϴ� ��
					dfs(i, length+1, visited, array); // �� dfs�� ��������� ȣ���ϸ鼭 ������ �̵���
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
		
		dfs(-1, 0, new boolean[N], new int[M]); // ������ ��尡 �߰������� �ʾ����Ƿ� -1���� ����
		System.out.print(sb.toString());
	}
}
