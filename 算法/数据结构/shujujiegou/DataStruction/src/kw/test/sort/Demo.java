package kw.test.sort;

public class Demo {
	public static void main(String[] args) {
		int length = 8;
		int arr[] = new int[length];
		for(int i=0;i<length;i++) {
			arr[i] = (int)(Math.random()*8000);
		}
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+"  ");
		}
		long startTime = System.currentTimeMillis();
		System.out.println("排序！");
		//quickSort(arr, 0, arr.length-1);
		int temp[] = new int[length];
		mergeSort(arr, 0, length, temp);
		System.out.println("排序！"+(System.currentTimeMillis() - startTime));
		System.out.println();
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+"  ");
		}
	}
	/**
	 * @param arr
	 * 	冒泡排序思想：
	 * 	从第一个开始比较，每次将需要选择最大（最小的）放到最后
	 * 	下次在从前面的数据里面找出，再次进行，直到结束
	 */
	public static void bubbleSort1(int arr[]) {
		int temp ;
		boolean flag = true;
		//比较的次数是arr.length-2次
		for(int i=0;i<arr.length-1;i++) {
			flag = true;
			for(int j = 0;j<arr.length-1-i;j++) {
				if(arr[j+1]>arr[j]) {
					flag = false;
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
				if(flag) {
					break;
				}
			}
		}
	}
	
	/**
	 * @param arr
	 * 	选择排序，每次选择最小（最大）放在第一个位置上，一次类推
	 */
	public static void bubbleSort2(int arr[]) {
		int min = 0;
		int temp  =0;
		for(int i=0;i<arr.length-1;i++) {
			min = i;
			for(int j = i;j<arr.length;j++) {
				if(arr[min]>arr[j]) {
					min = j;
				}
			}
			if(min != i) {
				temp = arr[i];
				arr[i]=arr[min];
				arr[min]=temp;	
			}
		}
	}
	
	/**
	 * @param arr
	 * 	插入排序
	 * 	从前面开始一个一个插入，有序和无序部分，将无序的一个一个的插入到有序里面
	 */
	public static void bubbleSort4(int arr[]) {
		int temp ; 
		int index;
		for(int j = 1;j<arr.length;j++) {
			temp = arr[j];
			index = j;
			for(int z = j-1;z >= 0;z=z-1) {
				if(temp<arr[z]) {
					arr[z+1]=arr[z];
					index = z;
				}
			}
			arr[index] = temp;
		}
	}
	
	/**
	 * @param arr
	 * 	shell排序，先明白插入排序，在进行将插入排序的大小变为数组1/2的大小，进行
	 */
	public static void bubbleSort3(int arr[]) {
		int temp ; 
		int index;
		for(int i= arr.length/2;i>0;i=i/2) {
			for(int j = i;j<arr.length;j++) {
				temp = arr[j];
				index = j;
				for(int z = j-i;z >= 0;z=z-i) {
					if(temp<arr[z]) {
						arr[z+i]=arr[z];
						index = z;
					}
				}
				arr[index] = temp;
			}
		}
	}
	public static void quickSort(int arr[],int left,int right) {
		int l = left;
		int r = right;
		int mid = (left+right)/2;
		int temp=0;
		int povi = arr[mid];
		while(l<r) {
			while(arr[l]<povi) {
				l+=1;
			}
			while(arr[r]>povi) {
				r--;
			}
			if(l>=r) {
				break;
			}
			temp = arr[l];
			arr[l]=arr[r];
			arr[r]=temp;
			if(arr[l]==povi) {
				r++;
			}
			if(arr[r]==povi) {
				l--;
			}
		}
		if(l==r) {
			l++;
			r--;
		}
		if(left<r) {
			quickSort(arr, left, r);
		}
		if(right>l) {
			quickSort(arr, l, right);
		}
	}
	
	
	
	
	
	
	public static void mergeSort(int arr[],int left,int right,int []temp) {
		if(left<right) {
			int mid = (left+right)/2;
			mergeSort(arr, left, mid, temp);
			mergeSort(arr, mid, right, temp);
			merge(arr, left, mid, right, temp);
		}
	}
	public static void merge(int arr[],int left,int mid,int right,int temp[]) {
		int i = left;
		int j = mid + 1;
		int t = 0;
		while(i<=mid&&j<=right) {
			if(arr[i]>arr[j]) {
				temp[t]=arr[j];
				t++;
				j++;
			}else {
				temp[t]=arr[i];
				t++;
				i++;
			}
		}
		while(i<=mid) {
			temp[t] = arr[i];
			t++;
			i++;
		}
		
		while(j<=right) {
			temp[t] = arr[j];
			t++;
			j++;
		}
		//将其复制
		t = 0;
		int tempLeft = left;
		while(tempLeft <= right) {
			arr[tempLeft] = temp[t];
			t++;
			tempLeft++;
		}
	}
	
}













