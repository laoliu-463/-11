-- ================================================
-- SPJ 数据库完整脚本（建表 + 测试数据）
-- 用于数据库复习练习
-- ================================================

-- 1. 创建供应商表 S
CREATE TABLE S (
    SNO CHAR(10) PRIMARY KEY,
    SNAME CHAR(12),
    STATUS CHAR(2),
    CITY CHAR(8)
);

-- 2. 创建零件表 P
CREATE TABLE P (
    PNO CHAR(10) PRIMARY KEY,
    PNAME CHAR(12),
    COLOR CHAR(5),
    WEIGHT INT
);

-- 3. 创建工程表 J
CREATE TABLE J (
    JNO CHAR(10) PRIMARY KEY,
    JNAME CHAR(10),
    CITY CHAR(8)
);

-- 4. 创建供应情况表 SPJ
CREATE TABLE SPJ (
    SNO CHAR(10),
    PNO CHAR(10),
    JNO CHAR(10),
    QTY INT,
    PRIMARY KEY (SNO, PNO, JNO),
    FOREIGN KEY (SNO) REFERENCES S(SNO),
    FOREIGN KEY (PNO) REFERENCES P(PNO),
    FOREIGN KEY (JNO) REFERENCES J(JNO)
);

-- ================================================
-- 插入测试数据
-- ================================================

-- 供应商数据
INSERT INTO S VALUES ('S1', '精益', '20', '天津');
INSERT INTO S VALUES ('S2', '盛锡', '10', '北京');
INSERT INTO S VALUES ('S3', '东方红', '30', '北京');
INSERT INTO S VALUES ('S4', '丰泰盛', '20', '天津');
INSERT INTO S VALUES ('S5', '为民', '30', '上海');
INSERT INTO S VALUES ('S6', '华盛', '10', '上海');
INSERT INTO S VALUES ('S7', '立达', '20', '广州');

-- 零件数据
INSERT INTO P VALUES ('P1', '螺母', '红', 12);
INSERT INTO P VALUES ('P2', '螺栓', '绿', 17);
INSERT INTO P VALUES ('P3', '螺丝刀', '蓝', 14);
INSERT INTO P VALUES ('P4', '螺丝刀', '红', 14);
INSERT INTO P VALUES ('P5', '凸轮', '蓝', 40);
INSERT INTO P VALUES ('P6', '齿轮', '红', 60);
INSERT INTO P VALUES ('P7', '轴承', '白', 55);

-- 工程数据
INSERT INTO J VALUES ('J1', '三建', '北京');
INSERT INTO J VALUES ('J2', '一汽', '长春');
INSERT INTO J VALUES ('J3', '弹簧厂', '天津');
INSERT INTO J VALUES ('J4', '造船厂', '天津');
INSERT INTO J VALUES ('J5', '机车厂', '唐山');
INSERT INTO J VALUES ('J6', '无线电厂', '常州');
INSERT INTO J VALUES ('J7', '半导体厂', '南京');

-- 供应情况数据
INSERT INTO SPJ VALUES ('S1', 'P1', 'J1', 200);
INSERT INTO SPJ VALUES ('S1', 'P1', 'J3', 100);
INSERT INTO SPJ VALUES ('S1', 'P1', 'J4', 700);
INSERT INTO SPJ VALUES ('S1', 'P2', 'J2', 100);
INSERT INTO SPJ VALUES ('S2', 'P3', 'J1', 400);
INSERT INTO SPJ VALUES ('S2', 'P3', 'J2', 200);
INSERT INTO SPJ VALUES ('S2', 'P3', 'J4', 500);
INSERT INTO SPJ VALUES ('S2', 'P3', 'J5', 400);
INSERT INTO SPJ VALUES ('S2', 'P5', 'J1', 400);
INSERT INTO SPJ VALUES ('S2', 'P5', 'J2', 100);
INSERT INTO SPJ VALUES ('S3', 'P1', 'J1', 200);
INSERT INTO SPJ VALUES ('S3', 'P3', 'J1', 200);
INSERT INTO SPJ VALUES ('S4', 'P5', 'J1', 100);
INSERT INTO SPJ VALUES ('S4', 'P6', 'J3', 300);
INSERT INTO SPJ VALUES ('S4', 'P6', 'J4', 200);
INSERT INTO SPJ VALUES ('S5', 'P2', 'J4', 100);
INSERT INTO SPJ VALUES ('S5', 'P3', 'J1', 200);
INSERT INTO SPJ VALUES ('S5', 'P6', 'J2', 200);
INSERT INTO SPJ VALUES ('S5', 'P6', 'J4', 500);

-- ================================================
-- 数据验证查询
-- ================================================

-- 查看所有供应商
SELECT * FROM S;

-- 查看所有零件
SELECT * FROM P;

-- 查看所有工程
SELECT * FROM J;

-- 查看所有供应情况
SELECT * FROM SPJ;

-- ================================================
-- 数据统计
-- ================================================
-- S表：7个供应商
-- P表：7种零件
-- J表：7个工程
-- SPJ表：19条供应记录
