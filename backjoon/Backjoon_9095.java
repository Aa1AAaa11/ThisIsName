package backjoon;

import java.util.Scanner;

// �� �����.. ���� 1, 2, 3�� �� ���� �� �ִ� ����� ���� ���ϴ� ����.

public class Backjoon_9095 extends Solution {
	public static int c(int n, int m) {
		int c = 1;
		for(int i = 0; i < m; i++) {
			c *= (n-i);
			c /= (i+1);
		}
		
		return c;
	}
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); //����.. k += (1 - k)/(k + 1); //k�� 0�� ��� 1, 0�ƴ� ��� k
		
		for(int i = 0; i < T; i++) {
			
			int result = 0;
			int n = sc.nextInt();
			
			for(int j = 0; j <= n/3; j++) { //3�� ����.. ��ȣ �־����
				
				for(int k = 0; k <= (n-j*3)/2; k++) { //2�� ����
					int nums = (n - 3*j - 2*k) + j + k; //������ ���� (1�� ���� :(n - 3*j - 2*k) / 2�� ���� : j / 3�� ���� : k )
					
					result += c(nums, (n - 3*j - 2*k))* c(j+k, j); //���� ���� : �ڸ��� ����/ �ϴ� 1�� �� �ڸ� ���� : c(nums, (n - 3*j - 2*k)) / �� ���� 2�� �� �ڸ� ����: 1�� ���� �ڸ� �����ϰ� ���� �� ����./c(k, k)�� �׻� 1�̹Ƿ� ��� �Ҥ�����.. 3��  �׻� ���� �ڸ��� ������ ��.
				}
			}
			System.out.println(result);
		}
		
	}
}
