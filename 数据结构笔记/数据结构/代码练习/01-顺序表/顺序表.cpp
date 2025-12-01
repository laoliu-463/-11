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
void addlist(myarrylist a,int size,int number)
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
  for (int j=size-1;j>=size;j--) {
   a.data[j+1]=a.data[j];
  }
  a.data[size]=number;
  a.number++;
 }
int findlist(myarrylist a,int b ) {
  for (int i=0;i<a.number-1;i++) {
   if (a.data[i]==b) {
    return i;
   }
   return -1;
  }
 }
myarrylist deletelist(myarrylist a,int b)
{//删除时找到对应位置后对应数据继续后移
  if (b>=a.number||b<=0) {
   printf("删除位置不合法");
  }
  if (a.number==0) {
   printf("空的");
  }
  int c=findlist(a,b);//通过查找操作找到删除位置
  for (int j=c;j<=a.number-2;j++) {
   a.data[j-1]=a.data[j];
  }
  a.number--;
  return a;

 }
myarrylist changelist(myarrylist a,int b) {
  int c=findlist(a,b);
  if (c==-1) {
   printf("没找到");

  }
  for (int j=0;j<=a.number-2;j++){
   a.data[j]=c;
  }
  return a;
 }
void showlist(myarrylist a) {
  if (a.number==0) {
   printf("当前为空表");
  }
  for (int i=0;i<a.number;i++) {
   printf("%d ",a.data[i]);
  }
 }

int main() {
myarrylist a;
  showlist(a);



}
