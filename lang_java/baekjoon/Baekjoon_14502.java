package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Baekjoon_14502 extends Solution {

	static ArrayList<int[]> virus;
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		virus = new ArrayList<int[]>();
		boolean[][] visited = new boolean[N][M]; // ���̷����� �湮 �����ϸ� false, �湮 �Ұ����Ұ�� true
		int wall_size = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) {
					visited[i][j] = true;
					wall_size++;
				} else if(map[i][j] == 2) {
					virus.add(new int[] {i, j});
					visited[i][j] = true;
				}
			}
		}

		int answer = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					// 1. dfs ���
					//answer = Math.max(answer, dfs(0, visited, i, j, N, M)); // i, j �� ������
					//visited[i][j] = false;
					
					// dfs2 ���
					visited[i][j] = true;
					answer = Math.max(answer, dfs2(0, visited, i, j, N, M)); // i, j �� ������
					visited[i][j] = false; // ������ �ݺ����� ������ ���� �ʱ� ����
				}
				
			}
		}
		
		System.out.println(answer-(wall_size+3));
		
	}
	static int dfs2(int depth, boolean[][] visited, int i, int j, int N, int M) { // ��� ���� ������� 3�� ����
		int answer = 0;
		// �� ������ ������ ���̻� Ž���� �ʿ䰡 ���� ���. 
		if(depth == 2) return getSafe(visited, N, M); //getSafe ������ visited�� �ٲ�Ƿ� �������縦�Ѵ�.��. �̰� dfs�� Ž���ϰ� 
		for(int t = i; t < N; t++) { // ���� ����, ������ �޶� ���� ����̹Ƿ� �׻� ���������� ���ؼ��� Ž���Ѵ�.
			for(int k = t == i?j+1: 0; k < M; k++) {
				 if(!visited[t][k]) { // ���̳� ���̷����� �ƴ� ���
					visited[t][k] = true; // ���� ���� ���´�.
					answer = Math.max(answer, dfs(depth+1,  visited, t, k, N, M));
					visited[t][k] = false; // ���� ���� ���� ġ��.
				 }
			}
		}
		return answer;
	}
	static int dfs(int depth, boolean[][] visited, int i, int j, int N, int M) {  // ��� ���� ������� 3�� ����. dfs ���� ���� for�� ������ �ɵ� 3���� ���ϴ°��̴�.
		int answer = 0;
		visited[i][j] = true; // ���� ���� ���´�.
		if(depth == 2) return getSafe(visited, N, M); // �� ������ ������ ���̻� Ž���� �ʿ䰡 ���� ���.
		for(int t = i; t < N; t++) { // ���� ����, ������ �޶� ���� ����̹Ƿ� �׻� ���������� ���ؼ��� Ž���Ѵ�.
			for(int k = t == i?j+1: 0; k < M; k++) {
				 if(!visited[t][k]) { // ���̳� ���̷����� �ƴ� ���
					answer = Math.max(answer, dfs(depth+1,  visited, t, k, N, M));
					visited[t][k] = false; // ���� ���� ���� ġ��. visited�� �������� �ǹǷ� dfs ������ [t][k] �� ������ �ٲ�ä�� for���� ���� �ȵǹǷ�
				 }
			}
		}
		return answer;
	}
	static int getSafe(boolean[][] visited, int N, int M) { // Ž���� dfs�� �ص� �ɵ�. ���� ���� virus���� �������� �Ǵ�
		ArrayList<int[]> virus_copy = (ArrayList<int[]>) virus.clone();
		boolean[][] is_spread = new boolean[N][M];
		int number_virus = 0;
		int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		while(!virus_copy.isEmpty()) {
			int[] virus_now = virus_copy.remove(0);
			number_virus++;
			for(int i = 0; i < 4; i++) {
				int[] next_virus = {virus_now[0] + move[i][0], virus_now[1] + move[i][1]};
				if(isOk(next_virus[0], next_virus[1], N, M) && !visited[next_virus[0]][next_virus[1]] && !is_spread[next_virus[0]][next_virus[1]] ) {
					virus_copy.add(next_virus);
					is_spread[next_virus[0]][next_virus[1]] = true;
				}
			}
		}
		return N*M - number_virus;
	}
	static boolean isOk(int i, int j, int N, int M) {
		return i >=0 && i < N && j >= 0 &&j < M;
	}
}
