package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_1748 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int length = (int)Math.log10(N) + 1; //N�� ���ڸ� ��������
		int answer = (N-(int)Math.pow(10, length-1)+1)*length;
		
		// now : i �ڸ� ���ڿ��� ������ ������ ����
		for(int i = 1, now = 9; i < length; i++, now *= 10) {
			answer += (now*i); //i �ڸ��̹Ƿ� *i�� �ؾ��Ѵ٤�.
		}
		
		System.out.println(answer);
	}
}