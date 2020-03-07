package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_2580 extends Solution {

	static int[][] sudokus = new int[9][9]; // �������
	static int empty_length = 0; // �� ������
	static ArrayList<int[]> empty_index; // �� ������ ��ġ ����
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		empty_index = new ArrayList<>();
		for(int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				sudokus[i][j] = Integer.parseInt(st.nextToken());
				if(sudokus[i][j] == 0) {
					empty_index.add(new int[] {i, j});
				}
			}
		}
		empty_length = empty_index.size();
		
		sudoku(0);
	}
	
	// �������� �˻�
	static boolean check(int x, int y, int number) {
		int arround_x = 3*(x/3), arround_y = 3*(y/3);
		// �ƴϸ� for �ȵ����� �迭�� ���� �Ҽ��� ���� �� (����[9][9], ����[9][9], ���� 9��[9][9] �� �迭 ����� �� �迭[i][number]�� true ���� �˻�) // �� ����, ����, ���� 9���� ���ڸ� �ٿ��� 0~8 ���� �迭�� �����.
		for(int i = 0; i < 9; i++) {
			if(sudokus[x][i] == number) return false; // ���� ���� ���� ���
			if(sudokus[i][y] == number) return false; // ���ι��� ���� ���
			if(sudokus[arround_x + i/3][arround_y + i%3] == number) return false; // ���� 9���� ���� ���ڰ� �ִ� ���
		}
		return true;
	}

	static boolean sudoku(int depth) {
		if(depth == empty_length) {
			StringBuilder answer = new StringBuilder();
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					answer.append(sudokus[i][j]).append(' ');
				}
				answer.append('\n');
			}
			System.out.print(answer.toString());
			return true;
		} else {
			int[] position = empty_index.get(depth);
			int x = position[0], y = position[1];
			for(int i = 1; i <= 9; i++) {
				int number = i;
				if(check(x, y, number)) { // number�� ������ ���
					sudokus[x][y] = number; // sudoku�� ���ڸ� �ִ´�.
					if(sudoku(depth+1)) return true; // ���� ������ �� ���ڸ� ���غ���. // (���� ������ �P���� ���غ��� �����ϸ� true/ �Ұ��� �� false return��)
				} 
				sudokus[x][y] = 0; // number�� �������� �ʴ� ��� �ٽ� ���� �־����. check..
			}
		}
		return false; // �߰��� return �ȸ����� ������� ���°Ŵ� �����ߴٴ°�.
	}
}