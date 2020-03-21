package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_12100 extends Solution {
	
	static int N;
	static int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // �̵� ��ǥ (��, ��, ��, ��) �̵� �� �˻� ����
	static int[] standard_start;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		standard_start = new int[]{N-1, N-1, 0, 0};
		int[][] board = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int max = 1 << 10; //2^10
		int answer = getBlock(0, board);
		for(int i = 0; i < max; i++) {
			 answer = Math.max(answer, getBlock(i, board));
		}
		System.out.println(answer);
		
	}
	static int getBlock(int direction, int[][] board) { // direction : 5ȸ �̵��� ����. 
		int answer = 0;
		int[][] tmp_board = new int[N][N];
		
		// 5ȸ �̵��Ѵ�.
		for(int k = 0; k < 5; k++) {
			int now = direction & 3; // ���� ���� 
			direction = direction >> 2;
			ArrayList<Integer> queue = new ArrayList<Integer>();
			int index_i = 0, index_j = 0; // ���ϴ� index_i �� �̵��Ѵ�.

			for(int i = 0; i < N; i++) {
				queue.clear();
				int queue_index = 0;
				// �����¿� �ٸ� // �����¿� �ٸ� �ʱ� �ε��� ��
				index_i = standard_start[now] + i*move[now][1];
				index_j = standard_start[now] + i*move[now][0]; // ������ �̵��ؾ���
				
			
				// ���� ��ġ�� ���� ����
				for(int j = 0; j < N ; j++) {
					if(k == 0)tmp_board[index_i][index_j] = board[index_i][index_j];
					if(tmp_board[index_i][index_j] != 0) {
						if(queue_index < queue.size() && tmp_board[index_i][index_j] == queue.get(queue_index)) {
							queue.remove(queue_index);
							queue.add(tmp_board[index_i][index_j] << 1); // ��ģ��.
							queue_index = queue.size(); // �̹� ������ ����̹Ƿ� ���̻� ���ϸ� �ȵ�
						} else {
							queue.add(tmp_board[index_i][index_j]); // �� ���� ����� ���غ��� �ϹǷ� queue_index�� ���ϸ� �ȵȴ�.
							queue_index = queue.size()-1; // �׻� ���� �߰��� �ֶ� ���ؾ���
						}
					}
					index_i += move[now][0];
					index_j += move[now][1];
				}
				index_i = standard_start[now] + i*move[now][1];
				index_j = standard_start[now] + i*move[now][0]; 

				// board�� �Է�
				for(int j = 0; j < N ; j++) {
					if(queue.size() > 0) {
						tmp_board[index_i][index_j] = queue.get(0);
						answer = Math.max(tmp_board[index_i][index_j], answer);
						queue.remove(0);
					}
					else tmp_board[index_i][index_j] = 0;
					index_i += move[now][0];
					index_j += move[now][1];
				}
			}
		}
		return answer;
	}
}

/*
 * package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_12100 extends Solution {
	
	static int N;
	static int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // �̵� ��ǥ (��, ��, ��, ��) �̵� �� �˻� ����
	static int[] standard_start;
	static int[] standard_move = {-1, -1, 1, 1};
	static int answer = 0;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		standard_start = new int[]{N-1, N-1, 0, 0};
		int[][] board = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				answer = Math.max(answer, board[i][j]);
			}
		}
		int max = 1 << 10;
		board = updateBoard(0, board);
		System.out.println("��");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		board = updateBoard(2, board);
		System.out.println("��");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		board = updateBoard(1, board);
		System.out.println("��");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		board = updateBoard(3, board);
		System.out.println("��");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		for(int i = 0; i < 4; i++) {
			//getBlock(i, board);
		}
		System.out.println(answer);
	}
	static void getBlock(int direction, int[][] board) { // direction : 5ȸ �̵��� ����. 
		//int answer = 0;
		// 5ȸ �̵��Ѵ�.
		for(int i = 0; i < 1; i++) {
			int now = direction & 3; // ���� ���� 
			direction = direction >> 2;
			board = updateBoard(now, board);
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
	}
	static int[][] updateBoard(int now, int[][] board) { // now : ���� �����̴� �椷��
		
		int index_i = 0, index_j = 0; // ���ϴ� index_i �� �̵��Ѵ�.
		for(int k = 0; k < N; k++) {
			
			// �����¿� �ٸ� // �����¿� �ٸ� �ʱ� �ε��� ��
			int tmp_i = standard_start[now] + k*move[now][1], tmp_j = standard_start[now] + k*move[now][0]; // ������ �̵��ؾ���
			System.out.println("start "+tmp_i+", "+tmp_j);
			
			int input_i = tmp_i, standard_i = tmp_i, standard_j = tmp_j, input_j = tmp_j;
			index_i = tmp_i;
			index_j  = tmp_j; 
			
			while(0 <= tmp_i && tmp_i <= N-1 && tmp_j <= N-1 && tmp_j >= 0 && board[tmp_i][tmp_j] == 0) {
				tmp_i += move[now][0];
				tmp_j += move[now][1];
			}
			// 1. ����� ���� �����Ѵ�.
			if((tmp_i != index_i || tmp_j !=index_j) && 0 <= tmp_i && tmp_i <= N-1 && 0 <= tmp_j && tmp_j <= N-1) {
				board[index_i][index_j] = board[tmp_i][tmp_j];
				board[tmp_i][tmp_j] = 0;
			}
			index_i = tmp_i + move[now][0];
			index_j = tmp_j + move[now][1];
			System.out.println("ONE LINE "+index_i+", "+index_j+"  tmp "+tmp_i+", "+tmp_j);
			
			//System.out.println(tmp);
			for(int l = 0; l < N ; l++) { // j�� ���� ��ġ
				//if(i == 3)System.out.println(standard+" "+input+" "+j+" "+board[j][i]);
				if(index_i < 0 || index_i > N-1 || index_j < 0 || index_j > N-1 || standard_i < 0 || standard_i > N-1 || standard_j < 0 || standard_j > N-1) break;
				if(board[index_i][index_j] == 0) {
					index_i += move[now][0];
					index_j += move[now][1];
					continue;
				}
				System.out.println("VALUE = "+board[index_i][index_j]+ " | index "+index_i+", "+index_j+" | standard "+standard_i+", "+standard_j+" | input "+input_i+", "+input_j+" | tmp "+tmp_i+", "+tmp_j);
				
				// 1. standard�� j �� ���Ƽ� ������ �� �ִ� ���
				if(board[index_i][index_j] == board[standard_i][standard_j] && board[standard_i][standard_j] != 0) {
					//System.out.println("��ģ��!!! "+index_i+", "+index_j+"  standard "+standard_i+" , "+standard_j+" input "+input_i+", "+input_j+"  "+( board[index_i][index_j] << 1));
					board[input_i][input_j] = board[index_i][index_j] << 1; // standard�� j �� ��ģ��.
					board[index_i][index_j] = 0; // j �� ���������� �׻� �̵���. ��ĭ���� �ٲ۴�.
					answer = Math.max(answer, board[input_i][input_j]);
					if(input_i + input_j < standard_move[now]*(standard_i + standard_j)) { // �Է� ��ġ�� �� ���� �ִ� ���. standard�� ��ġ�� �̵��� ��� input�� �� ���� ���
						board[standard_i][standard_j] = 0;
					//	System.out.println("�ళ�� 0!!!");
					}
					// input �� ���� ���̻� ���ŵ� ���� �����Ƿ� 
					input_i += move[now][0];
					input_j += move[now][1];
					
					// j �� ���������Ƿ� ���̻� ���������� ����.
					standard_i = index_i + move[now][0];
					standard_j = index_j + move[now][1];
				//	System.out.println("!!!���ƴ�!!! "+board[input_i][input_j]);

					//System.out.println(standard_i+", "+standard_j+"   "+board[standard_i][standard_i]+" "+board[2][0]);
					while(0 <= standard_i && standard_i <= N-1 && standard_j <= N-1 && standard_j >= 0 && board[standard_i][standard_j] == 0) {
						//System.out.println(standard_i+", "+standard_j+"   "+board[standard_i][standard_i]+" "+board[2][0]);
						// standard�� 0�� ���� �ʵ��� �Ѵ�.
						standard_i += move[now][0];
						standard_j += move[now][1];
					} 
					index_i = standard_i+ move[now][0];
					index_j = standard_j+ move[now][1];
					if(index_i < 0 || index_i > N-1 || index_j < 0 || index_j > N-1 || standard_i < 0 || standard_i > N-1 || standard_j < 0 || standard_j > N-1) break;
					
					System.out.println("�߰����� "+index_i+", "+index_j+" "+standard_i+", "+standard_j);
					while(0 <= index_i && index_i <= N-1 && index_j <= N-1 && standard_j >= 0 && board[index_i][index_j] == 0) {
						// ���� ���� j ���� ��ĭ�̸� ������ �ʾƵ� �ȴ�.
						index_i += move[now][0];
						index_j += move[now][1];
					}
					// �� �Ʒ� +move �����Ƿ�
					index_i -= move[now][0];
					index_j -= move[now][1];
					//System.out.println("!!!���ƴ�!!! "+index_i+", "+index_j+"  standard "+standard_i+" , "+standard_j+" input "+input_i+", "+input_j+"  ");

					//System.out.println("!!!���ƴ�!!! "+board[input_i][input_j]);
				} else { // 2. standard�� j �� ������ �� ���� ��� (���� ����(standard)��, ���ڰ� �ִ�(j) ���簪�� ������ �� ���� ���)
					//System.out.println("����ģ��!!! index "+index_i+", "+index_j+" |  standard "+standard_i+", "+standard_j+" | input "+input_i+", "+input_j);
					if(board[input_i][input_j] == 0) {// input�� �� ���� ���. 
						board[input_i][input_j] = board[standard_i][standard_j]; // �� ���� �����ϱ� ����
						board[standard_i][standard_j] = 0;
						input_i += move[now][0];
						input_j += move[now][1];
					} else { // input !=0 �� ��� (standard == input)�� ���
						input_i += move[now][0];
						input_j += move[now][1];
					}
					standard_i = index_i;
					standard_j = index_j;
				}
				index_i += move[now][0];
				index_j += move[now][1];
			}
			System.out.println(index_i+", "+index_j+"   "+standard_i+", "+standard_j+" "+input_i+" "+input_j+"  "+(input_i != standard_i)+"  "+( input_j != standard_j));
			if(0 <= standard_i && 0 <= standard_j && standard_i < N && standard_j < N && board[standard_i][standard_j] != 0 ) {
				board[input_i][input_j] = board[standard_i][standard_j];
				if(input_i != standard_i || input_j != standard_j)board[standard_i][standard_j] = 0;
				System.out.println("LAST "+board[input_i][input_j]+" "+board[standard_i][standard_j]);
			}

			System.out.println();
		}
		return board;
	}
}
 */
/*package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_12100 extends Solution {
	
	static int N;
	static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // �̵� ��ǥ (��, ��, ��, ��)
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int[][] board = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int max = 1 << 10;
		board = updateBoard(3, board);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		for(int i = 0; i < max; i++) {
			
		}
	}
	int getBlock(int direction, int[][] board) { // direction : 5ȸ �̵��� ����. 
		int answer = 0;
		// 5ȸ �̵��Ѵ�.
		for(int i = 0; i < 5; i++) {
			int now = direction & 3; // ���� ���� 
			direction = direction >> 2;
			
		}
		return answer;
	}
	static int[][] updateBoard(int now, int[][] board) { // now : ���� �����̴� �椷��
		
		for(int i = 0; i < N; i++) {
			// index_0 : �������� ��ġ
			//int index_0 = 0, index_j;
			//int stanard_index = 0; //���� ������ ��ġ (j�� ������ �� �ִ� ������ �ΉH��)
			int standard = 0;
			while(standard <= N-1 && board[standard][i] == 0)standard++;
			int input = standard;
			
			// ���� ������
			if(standard > 0) {
				board[0][i]=board[standard][i];
				board[standard][i] = 0;
				standard = 0;
				input = 0;
			}
			for(int j = standard+1; j < N; j++) { // j�� ���� ��ġ
				// input (������ ��ġ > �߰� ��ĭ������ standard�� �ٸ� �� �ִ�.)
				// standard (��������j �� ���� ��ġ)
				// j ���� 
				// standard
				// standard == j �� ��� standard�� j�� ��ģ��. 
					// >> �ѹ� �̵��� ������ ��ĥ �� �����Ƿ� standard�� j+1�� �ٲ۴�. j��ġ�� ���������Ƿ� 0 ��ĭ���� �����. input = standard+1�� �ٲ��ش�.
				// �������� �ʴ� ��� 	
					// j == 0�� ��� j++ (������ standard ��)
					// j != 0�� ��� standard�� ��ĥ �� ���ٴ� ��. standard = j�� �����Ѵ�.
				
				//if(board[j][i] == 0) continue;  
				if(now == 3) { // ��
					
					if(board[standard][i] == board[j][i] && board[j][i] != 0) { // �������� ��� (��ĭ�̸� �ȵ�)
						board[input][i] = (board[j][i] << 1); 
						if(standard > input) { // �������� ��ġ�� ���� ���� 2������ ��� ���� �ִ� ��� �Ѵ� 0���� �ٲ����
							board[standard][i] = 0; // 
						}
						board[j][i] = 0; // ��ĭ���� �����.
						input = standard+1; // ���ŵ� �� �ٷ� ������ �߰��Ǿ���ϴ� �� (�����̵� �ƴϵ� ���� �ϳ� 0���� �ٲ�� �������� ���̹Ƿ�)
						standard = j+1; // 1ȸ �̵��� �������� ���� �� �����Ƿ� 
						j++; // ���� j �� �˻����ʿ䰡 ���� standard�� �Ǿ����Ƿ�
						
					} else if(board[j][i] != 0){ // �������� �ʴ� ��� 
						 // standard�� j�� ������ �� ���� ��� 
						if(board[input][i] == 0)board[input][i] = board[standard][i]; // j�� �̵���Ų��. (�߰� ��ĭ ����)
						else {
							board[++input][i] = board[standard][i]; // ������ �ƴ� ���,, input�� �� �Է��� �Ұ����� ��� 
							board[standard][i] = 0; // �̵��� ��ĭ���� �����.
						}
						standard = j; // standard�� ���̻� ������ �� ���� ���̹Ƿ� ���� ���ο� ���ذ��� j �� ���Ѵ�. j+1....// input �� j���� ����.
					}
				}
				if(j == N-1 || standard == N-1) {
					 // ������ ���� ���
					board[input][i]=board[standard][i];
					if(input!=standard)board[standard][i] = 0;
				}
			}
		}
		
		return board;
	}
}*/
/*
for(int i = 0; i < N; i++) {
int standard = start[now];
while(0<= standard && standard <= N-1 && board[standard][i] == 0)standard += standard_move[now];
int input = standard;
for(int j = standard+1; j < N; j++) { // j�� ���� ��ġ
	// input (������ ��ġ > �߰� ��ĭ������ standard�� �ٸ� �� �ִ�.)
	// standard (��������j �� ���� ��ġ)
	// j ���� 
	// standard
	// standard == j �� ��� standard�� j�� ��ģ��. 
		// >> �ѹ� �̵��� ������ ��ĥ �� �����Ƿ� standard�� j+1�� �ٲ۴�. j��ġ�� ���������Ƿ� 0 ��ĭ���� �����. input = standard+1�� �ٲ��ش�.
	// �������� �ʴ� ��� 	
		// j == 0�� ��� j++ (������ standard ��)
		// j != 0�� ��� standard�� ��ĥ �� ���ٴ� ��. standard = j�� �����Ѵ�.
	
	if(now == 3) { // ��
		if(board[standard][i] == board[j][i] && board[j][i] != 0) { // �������� ��� (��ĭ�̸� �ȵ�)
			board[input][i] = (board[j][i] << 1); 
			if(standard > input) { // �������� ��ġ�� ���� ���� 2������ ��� ���� �ִ� ��� �Ѵ� 0���� �ٲ����
				board[standard][i] = 0; // 
			}
			board[j][i] = 0; // ��ĭ���� �����.
			input = standard+1; // ���ŵ� �� �ٷ� ������ �߰��Ǿ���ϴ� �� (�����̵� �ƴϵ� ���� �ϳ� 0���� �ٲ�� �������� ���̹Ƿ�)
			standard = j+1; // 1ȸ �̵��� �������� ���� �� �����Ƿ� 
			j++; // ���� j �� �˻����ʿ䰡 ���� standard�� �Ǿ����Ƿ�
			
		} else if(board[j][i] != 0){ // �������� �ʴ� ��� & ���� Ž���� ��ġ�� ��ĭ�� �ƴ� ���
			 // standard�� j�� ������ �� ���� ��� 
			if(board[input][i] == 0)board[input][i] = board[j][i]; // j�� �̵���Ų��. (�߰� ��ĭ ����)
			else board[++input][i] = board[j][i]; // ������ �ƴ� ���,, input�� �� �Է��� �Ұ����� ��� 
			board[j][i] = 0; // �̵��Ͽ����Ƿ� ��ĭ���� �����.
			standard = input; // standard�� ���̻� ������ �� ���� ���̹Ƿ� ���� ���ο� ���ذ��� j �� ���Ѵ�. j+1....
		}
	}
	 // ������ ���� ���
	board[input][i]=board[standard][i];
	board[standard][i] = 0;
}
}
*/
/*package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_12100 extends Solution {
	
	static int N;
	static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // �̵� ��ǥ (��, ��, ��, ��)
	static int[] start = {N-1, N-1, 0, 0};
	static int[] standard_move = {-1, -1, 1, 1};
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int[][] board = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int max = 1 << 10;
		board = updateBoard(3, board);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		board = updateBoard(1, board);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		board = updateBoard(2, board);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		board = updateBoard(0, board);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		for(int i = 0; i < max; i++) {
			
		}
	}
	int getBlock(int direction, int[][] board) { // direction : 5ȸ �̵��� ����. 
		int answer = 0;
		// 5ȸ �̵��Ѵ�.
		for(int i = 0; i < 5; i++) {
			int now = direction & 3; // ���� ���� 
			direction = direction >> 2;
			
		}
		return answer;
	}
	static int[][] updateBoard(int now, int[][] board) { // now : ���� �����̴� �椷��
		
		for(int i = 0; i < N; i++) {
			int standard = start[now];
			while(0<= standard && standard <= N-1 && board[standard][i] == 0)standard += standard_move[now];
			int input = standard;
			for(int j = standard + standard_move[now]; j < N && j >= 0; j+= standard_move[now]) { // j�� ���� ��ġ
				// input (������ ��ġ > �߰� ��ĭ������ standard�� �ٸ� �� �ִ�.)
				// standard (��������j �� ���� ��ġ)
				// j ���� 
				// standard
				// standard == j �� ��� standard�� j�� ��ģ��. 
					// >> �ѹ� �̵��� ������ ��ĥ �� �����Ƿ� standard�� j+1�� �ٲ۴�. j��ġ�� ���������Ƿ� 0 ��ĭ���� �����. input = standard+1�� �ٲ��ش�.
				// �������� �ʴ� ��� 	
					// j == 0�� ��� j++ (������ standard ��)
					// j != 0�� ��� standard�� ��ĥ �� ���ٴ� ��. standard = j�� �����Ѵ�.
				
				if(now == 3 || now == 0) { // ��
					System.out.println(standard+" "+i+" "+j+" "+now);
					if(board[standard][i] == board[j][i] && board[j][i] != 0) { // �������� ��� (��ĭ�̸� �ȵ�)
						board[input][i] = (board[j][i] << 1); 
						if(standard > input*standard_move[now]) { // �������� ��ġ�� ���� ���� 2������ ��� ���� �ִ� ��� �Ѵ� 0���� �ٲ����
							board[standard][i] = 0; // 
						}
						board[j][i] = 0; // ��ĭ���� �����.
						input = standard+1; // ���ŵ� �� �ٷ� ������ �߰��Ǿ���ϴ� �� (�����̵� �ƴϵ� ���� �ϳ� 0���� �ٲ�� �������� ���̹Ƿ�)
						standard = j+1; // 1ȸ �̵��� �������� ���� �� �����Ƿ� 
						j++; // ���� j �� �˻����ʿ䰡 ���� standard�� �Ǿ����Ƿ�
						
					} else if(board[j][i] != 0){ // �������� �ʴ� ��� & ���� Ž���� ��ġ�� ��ĭ�� �ƴ� ���
						 // standard�� j�� ������ �� ���� ��� 
						if(board[input][i] == 0)board[input][i] = board[j][i]; // j�� �̵���Ų��. (�߰� ��ĭ ����)
						else board[++input][i] = board[j][i]; // ������ �ƴ� ���,, input�� �� �Է��� �Ұ����� ��� 
						board[j][i] = 0; // �̵��Ͽ����Ƿ� ��ĭ���� �����.
						standard = input; // standard�� ���̻� ������ �� ���� ���̹Ƿ� ���� ���ο� ���ذ��� j �� ���Ѵ�. j+1....
					}
				} else {
					if(board[i][standard] == board[i][j] && board[i][j] != 0) { // �������� ��� (��ĭ�̸� �ȵ�)
						board[i][input] = (board[i][j] << 1); 
						if(standard > input*standard_move[now]) { // �������� ��ġ�� ���� ���� 2������ ��� ���� �ִ� ��� �Ѵ� 0���� �ٲ����
							board[i][standard] = 0; // 
						}
						board[i][j] = 0; // ��ĭ���� �����.
						input = standard+1; // ���ŵ� �� �ٷ� ������ �߰��Ǿ���ϴ� �� (�����̵� �ƴϵ� ���� �ϳ� 0���� �ٲ�� �������� ���̹Ƿ�)
						standard = j+1; // 1ȸ �̵��� �������� ���� �� �����Ƿ� 
						j++; // ���� j �� �˻����ʿ䰡 ���� standard�� �Ǿ����Ƿ�
						
					} else if(board[i][j] != 0){ // �������� �ʴ� ��� & ���� Ž���� ��ġ�� ��ĭ�� �ƴ� ���
						 // standard�� j�� ������ �� ���� ��� 
						if(board[i][input] == 0)board[i][input] = board[i][j]; // j�� �̵���Ų��. (�߰� ��ĭ ����)
						else board[i][++input] = board[i][j]; // ������ �ƴ� ���,, input�� �� �Է��� �Ұ����� ��� 
						board[i][j] = 0; // �̵��Ͽ����Ƿ� ��ĭ���� �����.
						standard = input; // standard�� ���̻� ������ �� ���� ���̹Ƿ� ���� ���ο� ���ذ��� j �� ���Ѵ�. j+1....
					}
				}
			}
			System.out.println(standard+" "+i+"  "+now);
			 // ������ ���� ���
			if(now == 3 || now == 0) {
				board[input][i]=board[standard][i];
				board[standard][i] = 0;
			} else {
				board[i][input]=board[i][standard];
				board[i][standard] = 0;
			}
		}
		
		return board;
	}
}*/