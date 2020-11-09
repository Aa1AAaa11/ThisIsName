package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_14442 extends Solution {
	static int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // ������ �׷�

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M= Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String tmp;
		int[][] map = new int[N][M];
		int[][] visited = new int[N][M]; // ������� ���µ� ���� � �μ������� (0: �湮 x, 1: �� �ϳ��� �Ⱥμ�, n: ���� n-1�� �μ�) -> �̰� ���� ��ġ�ε� ���� �μ� ������ �ٸ��� �ٸ��� �����Ѵ�.
		for(int i = 0; i < N; i++) {
			tmp = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j) - '0';
				visited[i][j] = -1; // �ϳ��� �μ��� ���� 0�� �ѹ��� �湮�������� �Ͱ��� �޶�� �ϹǷ�..0 ���� �ʱ�ȭ�ϸ� ���� �ϳ��� ���� �� ��� ����.
			}
		}
		// bfs �ȿ��� next�� �񱳵ǰ� �����Ƿ� ���� �� now���� ���� �� �ʿ�
		if(N ==1 && M == 1) System.out.println(1);
		else System.out.println(bfs(map, visited, N, M, K));
		
	}
	
	static int bfs(int[][] map, int[][] visited, int N, int M, int K) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {0, 0, 0, 1});
		visited[0][0] = 0;
		int[] now, next;
		while(!queue.isEmpty()) {
			now = queue.remove();
			for(int i = 0; i < 4; i++) {
				next = new int[] {now[0] + move[i][0], now[1] + move[i][1], now[2], now[3]+1};
				if(next[0] >= 0 && next[0] < N && next[1] >= 0 && next[1] < M) {
					int wall = next[2]+map[next[0]][next[1]]; // ���� �μ� ����
					// �μ� ���� ������ ���Ѻ��� �۰� �湮�� ���� ���ų� ������ �湮�Ͽ��µ� �μ� ���� ������ �̹��� �� ���� ���
					if(wall <= K && (visited[next[0]][next[1]] == -1 || visited[next[0]][next[1]] > wall )) { // �Դ� ���� bfs�� �ٽ� �´ٴ� ���� �ᱹ ������ �ƴ϶�� �Ҹ�, ��δ� ������ �Դ� �� �� ������ ���� �� �μ��� ���°�쵵 ������ �Ѵ�.
						next[2] += map[next[0]][next[1]];
						// System.out.println(now[0]+","+now[1]+" -> "+next[0]+","+next[1]+"  ("+next[2]+" "+next[3]+")");
						if(next[0] == N-1 && next[1] == M-1) return next[3];
						visited[next[0]][next[1]] = wall;
						queue.add(next);
					}
				}
			}
		}
		
		return -1;
	}
}
