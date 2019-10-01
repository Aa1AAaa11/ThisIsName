package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_6603 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		for(int K = sc.nextInt(); K > 6; K = sc.nextInt()) { //for(int K = sc.nextInt(); K > 6;)�����ָ� K �ѹ��� �Է¹ް� �ٲ�������.

			int[] S = new int[K]; //K ���� ���� ����
			
			int[] index = {0, 1, 2, 3, 4, 5};// �迭 S���� ���õ� 6�� ��ȣ�� �ε��� �迭
			
			for(int i = 0; i < K; i++) {
				S[i] = sc.nextInt(); //�迭 S�� �Է¹���
			}
			
			
			
			int increase_index = 5; // �����Ǵ� �ε���
			while(K-6 != index[0]) {

				// ���� ����Ѵ�. ���� �� ������ ����ؾ��ϹǷ� increase_index ���� ���� ��¸����ؾ���
				for(int i = 0; i < 6; i++) {
					System.out.print(S[index[i]]+" ");
				}
				System.out.println();
				
				//�ε����� ���̻� ������ �� ���� ��� ���� �ε����� ������Ŵ (���������� ���� ������ �� ���� �̵�)
				while(index[increase_index] >= K-(6-increase_index)) { // index[increase_index]�� �ִ밪��  K-(6-increase_index). �� �̻��� ���� �Ұ�...(�ߺ� �������� �����Ƿ�..)
					increase_index--; // ��ĭ ���� �� index ����
				}

				index[increase_index]++;
				for(int j = increase_index+1; j <= 5; j++) {
					index[j] = index[j-1]+1; //����� index ������ index�� ���� �ʿ�

					/*
					 * �Ʒ��� ���� �����
					if(index[5] < K-1)
						index[5]++;
					else {
						if(index[4]< K-2)
							{
								index[3]++;
							 		// .....
								index[4] = index[3]+1;
								index[5] = index[4]+1;
							}
						else {
							index[4]++;
							index[5] = index[4]+1;
						}
						}*/
				}
				
				increase_index = 5; // ������ �ε����� �ٽ� ������ �ε����� ����
				///
				// 1 2 3 4 5 6 7 8 9 10
				// 6, last: 7 8 9 10, 7 last: 8 9 10, 8 last: 9 10,9 last: 10 
				// 7, last: 8 9 10, 9 10, 10 
				/// �̷������� index �迭 ���� �ص� ����,,
			}
			for(int i = 0; i < 6; i++) {
				System.out.print(S[index[i]]+" ");
			}
			System.out.println("\n");
		}
	}
}
