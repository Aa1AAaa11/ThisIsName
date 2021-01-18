package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_14238 extends Solution {
	
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		int length = S.length();
		int[] num = new int[3]; //A,B,C�� ����
		for(int i = 0; i < length; i++) {
			num[S.charAt(i)-'A']++;
		}
		char[] answer = dp(num, length, 0, 0, 0, new boolean[length+1][num[0]+1][num[1]+1][4][4], 0, new char[length]);
		if(answer == null) System.out.print("-1");
		else System.out.print(answer);
	}
	// visited[���繮�ڿ�����][����� A][����� B][�ٷ� ������ ���Դ���][2��° ������ ���Դ���] // �湮�� ������ �� ������ �ٸ�����, �ٷ�����, 2��° ���� ���� & ����� ABC�� ������ ���ٸ� �ᱹ �̷����� ������ ����� �ʷ��ϱ� ������ ���� ���ʿ��� �˻��� ���� �ʱ� ����
	static char[] dp(int[] number, int length, int a, int b, int c, boolean[][][][][] visited, int i, char[] answer) {
		if(i > 2 && !visited[i-1][a][b][answer[i-2]-'A'][answer[i-3]-'A']) {
			visited[i-1][a][b][answer[i-2]-'A'][answer[i-3]-'A'] = true;
		} else if(i == 2 && !visited[i-1][a][b][answer[i-2]-'A'][3]) {
			visited[i-1][a][b][answer[i-2]-'A'][3] = true;
		} else if(i == 1 && !visited[i-1][a][b][3][3]) { 
			visited[i-1][a][b][3][3] = true; 
		} else if(i!=0) {
			return null;
		}
		// i�� ���ϱ� ���̹Ƿ� ���� ���ڿ��� �ϳ� ����
		
		
		if(i==length && a == number[0] && b ==number[1] && c== number[2]) {
			return answer;
		} else {
			if(number[0] > a) {
				answer[i] = 'A';
				if(dp(number, length, a+1, b, c, visited, i+1, answer)!=null) return answer;
			}
			
			if(number[1] > b) {	
				if(i == 0 || (answer[i-1] != 'B')) {
					answer[i] = 'B';
					if(dp(number, length, a, b+1, c, visited, i+1, answer)!=null) return answer;
				}
			}
			
			if(number[2] > c) {
				if(i == 0 || (i == 1 && (answer[i-1] != 'C') || (i >= 2 && answer[i-2] != 'C' && answer[i-1] != 'C'))) {
					answer[i] = 'C';
					if(dp(number, length, a, b, c+1, visited, i+1, answer)!=null) return answer;
				}
			}
			
		}
		
		return null; 
	}
}

