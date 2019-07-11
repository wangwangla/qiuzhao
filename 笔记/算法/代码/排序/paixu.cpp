// paixu.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

void show(int a[],int n){
	int i = 0;
	for(i=0 ; i<n ;i++){
		printf("%d ",a[i]);
	}
	printf("\n");
}
/*
寻找排序
	每次选择最小的，放在开始的位置

  第一个数字和后面的比较，如果后面有更小的就将更小的放在前面
*/
void xuanze(int a[],int n)
{
	int i = 0,j = 0 ,t = 0 , k = 0;
	for(;i<n-1;i++)
	{
		for(j = i + 1 ; j < n ; j++){
			if(a[i]>a[j])
			{
				t = a[i];
				a[i] = a[j];
				a[j] = t;
			}
		}
	}
}


void xuanze2(int a[],int n)
{
	int i = 0,j = 0 ,t = 0 , k = 0;
	for(;i<n-1;i++)
	{
		k = i;
		for(j = i + 1 ; j < n  ; j++){
			if(a[k]>a[j])
				k = j;
			}	
				t = a[i];
				a[i] = a[k];
				a[k] = t;	
	}
}



/*
冒泡算法
*/
void maopao(int a[],int n){
	int i = 0,j = 0;
	int temp = 0 ;
		show(a,7);
	for(i=0;i<n;i++) //n个数据比较n-1次
	{
		for(j=1;j<n+1-i;j++) //每次选择出最后一个数字，所以循环向前走
		{
			if(a[j-1]>a[j])  //如果前一个大于后一个，那就交换，所以结果是挑选出最大的
			{
				temp = a[j-1];
				a[j-1] = a[j];
				a[j] = temp;
			}
		}
		show(a,7);
	}
}

int main(int argc, char* argv[])
{
	int arr[] = {12,53,13,21,24,64,23};
	//maopao(arr,6);
	//xuanze(arr,7);
	xuanze2(arr,7);
	show(arr,7);
	printf("Hello World!\n");

	return 0;
}

