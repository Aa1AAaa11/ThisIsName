package lang_java.codeground;
import java.util.Scanner;

public class Codeground_56 extends Solution{
	/* ���
	 * 4�� 7�� �̷���� �� �� k ��° ���� ���ϱ�
	 * 
		1. 4�� 7 > 2���� ���ڷθ� �̷����
			4->7->44->47->74->77->444->......
			4 7/ 44 47 74 77/ 444 ...
		2. 4�� 7 �θ� �̷���� �����Ƿ� 1�� 0���� �̷���� 2������ ����
			��, n��° 2���� = n��° �ش����� �� �� ���� x
		3. k ��° ���ڰ� ���ڸ� �ش����� ��������, �� �߿����� ���°���� �˾ƾ���.
			2+4+8+... -> 2���� 2^n������ �� ->2^(n+1)-2
			2���� 2^(n-1)������ �� < k-1 < 2���� 2^(n)������ ��
				n�� �ڸ���, ������ k-1�� ���� ���� ���ڰ� 0(4) �����̱� ����, �ڸ����� 2^n ������ ��� n-1�ڸ���..
				2^n-2 < k-1 < 2^(n+1)-2
				2^n < k+1 < 2^(n+1) //// --- (1)
	*/
	static String Answer = "";//�ʱⰪ.. �� ����..
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			
			int k = sc.nextInt();//k��° ����
			k += 1; ///-----(1) �� ���� k+1
			
			int originK = k;
			int j, n = 0;//n�� ���ڸ�����, j�� n�ڸ����� �� ���°����
			do {
				n++;
				k /= 2;
			}while(k > 1);
			
			j = originK - (int)Math.pow(2, n);
			
			for(int i=0; i < n; i++){
				Answer = (3 * (j % 2) + 4) + Answer;//j%2==1�̸� 7��.. j%2==0�̸� 4��
				j /= 2;
			}
			
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
			Answer = "";
		}
	}
}
