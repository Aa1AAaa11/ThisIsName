package lang_java.baekjoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Baekjoon_10819 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		// ���� ū ��, ���� ���� �����ϰ� �� ���ķ� ���̰� �ִ밡 �� �� �ִ� ���� ���δ�.
		// ū��, ���� �� ¡�˴ٸ��� �־�� ���̰� �ִ�� ������
		int N = sc.nextInt();
		ArrayList<Integer> array = new ArrayList<Integer>();		

		int answer = 0;
		
		for(int i = 0; i<N; i++) {
			array.add(sc.nextInt());
		}
		
		Collections.sort(array); //����
		
		int left_index = 0;
		int right_index = N-1;

		//���� ������ - ���� ū ���� ���� 
		answer += array.get(right_index) - array.get(left_index); //���� �������� ���� ū ���� ����
		
		int last = 0; // ������ ���̰� Ȧ���� ��� ���� �߾ӿ� �ִ� ���ڸ� ������ ������, ������ ������ ��� �ʿ� (��, �� �߿��� ���̰� ū ���� ����) 
		
		left_index = 1;
		// ū��, �������� �����ϰ� �� ���ķ� ���̰� �ִ밡 �� �� �ֵ��� ���� ����
		// ���� ū �� ������ �ٴ¼��� (���� ū �� - �ι�°�� ���� �� - ����°�� ū ��  - �׹�°�� ���� �� - �ټ���°�� ū ��... ) �̷�������
		// �ι�°�� ���� �� ���� �ι�°�� ū ���� �ƴ� ������ �� ��°�� ū ���� ���� ���� �� ���ʿ� �پ����.
		for(int i = 0; i<N/2-1; i++) {
			answer += array.get(right_index) - array.get(left_index);
			left_index += i%2 * 2; //i�� Ȧ���� ��� left_index�� 2 ����
			right_index -= (i+1)%2 * 2; //i�� ¦���� ��� right_index 2����
		}
		
		if(N%2 != 0) //N�� Ȧ���� ��� ���� ���� ������ �ִ� ���ڿ� ���� �߾ӿ� �ִ� ������ ���� ����
			last = Math.abs(array.get(left_index *((N/2-1 )%2) + right_index*((N/2 )%2)) - array.get(N/2)); // ������ ���� ������ �� ���ڿ� �߾��� ������ ���� ���Ѵ�.
			// (N/2-1)�� Ȧ���� ��� left_index(���� ����)�� ���� ������ ���� (N/2-1)�� ¦���� ��� right_index(ū ����)�� ���� ������ ��.
			// (N/2-1)�� Ȧ���� ��� ���������� right_index�� ���ϹǷ� left_index�� -2�� �ٽ����� �ʿ�� ����. 
		
		left_index = 0;
		right_index = N-2;
		// ���� ���� �� ������ �ٴ� ���� (...-�ι�°�� ū ��-���� ���� ��) �̷���
		for(int i =0; i<N/2-1; i++) {
			answer += array.get(right_index) - array.get(left_index);
			left_index += (i +1)%2 * 2; //i�� ¦���� ��� left_index ����
			right_index -= i%2 * 2; //i�� Ȧ���� ��� right_index ����
		}
		
		if(N%2 != 0) { //N�� Ȧ���� ��� ���� ���� ������ �ִ� ���ڿ� ���� �߾ӿ� �ִ� ������ ���� ����
			//���� ���� �߾� ���ڰ� ������ �ٴ� ���� �������ٴ� ��� �� ���̰� �� ū ��츦 ������
			answer += last > Math.abs(array.get(right_index *((N/2-1)%2) + left_index*((N/2)%2) ) - array.get(N/2)) //
					? last : Math.abs(array.get(right_index *((N/2-1)%2) + left_index*((N/2)%2)) - array.get(N/2));
			}
		System.out.println(answer);
	
	}
}
