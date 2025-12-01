#include <stdio.h>
#include <stdlib.h>

#define MAXSIZE 100
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0

typedef int ElemType;
typedef int Status;

typedef struct {
    ElemType data[MAXSIZE];
    int length;
} SqList;

// 初始化
Status InitList(SqList *L) {
    L->length = 0;
    return OK;
}

// 插入
Status ListInsert(SqList *L, int i, ElemType e) {
    if (L->length >= MAXSIZE) return ERROR;
    if (i < 1 || i > L->length + 1) return ERROR;
    for (int j = L->length; j >= i; j--) {
        L->data[j] = L->data[j-1];
    }
    L->data[i-1] = e;
    L->length++;
    return OK;
}

// 删除
Status ListDelete(SqList *L, int i, ElemType *e) {
    if (L->length == 0) return ERROR;
    if (i < 1 || i > L->length) return ERROR;
    *e = L->data[i-1];
    for (int j = i; j < L->length; j++) {
        L->data[j-1] = L->data[j];
    }
    L->length--;
    return OK;
}

// 按位置查找
Status GetElem(SqList L, int i, ElemType *e) {
    if (L.length == 0 || i < 1 || i > L.length) return ERROR;
    *e = L.data[i-1];
    return OK;
}

// 按值查找
int LocateElem(SqList L, ElemType e) {
    for (int i = 0; i < L.length; i++) {
        if (L.data[i] == e) return i+1;
    }
    return 0;
}

// 遍历输出
void PrintList(SqList L) {
    printf("顺序表：");
    for (int i = 0; i < L.length; i++) {
        printf("%d ", L.data[i]);
    }
    printf("\n");
}

int main() {
    SqList L;
    ElemType e;
    int i;

    printf("=== 顺序表测试开始 (C语言版) ===\n");

    // 初始化
    InitList(&L);

    // 插入
    printf("1. 插入 10, 20, 30\n");
    ListInsert(&L, 1, 10);
    ListInsert(&L, 2, 20);
    ListInsert(&L, 3, 30);
    PrintList(L);

    // 在位置2插入
    printf("2. 在位置2插入 99\n");
    ListInsert(&L, 2, 99);
    PrintList(L);

    // 删除
    printf("3. 删除位置3的元素\n");
    if (ListDelete(&L, 3, &e)) {
        printf("删除的元素：%d\n", e);
    } else {
        printf("删除失败\n");
    }
    PrintList(L);

    // 查找
    printf("4. 查找元素 99 的位置\n");
    i = LocateElem(L, 99);
    printf("元素99的位置：%d\n", i);

    // 获取
    printf("5. 获取位置2的元素\n");
    if (GetElem(L, 2, &e)) {
        printf("位置2的元素：%d\n", e);
    } else {
        printf("获取失败\n");
    }

    printf("=== 顺序表测试结束 ===\n");
    return 0;
}
