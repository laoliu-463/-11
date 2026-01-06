# Java 基本程序设计结构（大白话）

这一章就是“写程序的基本功”：变量、流程控制、数组、方法。

---

## 1. 变量与类型

- 基本类型：byte/short/int/long/float/double/char/boolean
- 引用类型：String、数组、对象

类型转换：
- 小转大自动
- 大转小需要强转，可能丢精度

---

## 2. 运算符速记

- 算术：`+ - * / %`
- 关系：`== != > < >= <=`
- 逻辑：`&& || !`
- 三目：`条件 ? A : B`

---

## 3. 字符串（String）

- 不可变对象
- 常用：`length()`、`substring()`、`equals()`、`split()`

面试点：比较内容用 `equals`，不是 `==`。

---

## 4. 输入输出（简单会用）

```java
Scanner sc = new Scanner(System.in);
int n = sc.nextInt();
```

---

## 5. 流程控制

- if/else
- switch
- for / while / do-while
- break / continue

---

## 6. 数组

```java
int[] a = new int[3];
int[][] b = new int[2][3];
```

数组长度固定，越界就炸。

---

## 7. 方法（函数）

- 定义：返回值 + 方法名 + 参数
- 重载：方法名一样、参数不同

---

## 8. 大数

- `BigInteger`：超大整数
- `BigDecimal`：高精度小数（钱）

---

## 9. 面试高频问答

### Q1：`==` 和 `equals` 区别？
**答：**
`==` 比地址，`equals` 比内容（String 重写过）。

### Q2：数组和 ArrayList 区别？
**答：**
数组定长，ArrayList 可变长。

---

## 10. 一句话总结

变量 + 控制流 + 数组 + 方法，就是 Java 入门四件套。
