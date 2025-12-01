//
// Created by caojianing1 on 2025/12/1.
//顺序表代码
#include <cstdio>
#include <cstdlib>
#define MAXNUMBER 100
 typedef struct sqlist
{int data[MAXNUMBER];


    int number;

}myarrylist;
 myarrylist initlist() {
    myarrylist a;
    a.data=(int *)malloc(MAXNUMBER*sizeof(int));
    if (a.data == NULL) {
        printf("申请失败");
    }
}


int main() {
myarrylist a;

}
