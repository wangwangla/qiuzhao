package kw.test.sort;

public class SelectSort {
	public static void main(String[] args) {
		int length = 80000;
		int arr[] = new int[length];
		for(int i=0;i<arr.length;i++) {
			arr[i] = (int)(Math.random()*10);
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		long start = System.currentTimeMillis();
		test(arr);
		//18416
		//test1(arr);
		//18480
		
		System.out.println(System.currentTimeMillis() - start);
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}
	
	public static void test(int arr[]) {
		int min = 0;
		int index = 0;
		for(int i=0;i<arr.length;i++) {
			min = arr[i];
			index = i;
			for(int j= i+1;j<arr.length;j++) {
				if(arr[j]<min) {
					min = arr[j];
					index = j;
				}
			}
			arr[index] = arr[i];
			arr[i]=min;
		}
	}
	public static void test1(int arr[]) {
		int min = 0;
		int index = 0;
		for(int i=0;i<arr.length;i++) {
			min = arr[i];
			index = i;
			for(int j= i+1;j<arr.length;j++) {
				if(arr[j]<min) {
					min = arr[j];
					index = j;
				}
			}
			if(index!=i) {
				arr[index] = arr[i];
				arr[i]=min;
			}
		}	
	}
}
