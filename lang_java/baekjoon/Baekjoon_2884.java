package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_2884 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int H = sc.nextInt();
		int M = sc.nextInt();
		
		int num = 1 - (M / 45); //M�� 45 �̻��� ��� num�� 0, 45���� ���� ��� num�� 1
		int hour;
		if(H < num) { //H = 0, num = 1�� ���
			hour = 23;// 24 - H - num;
		} else {
			hour = H - num;
		}
		
		System.out.println(hour+" "+(M+60*num-45)); // �ƴϸ� �׳� ��°�� ������ �ٲ� �� �װ� /60, %60 �Ἥ �ð����� �ٲܼ���
	}
}
