package kw.test.sort;

public class QulickSort {
	public static void main(String[] args) {
		int length = 8;
		int arr[] = new int[length];
		for(int i=0;i<arr.length;i++) {
			arr[i] = (int)(Math.random()*10);
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		long start = System.currentTimeMillis();
		test(arr,0,arr.length-1);	
		System.out.println(System.currentTimeMillis() - start);
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}
	public static void test(int arr[],int left,int right) {
		int midNum = arr[(left+right)/2];
		int l = left;
		int r = right;
		int temp;
		while(l<r) {
			while(midNum>arr[l]) {
				l++;
			}
			while(midNum<arr[r]) {
				r--;
			}
			if(l>r) {
				break;
			}
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
			if(arr[r]==midNum) {
				r--;
			}
			if(arr[l]==midNum) {
				l++;
			}
		}
		if(l==r) {
			l++;
			r--;
		}
		if(left<r) {
			test(arr,left,r);
		}
		if(l<right) {
			test(arr,l,right);
		}
	}
}
