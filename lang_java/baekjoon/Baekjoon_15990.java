package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_15990 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
	    StringBuilder sb = new StringBuilder();
        
        long[][] P = new long[100000][3]; // P[i] �� ���� i+1�� ���� �� �ִ� ����Ǽ�
        P[0][0] = 1; // 1
        P[1][1] = 1; // 2
        P[2][0] = 1; // 2+1
        P[2][1] = 1; // 1+2
        P[2][2] = 1; // 3
        
        // 4�� ����� ���ؼ� 1�� ����� + 3 / 2�� ����� +2 /3�� ����� +1 - (�����ؼ� ���Ǵ� ��� -> 2�� ����� +2, 3�� ����� +1 �߿� 1�� ������..)
        // f(n) = f(n-1) + f(n-2) + f(n-3) - (f(n-1) �� 1�� ������ + f(n-2) �� 2�� ������ + f(n-3) �� 3���� ������)
        for(int i = 3; i < 100000; i++) {
        	P[i][0] = (P[i-1][1] + P[i-1][2])%1000000009; // f(n-1) �� 1�� ������ �� ����
        	P[i][1] = (P[i-2][0] + P[i-2][2])%1000000009; // f(n-2) �� 2�� ������ �� ����
        	P[i][2] = (P[i-3][0] + P[i-3][1])%1000000009; // f(n-3) �� 3�� ������ �� ����
        }
        
        for(int i = 0; i < T; i++) {
        	int n = Integer.parseInt(br.readLine());
        	// ���⼭ �� �������� ���������. -> P[n-1][0] + P[n-1][1] + P[n-1][2]�� 1000000009���� Ŀ�� �� �����Ƿ�
        	sb.append((P[n-1][0] + P[n-1][1] + P[n-1][2])%1000000009).append('\n');
        }
		System.out.println(sb.toString());
		
	}
}