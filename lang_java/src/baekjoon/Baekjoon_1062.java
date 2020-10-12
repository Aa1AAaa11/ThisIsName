package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1062 extends Solution {
	
	// �⺻���� ����ġ�� ���ڵ�
	static int basicWord = (1 << 'a' - 'a') | (1 << 'c' - 'a') | (1 << 'i' - 'a') | (1 << 'n' - 'a') | (1 << 't' - 'a'); // �⺻
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] words = new int[N];
		for(int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < tmp.length(); j++) {
				words[i] |= 1 << (tmp.charAt(j) - 'a'); 
			}
		}
		int answer = 0;
		int max = 1 << 26; // 2^26 
		for(int i = 0; i < max; i++) {
			if(check(i, M-5)) { // M-5 �� �ܾ� ����ģ ��� (antic �� �׻� ����) // �⺻���� ����ġ�� ���ڴ� �ƿ� �����ϰ� word�� �����Ͽ����Ƿ� M-5 �� ���ο� ���ؼ��� �Ǻ��ϸ�ȴ�.
				answer = Math.max(answer, readWord(words, i, N));
			}
		}
		System.out.println(answer);
	} 
	static boolean check(int number, int M) {
		int count = 0;
		
		// basicWord(acinta) �� ������ 5�� �߿��� �̾ƾ� �ϹǷ�.. 
		if((number & basicWord) != 0) return false;
		
		for(int i = 0; i < 26; i++) {
			if((number & (1<<i))  != 0) { 
				count++; // i ��° ���ĺ��� ����ģ ���
			}
			if(count > M) return false; // �ȵǴ� ���. ����ĥ �� �ִ� ���ڼ��� ��ġ�� ���
		}
		return count == M;
	}
	static int readWord(int[] words, int number, int N) {
		int answer = 0;
		int mask = number | basicWord; // 
		for(int i = 0; i < N; i++) {
			if((words[i] & mask) == words[i]) {
				 answer++; // ����ġ�� ���� ���ڰ� ���Ե� ���
			}
		}
		return answer;
	}
}