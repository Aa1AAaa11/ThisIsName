package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_10974 extends Solution {
	
	static int[] change(int[] array, int change_index) {
		
		int N = array.length;
		int tmp;
		for(int k = 0; k < N-1 - change_index ; k++ ) {//k�� ���� Ƚ�� (change_index�� ���� N-1 - change_index �� �ݺ� �ʿ�)
			if(change_index < N-2 )array = change(array, change_index+1); //change_index�� N-2 ���� �����ϹǷ�.
			int change = array[change_index];
			
			//change_index�� ��� �ٲ����� �ϴ��� Ȯ��
			for(int i = N-1; i >= change_index+1; i-- ) {
				if(array[i] > change) { 
					array[change_index] = array[i]; 
					array[i] = change;
					
					int sort_limit = ((N-1)-(change_index+1))/2; 
					
					//���ڸ� �ٲ۴�. 
					for(int j = 0; j <= sort_limit; j++) {
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
			System.out.println();
		}
		if(change_index < N-2)array = change(array, change_index+1); // ���������� �ٲ�� �ڸ����� �ö󰡱� ���� ������ �ѹ� �� ������Ѵ�.
		return array;
	}

	@Override
	public void solution() {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		
		int[] array = new int[N];
		for(int i = 0; i < N; i++) {
			array[i] = i+1;
			System.out.print((i+1)+" ");
		}
		System.out.println();
		
		// ��� �ڸ����� ���� ���ư��鼭 change�� ����.
		change(array, 0);
		
	}

}
