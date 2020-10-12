package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_14391 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] paper = new int[N][M]; // ����
		for(int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < M; j++) {
				paper[i][j] = tmp.charAt(j) - '0';
			}
		}
		int max = 1 << (N*M); // ������ �ִ밪
		int answer = 0;
		int n_max = N*M-1;
		for(int i = 0; i < max ; i++) {// ������ ��� ����� ���� ���� ��������.
			int tmp = 0;
			boolean [] prev_ver = new boolean[M];
			int[] tmp_ver = new int[M];
			for(int j = 0; j < N; j++) {
				boolean prev_hor = false;
				int hor = 0; // ���� ����
				for(int k = 0; k < M; k++) {
					int shift = (n_max - (j*M + k)); 
					boolean ishor = (i & (1 << shift)) == 0; // �������� ����
					if(ishor) {	// ������ ���
						if(prev_hor) { // (����� ����)
							hor = hor*10 + paper[j][k];
						} else { // (���� ����)
							tmp += hor;
							hor = paper[j][k];
						}
					} else { // ������ ��� (==1 �� ���� ��� 2 & 2 = 2 (!= 1) �̹Ƿ� ���������� ���η� �ȵ�� ���� ��� �߻�)
						if(prev_ver[k]) { // ���� ������, �������� ������ ��� (����� ����)
							tmp_ver[k] = tmp_ver[k]*10 + paper[j][k]; 
						} else { // ���� ������, ������ ���ΰ� �ƴ� ���(���� ����)
							tmp += tmp_ver[k]; // ���� k �������� ���� �־���. ������ �������Ƿ�
							tmp_ver[k] = paper[j][k]; //  ������ ���ΰ� �ƴϹǷ� *10 + �� �ƴ� �׳� ���Ը� ���ش�.
						}
					}
					prev_hor = ishor;
					prev_ver[k] = !ishor;
				}
				tmp += hor;
			}
		
			for(int l = 0; l < M; l++) tmp += tmp_ver[l];
			answer = Math.max(answer, tmp);
		}
		System.out.println(answer);
	}
}