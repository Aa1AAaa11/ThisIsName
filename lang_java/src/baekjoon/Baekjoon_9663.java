package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_9663 extends Solution {

	static int N;
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] queens = new int[N]; // ������ ��ġ�� ���� 
		System.out.println(nQueen(0, queens));
	}
	// ���� now �� ������ ������ �Ǵ�
	static boolean check(int depth, int[] queens, int now) {
		for(int i = 0; i < depth; i++) {
			//if(x == queens[i][0]) return false; // ���� ���� ���� depth�� �׻� �޶����� ������ ����
			if(now == queens[i]) return false; // ���� ���� ��
			if(now+depth == queens[i] + i) return false; // / �밢��
			if(now-depth == queens[i] - i) return false; // \ �밢��
		}
		return true;
	}
	// n-queen ���� ��ȯ
	static int nQueen(int depth, int[] queens) {
		if(depth == N) {
			return 1; // ����. ���� �����ϹǷ� 1 ��ȯ
		} else {
			int sum = 0;
			for(int i = 0; i < N; i++) {
				if(check(depth, queens, i)) {
					queens[depth] = i;
					sum += nQueen(depth+1, queens); // ������ ���� 1�ε� �װ͵��� ��ƾ���
				}
			}
			return sum;
		}
	}
}