package codeground;

import java.util.Scanner;

import common.Solution;

public class Codeground_75 extends Solution {
	static String Answer;
	/* 
	 * ��� : ȸ���� ���ڵ��� ������ ǥ��, �� ȸ���� ���ڰ� �ּҰ� �ǵ���
	 * 
	 * �Է°��� 10000 �����̹Ƿ� �ڸ��� ���� ������ Ȯ��
	 * 
	 * ����) ���� = ȸ��1 + notȸ��1
	 * 			ȸ��1 = ȸ��2 + notȸ��2
	 * 		���� = ȸ��2 + notȸ��1+notȸ��2
	 * 					notȸ��1+notȸ��2->ȸ���� �Ǵ��� Ȯ�� �ʿ� ----(2)
	 * 
	 * 1�ڸ���(1~9) : ������ ȸ��
	 * 2�ڸ���(10~99) : ȸ�� = ����ȸ�� + 11 >> �̹Ƿ� ��� ���� : ȸ�� + 0~10 ---(1)
	 *									 (ȸ��+n(n>11) = (ȸ�� +11->ȸ��)+(n-11))�̹Ƿ� --(1)
	 *				ȸ�� + 0~9 : ���ڰ� 2���� ȸ���� ������ ǥ�� ����
	 *				ȸ�� + 10 : a a-1 = b b + n >> n = a-b a-b-1 �̹Ƿ� n�� ȸ���� �� �� ����  >> ȸ��+10�� ��쿡�� ȸ������ 3���� ǥ�� ����
	 *				>>> ���� : ���ں��� ���� �ִ� ȸ�� + ����1 >>>���� : ���ں��� ���� �ִ� ȸ�� + ����1���� ���� �ִ� ȸ�� + ȸ��(0~9)
	 *				
	 * 3�ڸ���(100~999) : ȸ��  = ����ȸ�� + 10 or ȸ��  = ����ȸ�� + 11 >>> �̹Ƿ� ���� : ȸ��+0~10
	 * 					ȸ�� + 0~9 : ���ڰ� 2���� ȸ���� ������ ǥ�� ����
	 * 					ȸ�� + 10 : ���ѵ� ����� ��... 201, 302, 403... > n 0 n-1�� ���� ����
	 * 								n+1 0 n�� 2���� ȸ������ ǥ���Ϸ��� a k a + h n-a (��, k+h=10�� ��� ������ ���������� h n-a�� ȸ���̿��� �ϹǷ� h = n-a)
	 * 									n+1 0 n = a 10-n+a a + n-a n-a (n>a)>>> n=1(201)�� ��츦 �����ϰ�� 2���� ȸ���� ������ ǥ�� ����
	 * 					>>> 201 �����ϰ� 2���� ȸ���� ������ ǥ������
	 * 
	 * 4�ڸ���(1000~9999) : ȸ�� = ����ȸ�� + 110 or ȸ�� = ����ȸ�� + 2 >>> �̹Ƿ� ���� : ȸ�� + 0~109
	 * 					  (2) �� ���� ȸ���� ȸ���� �ƴѰ� ȸ���� ������ ǥ���������� Ȯ�� �ʿ�
	 * 
	 * 						110*n + 2*m (n<=9, n<=1)�� ȸ���� �ƴ� ----(3)
	 * 
	 * 						110*9 + 2 = 1001(ȸ��)> �Ϲ������� 9�� �� 2�� ����,, ���� ȸ������..
	 * 							1001 + 110*n�� ȸ�� 1001 + 1001*n�� ȸ��
	 * 
	 * 						ȸ��-1001(4�ڸ� �� �� �ּ� ȸ��) = ȸ��, �� 1001�������ʹ� 110 �� ���̰� �ƴϹǷ� 10/11/2(1001�� 99) �� ���̰� ��. ȸ�� ����.. ---(4)
	 * 
	 * 						(3) or (4)�� ������ �ȴ�.
	 * 						
	 * 							
	 * 
	 * 5�ڸ���(10000) : 9999 + 1>> 2�� ȸ������ ǥ�� ����
	 *  %10...�� /10..�� ���߸� ȸ��
	 *  ���κ��� ���� ���� �� �ִ� ȸ���� ���� �� ����. �� ���ڵ� ���������� ȸ���� ���Ѵ�.
	 * 0~9 : ������ ȸ��
	 * 2�ڸ� ���� : 11, 22, ... > ȸ���� 11 ���� > ȸ��+0~9 = ȸ�� 2���� ǥ�� ����, ȸ��+10 : ȸ��+ȸ������ ǥ�� �Ұ�.. 
	 *    2�ڸ� ���� : ȸ�� 2���� ������ ���� �� ���� 
	 *    
	 * 3�ڸ� ���� : 111, 121 ...202 > ȸ���� 10, 11 ���� > ȸ�� + 0~9:ȸ�� 2���� ǥ�� ����, 
	 *  ȸ��+11 :�����ڸ�=�����ڸ�+1, �����ڸ� = 0 �ΰ��.. �� ���� 3�ڸ��� ȸ��2���� ǥ�� �����ϰų�/ �ȵǰų� >  3�ڸ��� ȸ�� 2���� ǥ�� ������ ��� : �����ڸ� ���ڰ� 2���� ������ ������ ǥ�� ������ ��� -> 201�����ϰ� �� ��...>aba+cdc�϶�, a+c�� �����ڸ������̰� b+d�� 10�� �����ϴ� �ƹ� ����.. 
	 *    a+c = �����ڸ����� +1�� �ȵǴ°� �̷��� �����ڸ������� �ݿø��� �ȴ�.
	 *    3�ڸ� ���� : 201 �����ϰ� ȸ�� 2���� ������ ���� �� ���� 
	 *    
	 * 4�ڸ� ���� : 1001, 1111, 1221, ... 9999 > ȸ���� 110, 111 ���� > ȸ�� + 0~111�� ǥ�� ���� 
	 *  0~111 : ȸ�� 2���� ǥ�� ����.. 4�ڸ����� : ȸ�� 3���� ǥ�� ����. 
	 *  abba+cddc = efgh �� ��� efgh�� ȸ���� �ƴ� ��(ȸ���̸� �ٷ� ȸ�� 1���� ǥ���� ��..) e= h+1, f= g+1..  > 4�ڸ� ������ �� õ���ڸ�=�����ڸ�+1, �����ڸ�=�����ڸ�+1�� ��� ȸ��2���� ǥ������
	 * */

	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);

		//int T = sc.nextInt();
		for(int test_case = 0; test_case < 5000; test_case++) {
			Answer = "";
			int n = test_case+1;//sc.nextInt();
			if(n <= 10) {
				Answer = "1 "+n+"";
				if(n == 10) Answer = "2 9 1";
			}else if(n <= 100) {
				/*
				int digit_ten = n/10;
				int digit_one = n%10;
				if(digit_ten <= digit_one) {
					Answer = (digit_ten * 11) +" "+(digit_one - digit_ten);
				} else {
					Answer = (digit_ten - 1)*11 +" "+(digit_one+10 - (digit_ten-1));
				}
				if(n == 100) {Answer = "99 1";}*/
				if(n == 100) Answer = "2 99 1";
				else if(n % 11 == 10)Answer = 3+" "+(n/11)*11 +" 9 1";
				else {
					if(n%11 == 0) Answer = 1+" "+n;
					else Answer = 2+" "+(n/11)*11 +" "+(n%11);
					}
			}else if(n <= 1000) {
				int digit_hun = n / 100;//�����ڸ�
				int digit_ten = (n / 10)%10;//���� �ڸ�
				int digit_one = n % 10;//�����ڸ�
				if(n==1000) {
					Answer = "2 999 1";
				} else if(n == 201) {
					Answer = "3 191 9 1";
				} else if ((digit_hun == digit_one + 1) && digit_ten == 0){
					Answer = 2+" "+(n - 111) + " 111";//Answer = 2+" "+(n - 101) + " 101";
				} else {
					int near_circle;
					if(digit_hun == digit_one) {
						near_circle = n;
					} else if(digit_hun > digit_one) {
						if(digit_ten == 0) {
							near_circle = (digit_hun-1)*100 + 90 + digit_hun-1;
						} else {
							 near_circle = (digit_hun)*100 + (digit_ten - 1)*10 + digit_hun;
						}
					} else {
						near_circle = digit_hun*100 + digit_ten*10 + digit_hun;
					}
					if((n - near_circle)==0)Answer = 1+" "+near_circle;
					else Answer = 2+" "+near_circle + " " +(n - near_circle);
				}
			}else {
				Answer = "0";
				int left = n /100;
				int right = n % 100;
				int left_reverse = left%10*10 + left/10;
				int near_circle;
				if(left_reverse > right) { //1990 = 1881 + 109
					near_circle = (left-1)*100 + ((left-1)%10*10 + (left-1)/10);
				}else { //1999
					near_circle = left*100 + left_reverse;
				}
				
				if((left/10 == right%10) && (right/10 == left%10)) Answer = 1+" "+n;
				else {
					int B = n - near_circle;
					int m = (near_circle/100)%10;
					if(B<10) {
						Answer = 2+" "+near_circle+" "+B;
					}else if(B<100) {
						if(B%11==10)Answer = "0";//�Ұ����� ��쵵 ����. 1001 + 32 -> 1001 22 10
						else Answer = 3+" "+near_circle+" "+(B/11)*11+" "+(B%11);// �⺻
						
						
						if(m-B%10>0)  {//1013..���� ����. �ٽ� ����.
							if(B%10 + B/10 > 10) {
								if(B%10 + B/10!=10) {
									if(B%10 -1 <= m)Answer = 2+" "+(near_circle-(B%10-1)*110)+" "+(B+(B%10-1)*110);
									if(B%10==1)Answer = 2+" "+(near_circle-110)+" "+(B+110);
								}
								
							}  else if(m+B/10<10) {
								if(B%10 + B/10!=10) if(B%10<=m)Answer =2+" "+(near_circle-(B%10)*110)+" "+(B+(B%10)*110);
							}
							//11���� ���� m�� �ִ밪������ ������ �ƴϰ� �� Ŭ���� ���� ..n�� 0�̱���.
							if(B%10-B%10>1) { //�� 11�� ���� �� 1001���� Ŀ����
								if(B%10+B/10>9) {
									if(B%10 + B/10!=10) if(B%10>=m)Answer = 2+" "+(near_circle-(B%10)*110-11)+" "+(B+(B%10)*110+11);
								}else if(B%10+B/10<9) {
									if(B%10 + B/10!=10) if(B%10+1>=m)Answer = 2+" "+(near_circle-((B%10+1)*110)-11)+" "+(B+(B%10+1)*110+11);
								}
							}
							
						}else if(m==0 && left/10==1){
							if((2+B%10)>10) {
								if((B/10)<=(2+B%10)) {
									if((B/10)==(2+B%10))Answer = 2+" "+999+" "+(B+2+m*110);
									else Answer=2+" "+(999-(B%10+2-B/10)*10)+" "+ (B+2+(B%10+2-B/10)*10);
								}
							} 
						}

						if(B==10)Answer = 3+" "+near_circle+" "+9+" 1";// �⺻
						else if(B%11==0)Answer = 2+" "+near_circle+" "+(B);
						else if (B%10==0) Answer = 3+" "+near_circle+" "+(B/11)*11+" "+(B%11);//�����ڸ��� 0�̶� m �����ڸ��� 0�� �� ����
					}else {//110>B>=100
						//if(B==100)Answer = 3+" "+near_circle+" 99 1";
						//else Answer = 3+" "+near_circle+" 101 "+(B-101);
						Answer = 3+" "+near_circle+" 101 "+(B-101);
						if(m-B%10>0) {//�̰� �ƴ�
							if(B%10-1<=B/10) {
								//System.out.println(1);
								Answer = 2+" "+(near_circle-(B%10-1)*110)+" "+(B+(B%10-1)*110);}
							else if(B/10<=B%10) {
								if(B%10==9) Answer = 3+" "+near_circle+" "+"101 8";//���ϱ� 1�ϸ� 1���ڸ� 0��. ȸ�� �Ұ�..
								else Answer = 2+" "+(near_circle-(B%10)*110-11)+" "+(B+(B%10)*110+11);
								}
						}
						
						if(B==100)Answer = 3+" "+near_circle+" 99 1";
					}
					if(n==10000)Answer = "2 9999 1";
				}
			}
			
			int num = Integer.parseInt(Answer.charAt(0)+"");
			String result = Answer;
			if((result.length())<2)System.err.println("0�� ���  "+n);//System.err.println("0�� ���  "+result);
			else {
				result = result.substring(2);
				int sum=0;
				for(int k=0;k<num;k++) {
					int y;
					if(result.substring(result.lastIndexOf(" ")+1).equals(""))y=Integer.parseInt(result);
					else y = Integer.parseInt(result.substring(result.lastIndexOf(" ")+1));
					sum+=y;
					
					if(result.lastIndexOf(" ")==-1);
					else result = result.substring(0, result.lastIndexOf(" "));
					if(y<10) {
						if(y<0) {System.err.println("0�� ���� "+n+"  Answer  "+Answer);}
					}
					else if(y<100&&(y>=10)) {
						if(y%11!=0) {System.err.println("1�� ���� "+n+"  Answer  "+Answer+" "+y);}
					} else if(y<1000){
						if(y%10!=y/100)System.err.println("12�� ���� "+n+"  Answer  "+Answer+" "+y);
					}else {
						int y_left=y/100;
						int y_right = y%100;
						if((y_left%10==y_right/10)&&(y_left/10==y_right%10));
						else {System.err.println("2�� ���� "+n+"  Answer  "+Answer);}
					}
				}
				if(sum!=n)System.err.println("3�� ���� "+n+"  Answer  "+Answer);
			}
			
			
			//System.out.println("Case #"+(test_case+1));
			//System.out.println(Answer);
		}
		
	}
}
