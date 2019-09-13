package backjoon;

import java.util.Scanner;

public class Backjoon_9613 extends Solution {
	
	//������ ��� ���� GCD�� ��
	
	static int gcd(int a, int b) {
		int tmp;
		if(a<b) {
			tmp = b;
			b = a;
			a = tmp;
		}
		while(b!=0) {
			tmp = b;
			b = a % b;
			a = tmp;
		}
		return a;
	}
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int i = 0; i<t; i++) {
			int n = sc.nextInt();
			long result = 0; //int �ƴԿ� ����.. ū ���� ���� ��� int ������ ��ġ�� ���� ����.
			int [] array = new int [n];
			for(int j = 0; j < n;j++) {
				array[j] = sc.nextInt();
			}
			for(int k = 0; k < n; k++) {
				for(int u = k+1; u<n;u++) {
					result+= gcd(array[k], array[u]);
				}
			}

			System.out.println(result);
		}
		
	}

}
