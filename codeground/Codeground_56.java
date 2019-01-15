package codeground;
import java.util.Scanner;

public class Codeground_56 extends Solution{
	/* ���
	 * 4�� 7�� �̷���� �� �� k ��° ���� ���ϱ�
	 * 
		1. 4�� 7 > 2���� ���ڷθ� �̷����
			4->7->44->47->74->77->444->......
		2. 4�� 7 �θ� �̷���� �����Ƿ� 1�� 0���� �̷���� 2������ ����
			��, n��° 2���� = n��° �ش����� �� �� ���� x
		3. k ��° ���ڰ� ���ڸ� �ش����� ��������, �� �߿����� ���°���� �˾ƾ���.
			k=2^j+t �϶�, j �ڸ� �ش����μ�, �� �߿��� n ��° �ڸ�
	*/
	static String Answer = "";
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			
			int k = sc.nextInt();//k��° ����
			int j = k%2;
			int n = 0;
			int p = 2;
			while((k/=2)>=1) {
				n++;
				if(k!=1)j += p*(k%2);
				p*=2;
			}
			if(j==0)n--;
			p=p/2;
			j= (p-1)-p/2;
			System.out.println(n+"  "+j);
			for(int i=0;i<n||(j>0);i++) {
				Answer=(3*(j%2)+4)+Answer;
				j=j/2;
			}
			
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}
