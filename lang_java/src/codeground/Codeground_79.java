package codeground;
import java.util.Scanner;

import common.Solution;

public class Codeground_79 extends Solution {
	/*  ��� : Pivot�� �����ϴ� ���� � �����ϴ��� ���ϼ���.
	 * 
		a ���� ���� : ��� ������, a ���� ������ : ��� ū ��.. > a�� Pivot ���� ����
		-> a ���� ����: �ִ�, ������ : �ּ��� �迭 ���� �� ��
	*/
	static int Answer;
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			Answer = 0;
			int N = sc.nextInt();
			
			int[] array = new int[N];
			int[] max = new int[N];//a ���� ���ʿ��� �ִ� ���� : �ִ���ڰ� a���� �۾ƾ���. 
			int[] min = new int[N];//a ���� �����ʿ��� �ּҼ��� : �ּҼ��ڰ� a���� Ŀ����.
			
			array[0] = sc.nextInt();
			max[0] = -1;
			
			for(int i = 1; i < N; i++) {
				array[i] = sc.nextInt();
				if(max[i-1] < array[i-1]) max[i] = array[i-1];
				else max[i] = max[i-1];
			}
			
			min[N-1] = Integer.MAX_VALUE;
			
			for(int i = N-1; i > 0; i--) {
				if(min[i] > array[i]) min[i-1] = array[i];
				else min[i-1] = min[i];
			}
			
			for(int i = 0; i < N; i++) {
				if((min[i]>array[i])&&(max[i]<array[i])) {
					Answer++;
				}
			}
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}
