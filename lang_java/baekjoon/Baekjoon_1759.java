package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_1759 extends Solution {
	static char[] array;
	static int L; //��й�ȣ ����
	static int C; // �Է¹޴� ���� ����
    static StringBuilder sb;
    //static int[] num;
	
	// s : �⺻ ���ڿ�
	// index : �߰��� ���� ��ġ
	// length : ������� ������� ���ڿ� ����
	static void password(char[] s, int length, int index) {
		if(length == L) {
			//num = new int[26];
			int vowel_num = 0;//������ ����
			// ��й�ȣ�� �������� Ȯ��
			for(int i = 0; i < length; i++) { 
				//num[s[i]-'a']++;
				if(s[i]== 'a' || s[i] =='i' || s[i] =='u' || s[i] =='o' || s[i] =='e')vowel_num++;
			}
			//int vowel_num = num[0] + num[4] + num[8] + num[14] + num[20]; //������ ����
			if(vowel_num >=1 && length-vowel_num >= 2) //��й�ȣ�� ������ ��� (������ ������ 1�� �̻��̰� ������ 2�� �̻��� ���)
				sb.append(s).append("\n");
		}
		else {
			for(int i = index+1;i < C; i++) { //visited �� ��� �Ǵ� ������ for������ ���� �Է� ���ķ� �߰��ϱ� ������ �ߺ��� ���� -> �̰� ������ ������ �׻� �����ϱ� ����, 
				
				s[length] = array[i];//s = s.substring(0, length)+array[i]; 
				password(s, length+1, i);
				// �⺻ dfs�� �ٸ� .. dfs : 2,1,3�� 3,1,2 �� �ٸ��� ����. 
				// ��ȣ : 2,1,3�� 3, 1,2 ����
				/*if(!visited[i]) {
					//s += array[i]; > �̷��� ���� for���� ������ �ش�.
					if(length > 1)s = s.substring(0, length)+array[i]; 
					else s = s+array[i];
					password(s, length+1, i);
					//visited[i] = false;
				}*/
			}
		}
	}
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		array = new char[C];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<C;i++) {
			array[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(array);
		char[] start = new char[L];
		password(start,0, -1);
		
		System.out.println(sb.toString());
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
