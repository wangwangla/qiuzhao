/*
* ��Ŀ��
	һ�����꣬һ�δ��룬��Ϣ1.71%��һ��ȡ1000����Ҫ������ٲŹ��á�
	�����룬���һ������1000.

*/
#include<stdio.h>
void cun();
int main()
{
 cun();
}
void cun(){
	double month[48];
	int i=0;
	month[0]=1000;
	for(i=1;i<48;i++){
		month[i]=(month[i-1]+1000)/(1+0.0171/12);
	}
	for(i=0;i<48;i++){
		printf("��%d����%fǮ\n",i+1,month[i]);
	}
}
