package com.test.array;
/**
 *  �������飬���ҵķ�ʽ
 *   ��1��˳�����
 *   ��2�����ֲ���
 *   
 *   ���ֲ��ҿ��Լӿ��ٶȣ����ʹ���ڲ���Ҳ������̫����ƣ�ֻ���ڲ����λ�ú�ɾ����λ�ÿ��ٶ�λ
 * @author Administrator
 *
 */
public class FindMethod {
	
	public boolean find(int arr[],int searchKey) {
		int lower = 0;
		int upper = arr.length;
		int curIn;
		while(true) {
			curIn = (lower+upper)/2;
			if(arr[curIn]==searchKey) {
				return true;
			}else if(lower>upper) {
				return false;
			}else {
				if(arr[curIn]<searchKey) {
					lower=curIn+1;
				}else {
					upper=curIn-1;
				}
			}
		}
	}

}
