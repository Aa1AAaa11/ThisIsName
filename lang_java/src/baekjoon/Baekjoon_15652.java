package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_15652 extends Solution {
	
    static StringBuilder sb;
    static int N; // ���� ����
    static int M; //  ���� ����
    
	static void dfs(int length, String answer, int number) { //  length : ������� ������ �迭 ����(�߰��Ǿ���ϴ� �迭�� �ε���), answer : ����, number : �߰��� ����
		answer += number+" "; // ���⼭ �߰����ش�. for�� ������ �߰��� ��� ���� for������ ���� for���� ����� ������ ��ġ�� ���� 
		//answer �� �������� �����ؾ���. ��ΰ� ������ ������ �� \n�� �ٴµ� ������ ��� \n �ٰ� ���� �湮�ϴ� ������ �ϳ����� �߰��Ǳ� ����(��ΰ� ��µ��� ����..)
		//sb.append(number).append(" "); // ����
		
		if(length == M) { // ������ ����. �� �̻� �̾����� ������ ����� �ʿ�� ����
			//for(int i = 0; i< length; i++)sb.append(array[i]).append(" "); // �� for���� ���� �� ����,, �迭�� ������ ����ϴ� ��� ����ϴ� answer�� �ٷ� �ٿ���
			sb.append(answer).append("\n");
			return;
		} else { // ���� �湮 ��带 �� ����ؾ���
			for(int i = number-1; i < N; i++) { // number+1�� �ƴ� dfs���� i+1�� �ǹǷ�  (���� ���� ������庸�� ū ���� �ƴ϶� ���ų� ũ�Ƿ� number-1���� ���ָ� �ȴ� (dfs ȣ���ϸ鼭 i+1�� �ǹǷ� number ������ Ž����))
				//answer += (i+1)+" "; // ���⼭ answer�� �߰����� ��� ���� for���� ���� for������ �߰��� ��ΰ� �پ������ �ȵ� (1 2,  1 3 �̷��� �Ǵ°� �ƴ϶� 1 2 3 �̷��� �� >> ���� �߰��Ǵ� ��δ� ���� ����Լ����� �߰�.)
				dfs(length+1, answer, (i+1)); // �� dfs�� ��������� ȣ���ϸ鼭 ������ �̵���
			}
		}
	}
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// �ʹ� ������ �ϳ��� �湮�ؾ��Ѵ�. dfs �ʹݿ� answer�� �ٿ������� ������ ���� ���� �� ���������
		for(int i = 1; i < N+1; i++)dfs(1, "", i);
		
		System.out.print(sb.toString());
	}
}