package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_2753 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();

		// % 400
		// %100
		// %4
		// % 400==0 �� %4==0�� �����ϹǤ� %4==0�� �����ϴ� �� �߿��� %100==0�� �ƴ� �ָ� ã���� �� ->��, %100==0�̶�� �� ������ �ƴѰ� %100==0�� ��� %400==0�� ��츦 �����ϴ� ���� �ֱ� ����
		if( N%400==0 )
			System.out.println("1");
		else if( N%100 == 0 )
			System.out.println("0");
		else if( N%4 == 0 )
			System.out.println("1");
		else System.out.println("0");
	}
}
