# SQL与关系代数结合练习题

> 每道题同时练习：关系代数表达式 + SQL语句

## SPJ数据库结构

| 表名 | 字段 | 说明 |
|------|------|------|
| S | SNO, SNAME, STATUS, CITY | 供应商 |
| P | PNO, PNAME, COLOR, WEIGHT | 零件 |
| J | JNO, JNAME, CITY | 工程 |
| SPJ | SNO, PNO, JNO, QTY | 供应情况 |

---

## 一、单表查询（6题）

### 1. 查询所有供应商的姓名和所在城市
- 关系代数：
- SQL：

### 2. 查询重量大于50g的所有红色零件的名称
- 关系代数：
- SQL：

### 3. 查询工程编号以"J210"开头的所有工程的工程号和所在城市
- 关系代数：
- SQL：

### 4. 查询上海供应商的数量
- 关系代数：
- SQL：

### 5. 查询每个城市的工程数量
- 关系代数：
- SQL：

### 6. 查询使用3种以上零件的工程号
- 关系代数：
- SQL：

---

## 二、多表连接查询（5题）

### 7. 查询使用供应商S1所供应零件的工程号码
- 关系代数：
- SQL：

### 8. 查询工程J2使用的各种零件的名称及其数量，按数量降序排序
- 关系代数：
- SQL：

### 9. 查询上海厂商供应的所有零件编号
- 关系代数：
- SQL：

### 10. 查询使用上海产的零件的工程名称
- 关系代数：
- SQL：

### 11. 查询每项工程使用的零件总数，给出工程名和零件的总数
- 关系代数：
- SQL：

---

## 三、差集查询（2题）

### 12. 查询没给任何工程供应零件的设备供应商号
- 关系代数：
- SQL：

### 13. 查询没给任何工程项目供应零件的供应商名
- 关系代数：
- SQL：

---

## 四、关系代数表达式查询（4题）

### 14. 检索颜色为红色且重量超过50克的零件的零件名
- 关系代数：

### 15. 检索项目"三建"使用的零件编号和零件名
- 关系代数：

### 16. 查询没给任何工程项目供应零件的供应商名
- 关系代数：

### 17. 查询至少使用两种以上零件的工程项目号
- 关系代数：

---

## 答案区（先做题再看）

<details>
<summary>点击展开答案</summary>

### 一、单表查询

**1.**
- 关系代数：π<sub>SNAME,CITY</sub>(S)
- SQL：`SELECT SNAME, CITY FROM S`

**2.**
- 关系代数：π<sub>PNAME</sub>(σ<sub>COLOR='红' ∧ WEIGHT>50</sub>(P))
- SQL：`SELECT PNAME FROM P WHERE COLOR = '红' AND WEIGHT > 50`

**3.**
- 关系代数：π<sub>JNO,CITY</sub>(σ<sub>JNO LIKE 'J210%'</sub>(J))
- SQL：`SELECT JNO, CITY FROM J WHERE JNO LIKE 'J210%'`

**4.**
- 关系代数：γ<sub>COUNT(*)</sub>(σ<sub>CITY='上海'</sub>(S))
- SQL：`SELECT COUNT(*) FROM S WHERE CITY = '上海'`

**5.**
- 关系代数：γ<sub>CITY, COUNT(*)</sub>(J)
- SQL：`SELECT CITY, COUNT(*) FROM J GROUP BY CITY`

**6.**
- 关系代数：π<sub>JNO</sub>(σ<sub>cnt>3</sub>(γ<sub>JNO, COUNT(DISTINCT PNO)→cnt</sub>(SPJ)))
- SQL：`SELECT JNO FROM SPJ GROUP BY JNO HAVING COUNT(DISTINCT PNO) > 3`

---

### 二、多表连接查询

**7.**
- 关系代数：π<sub>JNO</sub>(σ<sub>SNO='S1'</sub>(SPJ))
- SQL：`SELECT JNO FROM SPJ WHERE SNO = 'S1'`

**8.**
- 关系代数：π<sub>PNAME,QTY</sub>(P ⋈ σ<sub>JNO='J2'</sub>(SPJ))
- SQL：`SELECT PNAME, QTY FROM P JOIN SPJ ON P.PNO = SPJ.PNO WHERE JNO = 'J2' ORDER BY QTY DESC`

**9.**
- 关系代数：π<sub>PNO</sub>(SPJ ⋈ σ<sub>CITY='上海'</sub>(S))
- SQL：`SELECT PNO FROM SPJ WHERE SNO IN (SELECT SNO FROM S WHERE CITY = '上海')`

**10.**
- 关系代数：π<sub>JNAME</sub>(J ⋈ π<sub>JNO</sub>(SPJ ⋈ σ<sub>CITY='上海'</sub>(S)))
- SQL：`SELECT JNAME FROM J WHERE JNO IN (SELECT JNO FROM SPJ WHERE SNO IN (SELECT SNO FROM S WHERE CITY = '上海'))`

**11.**
- 关系代数：π<sub>JNAME,SUM(QTY)</sub>(J ⋈ γ<sub>JNO, SUM(QTY)</sub>(SPJ))
- SQL：`SELECT JNAME, SUM(QTY) FROM J JOIN SPJ ON J.JNO = SPJ.JNO GROUP BY JNAME`

---

### 三、差集查询

**12.**
- 关系代数：π<sub>SNO</sub>(S) − π<sub>SNO</sub>(SPJ)
- SQL：`SELECT SNO FROM S WHERE SNO NOT IN (SELECT SNO FROM SPJ)`

**13.**
- 关系代数：π<sub>SNAME</sub>(S) − π<sub>SNAME</sub>(S ⋈ SPJ)
- SQL：`SELECT SNAME FROM S WHERE SNO NOT IN (SELECT SNO FROM SPJ)`

---

### 四、关系代数表达式查询

**14.** π<sub>PNAME</sub>(σ<sub>COLOR='红' ∧ WEIGHT>50</sub>(P))

**15.** π<sub>PNO,PNAME</sub>(P ⋈ (π<sub>PNO</sub>(SPJ ⋈ σ<sub>JNAME='三建'</sub>(J))))

**16.** π<sub>SNAME</sub>(S) − π<sub>SNAME</sub>(S ⋈ SPJ)

**17.** π<sub>JNO</sub>(σ<sub>cnt≥2</sub>(γ<sub>JNO, COUNT(PNO)→cnt</sub>(SPJ)))

</details>

---

## 练习进度

| 题号 | 完成情况 |
|------|----------|
| 1-6 | [ ] |
| 7-11 | [ ] |
| 12-13 | [ ] |
| 14-17 | [ ] |
