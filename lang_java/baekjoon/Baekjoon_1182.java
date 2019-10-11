package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_1182 extends Solution {
	static int[] array;
	static boolean[] a;
	
	// ���Ҹ� �ְų� ���ų� �Ͽ� 
    static int partitial(int goal, int index, int current, int length, int limit) { //goal:  ��ǥ���ϴ� ����, index : ����  �������� ���� ������� ���� ��ġ, current : ���±����� ��, length : ���ҿ��� ������ ���� ������ ���ߴ���, limit : ������ index
    	//System.out.println("test "+ index+" "+current+" "+length);
    	int answer = 0;
    	if(goal == current && length == limit) { // ���� ���� ��� (length == limit) �� �񱳰� �ʿ��Ѱ� ���Ұ� 0�ϼ��� �����Ƿ� ��� ���ҿ� ���غ񱳰� �ʿ�, goal�� �Ǵ� ���� �� ���� �񱳸� ���ϴ°� �ƴ� 0�� �������� �����Ƿ�
    		for(int i = 0;i<limit;i++) {
    			if(a[i])System.out.print("O ");
    			else System.out.print("X ");
    			}
    		System.out.println();
    		return 1;
    	} 
    	//else �ϸ� �ȵȴ� ���Ұ� 0�ϼ��� �����Ƿ� goal == current�� ���� ���� ���Ұ� 0�� ��쿡�� �� �ϳ� �� �����ϴ°�.
    	if(index < limit) { // ����� �ʿ��� ���
    		a[index] = true;
    		answer += partitial(goal, index+1, current+array[index], length+1, limit); //index ���� ������ ���
    		a[index] = false;
    		answer += partitial(goal, index+1, current, length+1, limit); //index ���� �������� ���� ���. ���⵵ length�� ���ϴ������� length�� ������� ���õȿ����� ������ �ƴ϶� ���� ������ �����̱� ����
    	}
		return answer;
    	//else return 0; // goal > current �� ���� �Ұ����� ��찡 �ƴ� ���Ұ� �����ϼ��� �����Ƿ�
    }
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		array = new int[N];
		a = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N;i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		int answer = partitial(S, 0, 0, 0, N);
		if(S == 0)answer--;
		System.out.println(answer);
	}
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
