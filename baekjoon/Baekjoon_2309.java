package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon_2309 extends Solution {
	
	//9���� �� 7�� ���� 100 -> 9���� 2 �� �����ϸ� ���� 100
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		short [] height = new short[9]; //���� �����ϱ�.
		short sum = 0;
		for(int i = 0; i < 9; i++) {
			height[i] = sc.nextShort();
			sum+= height[i];
		}
		Arrays.sort(height);
		//9���� 2����  ���ؼ� ���� sum-100�� ������ �ȴ�.
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(height[i] + height[j] == sum-100) {
					for(int k = 0; k < 9; k++) {
						if(k!=i && k!=j) {
							System.out.println(height[k]);
						}
					}
					i = 9; //�ٱ��� for���� ������ ����
					break;
				}
			}
		}
		
	}

}
