package baekjoon;

import java.util.Scanner;

import common.Solution;

public class Baekjoon_9498 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		char[] grades = {'F', 'D', 'C', 'B', 'A'};
		
		int score = sc.nextInt()/10; //
		int calculate = score/5;
		
		// if�� �Ⱦ��� Ǯ���.
		// 1. ((score/10)/5)* -> score/10�� 5 ���� ���� ��� 0�� �ȴ�. ****7) ����
		// 2. ((score/10)/5)/2 -> (score/10)/5�� 2���� ���� ��� 0�̵�. 2�� ���� 1�� �ȴ�.
		//		score/10 - ( 5 + 3*(((score/10)/5)/2)) -> (score/10)/5 �� 2���� ���� ��� 5�� �A��.****2), 3), 4), 5), 6) ����
		//												  (score/10)/5 �� 2�ΰ�� 8�� �A��. ****1) ����
		//int index = ((score/10)/5)*(score/10 - ( 5 + 3*(((score/10)/5)/2)));
		int index = -4*calculate+score*calculate-calculate*calculate*calculate; //���� ���ٴ� ����ϴ� ��� (������ ������ ����, ��ȣ ����)
		
		//socre/10 �� ���� ���Ǿ���ϴ� index ����
		//10 -> 4 ****1)
		// 9  -> 4 ****2)
		// 8 -> 3 ****3)
		// 7 -> 2 ****4)
		// 6 -> 1 ****5)
		// 5 -> 0 ****6)
		// 5 �̸� -> 0 ****7)
		
		System.out.print(grades[index]);
	}
}
