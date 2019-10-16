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
		//�ӵ�һ������һֱ�����
		for(int i = 1;i<arr.length;i++) {
			//����ǰ���ݴ洢
			num = arr[i];
			//���ε�����
			index = i;
			for(int j = i-1;j>=0;j--) {
				if(num>arr[j]) {
					arr[j+1]=arr[j];
					index = j;
				}else{
					break;
				}
			}
			//���ݲ���ʹ������λ����
			arr[index]=num;
		}
		
	}
}
