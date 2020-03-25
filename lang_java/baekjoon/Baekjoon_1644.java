package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1644 extends Solution {
	
	static int N;
	static boolean[] isNotPrime;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		isNotPrime = new boolean[N+2];
		int limit = (int)Math.sqrt(N);
		isNotPrime[1] = true;
		for(int i = 2; i <= limit; i++) { // �� ������ ����� �ִ��� �Ǵ��� ����
			if(isNotPrime[i]) continue; // i�� �Ҽ��� �ƴ� ��� 
			for(int j = i*2; j <= N; j +=i) { // j�� i�� ���
				isNotPrime[j] = true;
			}
		}
		System.out.println(get());
	}
	static int get() {
		int start = 2, end = 2, sum = 0;
		int answer = 0;
		while(end <= N || sum >= N) {
			if(sum > N) { // ���ڸ� �����ؾ���
				sum -= start;
				start = getNextPrime(start);// �Ҽ��� �ƴ� ��� �н�
			} else if(sum < N) {// ���ڸ� �� �־����
				sum += end;
				end = getNextPrime(end);
			} else { //�ʱ�ȭ �ʿ�
				answer++;
				start = getNextPrime(start);
				end = start;
				sum = 0;
			}
		}
		return answer;
	}
	static int getNextPrime(int number) {
		do {
			number++;
		} while(isNotPrime[number] && number <= N);  // ���̻� �Ҽ��� ���� ��� N+1�� ��ȯ�Ѵ�.
		return number;
	}
}

