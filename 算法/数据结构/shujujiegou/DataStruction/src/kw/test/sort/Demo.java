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
		System.out.println("����");
		//quickSort(arr, 0, arr.length-1);
		int temp[] = new int[length];
		mergeSort(arr, 0, length, temp);
		System.out.println("����"+(System.currentTimeMillis() - startTime));
		System.out.println();
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+"  ");
		}
	}
	/**
	 * @param arr
	 * 	ð������˼�룺
	 * 	�ӵ�һ����ʼ�Ƚϣ�ÿ�ν���Ҫѡ�������С�ģ��ŵ����
	 * 	�´��ڴ�ǰ������������ҳ����ٴν��У�ֱ������
	 */
	public static void bubbleSort1(int arr[]) {
		int temp ;
		boolean flag = true;
		//�ȽϵĴ�����arr.length-2��
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
	 * 	ѡ������ÿ��ѡ����С����󣩷��ڵ�һ��λ���ϣ�һ������
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
	 * 	��������
	 * 	��ǰ�濪ʼһ��һ�����룬��������򲿷֣��������һ��һ���Ĳ��뵽��������
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
	 * 	shell���������ײ��������ڽ��н���������Ĵ�С��Ϊ����1/2�Ĵ�С������
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
		//���临��
		t = 0;
		int tempLeft = left;
		while(tempLeft <= right) {
			arr[tempLeft] = temp[t];
			t++;
			tempLeft++;
		}
	}
	
}













