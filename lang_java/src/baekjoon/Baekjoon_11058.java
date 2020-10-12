package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_11058 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		if(N < 6) System.out.println(N);
		else {
			long[] memo = new long[N];
			for(int i = 0; i < 6; i++) {
				memo[i] = (i+1);
			}
			for(int i = 6; i < N; i++) {
				long tmp = memo[i-1] + 1; // i-1 ���� A �� �Է��� ���
				for(int j = i-3; j >= 2; j--) {
					tmp = Math.max(tmp, memo[j]*(i-j-1)); // j �� �����Ͽ� ��� (i ���� �����Ͽ� ��� ������ ��� ��쿡 ���� ���غ���.) // �ִ밪�� �Ǳ� ���� �����ϴ� ����  i-1 ������ i ���� �ٸ� �� �����Ƿ� buffer�� ���� ���������� �ʴ´�.
				}
				memo[i] = tmp;
			}
			System.out.println(memo[N-1]);
		}
	}
	
}