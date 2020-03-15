package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1062 extends Solution {
	
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
				if(tmp.charAt(j)!= 'a' && tmp.charAt(j)!= 'c' && tmp.charAt(j)!= 't' && tmp.charAt(j)!= 'n' && tmp.charAt(j)!= 'i') 
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
		if(((number & (1 << 0)) != 0) || ((number & (1 << 2)) != 0) || ((number & (1 << 8)) != 0) || ((number & (1 << 13)) != 0) || ((number & (1 << 19)) != 0)) return false;
		
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
		for(int i = 0; i < N; i++) {
			if((words[i] & number) == words[i]) {
				 answer++; // ����ġ�� ���� ���ڰ� ���Ե� ���
			}
		}
		return answer;
	}
}