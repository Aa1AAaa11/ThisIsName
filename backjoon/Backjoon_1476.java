package backjoon;

import java.util.Scanner;

public class Backjoon_1476 extends Solution {
	
	// �������� �����ϴ� ���� ���ϱ� // (x-1)%15+1=E , (x-1)%28+1=S, (x-1)%19+1 = M�� ���� ���ϱ�
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		char E = (char)sc.nextShort(); //char �ϸ�. 
		char S = (char)sc.nextShort();
		char M = (char)sc.nextShort();
		// (x-1)%15+1=E , (x-1)%28+1=S, (x-1)%19+1 = M�� ���� ���ϱ�
		//���� 15, 28, 19�� �Ѿ�� 1�� �ʱ�ȭ �ǰ� �����ϴ� �ٽ� 15, 28, 19�� �� �����ϸ� �ٽ� 1�� �ʱ�ȭ��. ������ ����� ������ �׷���..
		//-> ������ 1<= <=15, 28, 19 �̹Ƿ� %15,28, 19 +1�� �ؾ� ������ ������. �ٸ� �ִ밪�� ���� ���ڰ� 15*k, 28*k, 19*k �̹Ƿ� -1 �� �� �����������ϰ� �ű⿡ +1���ѰͰ� ����
		
		for(int i = S-1; i < Integer.MAX_VALUE; i+= 28) { //0*28+E 1*28+E...
			if(i%15 == E-1 && i%19 == M-1) { 
				System.out.println(i+1);//x-1�� i�̹Ƿ� ������ x�� i+1
				break;
			}
		}
	}

}
