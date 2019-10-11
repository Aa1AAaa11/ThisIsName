package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_6603 extends Solution {
	
	static int[] S;
	static int LOTTO_LEN = 6;
	static void lotto(int[] numbers, int index, int length, int limit) { //numbers : ������� ������ �ζ� ��ȣ �迭, index: �߰��� ������ index, length : ������� ������ �ζ��� ����
		//if(length == LOTTO_LEN) { �� �ϸ� index �ȳִ°� ��� ���ư��� ����..
		// length - (index+1) + length <= LOTTO_LEN // ��� ���θ� �����°���. �̰Ŵ� ������ �� �� �ʿ䰡 �ִ�������
		// length== LOTTO_LEN && index >= limit -> �̸� else���� length!= LOTTO_LEN || index < limit  �̹Ƿ� length > LOTTO_LEN�� ��쵵 ����..(&&�� ��������..) length < index�ε� index�� �׻� 6������ �ִ°� �ƴ϶� �� ����� �ְŴ�. > else if(length < LOTTO_LEN && index < limit)
		// length== LOTTO_LEN && index <  limit -> index < limit �� ���� ��¾��ص���.. length== LOTTO_LEN�� ��츸 ���
		// length== LOTTO_LEN || index >= limit  -> ���ϸ� OR�̶� index >= limit�� �����ص� �����. ���..
		if( length== LOTTO_LEN ) { //length : ������� ������ �ζ� ����, index : ���� index,, ������ �ִ� limit - (index+1) ���� �ζǹ�ȣ ���� ����..   limit - (index+1) + length >= 6
			for(int i = 0; i < 6; i++) {
				System.out.print(numbers[i]+" ");
			}
			System.out.println();
			return; //
		} else if(index < limit) {
			numbers[length] = S[index];
			// index : ���� ���� index, 
			// index ���ķ� K-1 ���� �ְų� ���ų��̱� ������ �ִ� K-1 - inex�� ����, ������� ���� ���̴� length..
			lotto(numbers, index+1, length+1, limit); //index �� �����ϴ� ��� .. �̰� �����ϸ� �������� ��µ�. 
			lotto(numbers, index+1, length, limit); //index �� �������� �ʴ� ���
		}
	}
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		for(int K = sc.nextInt(); K > 6; K = sc.nextInt()) { //for(int K = sc.nextInt(); K > 6;)�����ָ� K �ѹ��� �Է¹ް� �ٲ�������.

			S = new int[K];
			for(int i = 0; i < K; i++) {
				S[i] = sc.nextInt(); //�迭 S�� �Է¹���
			}
			lotto(new int[6], 0, 0, K);
			System.out.println();
			/*
			 * // ����Լ� ������� �ʴ� ���
			 * 
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
				//	 * �Ʒ��� ���� �����
				//	if(index[5] < K-1)
				//		index[5]++;
				//	else {
				//		if(index[4]< K-2)
				//			{
				//				index[3]++;
				//			 		// .....
				//				index[4] = index[3]+1;
				//				index[5] = index[4]+1;
				//			}
				//		else {
				//			index[4]++;
				//			index[5] = index[4]+1;
				//		}
				//		}

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
			System.out.println("\n");*/
		}
	}
}
