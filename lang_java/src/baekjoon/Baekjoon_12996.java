package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_12996 extends Solution {
	
	static boolean[][][][] visited;
	static long[][][][] memo;
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int[] sing_list = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		memo = new long[S+1][sing_list[0]+1][sing_list[1]+1][sing_list[2]+1];
		visited = new boolean[S+1][sing_list[0]+1][sing_list[1]+1][sing_list[2]+1];
		System.out.println(dp(S, sing_list[0], sing_list[1], sing_list[2]));
		
	}
	static long dp(int S, int a, int b, int c) {
		// 0���� ���� ��� : ���� x / a,b,c �� �ϳ��� S���� ū ��� �ҷ����ϴ� ���� ����ŭ �θ� �� ����. / a+b+c�� S���� ������ �� �ҷ��� S�� ä�� �� ����.
		if(a < 0 || b < 0 || c < 0 || S < 0 || a > S || b > S || c > S || a+b+c < S) return 0; 
		if(S == 1) { // ���� �ϳ��� ��� ����� ���� �ϳ��ۿ� ����. �� �� �θ��� �� �ۿ�.. 1���� �ҷ��� �ϴ� ��Ȳ�̹Ƿ� ������ ������ ��찡 �ƴϸ� �� �ҷ�����.. ����Ǽ� 1����.
			memo[S][a][b][c] = 1;
		} else if(!visited[S][a][b][c]) { // �湮�� ���� ���ٸ�..
			visited[S][a][b][c] = true;
			// S-1 ���� ���� �θ��� ������ �ϳ��� � ���� �߰��ȴٰ� ����
			// �� �ϳ��� �θ� �� �ִ� ����� ���� �ִ� 7���� : a,b,c,ab,ac,cb,abc
			memo[S][a][b][c] = (dp(S-1, a-1, b, c) + dp(S-1, a, b-1, c) + dp(S-1, a, b, c-1) + dp(S-1, a-1, b-1, c) + dp(S-1, a-1, b, c-1) + dp(S-1, a, b-1, c-1) + dp(S-1, a-1, b-1, c-1))% 1000000007;
		}
		return memo[S][a][b][c];
	}
}

