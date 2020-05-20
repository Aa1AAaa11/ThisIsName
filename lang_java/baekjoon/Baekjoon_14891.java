package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14891 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] wheel = new int[4][8];
		for(int i = 0; i < 4; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < 8; j++) wheel[i][j] = tmp.charAt(j) - 48; //tmp.charAt(j)
		}
		int K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int index_wheel = Integer.parseInt(st.nextToken())-1;
			int direction = Integer.parseInt(st.nextToken());
			wheel[index_wheel] = rotation(direction, wheel[index_wheel]); // ȸ��
			int prev_direction = direction; // ���� ����� ȸ�� ����
			for(int right = index_wheel + 1; right < 4; right++) {
				if(wheel[right][6] != wheel[right-1][2 + prev_direction]) { // right-1�� ȸ���Ͽ� ��ġ�� �ٲ�����Ƿ� ȸ�� ���϶��� ��ġ�� ����ؾ���.
					prev_direction = -prev_direction;
					wheel[right] = rotation(prev_direction, wheel[right]); // ȸ��
				} else break; // ��ϰ� ȸ������ ���� ��� �� ������ ��Ϲ����� ȸ������ ����
			}
			// ȸ���ϴ� ��� �߽����� ������ ��ϵ鿡 ���� ���
			prev_direction = direction; // ���� ����� ȸ�� ����
			for(int left = index_wheel - 1; left >= 0; left--) {
				if(wheel[left][2] != wheel[left+1][6 + prev_direction]) {
					prev_direction = -prev_direction;
					wheel[left] = rotation(prev_direction, wheel[left]); // ȸ��
				} else break; // ��ϰ� ȸ������ ���� ��� �� ������ ��Ϲ����� ȸ������ ����
			}
		}
		int sum = 0;
		int[] scores = {1, 2, 4, 8};
		for(int i = 0; i < 4; i++) {
			sum += scores[i]*wheel[i][0];
		}
		System.out.println(sum);
		
	}
	static int[] rotation(int direction, int[] wheel) {
		// ȸ���� �迭�� ���ϰ� �׳� int �� �����ؼ� shift ���� ����ص� �ɵ�.
		int start, move = -direction;
		if(direction == -1) start = 0; // �ݽð� ȸ���� ���
		else start = 7;
		int tmp = wheel[start];
		int index = start;
		for(int k = 0; k < 7; k++) { // ������ 7ȸ
			//int next = (index + move + 8) % 8; %������ ���� �������� �ݽð� ȸ���� 0����, �ð�ȸ���� 7���� �ؼ� ������ ���� �ʵ��� �Ѱ�.�̹Ƿ� %������ �ʿ����
			int next = index + move; // �ݽð��� ��� 0���� +1��, �ð��� ��� 7���� -1�� �ǹǷ� 0~7������ ���� �ʴ´�.
			wheel[index] = wheel[next];
			index = next;
		}
		wheel[-start+7] = tmp; // start�� 0�̸� 7, start�� 7�̸� 0
		return wheel;
	}	
}
/*
 * package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14891 extends Solution {

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] wheel = new int[4][8];
		int[] rotation_directions = new int[4];
		for(int i = 0; i < 4; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < 8; j++) wheel[i][j] = tmp.charAt(j) - 48; //tmp.charAt(j)
		}
		int K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int index_wheel = Integer.parseInt(st.nextToken())-1;
			int direction = Integer.parseInt(st.nextToken());
			rotation_directions[index_wheel] = direction;
			// ȸ���ϴ� ��� �߽����� ������ ��ϵ鿡 ���� ȸ�� ������ ����
			for(int right = index_wheel + 1; right < 4; right++) {
				rotation_directions[right] = 0;
				if(wheel[right][6] != wheel[right-1][2]) rotation_directions[right] = -rotation_directions[right-1];
			}
			// ȸ���ϴ� ��� �߽����� ������ ��ϵ鿡 ���� ȸ�� ������ ����
			for(int left = index_wheel - 1; left >= 0; left--) {
				rotation_directions[left] = 0;
				if(wheel[left][2] != wheel[left+1][6]) rotation_directions[left] = -rotation_directions[left+1];
			}

			for(int j = 0; j < 4; j++) {
				if(rotation_directions[j] != 0) {
					wheel[j] = rotation(rotation_directions[j], wheel[j]); // ȸ��
				}
			}
		}
		int sum = 0;
		int[] scores = {1, 2, 4, 8};
		for(int i = 0; i < 4; i++) {
			sum += scores[i]*wheel[i][0];
		}
		System.out.println(sum);

	}
	static int[] rotation(int direction, int[] wheel) {
		// ȸ���� �迭�� ���ϰ� �׳� int �� �����ؼ� shift ���� ����ص� �ɵ�.
		int start, move = -direction;
		if(direction == -1) start = 0; // �ݽð� ȸ���� ���
		else start = 7;
		int tmp = wheel[start];
		int index = start;
		for(int k = 0; k < 7; k++) { // ������ 7ȸ
			//int next = (index + move + 8) % 8; %������ ���� �������� �ݽð� ȸ���� 0����, �ð�ȸ���� 7���� �ؼ� ������ ���� �ʵ��� �Ѱ�.�̹Ƿ� %������ �ʿ����
			int next = index + move; // �ݽð��� ��� 0���� +1��, �ð��� ��� 7���� -1�� �ǹǷ� 0~7������ ���� �ʴ´�.
			wheel[index] = wheel[next];
			index = next;
		}
		wheel[-start+7] = tmp; // start�� 0�̸� 7, start�� 7�̸� 0
		return wheel;
	}	
}*/
