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


int main() {
myarrylist a;


}
