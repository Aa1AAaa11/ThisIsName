package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1912 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] memo = new int[n];
        int[] numbers = new int[n];

        st =  new StringTokenizer(br.readLine());
        int answer = memo[0] = numbers[0] = Integer.parseInt(st.nextToken());
        
        
        for(int i = 1; i < n; i++) {
        	numbers[i] = Integer.parseInt(st.nextToken()); //�Է�
        	
            // memo[n] = n+1��° ���ڸ� ����Ͽ��� �� ���� �� �ִ� �κм����� �ִ� ��
            // ���ų� ���� �ʰų� �����������ʿ�� ����. �� �����������ϸ� ������ �����������. ������� ���� ������ ����
            // memo[n] : n ���ڸ� �̾ ���̰ų� �̾ �٤������ʰų�
            // memo[n] = max(memo[n-1]+num[n], num[n]);
        	memo[i]= Integer.max(memo[i-1] + numbers[i], numbers[i]);
        	if(answer < memo[i]) answer = memo[i];
        }
        
        // memo[n-1]�� ���� �ƴ�.memo[n-1]�� n-1 ��°���ڸ� ����� ��� ������ ������.
        System.out.println(answer);
	}

}