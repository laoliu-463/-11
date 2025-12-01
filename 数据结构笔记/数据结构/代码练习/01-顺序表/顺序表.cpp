//
// Created by caojianing1 on 2025/12/1.
//顺序表代码
#include <cstdio>
#include <cstdlib>
#define MAXNUMBER 100
 typedef struct sqlist

{int data[MAXNUMBER];

//int *data;//指针开动态数组
    int number;

}myarrylist;
 myarrylist initlist() {
    myarrylist a;

    if (a.data == NULL) {
        printf("申请失败");
        //分配的数组里会有脏数据
    }
     a.number=0;
     return a;
}
myarrylist finallist(myarrylist a,int b)
//在末尾插入
{if (a.number=MAXNUMBER) {
 printf("满了");


}
  a.data[a.number]=b;
  a.number++;
  return a;

 }
void addlist(myarrylist a,int size)
//在任意位置插入
{  if (size<=0||size>MAXNUMBER)
{
 printf("插入位置不合法");
}
  //数据向后移动
  //必须倒着循环,否则后面数据都会变成一样的
  if (a.number==MAXNUMBER) {
   printf("表满了");
  }
  for (int j,j) {
   data[j+1]=data[j];
  }


 }


int main() {
myarrylist a;


}
