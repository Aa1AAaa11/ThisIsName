package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_13398 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] memo = new int[n][2]; 
        int[] numbers = new int[n];

        st =  new StringTokenizer(br.readLine());
        int answer = memo[0][0] = memo[0][1] = numbers[0] = Integer.parseInt(st.nextToken());
        
        for(int i = 1; i < n; i++) {
        	numbers[i] = Integer.parseInt(st.nextToken()); //�Է�
        	
        	// memo[n][0] : n-1 ��° ���ڱ��� ���ڸ� �ϳ��� �������� ���� ��� (������ �������� ���� ��츸 ����) -> ������ �ƴ϶� ���� ���� ������ ����.������ ã������ �� (���� ���� �Ұ��� �ϴٸ� �̰� �����̰ڤ���)
        	// memo[n][1] : n-1 ��° ���ڱ��� ���ڸ� �����ϰų� �������� ��� (������ ����̰ų� ����������������ϼ� ����.) ->�����ϰų� ���������ʰų� ��� ����� ������
        	// ���� ������ �����Ƿ� 2���� �迭�� ���� �����.
            // memo[n][0] = max(memo[n-1][0]+num[n], num[n]); // n�� ������ ���Խ�Ű�ų� n �ϳ����ϰų�
            // memo[n][1] = max(memo[n-1][0], memo[n-1][1] + numbers[n], numbers[n]); // n�� �����ϰų� ���� �ʰų� ���� ��Ű�ų� n�ϳ����ϰų�
        	// 1. n�� �����Ѵ�.(n-1 ������!���ž��� ���) 2. n���������� �ʴ´� (n-1 ����or���ž��Ѱ�� �� ����� ���� + n). 
        	// 3. n ȥ�ھ���. ->�̰��� memo[n][0]�������
        	
        	memo[i][0] = Integer.max(memo[i-1][0] + numbers[i], numbers[i]);
        	memo[i][1] = Integer.max(memo[i-1][1] + numbers[i], memo[i-1][0]);
        	
        	// for�� �ȿ��� memo[i-1]�� ���� �ִ밪�� ���ϸ� �Ǵ°Ŵϱ� memo �迭 �ʿ� ���� �� ��� �־ ��������.
        	answer = Integer.max(memo[i][1], answer);
        	answer = Integer.max(memo[i][1], answer);
        }
        
        // memo[n-1]�� ���� �ƴ�.memo[n-1]�� n-1 ��°���ڸ� ����� ��� ������ ������.
        System.out.println(answer);
	}

}