package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_9376 extends Solution {

	static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static char[][] map;
	static boolean [][] isWall;
	static boolean[][] isOut;
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		StringBuilder answer = new StringBuilder();
		for(int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			isWall = new boolean[h][w];
			isOut = new boolean[h][w];
			ArrayDeque<int[]> out = new ArrayDeque<int[]>(); // Queue<int[]> out = new LinkedList<int[]>();
			int[][][] wall = new int[3][h][w]; // �ش� ��ġ���� ���� � ���������ϴ���
			boolean[][][] visited = new boolean[3][h][w]; // �湮����
			int[][] start = new int[2][2];
			int who = 0;
			for(int i = 0; i < h; i++) {
				StringBuilder sb = new StringBuilder(br.readLine());
				for(int j = 0; j < w; j++) {
					map[i][j] = sb.charAt(j);
					if(isOut(i, j, h, w) && map[i][j]!='*') {
						isOut[i][j] = true;
						visited[0][i][j] = true; 
						// out.offer(new int[] {i, j});
						if(map[i][j] == '.')out.addFirst(new int[] {i, j});
						else out.addFirst(new int[] {i, j});
					}
					if(map[i][j]=='*') {// ��
						visited[0][i][j] = true; 
						visited[1][i][j] = true; 
						visited[2][i][j] = true; 
					} else if(map[i][j]=='#') {// ��
						wall[0][i][j] = 1; 
						wall[1][i][j] = 1; 
						wall[2][i][j] = 1; 
						isWall[i][j] = true;
					} else if(map[i][j] == '$') {
						start[who++] = new int[] {i, j};
						visited[who][i][j] = true; 
					}
				}
			}
			int[][][] wallMaps = new int[3][h][w];
			ArrayDeque<int[]> queue1 = new ArrayDeque<int[]>();
			ArrayDeque<int[]> queue2 = new ArrayDeque<int[]>();
			queue1.add(start[0]);
			queue2.add(start[1]);
			wallMaps[0] =bfsDQ(out, h, w, wall[0], visited[0]);
			wallMaps[1] =bfsDQ(queue1, h, w, wall[1], visited[1]); // Queue<int[]> queue1 = new LinkedList<int[]>();
			wallMaps[2] =bfsDQ(queue2, h, w, wall[2], visited[2]); // Queue<int[]> queue2 = new LinkedList<int[]>();
			
			int tmp = 100000;
			int minA = 10000, minB = 10000;
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					if((isWall[i][j] || isOut[i][j]) && (visited[1][i][j] && visited[2][i][j] )) { // �𿩼� ���� ���
						int sub = isWall[i][j]? -2: 0;
						tmp = Math.min(tmp, wallMaps[0][i][j]+wallMaps[1][i][j]+wallMaps[2][i][j] + sub);	
					} 
					if(isOut[i][j]) { // �𿩼� Ż������ �ʰ� �ٸ� Ż�ⱸ�� Ż���ϴ� ��� 
						// �����¿� ��ĭ���� �ø� �� 0,0 �� ��������� �̰� �ʿ����. �������� �����Ƿ�  �ᱹ���� 0,0���� ������ ����
						if(visited[1][i][j])minA = Math.min(minA, wallMaps[1][i][j]);
						if(visited[2][i][j])minB = Math.min(minB, wallMaps[2][i][j]);
					}
				}
			}
			tmp = Math.min(tmp, minA+minB);
			answer.append(tmp).append('\n');
		}
		System.out.print(answer.toString());
	}
	static boolean isOut(int i, int j, int height, int width) {
		return i == 0 || i == height-1 || j == 0 || j == width-1;
	}
	static int[][] bfsDQ(ArrayDeque<int[]> queue, int height, int width, int[][] wall, boolean[][] visited) { // �켱���� ť ���
		ArrayDeque<int[]> deque = (ArrayDeque<int[]>) queue;
		while(!deque.isEmpty()) {
			int[] now = queue.poll();
			int now_i = now[0];
			int now_j = now[1];
			for(int i = 0; i < 4; i++) {
				int next_i = now_i + move[i][0];
				int next_j = now_j + move[i][1];
				if(0 <= next_i && next_i < height && 0 <= next_j && next_j < width ) {
					if(!visited[next_i][next_j]) {
						char tmp = map[next_i][next_j];
						visited[next_i][next_j] = true;
						if(tmp == '#') { 
							wall[next_i][next_j] = wall[now_i][now_j] + 1;
							deque.addLast(new int[] {next_i, next_j}); // ���� ��� ���߿� �湮
						} else { 
							wall[next_i][next_j] = wall[now_i][now_j];
							deque.addFirst(new int[] {next_i, next_j}); // ���� �ƴ� ��� �ٷ� �湮
						}
						// ���ΰ�� ����, ���� �ƴ� ��� �ٷ� �湮�ϹǷ� ���� ó�� ä���� �� �ּҰ����� ä�����Ե�.
					}
				}
			}
		}
		return wall;
	}

	static void print(int[][] array) {
		for(int i = 0; i < array.length;i++) {
			for(int j = 0; j < array[i].length;j++) System.out.print(array[i][j]+" ");
			System.out.println();
		}
		System.out.println();
	}
	static int[][] bfs(Queue<int[]> queue, int height, int width, int[][] wall, boolean[][] visited) {
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int now_i = now[0];
			int now_j = now[1];
			for(int i = 0; i < 4; i++) {
				int next_i = now_i + move[i][0];
				int next_j = now_j + move[i][1];
				if(0 <= next_i && next_i < height && 0 <= next_j && next_j < width ) {
					if(visited[next_i][next_j]) { // �湮�ߴ� ����̸�  wall�� ���������� ������Ʈ�ؾ��� �ٽ� Ž���� �ʿ�� �����Ƿ� ť�� �߰������� �ʴ´�.
						int add = isWall[next_i][next_j]? 1 : 0;
						if(wall[next_i][next_j] > wall[now_i][now_j] + add) {
							wall[next_i][next_j] =  wall[now_i][now_j] + add; //�ּҷ� ������Ʈ �Ŀ� �ּҿ��� �ٽ� �̵��غ�����
							queue.offer(new int[] {next_i, next_j});
						}
					} else { // �湮������ ���� ���
						visited[next_i][next_j] = true;
						if(!isWall[next_i][next_j]) { // ���� �ƴ� ���
							wall[next_i][next_j] = wall[now_i][now_j];
						} else { // ���� ��� ���� �� + �������� �� ��
							wall[next_i][next_j] += wall[now_i][now_j]; 
						}
						queue.offer(new int[] {next_i, next_j});
					}
				}
			}
		}
		return wall;		
	}
}
