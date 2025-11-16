/**
 * 顺序表（Sequence List）Java实现
 * 
 * 顺序表是线性表的顺序存储结构，使用数组实现
 * 特点：随机存取 O(1)，插入删除 O(n)
 * 
 * @author 数据结构练习
 * @version 1.0
 */
public class SqList {
    private static final int MAXSIZE = 100;  // 最大容量
    private int[] data;                      // 存储数据元素的数组
    private int length;                      // 当前长度（实际元素个数）
    
    /**
     * 构造函数：创建一个空的顺序表
     */
    public SqList() {
        this.data = new int[MAXSIZE];
        this.length = 0;
    }
    
    /**
     * 在指定位置插入元素
     * 
     * @param i 插入位置（从1开始）
     * @param e 要插入的元素
     * @return 插入成功返回true，失败返回false
     */
    public boolean insert(int i, int e) {
        // 1. 检查插入位置合法性
        if (i < 1 || i > length + 1) {
            System.out.println("插入位置不合法！位置范围：1 ~ " + (length + 1));
            return false;
        }
        
        // 2. 检查顺序表是否已满
        if (length >= MAXSIZE) {
            System.out.println("顺序表已满，无法插入！");
            return false;
        }
        
        // 3. 将第 i 个位置及之后的元素后移
        for (int j = length; j >= i; j--) {
            data[j] = data[j - 1];
        }
        
        // 4. 插入新元素
        data[i - 1] = e;
        
        // 5. 长度加1
        length++;
        
        return true;
    }
    
    /**
     * 删除指定位置的元素
     * 
     * @param i 删除位置（从1开始）
     * @return 删除的元素值，失败返回null
     */
    public Integer delete(int i) {
        // 1. 检查删除位置合法性
        if (i < 1 || i > length) {
            System.out.println("删除位置不合法！位置范围：1 ~ " + length);
            return null;
        }
        
        // 2. 保存要删除的元素
        int deletedValue = data[i - 1];
        
        // 3. 将第 i+1 个位置及之后的元素前移
        for (int j = i; j < length; j++) {
            data[j - 1] = data[j];
        }
        
        // 4. 长度减1
        length--;
        
        return deletedValue;
    }
    
    /**
     * 按位置查找元素
     * 
     * @param i 查找位置（从1开始）
     * @return 元素值，位置不合法返回null
     */
    public Integer get(int i) {
        if (i < 1 || i > length) {
            System.out.println("查找位置不合法！位置范围：1 ~ " + length);
            return null;
        }
        return data[i - 1];
    }
    
    /**
     * 按值查找元素位置
     * 
     * @param e 要查找的元素值
     * @return 元素位置（从1开始），未找到返回0
     */
    public int locate(int e) {
        for (int i = 0; i < length; i++) {
            if (data[i] == e) {
                return i + 1;  // 返回位置（从1开始）
            }
        }
        return 0;  // 未找到
    }
    
    /**
     * 遍历输出顺序表
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("顺序表为空！");
            return;
        }
        
        System.out.print("顺序表内容：");
        for (int i = 0; i < length; i++) {
            System.out.print(data[i]);
            if (i < length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
    
    /**
     * 判断顺序表是否为空
     * 
     * @return 为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return length == 0;
    }
    
    /**
     * 获取顺序表的当前长度
     * 
     * @return 当前长度
     */
    public int getLength() {
        return length;
    }
    
    /**
     * 获取顺序表的最大容量
     * 
     * @return 最大容量
     */
    public int getMaxSize() {
        return MAXSIZE;
    }
    
    /**
     * 清空顺序表
     */
    public void clear() {
        length = 0;
    }
    
    /**
     * 测试主函数
     */
    public static void main(String[] args) {
        System.out.println("========== 顺序表测试 ==========\n");
        
        // 创建顺序表
        SqList list = new SqList();
        System.out.println("创建顺序表成功！最大容量：" + list.getMaxSize());
        System.out.println("当前长度：" + list.getLength());
        System.out.println();
        
        // 插入元素
        System.out.println("【测试1：插入元素】");
        list.insert(1, 10);
        list.insert(2, 20);
        list.insert(3, 30);
        list.print();  // 输出：顺序表内容：10 20 30
        System.out.println("当前长度：" + list.getLength());
        System.out.println();
        
        // 在中间位置插入
        System.out.println("【测试2：在位置2插入99】");
        list.insert(2, 99);
        list.print();  // 输出：顺序表内容：10 99 20 30
        System.out.println();
        
        // 删除元素
        System.out.println("【测试3：删除位置3的元素】");
        Integer deleted = list.delete(3);
        if (deleted != null) {
            System.out.println("删除的元素：" + deleted);  // 输出：删除的元素：20
        }
        list.print();  // 输出：顺序表内容：10 99 30
        System.out.println();
        
        // 按值查找
        System.out.println("【测试4：查找元素99】");
        int pos = list.locate(99);
        if (pos > 0) {
            System.out.println("元素99的位置：" + pos);  // 输出：元素99的位置：2
        } else {
            System.out.println("未找到元素99");
        }
        System.out.println();
        
        // 按位置查找
        System.out.println("【测试5：查找位置2的元素】");
        Integer elem = list.get(2);
        if (elem != null) {
            System.out.println("位置2的元素：" + elem);  // 输出：位置2的元素：99
        }
        System.out.println();
        
        // 测试边界情况
        System.out.println("【测试6：边界情况测试】");
        System.out.println("顺序表是否为空：" + list.isEmpty());
        System.out.println("当前长度：" + list.getLength());
        
        // 测试错误输入
        System.out.println("\n【测试7：错误输入测试】");
        list.insert(0, 100);      // 位置太小
        list.insert(100, 200);     // 位置太大
        list.delete(0);            // 位置太小
        list.delete(100);          // 位置太大
        
        System.out.println("\n========== 测试完成 ==========");
    }
}

