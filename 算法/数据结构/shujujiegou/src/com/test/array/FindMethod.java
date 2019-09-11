package com.test.array;
/**
 *  有序数组，查找的方式
 *   （1）顺序查找
 *   （2）二分查找
 *   
 *   二分查找可以加快速度，如果使用在插入也不会有太大改善，只能在插入的位置和删除的位置快速定位
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
