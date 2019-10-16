package kw.test.sort;

public class MertSort {
	public static void main(String[] args) {
		int length = 8;
		int arr[] = new int[length];
		for(int i=0;i<arr.length;i++) {
			arr[i] = (int)(Math.random()*10);
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		long start = System.currentTimeMillis();
		
		//m(arr,ar);		
		System.out.println(System.currentTimeMillis() - start);
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}

	}
	public static void m(int arr[],int left,int right,int temp[]) {
		if(left<right) {
			int mid = (left+right)/2;
			m(arr, left, mid, temp);
			m(arr, mid+1, right, temp);
			marge(arr, left, mid, right, temp);			
		}
	}
	public static void marge(int arr[],int left,int mid,int right,int temp[]) {
		int i = left;
		int j = mid+1;
		int t = 0;
		while(i<=mid&&j<=right) {
			if(arr[i]>arr[j]) {
				temp[t] = arr[j];
				t++;
				j++;
			}else {
				temp[t] = arr[i];
				t++;
				i++;
			}
		}
		if(i<=mid) {
			temp[t] = arr[i];
			t++;
			i++;
		}
		if(j<=right) {
			temp[t] = arr[j];
			t++;
			j++;
		}
		int leftTemp = left;
		t = 0;
		for(int z = left;z<=right;z++) {
			arr[z] = temp[t];
			t++;
		}
	}
}
