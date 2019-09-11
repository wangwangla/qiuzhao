package com.test.sort;

public class BubbleSort<T> implements BubbleSortInterface {
	private long [] arr;
	private int len;
	
	public BubbleSort(int length) {
		// TODO Auto-generated constructor stub
		arr = new long[length];
	}
	@Override
	public void insert(long value) {
		// TODO Auto-generated method stub
		arr[len] = (long) value;
		len++;
	}

	@Override
	public void bubbleSort() {
		// TODO Auto-generated method stub
		for(int i=0;i<len;i++) {
			for(int j=0;j<len-1;j++) {
				if(arr[j]>arr[j+1]) {
					swap(j, j+1);
				}
			}
		}
	}

	private void swap(int l1,int l2) {
		long temp = arr[l1];
		arr[l1] = arr[l2];
		arr[l2] = temp;
	}
	
	@Override
	public void print() {
		// TODO Auto-generated method stub
		for(int i=0;i<len;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		BubbleSort b = new BubbleSort(10);
		b.insert(13);
		b.insert(2);
		b.insert(5);
		b.insert(4);
		b.insert(15);
		b.insert(42);
		b.print();
		b.bubbleSort();
		b.print();
	}
}