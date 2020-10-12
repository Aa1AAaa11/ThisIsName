package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1182 extends Solution {
	

	static int[] array;
	static int[] sum;
	
	static int answer = 0;
    
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		array = new int[N];
		int sum_len = 1 << N;
		sum = new int[sum_len];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N;i++) {
			array[i] = Integer.parseInt(st.nextToken());
			sum[1 << i] = array[i];
		}
		int j = 1;
		int answer = 0;
		int tmp = 1 << j;
		for(int i = 1; i < sum_len; i++) { // for ������ N�� for���� ������ i�� ���Ͽ� �ְų� ���ų��ص���.
			if(i > tmp)sum[i] = sum[tmp] + sum[i - tmp];
			if(sum[i] == S)answer++;
			if((i+1) == (1 << (j + 1))) {
				j++;
				tmp = 1 << j;
				}
		}
		System.out.println(answer);
	}
	/*
	// ����Լ� ���
	static int[] array;
	
	// ���Ҹ� �ְų� ���ų� �Ͽ� 
    static int partitial(int goal, int index, int current, int length, int limit) { //goal:  ��ǥ���ϴ� ����, index : ����  �������� ���� ������� ���� ��ġ, current : ���±����� ��, length : ���ҿ��� ������ ���� ������ ���ߴ���, limit : ������ index
    	//goal == current�� �� ���� ���ϸ� �ȵ�. ���Ұ� 0�ϼ��� �ְ� ���Ұ� ������ ��쵵 �־ goal == current�� ������ ������, ���� �ʴ��� ���� �Ǵ� �� Ȯ�� �ʿ���.
    	if(length == limit) { // ���� ���� ��� (length == limit)  �񱳰� �ʿ��Ѱ� ���Ұ� 0�ϼ��� �����Ƿ� ��� ���ҿ� ���غ񱳰� �ʿ�, goal�� �Ǵ� ���� �� ���� �񱳸� ���ϴ°� �ƴ� 0�� �������� �����Ƿ�
    		if(goal == current) return 1; //if(length == limit && goal == current) return 1; ���� ������. ��� ���� ��츸 goal==current���� Ȯ���ϸ� �Ǵϱ�.
    	} 
    	else if(index < limit) { // ����� �ʿ��� ���
    		///int answer = 0;
    		//answer += partitial(goal, index+1, current+array[index], length+1, limit); //index ���� ������ ���
    		//answer += partitial(goal, index+1, current, length+1, limit); //index ���� �������� ���� ���. ���⵵ length�� ���ϴ������� length�� ������� ���õȿ����� ������ �ƴ϶� ���� ������ �����̱� ����
    		return partitial(goal, index+1, current+array[index], length+1, limit) + partitial(goal, index+1, current, length+1, limit);
    	} 
    	return 0; // goal > current �� ���� �Ұ����� ��찡 �ƴ� ���Ұ� �����ϼ��� �����Ƿ�
    }
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		array = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N;i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		int answer = partitial(S, 0, 0, 0, N);
		if(S == 0)answer--; //�������� ����
		System.out.println(answer);
	}*/
}
/*package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_1759 extends Solution{
	static String[] array;
	static int L; //��й�ȣ ����
	static int C; // �Է¹޴� ���� ����
	static String answer = "";
	static boolean[] visited;
	
	// s : �⺻ ���ڿ�
	// index : �߰��� ���� ��ġ
	// length : ������� ������� ���ڿ� ����
	static void password(String s, int index, int length) {/*
		String answer="";
		System.out.println("test"+" "+index+" len "+length+" s="+s+" L = "+L+" C = "+C);
		if(length == L ) {
			visited[index] = false;
			return s+"\n";
		} else {
			for(int i = 0; i< C; i++) { // ->�ߺ��Ǵ°��� ����!!!
				System.out.println("answer : "+answer+" answer/////"+length+" "+i);
				if(!visited[i])answer += password(s+array[i], i, length+1); //??
				visited[i] = true;
			}
		}
		return answer;///////
		
		//visited[index] = true; 
		//String answer = s + array[index];
		visited[index] = true; 
		System.out.println("test"+" "+index+"  s="+s+" ");
		if(length == L ) {
			System.out.println("return  "+s);
			visited[index] =false;
			answer+=s+"\n";
		} else {
			for(int i = 0; i< C; i++) { 
				System.out.println("answer : "+answer+" answer/////"+length+" "+i);
				if(!visited[i]) password(s+array[i], i, length+1);//if(!visited[i])answer += password(answer, i, length+1); //??
				//visited[i] = false; // password �� ������ �ؼ� ������ ���� �� �н����忡 ���� ���� �� -> �̷��� ���� ����Լ����� visited = true�� ���� �湮���� �����͵� �湮�ϼ��ȴ�.
			}
		}
		System.out.println("visited["+index+"] false");
		visited[index] =false;
	}
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		L = Integer.parseInt(br.readLine());
		C = Integer.parseInt(br.readLine());
		array = br.readLine().split(" ");
		visited = new boolean[C];
		password(array[0], 0, 1);
		for(int i = 0;i<C;i++) {
			password(array[i], 0, 1);
		}
		System.out.println(answer);
	}
}

*/
