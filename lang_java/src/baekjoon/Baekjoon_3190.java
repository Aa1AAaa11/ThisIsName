package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_3190 extends Solution {
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // ���� ũ��
		int K = Integer.parseInt(br.readLine()); // ����� ����
		boolean[][] apples = new boolean[N][N];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			apples[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = true;
		}
		int L = Integer.parseInt(br.readLine()); // ���� �̵� Ƚ��
		int[][] move_list = new int[L][2];
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			move_list[i][0] = Integer.parseInt(st.nextToken());
			move_list[i][1] = st.nextToken().toCharArray()[0];
		}
		int[] move = {0, 1}; // �̵��� ���� �ε��� ���� ��� ���ϴ���
		int time = 0;
		int move_index = 0;
		int[] head = {0, 0};
		boolean[][] isSnake = new boolean[N][N]; // �ش� ��ġ�� ������ ������
		// tail�� history ���� �Ӹ��� �ش� ��ġ�� ������ ��ġ�ߴ��� �����Ͽ��� �� ���̿� �ð��� �� ���ؼ� ���ص� �ɵ�.
		int[][][] history = new int[N][N][2]; // �ش� ��ġ���� ���� �̵��ߴ��� ����
		int[] tail = {0, 0};
		while(true) {
			time++;
			// ���� �Ӹ��� ��ġ�� ����
			int next_head_i = head[0]+move[0];
			int next_head_j = head[1]+move[1];
			
			if(next_head_i >= N || next_head_j >= N || next_head_j < 0 || next_head_i < 0 || isSnake[next_head_i][next_head_j]) break; // ���� ��ų� �ڱ� ���� ���� ���
			
			// �Ӹ��̵�
			history[head[0]][head[1]] = new int[]{next_head_i, next_head_j}; // ���� �Ӹ����� ���� ������ ����
			head = new int[] {next_head_i, next_head_j};
			isSnake[next_head_i][next_head_j] = true;
			
			if(apples[head[0]][head[1]]) { // ����� �ִ� ���
				apples[head[0]][head[1]] = false;
			} else { // ����� ���� ���
				// ���� ��ġ�� ����ش�.
				isSnake[tail[0]][tail[1]] = false;
				tail = new int[] {history[tail[0]][tail[1]][0], history[tail[0]][tail[1]][1]};
			}
			if(move_index < L && move_list[move_index][0] == time) { // ���� ȸ���� ���
				if(move_list[move_index][1] == 'L') move = new int[]{-move[1], move[0]}; // �������� ȸ�� 
				else move = new int[]{move[1], -move[0]}; // ���������� ȸ��
				move_index++;
			}
		}
		System.out.println(time);
	}
}
