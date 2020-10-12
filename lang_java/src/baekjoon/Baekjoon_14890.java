package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_14890 extends Solution {
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ���� ũ��
		int L = Integer.parseInt(st.nextToken()); // ���� ����
		int[][][] map = new int[2][N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[0][i][j] = Integer.parseInt(st.nextToken());
				map[1][j][i] = map[0][i][j];
			}
		}
		int answer = 0;
		for(int t = 0; t < 2; t++) {
			for(int i = 0; i < N; i++) {
				if(canGo(map[t][i], N, L)) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
	static boolean canGo(int[] map, int N, int L) { // ���ٿ� ���� ������ �� �ִ��� Ȯ���Ѵ�.
		boolean[] isSlope = new boolean[N];
		for(int i = 0; i < N-1; i++) {
			int difference = map[i] - map[i+1]; // i �� i+1�� ���� ����
			if( difference > 1 || map[i] - map[i+1] < -1) return false; // ���̰� �ٸ����� ���θ� ���� �� ���� ��� //���� ���Ƽ� ���θ� ���� �� ���� ���
			else if(difference == -1 || difference == 1) { // ���θ� ���� �� �ִ� ���
				//  difference�� 1�� ���(i+1�� �� ����) > ������ ���� ��ġ :i+1 / ������ ������ ��ġ : i+1+L-1
				//  difference�� -1�� ���(i+1�� �� ����) > ������ ���� ��ġ :i-(L-1) / ������ ������ ��ġ : i
				int start_slop = i + 1 + (difference - 1)/2*L; // ������ ���� ��ġ
				int last_slop = i + (difference + 1)/2*L; // ������ ������ ��ġ
				for(i = start_slop;i < last_slop; i++) { // start_slop~last_slop�� ���θ� ���� �� �ִ��� �Ǵ���
					if(i < 0 || i >= N-1 || isSlope[i] || map[i] != map[i+1]) return false; // ���� �� ���� ��ġ�ų�/ �̹� ���ΰ� ������ �ְų�/���� ���� �߰��� ���̰� �ٲ�� ������ ���θ� ���� �� ����.
					isSlope[i] = true; // ���θ� ���´�.
				}
				if(isSlope[i]) return false;
				isSlope[i] = true; // ������ last_slop�� ���θ� ���´�.
				i = i - (difference + 1)/2; // i+1�� �� ���� ��� i--�� ���ش�. ������ ���� ���� °���� �ٽ� �ö󰡾��ϴ��� �� �ʿ�. i+1�� �� ���� ���� ������ ����  ���� ������ ���̹Ƿ� i+1���� ���ص� ���� ����.
			}
			// ���ΰ� �ʿ� ���� ���� ���� i�� ���ؼ� ���Ѵ�.
		}
		return true;
	}
}
