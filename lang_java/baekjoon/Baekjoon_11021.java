package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_11021 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int copy_M = M;
		
		for(int i = 0; i < 3; i++) {
			System.out.println(N*(M%10)); //M�� ���ڸ��� �и��ؼ� N�� ����
			M /= 10;
		}
		System.out.println(N*copy_M);
	}
}