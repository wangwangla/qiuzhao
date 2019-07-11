/*
*贪婪算法求解找零问题
	有不同的面额，要找某个值，怎样选择纸币，进行找零
*/
#include<stdio.h>
#define MAX 9
int miane[MAX]={10000,5000,2000,1000,500,200,50,20,10};
int min[MAX];
int exchange(int num);
//其实就是找出哪一个纸币用了多少次
int main()
{
	int i;
	float m;
	int nu;
	scanf("%f",&m);
	nu=exchange((int(m*100)));
	for(i=0;i<MAX;i++)
	{
		if(min[i]>0)
		printf("%d  %d\n",min[i],miane[i]);
	}


}
int exchange(int n){
	int i,j;
	//找出最大的面额
	for(i=0;i<MAX;i++)
	{
		if(n>miane[i])
			break;
	}

	//判断是不是真的找到了
	while(n>0&&i<MAX){
		if(n>=miane[i])
		{
			n-=miane[i];
			min[i]++;
		}else
			i++;
	}
return 0;
}