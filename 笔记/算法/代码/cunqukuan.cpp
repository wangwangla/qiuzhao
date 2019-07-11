/*
* 题目：
	一共四年，一次存入，利息1.71%，一月取1000，需要存入多少才够用。
	逆向想，最后一个月是1000.

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
		printf("第%d月有%f钱\n",i+1,month[i]);
	}
}
