package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1525 extends Solution {
	
	static int[] numbers = new int[11];
	// static boolean[][] visited = new boolean[9][9]; // visited[i][j] ���� i �� j ��ġ�� �湮�Ͽ����� ���� -> �̷��� 012 345 -> 102 305 -> 142 305 ���� 142 305 �� �湮�� ������ �ȴ�.
	// static boolean[] used = new boolean[87654322]; // 0~8�� �ߺ����� �ѹ����� ����ϹǷ� �տ� 7�ڸ��� ������ �ڿ� ���ڸ��� ����.. key/10���� �Ǵ��ص� �ɵ�..
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				int index = 3*i + j;
				numbers[index] = Integer.parseInt(st.nextToken());
				if(numbers[index] == 0) numbers[9] = index;
			}
		}
		numbers[10] = 0;
		System.out.println(bfs());
		
	}
	static int key(int[] array) {
		int key = 0;
		int ten = 1;
		for(int i = 0; i < 9; i++, ten*=10) {
			key += array[8-i]*ten;
		}
		return key;
	}
	static int bfs() {
		// ���� �����¿�� �̵��Ѵ�.
		Queue<int[]> queue = new LinkedList<int[]>();
		HashSet<Integer> visited = new HashSet<>();
		queue.add(numbers);
		visited.add(key(numbers));
		int depth = numbers[10];
		int[] move = {-1, 1, 3, -3};
		while(!queue.isEmpty()) {
			int[] present = queue.poll();
			int pos_0 = present[9];
			depth = present[10];
			// print(present);
			if(isAnswer(present)) return depth;
			for(int i = 0; i < 4; i++) { // ��, ��, ��, ������ �̵�
				int next = pos_0 + move[i];
				int[] tmp = present.clone();
				
				if(isOk(pos_0, next, move[i])) { // next%3 == 0
					tmp[pos_0] = tmp[next];
					tmp[next] = 0;
					int key = key(tmp);
					if(visited.add(key)) { // �湮���� ���� ���
						tmp[9] = next;
						tmp[10] = depth + 1;
						queue.offer(tmp);
					}
				}
			}
		}
		return -1;
	}
	static boolean isOk(int pos_0, int next, int direction) {
		if(direction == 1 ||direction == -1) {
			return direction*next%3 > direction*pos_0%3 && next >= 0 && next < 9;
		} else {
			return next < 9 && next >= 0;
		}
	}
	static boolean isAnswer(int[] array) {
		for(int i = 0; i < 9; i++) {
			if(array[i] != (i+1)%9) return false;
		}
		return true;
	}
}
