package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;

import common.Solution;

public class Baekjoon_1339 extends Solution {
	
	static int[][] numbers;
	static int answer = 0;
	static int[] lengths;
	static HashSet<Integer> list_alphabet; // �������ִ� ���ĺ�
	static int[] map_alphabet; // ����
	static int[] string; // ���ĺ��� ���°���� ����
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		numbers = new int[k][]; // ����Ǵ� ���ڿ���
		StringBuilder sb = new StringBuilder();
		lengths = new int[k];
		list_alphabet = new HashSet<Integer>();
		for(int i = 0; i< k; i++) {
			sb = new StringBuilder(br.readLine());
			int limit = sb.length();
			numbers[i] = new int[limit];
			lengths[i] = limit;
			for(int j = 0; j < limit; j++) {
				char tmp = sb.charAt(j);
				numbers[i][j] = tmp - 'A';
				if(!list_alphabet.contains(numbers[i][j])) {
					list_alphabet.add(numbers[i][j]);
				}
			}
		}
		int length = list_alphabet.size();
		map_alphabet = new int[length];
		string = new int[26];
		Iterator it = list_alphabet.iterator();
		int i = 0;
		while(it.hasNext()) {
			map_alphabet[i] = 9-i;
			string[(int)it.next()] = i;
			i++;
		}
		int index = 0;
		do {
			answer = Math.max(answer, calculate(numbers, k));
		}while(next(length));
		
		System.out.println(answer);
    	
	}
	// ������ ������ �������� ���
	static int calculate(int[][] strings, int k) {
		int answer = 0;
		
		for(int i = 0; i < k; i++) {
			int length = lengths[i];
			
			for(int j = length-1, ten = 1; j >= 0; j--, ten *= 10) {
				answer += map_alphabet[string[strings[i][j]]] * ten;
			}
		} 
		
		return answer;		
	}
	// ���� ������ �����. (��ȯ���� ���� ������ �������� ����)
	static boolean next(int length) {

		int i = length-1;
		int standard_index = 0; // �ٲ� index

		for(;i > 0 && map_alphabet[i-1] < map_alphabet[i]; i--);
		standard_index = i-1; //standard_index �������δ� �������� ���ĵǾ�����.
		if(standard_index < 0) return false;
		
		
		for(i = length-1; i > 0 && map_alphabet[i] > map_alphabet[standard_index]; i--);
		int change_index = i; // standard_index�� �ٲ� �ε���
		// change_index, standard_index ����
		int tmp = map_alphabet[change_index];
		map_alphabet[change_index] = map_alphabet[standard_index];
		map_alphabet[standard_index] = tmp;
		//����
		int limit = (length-standard_index)/2;//((length-1)-(standard_index)+1)/2; // standard ~ length-1 �� �¿� �ٲ�
		for(int j = 0, left = 0, right = 0; j < limit; j++) {
			left = standard_index+1+j;
			right = length - 1 - j;
			tmp = map_alphabet[left];
			map_alphabet[left] = map_alphabet[right];
			map_alphabet[right] = tmp;
		}
		
		return true;
	}
	
	/*
	 * static int[][] numbers;
	static int answer = 0;
	static int[] lengths;
	static HashSet<Integer> have_alphabet;
	static int[] map_alphabet;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		numbers = new int[k][]; // ����Ǵ� ���ڿ���
		StringBuilder sb = new StringBuilder();
		lengths = new int[k];
		have_alphabet = new HashSet<Integer>();
		map_alphabet = new int[26];
		int index = 0;
		for(int i = 0; i< k; i++) {
			sb = new StringBuilder(br.readLine());
			int limit = sb.length();
			numbers[i] = new int[limit];
			lengths[i] = limit;
			for(int j = 0; j < limit; j++) {
				char tmp = sb.charAt(j);
				numbers[i][j] = tmp - 'A';
				if(!have_alphabet.contains(numbers[i][j])) {
					map_alphabet[numbers[i][j]] = index++;
					have_alphabet.add(numbers[i][j]);
				}
			}
		}
		int length = have_alphabet.size();
		calculate(0, k, length, new boolean[10], new int[length]);
		System.out.println(answer);
    	
	}
	static void calculate (int depth, int k, int number, boolean[] visited, int[] array) {
		if(depth == number) {
			int tmp = 0;
			for(int i = 0; i < k; i++) { //i ��° ���ڿ�
				int limit = lengths[i];
				for(int j = 0, ten = (int)Math.pow(10, limit-1); j < limit; j++, ten /= 10) {
					tmp += ten*array[map_alphabet[numbers[i][j]]];
				}
			}
			answer = Math.max(tmp, answer);
		} else {
			for(int i = 10-number; i < 10; i++) {
				if(!visited[i]) {
					visited[i] = true;
					array[depth] = i;
					calculate(depth+1, k, number, visited, array);
					visited[i] = false;
				}
			}
		}
	}
	*/
}