package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

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
			ladder[a-1][b-1] = true; // N : b, b+1 선이 연결됨
		}
		int answer = canGo(0, ladder, N, H, 0, -1);
		if(answer == 100) answer = -1;
		System.out.println(answer);
	}
	static int canGo(int depth, boolean[][] ladder, int N, int H, int now_i, int now_j) { 
		int answer = 100;
		if(depth <= 3 && check(ladder, N, H)) return depth; // 추가한 사다리의 개수가 3개 이하이면 제대로 도착하는지 구해본다.
		else if(depth >= 3) return 100; // 3개를 추가하였는데 제대로 도착하지 못하는 경우
		for(int i = now_i; i < H; i++) { //now_h에 대해 모든 가로줄에 대해 가능하면 추가해본다. // 이러면 깊이 하나에 하나만. 가능함.
			int start_j = i == now_i?now_j+1: 0;
			for(int j = start_j; j < N-1; j++) {
				if(!ladder[i][j] && ((j > 0 && !ladder[i][j-1]) || j == 0) && ((j < N-2 && !ladder[i][j+1]) || j == N-2)) { // 현재 위치에 선이 없고 연속되는 가로 선이 없는 경우
					ladder[i][j] = true;
					answer = Math.min(answer, canGo(depth+1, ladder, N, H, i, j));// i, j가 포함되는 경우 // 값을 구하면 바로 반환하지 않고 0, 1, 2, 3 모두 구하게 되므로 시간이 오래걸린다.dfs로 해서 오래걸림.
					ladder[i][j] = false;
					//answer = Math.min(answer, canGo(depth, check+1, false, ladder, N, H, i, j)); 
					// i, j가 포함되지 않는 경우는 호춮할 필요가 없음. 호출을 안하면 포함을 안하기 때문. 포함되는 경우, 안되는 경우 모두 호출하는 코드는 모든 정점에 대해 참여, 비참여 따져와야할 경우. 이 경우는 참여할 3개만 구하면 된다.
				}
			}
		}
		return answer;
	}
	static boolean check(boolean[][] ladder, int N, int H) { // 해당 사다리를 사용할 때 i->i로 가는지
		for(int i = 0; i < N; i++) {
			int now = i;
			for(int j = 0; j < H; j++) {
				if(now < N-1 && ladder[j][now]) { //우측으로 이동
					now++;
				} else if(now > 0&& ladder[j][now-1]) { //좌측으로 이동
					now--;
				}
			}
			if(now != i) return false; 
		}
		return true;
	}
}
