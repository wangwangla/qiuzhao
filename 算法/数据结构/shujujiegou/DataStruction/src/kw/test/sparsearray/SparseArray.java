package kw.test.sparsearray;
/**
 * 	稀疏数组存储二维数组，以及二维数组的恢复
 * @author Administrator
 */
public class SparseArray {
	public static void main(String[] args) {
		int arr [][] = new int[11][11];
		arr[1][4]= 1;
		arr[3][5]=2;
		for(int a[] : arr) {
			for(int data : a) {
				System.out.print(data+"  ");
			}
			System.out.println();
		}
		System.out.println("===================");
		
		int sum = 0;
		for(int i=0;i<11;i++) {
			for(int j = 0;j<11;j++) {
				if(arr[i][j]!=0)
					sum +=1;
			}
		}
		int sparseArr[][] = new int[sum+1][3];
		sparseArr[0][0]=11;
		sparseArr[0][1]=11;
		sparseArr[0][2]=sum;
		int count = 0;
		for(int i=0;i<11;i++) {
			for(int j = 0;j<11;j++) {
				if(arr[i][j]!=0){
				count++;
				sparseArr[count][0] = i;
				sparseArr[count][1] = j;
				sparseArr[count][2] = arr[i][j];
				}
			}
		}
		
		for(int a[] : sparseArr) {
			for(int data : a) {
				System.out.print(data+"  ");
			}
			System.out.println();
		}
		//读取
		int h = sparseArr[0][0];
		int w = sparseArr[0][1];
		int arr1[][] = new int[h][w];
		int x=0,y=0;
		for(int i=1;i<sparseArr.length;i++) {
			x = sparseArr[i][0];
			y = sparseArr[i][1];
			arr1[x][y] = sparseArr[i][2];			
		}
		System.out.println("===================");
		
		for(int a[] : arr1) {
			for(int data : a) {
				System.out.print(data+"  ");
			}
			System.out.println();
		}
	}
}
