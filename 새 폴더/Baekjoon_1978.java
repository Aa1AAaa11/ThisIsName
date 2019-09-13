package backjoon;

import java.util.Scanner;

public class Backjoon_1978 extends Solution {
	
	//N�� �߿��� �Ҽ��� ���� ���
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int x;
		int result = 0;
		for(int i = 0; i < N; i++) {
			x  = sc.nextInt();
			if(x == 1)continue;//1�� �Ҽ� �ƴ�.
			result++;
			for(int j = 2; j*j <= x; j++) { //x�� ����� ������ ��� ���ڿ� ���� ��(��Ʈn ���ϴ� ��Ʈ n �̻��� ����� ¦�������ֱ� ������ ��Ʈ n ������ �ص� ���.)
				if(x%j == 0) {
					result--;//�Ҽ��� �ƴ� ���
					break;//�̰� ������ ��� ���ư��� ������ ���ü���..����..
				}
			}
			System.out.println(result);
		}
		System.out.println(result);
	}

}
