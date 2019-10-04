package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon_10974 extends Solution {

	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] array;
	static boolean[] visited;
	
	
	/* DFS�� ������� �ʰ� change_index �� �ٲٸ鼭 �˸°� �����ϴ� ���
	 static void change (int change_index) {
		
		for(int k = 0; k < N-1 - change_index ; k++ ) {//k�� ���� Ƚ�� (change_index�� ���� N-1 - change_index �� �ݺ� �ʿ�)
			if(change_index < N-2 )change(change_index+1); //change_index�� N-2 ���� �����ϹǷ�.
			int change = array[change_index];
			
			//change_index�� ��� �ٲ����� �ϴ��� Ȯ��
			for(int i = N-1; i >= change_index+1; i-- ) {
				if(array[i] > change) { 
					array[change_index] = array[i]; 
					array[i] = change;
					
					int sort_limit = (N-change_index-2)/2;//((N-1)-(change_index+1))/2; 
					
					//���ڸ� �ٲ۴�. 
					for(int j = 0; j <= sort_limit; j++) {
						int tmp = array[change_index+1+j];
						array[change_index+1+j] = array[N-1-j];
						array[N-1-j] = tmp;
					}
					
					break;
				}
			}
			for(int i = 0; i < N; i++) {
				sb.append(array[i]).append(' ');
			}
			sb.append('\n');
		}
		if(change_index < N-2) change(change_index+1); // ���������� �ٲ�� �ڸ����� �ö󰡱� ���� ������ �ѹ� �� ������Ѵ�.
	}*/

	static void change(int depth) {
		// depth(int) : ���° ��������, N ��°�� ��� ������
		// array(int[]) : ����
		// visited(int[]) : �湮���θ� Ȯ���ϱ� ���� �迭
		
		if(depth == N) { //depth == N �� ��� ��� ���� Ž�� �Ϸ�, ���̻� Ž���� �ʿ�� ����
			for(int i = 0; i < N; i++) {
				sb.append(array[i]).append(' ');
			}
			sb.append('\n');
			//System.out.println(sb.toString()); //�� �������� �볪�� ����� �ʿ�� ����
			
		} else {
			//depth : ���°�� �湮�� ��������
			//i : ������ �湮�ϴ� ����
			for(int i = 0; i < N; i++) { //��� ������ ���� Ȯ�� �ʿ�
				if(!visited[i]) { //(i+1)�� �湮�� ���� ���� ������ ���
					array[depth] = (i+1); //������1 ~ N�̹Ƿ� i+1�� �Ѵ�. //array[i]�� �ƴ�. change(depth,..)�̹Ƿ� depth��°���� ��� �����̿ð����� ��� �ʿ�. ���� array[depth]�̴�.
					visited[i] = true; //visited�� depth�� �ƴ�. ���� �湮�� ������ i+1��.depth���ƴϰ�.
					change(depth+1); //depth�� 1���� �� �ٽ� Ȯ��.
					visited[i] = false; //�ݺ����� �� ���� �ƿ� �ٸ� ��ΰ� �ǹǷ� viseted�� �ʱ�ȭ �������, DFS�� �ݺ����� �ٸ� ���, change -> change(depth+1)�� ���� ����̴�.
				}
			}
			
			
		}
	}
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		array = new int[N];
		
		/*// DFS �Ⱦ��� ���
		 * for(int i = 0; i<N;i++) {
			array[i] = i+1;
			sb.append(array[i]).append(' ');
		}
		sb.append('\n');
		
		// ��� �ڸ����� ���� ���ư��鼭 change�� ����.
		change(0);*/
		
		
		visited = new boolean[N];
		change(0);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
