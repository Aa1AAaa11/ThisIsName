package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_10973 extends Solution {

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
			if(array[i-1] > array[i]) { //i-1 ��° �ڸ����� �ٲ�����
				change_index = i-1;
				change = array[change_index]; 
				break;
			}
		}
		if(change_index == -1) { // ������������ ���ĵ� ���
			System.out.println(-1);
		} else {
			
			for(int i = N-1; i >= change_index+1; i-- ) {
				if(array[i] < change) { 
					array[change_index] = array[i]; 
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
