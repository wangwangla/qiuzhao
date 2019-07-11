#include<stdio.h>
void fbnq();
int f[20];
int main()
{
	fbnq();
}

void fbnq(){
	int i=0;
	f[0]=1;
	f[1]=1;
	for(i=2;i<20;i++)
	{
		f[i]=f[i-1]+f[i-2];
	}
	for(i=1;i<=20;i++)
	{
		printf("第%d个数的值为：%d\n",i,f[i-1]);
	}
}
