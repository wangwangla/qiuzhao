package com.test.array;

/**
 * 这种方法是可以进行操作的，但是可以发现它 并没有提供什么方便的地方，下来进行改进
 * @author Administrator
 *
 */
public class SimpleArray {
	public static void main(String[] args) {
		LowerArray lowerArray = new LowerArray(20);
		int n=0;
		lowerArray.setElem(0, 1);
		lowerArray.setElem(1, 4);
		lowerArray.setElem(2, 5);
		lowerArray.setElem(3, 9);
		for(int i=0;i<4;i++) {
			System.out.println(lowerArray.getElem(i));
		}
	}
}
class LowerArray{
	private long[] a;
	public LowerArray(int size) {
		a = new long[size];
	}
	public void setElem(int index,long num) {
		a[index] = num;
	}
	public long getElem(int index){
		return a[index];
	}
}