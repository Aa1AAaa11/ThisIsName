package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14002 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N]; //A[i] :i ��° ����
        int[][] memo = new int[N][2]; // memo[i][0] : i+1 ��° ���ڸ� ����� ��� ������ �ִ����. memo[i][1] : �ִ������ ��� i������ ���� ���� ��ġ
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st =  new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        memo[0][0] = 1;
        int max = 1; // �ʱⰪ 1
        int max_i = 0;
        
        // memo[i][0] : memo[j][0] +1, memo[i][1] : j
        // j�� A[i] > A[j]�� �����ϰ� memo[j]�� �ִ��� ���
        for(int i = 1; i < N; i++) {
        	int tmp_j = i;
        	for(int j = i-1; j >= 0; j--) { // if(A[tmp_j] > A[j])
        		if(A[i] > A[j] && memo[tmp_j][0] < memo[j][0]) {
        			tmp_j = j;
        		}
        	}
        	
        	if(tmp_j == i) {
            	memo[i][0] = 1; // �ƹ��͵� ������� ����.. �ڱ� �ڽ��� ������ ��
        	} else { // tmp_j�� i�� ����
            	memo[i][0] = memo[tmp_j][0] + 1;
            	memo[i][1] = tmp_j;
        	}
        	
        	// �ִ��� ���
        	if(max < memo[i][0]) {
        		max = memo[i][0];
        		max_i = i;
        	}
        }
        
        int print_j = max_i;
        for(int i = 0; i < max-1; i++) {
        	sb.insert(0, A[print_j]).insert(0, ' ');
        	print_j = memo[print_j][1];
        }
    	sb.insert(0, A[print_j]);
    	sb.insert(0, '\n').insert(0, max);
    	System.out.print(sb.toString());
	}
}