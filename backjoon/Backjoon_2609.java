package backjoon;

import java.util.Scanner;

public class Backjoon_2609 extends Solution {
	
	//�� ���� �ڿ����� �Է¹޾� �ִ� ������� �ּ� ������� ���
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int tmp;
		int originX = x;
		int originY = y;
		while(y!=0) {
			tmp = y;
			y = x % y;
			x = tmp;
		}
		int GCD = x;
		int LSD = (originX * originY)/GCD;
		
		System.out.println(GCD);
		System.out.println(LSD);
		
	}

}
