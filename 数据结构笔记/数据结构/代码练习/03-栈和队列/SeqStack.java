/**
 * 顺序栈 Java实现
 * 
 * 顺序栈：使用数组实现的栈
 * 特点：后进先出（LIFO：Last In First Out）、只能在栈顶操作
 * 
 * 优点：
 * - 访问速度快（数组访问效率高）
 * - 空间效率高（不需要额外的指针空间）
 * - 实现简单
 * 
 * 缺点：
 * - 容量固定，需要预先分配空间
 * - 空间浪费（如果实际使用量远小于容量）
 * - 扩容困难
 * 
 * @author 数据结构练习
 * @version 1.0
 */

public class SeqStack {
    private int[] data;      // 存储栈元素的数组
    private int top;         // 栈顶指针（指向栈顶元素的下一个位置）
    private int capacity;    // 栈的容量
    
    /**
     * 构造函数：创建指定容量的顺序栈
     * 
     * @param capacity 栈的容量
     */
    public SeqStack(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
        this.top = 0;  // 初始时栈顶指针为0，表示空栈
    }
    
    /**
     * 构造函数：创建默认容量为10的顺序栈
     */
    public SeqStack() {
        this(10);  // 默认容量为10
    }
    
    /**
     * 入栈：在栈顶插入新元素
     * 
     * @param element 要插入的元素
     * @return 成功返回true，栈满返回false
     */
    public boolean push(int element) {
        // 检查栈是否已满
        if (isFull()) {
            System.out.println("栈已满，无法入栈！");
            return false;  // 栈满，无法入栈
        }
        
        // 将元素放入栈顶，然后栈顶指针加1
        data[top] = element;
        top++;
        return true;
    }
    
    /**
     * 出栈：删除栈顶元素并返回
     * 
     * @return 栈顶元素，栈空返回null
     */
    public Integer pop() {
        // 检查栈是否为空
        if (isEmpty()) {
            System.out.println("栈为空，无法出栈！");
            return null;  // 栈空，无法出栈
        }
        
        // 栈顶指针减1，然后返回栈顶元素
        top--;
        return data[top];
    }
    
    /**
     * 取栈顶：获取栈顶元素但不删除
     * 
     * @return 栈顶元素，栈空返回null
     */
    public Integer peek() {
        if (isEmpty()) {
            System.out.println("栈为空，无法取栈顶！");
            return null;
        }
        return data[top - 1];  // 返回栈顶元素，top不变
    }
    
    /**
     * 判空：判断栈是否为空
     * 
     * @return 栈空返回true，否则返回false
     */
    public boolean isEmpty() {
        return top == 0;  // 栈顶指针为0表示空栈
    }
    
    /**
     * 判满：判断栈是否已满
     * 
     * @return 栈满返回true，否则返回false
     */
    public boolean isFull() {
        return top >= capacity;  // 栈顶指针等于容量表示栈满
    }
    
    /**
     * 获取大小：获取栈中元素个数
     * 
     * @return 栈中元素个数
     */
    public int size() {
        return top;  // 栈顶指针的值就是栈中元素个数
    }
    
    /**
     * 获取容量：获取栈的最大容量
     * 
     * @return 栈的最大容量
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * 清空栈：清空栈中所有元素
     */
    public void clear() {
        top = 0;  // 将栈顶指针置为0即可
    }
    
    /**
     * 遍历输出栈：从栈顶到栈底输出所有元素
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("栈为空！");
            return;
        }
        
        System.out.print("栈（从栈顶到栈底）：");
        // 从栈顶到栈底遍历
        for (int i = top - 1; i >= 0; i--) {
            System.out.print(data[i]);
            if (i > 0) {
                System.out.print(" → ");
            }
        }
        System.out.println();
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("========== 顺序栈测试 ==========\n");
        
        // 创建顺序栈
        SeqStack stack = new SeqStack(5);
        System.out.println("创建顺序栈成功！容量：" + stack.getCapacity());
        System.out.println("当前大小：" + stack.size());
        System.out.println("栈是否为空：" + stack.isEmpty());
        System.out.println();
        
        // 测试入栈
        System.out.println("【测试1：入栈操作】");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.print();
        System.out.println("当前大小：" + stack.size());
        System.out.println("栈顶元素：" + stack.peek());
        System.out.println();
        
        // 测试出栈
        System.out.println("【测试2：出栈操作】");
        Integer popped = stack.pop();
        if (popped != null) {
            System.out.println("出栈元素：" + popped);
        }
        stack.print();
        System.out.println("当前大小：" + stack.size());
        System.out.println();
        
        // 测试栈满
        System.out.println("【测试3：栈满测试】");
        stack.push(40);
        stack.push(50);
        stack.push(60);  // 尝试超过容量
        stack.print();
        System.out.println("栈是否已满：" + stack.isFull());
        System.out.println();
        
        // 测试清空
        System.out.println("【测试4：清空栈】");
        stack.clear();
        System.out.println("清空后栈是否为空：" + stack.isEmpty());
        System.out.println("当前大小：" + stack.size());
        System.out.println();
        
        // 测试栈空出栈
        System.out.println("【测试5：栈空出栈测试】");
        stack.pop();
        System.out.println();
        
        System.out.println("========== 顺序栈测试完成 ==========");
    }
}

