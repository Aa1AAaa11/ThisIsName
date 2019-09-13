package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_6588 extends Solution {

	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		/* // �Ҽ��� �迭 ����
		int max_n = 1000000;
		boolean [] numbers = new boolean[max_n-1]; 
		int [] primes = new int[max_n-1];//numbers���� ó������ �������� ���ϸ鼭 �Ҽ� ã�� �� ���� n �̸��� i ��° �Ҽ� �ѹ��� ã���� �迭 ���� ������µ� �������� ���� �Ҽ� ã�� �� �� �Ȱɷ���. 
		int limit = (int)Math.floor(Math.sqrt(max_n)); 
		int real_num;
		int prime_num = 0;
		for(int i = 0; i < limit; i++) { //�Ҽ� ���� ���°� �ƴ϶� ���⸸ �ϸ� �ǹǷ� limit ������ �ϸ� ��. 
			if(!numbers[i]) {
				real_num = i+2; 
				primes[++prime_num]=real_num; 
                //i < limit�̹Ƿ� real_num�� �׻� n���� ����.
				for(int j = (real_num)*(real_num)-2; j < max_n-1; j+=(real_num)) { 
						numbers[j] = true; //����� �迭���� ����
				}
			}
		}
		
		int n;
		do {
			n = sc.nextInt();
			//primes[i] : i��° �Ҽ�
			for(int i = 0; primes[i] < n; i++) {
				if(!numbers[n-primes[i]-2]) {//n-�Ҽ��� �Ҽ��� ���
					System.out.println(n+" = "+primes[i]+" + "+(n-primes[i]));
					break;
				}
			}
		}while(n>0);*/
		int max_n = 1000000;
		boolean [] numbers = new boolean[max_n-1];  
		//int limit = (int)Math.floor(Math.sqrt(max_n)); 
		int real_num;
		for(int i = 0; i < 1000; i++) { //�Ҽ� ���� ���°� �ƴ϶� ���⸸ �ϸ� �ǹǷ� limit ������ �ϸ� ��. 
			if(!numbers[i]) {
				real_num = i+2; 
                //i < limit�̹Ƿ� real_num�� �׻� n���� ����.
				for(int j = (real_num)*(real_num)-2; j < max_n-1; j+=(real_num)) { 
						numbers[j] = true; //����� �迭���� ����
				}
			}
		}
		
		int n;
		do {
			n = sc.nextInt();
			//primes[i] : i+2�� �Ҽ����� ����
			for(int i = 0; i < n; i++) { //n/2�������ص� �Ǵµ� (���̶�.) ������ �߰��� �����Ƿ� .
				//[n] �� n+2�� �Ҽ����� ���� 
				//�� for���� 0���� �����̹Ƿ� ���⼭�� i�� ���� ���ں��� 2 ����.
				// i �� �������� i+2
				//�������� n-(i+2) �� �Ҽ����� �Ǻ� -> [n-(i+2)-2]
				if(!numbers[n-i-4] && !numbers[i]) {//n-�Ҽ��� �Ҽ��� ���
					System.out.println(n+" = "+(i+2)+" + "+(n-i-2)); //�������� ���
					break;
				}
			}
		}while(n>0);
		
	}

}
