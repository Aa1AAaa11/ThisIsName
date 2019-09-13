package baekjoon;

import java.util.Scanner;

public class Baekjoon_1002 extends Solution {

	/*
	 * ��� : �� ���� �߽���ǥ�� �������� �־��� �� ������ ������ ���ϼ��� >>��ǥ�� ��ǥ�� ���� �Ÿ��� ������ ���� : ���� �ȴ�.
	 * 
	 * */
	
	@Override
	public void solution() {
		int Answer = 0;
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		int[] a = new int[3];//��ǥ�� ��ġ
		int[] b = new int[3];//��ǥ�� ��ġ
		
		for(int j = 0; j < T;j++) {
			for(int i = 0; i < 3; i++) {
				a[i] = sc.nextInt();
			}
			for(int i = 0; i < 3; i++) {
				b[i] = sc.nextInt();
			}
			
			int sumR =  a[2] + b[2];
			int subR = Math.abs(a[2]-b[2]);
			double distance = Math.sqrt((a[0]-b[0])*(a[0]-b[0])+(a[1]-b[1])*(a[1]-b[1]));
			
			if(distance == subR || distance == sumR) {
				if(distance == 0 && (a[2] == b[2]))Answer = -1;//-1�� ���(���� ��)
				else Answer = 1;//���� or ����
				}
			else if(distance > subR && (distance < sumR) )Answer = 2;//2�� ���(����)
			else  Answer = 0;//0�� ��� (�ȸ���)
			
			
			System.out.println(Answer);
		}
		
	}

}
