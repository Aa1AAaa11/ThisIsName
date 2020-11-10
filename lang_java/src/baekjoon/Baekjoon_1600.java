package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1600 extends Solution {
	static int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static int[][] horse_move = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken()), H = Integer.parseInt(st.nextToken());
		
		int[][] visited = new int[H][W]; // ���� ��ġ���� ���� ���������� �̵��� Ƚ���� �ٸ��� �ٸ�. ���ư����� ���� �̵����� ���� �̵��ؾ� ������ �� �� �����Ƿ� boolean�̸� ���� �������� �׻� ���� ���� �� ������ �׻� ��ȭ�¿츸 �̵��� 
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				visited[i][j] = -Integer.parseInt(st.nextToken())-1; // �湮 x, 0ȸ�� �湮�� ��� �и��� �ʿ���
 			}
		}
		if(W == 1 && H == 1) System.out.println(0);
		else System.out.println(bfs(visited, H, W, K));
	}
	
	static int bfs(int[][] visited, int H, int W, int K) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {0, 0, 0, 0});
		visited[0][0] = 0;
		while(!queue.isEmpty()) {
			int[] now = queue.remove();
			for(int i = 0; i < 4; i++) {
				int[] next = new int[] {now[0]+move[i][0], now[1]+move[i][1], now[2]+1, now[3]};
				//System.out.println("move: "+now[0]+","+now[1]+"("+now[2]+" "+now[3]+")"+" -> "+next[0]+","+next[1]+"("+next[2]+" "+next[3]+")"+" / ");
				//if(next[0] >= 0 && next[0] < H && next[1] >= 0 && next[1] < W)System.out.println(visited[next[0]][next[1]]);
				if(next[0] >= 0 && next[0] < H && next[1] >= 0 && next[1] < W && (visited[next[0]][next[1]] != -2 && (visited[next[0]][next[1]] > next[3] || visited[next[0]][next[1]] == -1))) { // next[3]�� �� �۴ٴ� ���� ������ �湮�ߴ� �� ���� ���� �̵��� ���� �� ���� �̵��� �Ƴ��� ��

					//System.out.println("ADD ");
					if(next[0] == H-1 && next[1] == W-1) return next[2];
					visited[next[0]][next[1]] = next[3];
					queue.add(next);
				}
			}
			for(int i = 0; i < 8; i++) {
				int[] next = new int[] {now[0]+horse_move[i][0], now[1]+horse_move[i][1], now[2]+1, now[3]+1};
				if(next[0] >= 0 && next[0] < H && next[1] >= 0 && next[1] < W && (visited[next[0]][next[1]] != -2  && (visited[next[0]][next[1]] > next[3] || visited[next[0]][next[1]] == -1)) && next[3] <= K) {
					if(next[0] == H-1 && next[1] == W-1) return next[2];
					visited[next[0]][next[1]] = next[3];
					queue.add(next);
				}
			}
		}
		return -1;
	}
}
