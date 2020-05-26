package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_15685 extends Solution {
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // �巡�� Ŀ���� ����
		boolean[][] map = new boolean[101][101]; //map[y][x] : ��ǥ (x, y) �� �巡�� Ŀ�꿡 ���ԵǴ���
		int[][] move = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; // �� ��, ��, ��, �� ���п� ���� x, y �� ��� ���ϴ���
		int[] curve_len_list = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024}; // g ���� ������ ���̰� �󸶳� �Ǵ���

		// �ùķ��̼��� ����� n������ �� �巡�� Ŀ�갡 ��� �̵��ϴ��� ������
		int[] dragon_curve = new int[1024]; // 0�� ���� ������ ��쿡 ���� �巡�� Ŀ�긦 �̷�� ���е鿡 ���� ���� (�� : 0, ��: 1, ��: 2, ��: 3)
		dragon_curve[0] = 0;
		int add_len = 1;
		for(int i = 1; i <= 10; i++, add_len*= 2) { //i ����
			for(int j = 0; j < add_len; j++) { //i ���� ������ i-1 ���뺸�� add_len ��ŭ ����
				dragon_curve[add_len + j] = (dragon_curve[add_len-1-j] + 1)%4; // 90�� ȸ�� �� �̾� ���̹Ƿ� �����Ǵ� ù��° : ������ �����ϴ� ������������ ȸ������ ����
			}
		}
		
		// �Է� �� Ŀ�� �̵�
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x =  Integer.parseInt(st.nextToken());
			int y =  Integer.parseInt(st.nextToken());
			int d =  Integer.parseInt(st.nextToken());
			int g =  Integer.parseInt(st.nextToken());
			int curve_len = curve_len_list[g]; 
			map[y][x] = true;
			
			for(int j = 0; j < curve_len; j++) { // 
				// �Է����� �־����� �巡�� Ŀ��� ���� ������ ����� �����Ƿ� �߰� �˻�� �ʿ����
				x = x + (move[(dragon_curve[j]+d)%4][1]); // ���� x ��ǥ�� ���ص� �巡�� Ŀ�� ���� ���� ���� ���� ���� ����
				y = y + (move[(dragon_curve[j]+d)%4][0]);
				map[y][x] = true;
			}
		}
		
		// �� �𼭸��� ���� �˻�
		int answer = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) answer++; // �� �������� ��� �巡��Ŀ���� �Ϻ��� ���
			}
		}
		System.out.println(answer);
		
		
	}
}
