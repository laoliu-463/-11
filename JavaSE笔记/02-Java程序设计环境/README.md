# Java 程序设计环境（大白话）

这一章讲“怎么把 Java 跑起来”，核心是 JDK 安装、环境变量和基本命令。

---

## 1. 必装：JDK

- 官网下载并安装
- 装好后记住安装路径

---

## 2. 环境变量（面试常问）

常见设置：
- `JAVA_HOME`：指向 JDK 根目录
- `PATH`：加上 `%JAVA_HOME%\bin`

一句话：PATH 让你在任何目录都能敲 `javac` / `java`。

---

## 3. 基本命令（必须会）

```bash
javac Hello.java  # 编译
java Hello        # 运行
jar               # 打包工具
```

---

## 4. classpath 是什么？

JVM 找类的“路径列表”。  
可以用 `-cp` 指定，也可以配环境变量（不推荐全局乱配）。

---

## 5. IDE（选会一种就行）

常见：IntelliJ IDEA、Eclipse。  
IDE 只是帮你写代码，本质还是 JDK 在工作。

---

## 6. 面试高频问答

### Q1：java 文件名必须和类名一致吗？
**答：**
public 类必须一致，普通类无所谓。

### Q2：为什么命令行能直接用 javac？
**答：**
因为 PATH 配了 JDK 的 bin 目录。

---

## 7. 一句话总结

环境装对 + PATH 配对 + 会用 javac/java 就够用。
