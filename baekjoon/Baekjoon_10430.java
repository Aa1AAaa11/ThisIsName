package baekjoon;

import java.util.Scanner;

public class Baekjoon_10430 extends Solution {

	// ù° �ٿ� (A+B)%C, ��° �ٿ� (A%C + B%C)%C, ��° �ٿ� (A��B)%C, ��° �ٿ� (A%C �� B%C)%C�� ���
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated method stub
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		int a1 = (A+B)%C;
		int a2 = (A%C + B%C)%C;
		int a3 = (A*B)%C;
		int a4 = (A%C * B%C)%C;
		
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
		System.out.println(a4);
	}

}
