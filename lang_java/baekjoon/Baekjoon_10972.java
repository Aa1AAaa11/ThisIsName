package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_10972 extends Solution {

	@Override
	public void solution() {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int change_index = -1, change = 0;
		int tmp;
		
		int[] array = new int[N];
		for(int i = 0; i < N; i++) {
			array[i] = sc.nextInt();
		}
		
		for(int i = N-1; i > 0; i--) {
			if(array[i-1] < array[i]) { //i-1 ��° �ڸ����� �ٲ�����
				change_index = i-1;
				change = array[change_index]; // ---1
				break;
			}
		}
		if(change_index == -1) { // ������������ ���ĵ� ���
			System.out.println(-1);
		} else {
			
			//���⼭ ���� for �ȵ����� ---1���� �ٲٰ� ����� change_index+1 ���� ���ĸ� �ص� �ɵ�.
			for(int i = N-1; i >= change_index+1; i-- ) { //change_index�� ��� �ٲ����� �ϴ��� Ȯ��. change_index ������ �ִ� ���ڿ� �ٲ�, change_index ������ ���ڵ� �ٲ�� �� ���� �ڸ��� ���ڵ� �ٲ�� �ͤ��̹Ƿ� ���� �� �ٷ� ������ �ƴ�.
				// N-1�� �������̰� -1 �ɼ��� �������� ���°�.. 
				
				//i�� change_index+1���� N-1 ���� +1 �ذ��鼭 
				// if(array[i] < change) �̰ɷ� ���ؼ� i-1�� �ٲٴ� ���������� ������ N-1>=i>=change_index+1 �̹Ƿ�
				// N-2 = change_index�� ���  N-1>=i>=N-1�� �Ǿ array[N-1] > array[N-2] ����������
				// ������ ��� �������� ���ĵǾ��� ��� (N-2 = change_index �� ��� ) �ٲ�� �������� i�̰� (�� ū ������ ���� ���� ���� ���)
				// (N-2 != change_index �� ��� ) �ٲ�� �������� i-1�� (�� ū ������ �ϳ� ���� ���ڰ� �ִ� ���)
				
				
				//array[change_index] ������ �������� �����̹Ƿ� index�� Ŭ���� ���ڰ� ũ��. 
				//-> array[i]�� change ���� ũ�� array[i]�� change ���� ū ���� ���� �� (array[N-1] ���� ���ؾ� ��)
				if(array[i] > change) { 
					array[change_index] = array[i]; // array[i] = change; �����ϸ� �ȵ�
					array[i] = change;
					// �ٲ� �� change_index ���� ���� �������� ������ �ؾ���
					
					int sort_limit = ((N-1)-(change_index+1))/2; // ���� �ٲپ���ϴ� Ƚ��
					// ������ �������� �����̿����Ƿ� ���� ���� �ٲٸ� �ȴ�.
					
					//���ڸ� �ٲ۴�. 
					for(int j = 0; j <= sort_limit; j++) {//for(int j = change_index+1; j <= sort_limit; j++) {
						tmp = array[change_index+1+j];
						array[change_index+1+j] = array[N-1-j];
						array[N-1-j] = tmp;
					}
					
					break;
				}
			}
			for(int i = 0;i<N;i++) {
				System.out.print(array[i]+" ");
			}
		}
		
	}

}
