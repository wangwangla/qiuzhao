/*
*̰���㷨�����������
	�в�ͬ����Ҫ��ĳ��ֵ������ѡ��ֽ�ң���������
*/
#include<stdio.h>
#define MAX 9
int miane[MAX]={10000,5000,2000,1000,500,200,50,20,10};
int min[MAX];
int exchange(int num);
//��ʵ�����ҳ���һ��ֽ�����˶��ٴ�
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
	//�ҳ��������
	for(i=0;i<MAX;i++)
	{
		if(n>miane[i])
			break;
	}

	//�ж��ǲ�������ҵ���
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