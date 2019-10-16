package kw.test.sort;

public class InsertSort {
	public static void main(String[] args) {
		int length = 8;
		int arr[] = new int[length];
		for(int i=0;i<arr.length;i++) {
			arr[i] = (int)(Math.random()*10);
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		long start = System.currentTimeMillis();
		test(arr);	
		System.out.println(System.currentTimeMillis() - start);
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}
	public static void test(int arr[]) {
		int num ;
		int index = 0;
		//从第一个数据一直到最后
		for(int i = 1;i<arr.length;i++) {
			//将当前数据存储
			num = arr[i];
			//本次的数据
			index = i;
			for(int j = i-1;j>=0;j--) {
				if(num>arr[j]) {
					arr[j+1]=arr[j];
					index = j;
				}else{
					break;
				}
			}
			//数据插入使当的是位置上
			arr[index]=num;
		}
		
	}
}
