package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_8393 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();

		// 1~ n���� ���� (n*(n+1))/2��
        // (1+n)+ (2 + n-1) + (3 + n-2) ... = 1+n�� n/2��
		// ��ȣ����� ������... �� for�� �����°�  ����.
		System.out.print((n*n+n)/2);
	}
}
