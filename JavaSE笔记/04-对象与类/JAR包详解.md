# JAR 包详解：Java 的“集装箱”

写完 Java 代码后会生成一堆 `.class` 文件。把它们零散发给别人很麻烦，所以就用 JAR 把这些文件“打包带走”，像把货物装进一个箱子，方便传输和运行。

---

## 1. 什么是 JAR（大白话版）

- 全称：Java Archive
- 本质：一个 **ZIP 压缩包**
- 里面装：`.class`、配置文件、图片、音频等资源

小贴士：把 `.jar` 改成 `.zip` 就能直接解压看到内部结构。

---

## 2. 为啥要用 JAR

- 方便分发：一堆文件变一个包
- 体积更小：压缩后更省空间
- 可执行：配置好后可以直接 `java -jar`
- 可签名：能做安全校验（防止被篡改）

---

## 3. JAR 的核心：MANIFEST.MF

每个 JAR 里都有 `META-INF/MANIFEST.MF`，它是“说明书”，告诉 JVM 入口类是谁。

必须有这一行，才能直接运行：

```text
Main-Class: com.example.MainClass
```

没有它，就会报“没有主清单属性”。

---

## 4. 常用命令（了解就行）

```bash
# 打包
jar cvf myprogram.jar *.class

# 查看内容
jar tvf myprogram.jar

# 解压
jar xvf myprogram.jar

# 运行可执行 JAR
java -jar myprogram.jar
```

参数含义：
- `c` 创建
- `v` 显示过程
- `f` 指定文件名
- `t` 查看
- `x` 解压

---

## 5. 普通 JAR vs 胖 JAR

- 普通 JAR：只打你写的代码
- 胖 JAR（Fat/Uber JAR）：把依赖库也一起打进去

Spring Boot 默认就是胖 JAR，方便“拎包即跑”。

---

## 6. 运行时改配置的常见做法

不推荐直接修改 JAR 内部文件。更常见的是：
- 把配置放在 JAR 同级目录
- 运行时优先读取外部配置

比如 Spring Boot：外部 `application.properties` 会覆盖包内配置。

---

## 7. 面试高频问答（大白话版）

### Q1：JAR 和 WAR 的区别？
**答：**
- JAR：给普通 Java 程序、类库、Spring Boot 用
- WAR：给 Web 服务器（Tomcat）用，包含网页资源和固定目录结构  
一句话：JAR 是通用箱子，WAR 是 Web 专用箱子。

### Q2：运行 JAR 报“没有主清单属性”怎么办？
**答：**
说明 `MANIFEST.MF` 没有写 `Main-Class`。重新打包时补上入口类配置即可。

### Q3：什么是胖 JAR？
**答：**
把自己代码 + 依赖库全部打进一个 JAR，运行时不用再手动带一堆依赖。

### Q4：如何在不改 JAR 的情况下改配置？
**答：**
把配置放在外部，同级目录优先读取。这样不用重新打包，也更安全。

---

## 8. 一句话总结

JAR 就是 Java 程序的“打包箱”，懂它的结构、入口类和依赖方式，部署和面试就稳了。
