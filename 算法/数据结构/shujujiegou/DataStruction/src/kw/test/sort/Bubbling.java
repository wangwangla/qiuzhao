package kw.test.sort;

public class Bubbling {
	public static void main(String[] args) {
		int length = 80000;
		int arr[] = new int[length];
		for(int i=0;i<arr.length;i++) {
			arr[i] = (int)(Math.random()*10);
		}
		long start = System.currentTimeMillis();
		//test(arr);
		//18416
		test1(arr);
		//18480
		
		System.out.println(System.currentTimeMillis() - start);
		
	}
	
	public static void test(int arr[]) {
		boolean flag = false;
		int temp =0 ;
		for(int i=0;i<arr.length-1;i++) {
			flag = false;
			for(int j=0;j<arr.length-1-i;j++) {
				if(arr[j]>arr[j+1]) {
					flag = false;
					temp = arr[j];
					arr[j]=arr[j+1];
					arr[j+1] = temp;
				}
			}
			if(flag) {
				break;
			}
		}	
	}

	public static void test1(int arr[]) {
		int temp =0 ;
		for(int i=0;i<arr.length-1;i++) {
			for(int j=0;j<arr.length-1-i;j++) {
				if(arr[j]>arr[j+1]) {
					temp = arr[j];
					arr[j]=arr[j+1];
					arr[j+1] = temp;
				}
			}
		}	
	}
}
