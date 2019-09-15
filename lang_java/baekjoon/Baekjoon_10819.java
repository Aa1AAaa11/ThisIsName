package lang_java.baekjoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Baekjoon_10819 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		ArrayList<Integer> array = new ArrayList<Integer>();		

		int answer = 0;
		
		for(int i = 0; i<N; i++) {
			array.add(sc.nextInt());
		}
		
		Collections.sort(array);
		
		int left = N/2 - N%2, right = N/2+1; //N�� Ȧ���� ��� ���� �߾��� �ƴ� �ϳ� ������ ����, ���� �߾��� left, right �� ���̰� ū ���� ����ȴ�.
		int left_index = 0;
		int right_index = N-1;
		for(int i =0; i<N/2; i++) {
			answer += array.get(right_index) - array.get(left_index);
			
		}
		
		
	}
}
