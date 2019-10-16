package kw.test.sort;

public class ShellSort {
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
		for(int g = arr.length/2;g>0;g = g / 2) {
			for(int i = g;i<arr.length;i++) {
				//����ǰ���ݴ洢
				num = arr[i];
				//���ε�����
				index = i;
				for(int j = i-g;j>=0;j=j-g) {
					if(num>arr[j]) {
						arr[j+g]=arr[j];
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
}