package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2667 extends Solution {

	static boolean visited[][]; // ���� �湮 ����
	static int N; // �Է¹��� N ��
	static int complex = 0; // ���� (���� Ž������)
	static int[] number; // �� ������ ����
	
	static void search(int i, int j) {
		visited[i][j] = true; //�湮
		number[complex]++; // ������ ���Ե� ���� ����

		// ���� ������ �湮 ������ �������� �湮
		if(j < N-1)if(!visited[i][j+1])search(i, j+1);
		if(j > 0)if(!visited[i][j-1])search(i, j-1);
		if(i > 0)if(!visited[i-1][j])search(i-1, j);
		if(i < N-1)if(!visited[i+1][j])search(i+1, j);
		
	}
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		visited = new boolean[N][N];
		int max_complex = N*N/2+1;// ������ �ְ� ������ ������ �ִ� ���ϱ� (N*N)/2 �ݿø� ������ŭ ���� �� �֤���
		number = new int[max_complex]; 
		
		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			for(int j = 0; j <N; j++) {
				if(tmp.charAt(j) == '0')visited[i][j] = true; //0 �� ���� �湮�� �ʿ䰡 ����
			}
		}
		
		for(int i = 0; i< N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) { // �湮���� ���� ������ ���
					search(i, j); // �湮
					complex++; // �湮 �� ���� ���������� �����ϴ� ���� �ٸ� ������
				}
			}
		}
		Arrays.sort(number); // ����
		System.out.println(complex); // ������complex-1������ ������ 1���� �����ϰ� complex�� 0���� �����ϹǷ� complex���� ���� ����
		for(int i = 0; i < complex; i++) { //������ complex-1��
			System.out.println(number[max_complex - complex + i]); // 0���� complex��  ����ϸ� max_complex�� ä������ ���� �κ��� 0���� ��µǹǷ�..�ǵ����� ���� ��� ��µ�
		}
	}
}
