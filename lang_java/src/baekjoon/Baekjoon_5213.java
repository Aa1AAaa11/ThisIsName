package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_5213 extends Solution {
	
	static int N;
	static int[][][] tile;
	static boolean[][] visited;
	static int[] move_i_list = {-1, 1, 0, -1, 1, 0}; // ���ʿ��� ��(j-> Ȧ: 0, ¦: -1) , ���ʿ��� �Ʒ�(j-> Ȧ: 0, ¦: -1), ���ʿ��� ���� (j -> -1))
	// �����ʿ��� ��(j-> Ȧ: 1, ¦:0), �����ʿ��� �Ʒ�(j-> Ȧ: 1, ¦:0), �����ʿ��� ������(j -> 1)
	

	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		tile = new int[N][N][2];
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			int row = N - i%2;
			for(int j = 0; j < row; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				tile[i][j][0] = Integer.parseInt(st.nextToken());
				tile[i][j][1] = Integer.parseInt(st.nextToken());
			}
		}
		int[] route = new int[N*N-N/2+1]; // ��θ� ���� i ���� �湮 �� ��� ��带 ���� �;� �ִ����� �����ϴ���
		Queue<int[]> queue = new LinkedList<int[]>(); // ���� Ž������ Ÿ���� ����
		queue.offer(new int[] {0, 0}); // �ʱ� ��ġ
		visited[0][0] = true;
		int max = 0;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int i = 0; i < 6; i++) {
				int mod = now[0] % 2;
				int move_i = move_i_list[i];
				int move_j = i==2 || i == 5? 1-2*(1-i/3) : i/3 - 1 + mod; // i/2-(1-mod);
				int[] next = new int[] {now[0]+move_i, now[1]+move_j}; 
				if(isOk(now, next, i/3, 1-i/3)) {
                    max = Math.max(max, getNumber(next));
					visited[next[0]][next[1]] = true; // bfs �̹Ƿ� ������ �湮�ϸ� �ٽ� �湮�� �ʿ䰡 ���� dfs�� �ٸ��� visited �߰��� true�� �ٲ��� �ʾƵ� �ȴ�.
					// System.out.println(i+" : "+now[0]+","+now[1]+"("+i/3+") -> "+next[0]+","+next[1]+"("+(1-i/3)+")"+"   ");
					
                    route[getNumber(next)] = getNumber(now);
					queue.offer(next);
				}
			}
		}
		int length = 1;
		int now_route = max;
		answer.append(now_route).append(' ');
		while(now_route > 1) {
			length++;
			now_route = route[now_route];
			answer.insert(0, now_route+" ");
		}
		answer.insert(0, length+"\n");
		System.out.println(answer);
	}
	static boolean isOk(int[] now, int[] next, int tile_pos_now, int tile_pos_next) {
		// �� Ÿ���̸� 0�̶� ������ �ٸ�. 1���� �ԷµǱ� ������
		return (next[0] >= 0 && next[0] < N && next[1] >= 0 && next[1] < N) && (tile[now[0]][now[1]][tile_pos_now] == tile[next[0]][next[1]][tile_pos_next]) && !visited[next[0]][next[1]];
	}
	static int getNumber(int[] pos) {
		int number = pos[0]*N + pos[1] - pos[0]/2 + 1;
		return number;
	}
}
