package baekjoon;

import java.util.Scanner;

import common.Solution;

public class Baekjoon_2609 extends Solution {
	
	//�� ���� �ڿ����� �Է¹޾� �ּ� ������� ���
	
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
		
		System.out.println(LSD);
		
	}

}
