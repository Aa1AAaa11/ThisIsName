package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_15662 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[][] wheel = new int[T][8];
		for(int i = 0; i < T; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < 8; j++) wheel[i][j] = tmp.charAt(j) - 48; //tmp.charAt(j)
		}
		int K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int index_wheel = Integer.parseInt(st.nextToken())-1;
			int direction = Integer.parseInt(st.nextToken());
			wheel[index_wheel] = rotation(direction, wheel[index_wheel], T); // ȸ��
			int prev_direction = direction; // ���� ����� ȸ�� ����
			for(int right = index_wheel + 1; right < T; right++) {
				if(wheel[right][6] != wheel[right-1][2 + prev_direction]) { // right-1�� ȸ���Ͽ� ��ġ�� �ٲ�����Ƿ� ȸ�� ���϶��� ��ġ�� ����ؾ���.
					prev_direction = -prev_direction;
					wheel[right] = rotation(prev_direction, wheel[right], T); // ȸ��
				} else break; // ��ϰ� ȸ������ ���� ��� �� ������ ��Ϲ����� ȸ������ ����
			}
			// ȸ���ϴ� ��� �߽����� ������ ��ϵ鿡 ���� ���
			prev_direction = direction; // ���� ����� ȸ�� ����
			for(int left = index_wheel - 1; left >= 0; left--) {
				if(wheel[left][2] != wheel[left+1][6 + prev_direction]) {
					prev_direction = -prev_direction;
					wheel[left] = rotation(prev_direction, wheel[left], T); // ȸ��
				} else break; // ��ϰ� ȸ������ ���� ��� �� ������ ��Ϲ����� ȸ������ ����
			}
		}
		int sum = 0;
		for(int i = 0; i < T; i++) {
			sum += wheel[i][0];
		}
		System.out.println(sum);
		
	}
	static int[] rotation(int direction, int[] wheel, int T) {
		int start, move = -direction;
		if(direction == -1) start = 0; // �ݽð� ȸ���� ���
		else start = 7;
		int tmp = wheel[start];
		int index = start;
		for(int k = 0; k < 7; k++) { 
			int next = index + move;
			wheel[index] = wheel[next];
			index = next;
		}
		wheel[-start+7] = tmp; 
		return wheel;
	}	
}
