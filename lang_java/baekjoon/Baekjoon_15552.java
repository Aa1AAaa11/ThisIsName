package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon_15552 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(bf.readLine());
		String answer = "";
		for(int i = 0; i < T; i++) {
			String s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			answer += (A+B)+"\n";
		}
		bw.write(answer);//���
		bw.flush();//�����ִ� �����͸� ��� ��½�Ŵ
		bw.close();//��Ʈ���� ����
	}
}
