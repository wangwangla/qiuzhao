package kw.test.find;

public class Demo {
	public static void main(String[] args) {
		int arr[] = {1,2,5,7,32};
		System.out.println(find(arr,2));
	}
	
	public static int erfen(int arr[],int key){
		int le = 0;
		int mid = 0;
		int ri = 0;
		while(true) {
			if(arr[mid]==key) {
				return mid;
			}
			if(arr[mid]>key) {
				ri = mid-1;
				mid = (ri+le)/2;
			}
			if(arr[mid]<key) {
				le = mid+1;
				mid = (ri+le)/2;
			}
			if(le>=ri) {
				break;
			}
		}
		return -1;
	}
	
	
	
	public static int find(int arr[],int key){
		for(int i = 0;i < arr.length;i++) {
			if(arr[i]==key) {
				return i;
			}
		}
		return -1;
	}
}
