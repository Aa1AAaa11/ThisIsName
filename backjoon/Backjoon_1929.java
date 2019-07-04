package backjoon;

import java.util.Scanner;

public class Backjoon_1929 extends Solution {

	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		
		//N �̸��� �Ҽ� �ƴ� ���ڸ� ���� (-> N �̸������� ������� �� �����.)
		int [] numbers = new int[N-1]; // 0, 1��� ����(���� �ΰ� ����)
		int limit = (int)Math.floor(Math.sqrt(N)); // ��Ʈ n ������ ������� ���ܽ�Ű�� ��Ʈ n �ʰ��� ��쵵 �Բ� �ڵ����� ������
		int real_num;//���� ���� index���� 2 ũ��(index=0,����=2�� ġȯ)
		for(int i = 0; i < N-1; i++) { 
			if(numbers[i] == 0) {//�ʱⰪ�� ���(���ܵ��� ���� ������ ���) �ش� ������ ����� �迭���� �����.
				real_num = i+2; //���� ����. (�ʱⰪ�� ���� �� ��¿� ����)
				if(real_num >= M)System.out.println(real_num); //M ���� Ŭ ��쿡�� ���
				if(real_num <= limit) { //��Ʈ n ������ ������� ���ܽ�Ű�� ��Ʈ n �ʰ��� ��쵵 �Բ� �ڵ����� �������Ƿ� ��Ʈ n ������ ��츸 ����� �����.
					// j �ʱⰪ�� i*i �ƴ�. i+2�� ������ �ƴ�. i+2(���� ����)�� ������ �ʿ��ѵ� �� ���� �������� ������ -2�� �ؾ���.(�迭 0, 1�� �����Ͽ����ϱ�)
					for(int j = (real_num)*(real_num)-2; j < N-1; j+=(real_num)) { //�׳� i*i�� 0�� ��� ���� ������ ����.. ����...
						numbers[j] = -1; //����� �迭���� ����
					}
				}
			}
		}
		
	}

}
