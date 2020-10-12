package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_11052 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String[] strP = br.readLine().split(" ");
        int[] P = new int[N];
        
		int max = 0;
		P[0] = Integer.parseInt(strP[0]);
		
		for(int i = 1; i < N;i++) {
			P[i] = Integer.parseInt(strP[i]);
			// max = 0; max�� 0���� �ʱ�ȭ�� �ʿ�� ����. f(n)�� �׻� f(n-1)����ũ�ϱ�.. f(n-1) + f(1) , ...�߿��� �ִ밪�� ã�� ���̱� ����,
			
			// n�� 1�� n-1 / 2�� n-2 ... / �׳� n �̷������� ���� �� �����Ƿ�
			// f(n) = max((f(1) + f(n-1)), (f(2) + f(n-2)), ''' , n�� ����ִ� ī�� ���� )
			for(int j = 0; j <= (i/2); j++) {
				if(max < P[j] + P[(i-1)-j]) max =  P[j] + P[(i-1)-j];
			}
			if(max > P[i])P[i] = max;
		}
		
		System.out.println(P[N-1]);
		
	}
}