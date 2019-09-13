package baekjoon;

import java.util.Scanner;

public class Baekjoon_1003 extends Solution{

	/*
	 * ��� : �Ǻ���ġ 0,1 ��ȣ��Ǵ� Ƚ�� ���ϱ�
	 * */
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);

		// 0 ~ 40 ������ ������ ���� 0�� 1�� ��� ȣ��Ǵ��� ���
		
		/* 2���� �迭�� ���� ������ ���� ������ 0�� 1->0->1->1... 1�� 0->1->1->2... �̷��� �ǹǷ� 
		 *  0 : 1 0 1 1 2 ...
		 *  1 : 0 1 1 2 3 ...
		 *        0�� 1�� n-1�� ����(��, n > 0 n=0�� ��� 0 : 1, 1 : 0)  */
		
		/*
		array[n-1][0] = ���� n�϶� 0�� ȣ��Ǵ� Ƚ��, array[n-1][1] = ���� n�϶� 1�� ȣ��Ǵ� Ƚ��
		int[][] array = new int[41][2];
		array[0][0] = 1;
		array[0][1] = 0;
		array[1][0] = 0;
		array[1][1] = 1;
		for(int i = 2; i < 41; i++) {
			array[i][0] = array[i-1][0] + array[i-2][0];
			array[i][1] = array[i-1][1] + array[i-2][1];
		}
		*/
		int[] array = new int[41];
		array[0] = 0;
		array[1] = 1;
		for(int i = 2; i < 41; i++) {
			array[i] = array[i-1] + array[i-2]; 
		}
		
		int time = sc.nextInt();
		for(int i = 0; i < time; i++) {
			int num = sc.nextInt();
			if(num > 0)System.out.printf("%d %d\n", array[num-1], array[num]);
			else System.out.printf("%d %d\n", 1, 0);
		}
		
	}

}
