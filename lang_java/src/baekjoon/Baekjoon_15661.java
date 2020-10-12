package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_15661 extends Solution {
	
	static int[][] S;
	static int N;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) S[i][j] = Integer.parseInt(st.nextToken());
		}
		System.out.println(search());
		//System.out.println(dfs(new int[N], new int[N], 0, 0, 0));
		
	}
	static int search() {
		int answer = 0;
		int max = (1 << N)-1;
		int[] score = new int[max]; // ���� ������ i �� ��� ���� �� �ִ� ����
		for(int i = 1; i < max; i++) { // �Ѹ� ���� ���� ����. 0... �̰ų� 1...�� ���� ����
			int new_student = -1;
			for(int j = 0; j < N; j++) { //j �� �л��� �ش� ���� ���ϴ��� Ȯ��
				int check = i & (1 << j);
				if(check != 0) { // j ��° �л��� ���� ���ԵǴ� ��� (==1 �ƴ�.. 1�� �ƴҼ��� ���� 2��.. 4��..)
					if(new_student == -1) { // ������ ���ߴ� ������ ���ο� �л��� ������ ��� (���ʺ��� ù��° 1�� ������ �ش� �л��� ���ο� �л�)
						int prev_team = i &(~check); // �� i ���� ���� ������ 1�� ������ ���� (i ���� �����Ƿ� ������ ���� ������ ����)
						score[i] = score[prev_team];
						new_student = j; //���� ���� 1�� ���ο� �л�
					} else { // ���� ���� �л��� ���� �������� �л��鳢�� �������� ������ ����
						score[i] += S[new_student][j] + S[j][new_student];
					}
				} 
			}
		}
		answer = getDifference(score, max, (max+1)/2);
		print(score);
		return answer;
	}
	static int getDifference(int[] score, int max, int half) {
		int difference = Integer.MAX_VALUE;
		for(int i = 1; i <= half; i++) { // ��Ī�̹Ƿ� �ݸ� �Ѵ�. 
			difference = Math.min(difference, Math.abs(score[max-i] - score[i])); // i�� i �ݴ�� (1011, 0100) ���� ����
		}
		return difference;
	}
	static int dfs(int[] start, int[] link, int start_index, int link_index, int now) {
		if(now == N) { 
			return Math.abs(getScore(start, start_index) - getScore(link, link_index));
		}
		int answer = Integer.MAX_VALUE;
		if(start_index < N-1) {
			start[start_index] = now;
			answer = dfs(start, link, start_index+1, link_index, now+1); // now�� start ���� ����
		}
		if(link_index < N-1) { // start ������ �Ѹ��̻� �־���ϹǷ� link���� �ο����� N-1�� �̸��� ��쿡�� link ���� �ִ´�.
			link[link_index] = now;
			answer = Math.min(answer, dfs(start, link, start_index, link_index+1, now+1)); //now�� link ���� ����
		}
		return answer;
	}
	static int getScore(int[] team, int length) {
		int score = 0;
		for(int i = 0; i < length; i++) {
			for(int j = i+1; j < length; j++) {
				score += S[team[i]][team[j]] + S[team[j]][team[i]];
			}
		}
		return score;
	}
}
