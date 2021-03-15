package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2008 extends Solution {
	
	static int N, M, a, b, X, Y;
	static int[] memo; // memo[i] : a ~ i ���� ���� �� �ɸ��� �ּ� ���
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // ���μ��� ����
		M = Integer.parseInt(st.nextToken()); // ���μ��� ����
		
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken()); // ����
		b = Integer.parseInt(st.nextToken()); // ����
		X = Integer.parseInt(st.nextToken()); // ���μ��� ���� �� ��� ���
		Y = Integer.parseInt(st.nextToken()); // ���μ��� �׸� �� ��� ���
		int min = Math.min(X, Y);
		
		memo = new int[N];
		for(int i = 0; i < N; i++) {
			memo[i] = Math.abs(i-(a-1))*Y; // �ٸ��� �ϳ��� ���� ��� (a-1�� memo�� 0���� �����ϹǷ� ������ ����)
		}
		
		int p = -1;
		for(int i = 0; i < M; i++) { // i ��° ���μ����� �ִٰ� �����Ͽ��� �� �ּҺ��
			p = Integer.parseInt(br.readLine())-1; //p, p+1�� �մ� ���� ��.. memo[i]�� 0�����̹Ƿ� �����Ѵ�.
			
			int tmp = memo[p];
			memo[p] = Math.min(memo[p+1], memo[p]+min); // memo[p] : p+1 -> p�� i ��° ���μ��� Ÿ�� �̵��ϴ� ���(memo[p+1]) : p+1 ���ٰ� p��,.. / i ��° ���μ��� ����ϰų� �����ϴ� ���.( memo[p]+min) 
			memo[p+1] = Math.min(tmp, memo[p+1]+min);
			
			for(int j = 0; j < N; j++) { // ������ ���� p�� p+1�� ���� �ּҺ���� ����� �� ���� p�� p+1�� ���ļ� ���� ���
				memo[j] = Math.min(memo[p] + Math.abs(j-p)*Y, Math.min(memo[p+1] + Math.abs(j-(p+1))*Y, memo[j])); // p ���� j�� / p+1���� j��(���� ���� �� i �� ���� ������Ʈ�Ǿ����Ƿ� p ���� j�� ���� ��� Ȯ�� �ʿ�) / i �� �����ϴ°� �ƴϰ� i �� �ƿ� ������� ���� ����.. �ƿ� ���μ� i�� ������� �ʰ� ���� ���... i ����� ���� p�� p+1�� ��ġ�� ��쿡�� Ȯ���� 
			}
		}
		
		System.out.println(memo[b-1]);
	}
}