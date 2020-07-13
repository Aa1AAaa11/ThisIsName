package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_15684 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		boolean[][] ladder = new boolean[H][N-1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladder[a-1][b-1] = true; // N : b, b+1 ���� �����
		}
		int answer = canGo(0, ladder, N, H, 0, -1);
		if(answer == 100) answer = -1;
		System.out.println(answer);
	}
	static int canGo(int depth, boolean[][] ladder, int N, int H, int now_i, int now_j) { 
		int answer = 100;
		if(depth <= 3 && check(ladder, N, H)) return depth; // �߰��� ��ٸ��� ������ 3�� �����̸� ����� �����ϴ��� ���غ���.
		else if(depth >= 3) return 100; // 3���� �߰��Ͽ��µ� ����� �������� ���ϴ� ���
		for(int i = now_i; i < H; i++) { //now_h�� ���� ��� �����ٿ� ���� �����ϸ� �߰��غ���. // �̷��� ���� �ϳ��� �ϳ���. ������.
			int start_j = i == now_i?now_j+1: 0;
			for(int j = start_j; j < N-1; j++) {
				if(!ladder[i][j] && ((j > 0 && !ladder[i][j-1]) || j == 0) && ((j < N-2 && !ladder[i][j+1]) || j == N-2)) { // ���� ��ġ�� ���� ���� ���ӵǴ� ���� ���� ���� ���
					ladder[i][j] = true;
					answer = Math.min(answer, canGo(depth+1, ladder, N, H, i, j));// i, j�� ���ԵǴ� ��� // ���� ���ϸ� �ٷ� ��ȯ���� �ʰ� 0, 1, 2, 3 ��� ���ϰ� �ǹǷ� �ð��� �����ɸ���.dfs�� �ؼ� �����ɸ�.
					ladder[i][j] = false;
					//answer = Math.min(answer, canGo(depth, check+1, false, ladder, N, H, i, j)); 
					// i, j�� ���Ե��� �ʴ� ���� ȣ���� �ʿ䰡 ����. ȣ���� ���ϸ� ������ ���ϱ� ����. ���ԵǴ� ���, �ȵǴ� ��� ��� ȣ���ϴ� �ڵ�� ��� ������ ���� ����, ������ �����;��� ���. �� ���� ������ 3���� ���ϸ� �ȴ�.
				}
			}
		}
		return answer;
	}
	static boolean check(boolean[][] ladder, int N, int H) { // �ش� ��ٸ��� ����� �� i->i�� ������
		for(int i = 0; i < N; i++) {
			int now = i;
			for(int j = 0; j < H; j++) {
				if(now < N-1 && ladder[j][now]) { //�������� �̵�
					now++;
				} else if(now > 0&& ladder[j][now-1]) { //�������� �̵�
					now--;
				}
			}
			if(now != i) return false; 
		}
		return true;
	}
}
