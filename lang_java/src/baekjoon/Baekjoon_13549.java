package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_13549 extends Solution {

	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int K = Integer.parseInt(st.nextToken()); 
		ArrayList<Integer> queue = new ArrayList<Integer>();
		int[] time = new int[100001];
		if(N != K)queue.add(N); // N==K��� while�� �ȵ��� 0 �����.
		time[N] = 1; // �湮���� ���� : 0�� ���̸��ֱ� ����
		while(!queue.isEmpty()) {
			//���� index �湮
			int index = queue.get(0);
			queue.remove(0);
			if(index == K) break;// �������� ������ ��� ���̻� Ž�����ʿ䰡 ����
			
			if(index <= K) { // index > K�� ��� 2*index > 2*K => ���Ҵ� -1���� �����ϹǷ�  2*K���� K�� ������  K�� �߰� �̵� �ʿ�.. �׷� �ٿ��� index > K�̹Ƿ� index����-1�� �̵���.
				// index > K��� *2�� +1�� ��Ŀ���ʿ� ���� �׳� �Լ� ���°� �ֻ��ǰ����
				
				// �����̵� �ϴ� ���
				if(index*2 <= 100000 && time[index*2] == 0) {// �湮�� ���� ���� ��� (time[index*2] == 0) && �湮�� ������ ���� �����ִ°�� (index*2 <= 100000)-> index*2 < 100000 �ƴ�, 10������ �����ϹǷ� ������ 10���ϼ��� ����
					time[index*2] = time[index];
					queue.add(0, 2*index); 
					}
				// �ɾ�� ��� (����)
				if(index+1 <= 100000 && time[index+1] == 0) { // �湮�� ���� ���� ��� (time[index+1] == 0) && �湮�� ������ ���� �����ִ°�� (index+1 <= 100000)-> index+1 < 100000 �ƴ�, 10������ �����ϹǷ� ������ 10���ϼ��� ����
					queue.add(index+1); 
					time[index+1] = time[index]+1;
				}
			}

			// �ɾ�� ��� (����)
			// �����ϴ� ���� index > K, inedx <= K�� ��� �Ѵ��غ�����. index�� 0���� ũ�⸸�ϸ�.
			if(index > 0 && time[index-1] == 0) {
				time[index-1] = time[index]+1;
				queue.add(index-1); // �� +�� �̵��ϴ� ����+1���ִ°��̾ƴ϶� *2�� �����Ƿ� -1�� ���� �� �����̵� �ϴ� ��� �ֻ��� ����� ���� �� ����.
			}
		}
		
		// 1���� ���������Ƿ�
		System.out.println(time[K]-1);
	}
	
}
