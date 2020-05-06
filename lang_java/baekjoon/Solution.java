package lang_java.baekjoon;

import java.io.IOException;

public abstract class Solution {
	public abstract void solution() throws IOException;

	
	static void print(int[][] array) {
		System.out.println();
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}
}