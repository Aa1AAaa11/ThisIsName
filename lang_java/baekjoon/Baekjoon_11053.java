package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_11053 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N]; //A[i] :i ��° ����
        StringTokenizer st;

        st =  new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] memo = new int[N]; 
        int answer = 1;
        memo[0] = 1;
        // 1. memo[i] : i+1��° ���ڿ��� ������ �κм����� �ִ� ���� �� ���
        // memo[i] = memo[j]+1 (��, A[i] < A[j]�� �����ϴ� �� �� memo[j]�� �ִ��� j)
        for(int i = 1; i < N; i++) {
            memo[i] = 1;
        	for(int j = 0; j < i; j++) {
        		if(A[j] < A[i] && memo[i] < memo[j]+1) memo[i] = memo[j]+1;
        	}
        	if(memo[i] > answer) answer = memo[i];
        }
        System.out.println(answer);

        /*// 2. memo[i] : ���� i�� �������� ������ ���� (���� ����) ������ ��
        // memo[i] = A[j] (memo[i-1] < A[j]�� ���)
        // memo[i-k] = A[j] (memo[i-1] > A[j]�� ���, A[i-k] < A[j] < A[i-k+1]�� �����ϴ� ��)
        memo[0] = A[0];
        int j = 0;
        for(int i = 1; i < N; i++) { // ��� A[i]�� ���� ��� memo�� ���� �� ���Ѵ�.
        	if(memo[j] < A[i]) {
        		memo[++j] = A[i]; // memo[j] ���� A[i] �� ũ�Ƿ� �̾��� ������ A[i]�� ���� �� �ִ�.
        	} else { // memo[j]�� �̾ A[i]�� �� ���̴� ���
        		int tmp_j = 0;
        		while(memo[tmp_j] < A[i] && tmp_j < i) { // memo[tmp_j] >= A[i]�� ��� memo[tmp_j]�� A[i]�� �ٲ��.
        			tmp_j++;
        		}
        		memo[tmp_j] = A[i];
        	}
        }
        
        System.out.println(j+1); */
        
        
	}
	
}