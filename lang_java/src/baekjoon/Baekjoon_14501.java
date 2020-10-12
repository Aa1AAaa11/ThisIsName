package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_14501 extends Solution {
	static int[][] array;
	static int N;
    
	//profit�� today�Ͽ� ����� �ϰų�/ ���� �ʰų� �Ͽ��� ���� ������ ����ϴ� �Լ� > ���� next_day ==N �̰ų� >N�� ��쵵 today�� ���ԵǴ��� �ȵǴ��� Ȯ���� �ʿ䰡 ����.
	static int profit(int today) { 
        //�⺻������ today �� ����� ���� ������� ���� ��� �� ������ �ִ밡 �Ǵ� ��츦 ��
		
		if(today >= N) return 0; // ��� �Ұ�! ( ==�� �ƴ� >=�� ���ϴ� ������ today�� ���� ��� ������ ���� �����µ� �̰� �׻� n�� ���缭 �����°� �ƴϴϱ�.)
		
		int next_day = today + array[today][0]; //next_day : today�� ����Ͽ��� ��� ��� ������ ���� ��
		
		// next_day == N �� ���� today�� ����� ���������� next_day���� ���Ұ�.. (today�� N-1 ���� �����ϹǷ�.(���� N �迭�� �ε����ϱ�.))
		if(next_day == N) {
			return Math.max(array[today][1], //today�� ��� ���������� �� ������ ��� �Ұ�
					profit(today+1)); //today �� ��� ���ϴ� ��쵵 ����
		}else if(next_day > N) { // ��� ������ �������� N�� �ʰ��� ���� today �� ��� �Ұ����ϴ�. ��� ���ı��� ���ͼ� ����Ҽ��� �����Ƿ�
			return profit(today+1); //today �� ��� ���ϴ� ��츸 ���
        } else { //today, next_day ��� ��� ������ ���
        	return Math.max(
    				array[today][1] + profit(next_day), //today �� ����� ���(today ������� ���� �̵� + next_day ������� ���� �̵� ) //array[index][1]  ���� ������ ������ �� ������ return�� ���̶�� ��� 0�� �����°� �ƴ�..
    				profit(today+1)); //today �� ������� ���� ���.. ������ next_day�� today+1 �̴�.
        }
	}
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			array = new int[N][2];
			for(int i = 0; i<N;i++) {
				st = new StringTokenizer(br.readLine());
				array[i][0] = Integer.parseInt(st.nextToken()); //����� ��ĥ �ɸ�����
				array[i][1] = Integer.parseInt(st.nextToken()); //��� �� ��� ����
			}
			System.out.println(profit(0));
		}
	}
}
