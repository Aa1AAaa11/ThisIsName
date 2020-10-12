package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_4902 extends Solution {
	
    static int[] sum;
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int line = Integer.parseInt(st.nextToken());
		int[] triangles;
		int case_num = 1;
		StringBuilder sb = new StringBuilder();
		while(line != 0) {
			int total = getLinetotal(line);
			triangles = new int[total];
			sum = new int[total];
			triangles[0] = Integer.parseInt(st.nextToken());
			sum[0] = triangles[0];
			for(int i = 1; i < total; i++) {
				triangles[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + triangles[i];
			}
			sb.append((case_num++)+". "+getMax(line, total, triangles)).append("\n");
			st = new StringTokenizer(br.readLine());
			line = Integer.parseInt(st.nextToken());
		}
		System.out.println(sb.toString());
	}
	static int getMax(int line, int total, int[] triangles) {
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < total; i++) { // ��� �ﰢ���鿡 ���� ������ �� �غ�
			int step = getStep(i);
			int ableN = ableLine(i, line, step); // i ���� ������ �ִ� N
			int modify = i%2 == step%2?1: -1; // ���������� ����������
			max = Math.max(max, getMaxTriangle(i, step, modify, ableN, triangles)); // �ִ� N �� ��� �ѹ��� ȣ���Ѵ�. getMaxTriangle �Լ� ������ ableN�� ���� �� ableN���� �����͵鵵 �����Ͽ� ���ϱ� ������
		}
		return max;
	}
	static int ableLine(int i, int line, int step) { // �ﰢ�� i�� �ֻ�� �������̶� �Ͽ��� �� ������ N�� �ִ븦 ����
		// i : ���� �ﰢ�� ��ġ, line: ��ü �ﰢ���� line ��(1~), step: ���� i�� ���° �ٿ� �ִ��� (0~)
		if(i%2 == step%2) { // ���ﰢ���� ���
			return line - step;
		} else { // ���ﰢ���� ���
			int position = (i- getLinetotal(step))/2 + 1; // i �� step ��ġ���� ���°�� �ִ��� (��, ���ﰢ���� ����� ��ġ) (1~)
			int max_triangle = (step+1)/2; // step��° ��(0~) ���� �ִ��� ũ�� ���� �� �ִ� ���ﰢ��
			if(step%2 == 0 && position > max_triangle) return max_triangle-(Math.abs(max_triangle-position)-1);
			else return max_triangle-Math.abs(max_triangle-position);
		}
	}
	static int getMaxTriangle(int i, int step, int modify, int ableN, int[] triangles) { // i : ���� ��ġ �ﰢ��, step: i�� ���° �ٿ� �ִ��� ,modify : ���� ���� ��� ���Ͽ��� �� �����ٷ� ��� �̵��ϴ���, ableN : ������ �ִ� N
		int max = Integer.MIN_VALUE;
		int tmpsum = 0;
        int left = i;
        int right = i;
		for(int N = 1; N <= ableN; N++) { // �� �ٿ� ���� ����
			tmpsum += sum[right] - sum[left] + triangles[left]; // �� ������ �ִ� 16�����̹Ƿ� -> ���� Ž���ϱ⿡�� �ʹ� ����.. ��� triangles�� Ž������ �ʰ� ���صξ��� ���� �̿��Ѵ�.	
            if(modify == 1) { // ���ﰢ��
                left = left + getStepTotal(step+N-1); // �Ʒ����� ����
                right = left + getStepTotal(N-1) +1; // �Ʒ����� ������
			} else { // ���ﰢ���� ���
                right = right - getStepTotal(step-N); // ���ﰢ���� �Ųٷ� �ö�. ������ ����
                left = right - getStepTotal(N)+1; // ������ ������
			}
            
			max = Math.max(max, tmpsum); // ���� N�� ��쵵 ���غ��ƾ���
		}
		
		return max;
	}
	static int getLinetotal(int line) { // line�� (1~) �ִ� ��� �ﰢ���� ����
		return line*line;
	}
	static int getStepTotal(int step) { // step ��° ��(0~)�� �ﰢ���� ����
		return 2*step+1;
	}
	static int getStep(int i) {
		return (int)Math.sqrt(i);
	}
}
