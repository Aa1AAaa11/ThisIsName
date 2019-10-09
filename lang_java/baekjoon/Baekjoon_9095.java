package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

// �� �����.. ���� 1, 2, 3�� �� ���� �� �ִ� ����� ���� ���ϴ� ����.
/*
 // ����Լ� ������� �ʴ� ��� > for�� Ƚ����ü�� ������ �ʤ����� ������ �� �ʹ� ����. �����ɸ�,,
public class Baekjoon_9095 extends Solution {
	public static int c(int n, int m) {
		int c = 1;
		for(int i = 0; i < m; i++) {
			c *= (n-i);
			c /= (i+1);
		}
		
		return c;
	}
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); //����.. k += (1 - k)/(k + 1); //k�� 0�� ��� 1, 0�ƴ� ��� k
		
		for(int i = 0; i < T; i++) {
			
			int result = 0;
			int n = sc.nextInt();
			
			for(int j = 0; j <= n/3; j++) { //3�� ����.. ��ȣ �־����
				
				for(int k = 0; k <= (n-j*3)/2; k++) { //2�� ����
					int nums = (n - 3*j - 2*k) + j + k; //������ ���� (1�� ���� :(n - 3*j - 2*k) / 2�� ���� : j / 3�� ���� : k )
					
					result += c(nums, (n - 3*j - 2*k))* c(j+k, j); //���� ���� : �ڸ��� ����/ �ϴ� 1�� �� �ڸ� ���� : c(nums, (n - 3*j - 2*k)) / �� ���� 2�� �� �ڸ� ����: 1�� ���� �ڸ� �����ϰ� ���� �� ����./c(k, k)�� �׻� 1�̹Ƿ� ��� �Ҥ�����.. 3��  �׻� ���� �ڸ��� ������ ��.
				}
			}
			System.out.println(result);
		}
		
	}
}*/

public class Baekjoon_9095 extends Solution {

	//return ���� �߰��� ������ ������ ���� > return ���� ���� ���� �Ǿ����
	static int sum(int current, int goal) {  //current : ������� ��, goal : ������� �ϴ� ��, num : 1, 2, 3�� ���� 
		if(goal == current) {
			return 1; //1�� ����������
		} else if(goal > current) {
			// 1, 2, 3 ������ ���� ������ ������ �����ؾ���, ���߿� �� ���ؾ��ϹǷ�
			int temp_answer = 0; // ���ο� ������ ���� �� ���� �ʱ�ȭ�������
			// ���� sum(int current, int goal,int temp_answer)�� �� ��� temp_answer�� ���� ���������� ���� ������ �Ǵµ� ���߿� return�� ��� ���� ���� ������ ������ ��� �������� ���簡 �߻�..> �߰��� ������ ��츸 ���������ϴµ�..
			for(int i = 1; i< 4; i++) { //1, 2, 3�� ���� ������ ���غ�..
				temp_answer += sum(current+i, goal);
			}
			return temp_answer;
		} else return 0; //current�� goal���� Ŀ�� ����̹Ƿ� ���� �ƴ�
	}
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
	    StringBuilder sb = new StringBuilder();
		
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			sb.append(sum(0, n)).append('\n');
		}
		System.out.println(sb.toString());
		
	}
}
