/**
 * 链栈 Java实现
 * 
 * 链栈：使用链表实现的栈
 * 特点：后进先出（LIFO：Last In First Out）、只能在栈顶操作
 * 
 * 优点：
 * - 动态分配，不需要预先分配固定大小
 * - 空间灵活，按需分配，不会浪费空间
 * - 无容量限制，只要内存足够，可以无限增长
 * 
 * 缺点：
 * - 空间开销大（每个节点需要额外的指针空间）
 * - 访问速度慢（需要通过指针访问）
 * - 内存碎片（动态分配可能导致内存碎片）
 * 
 * @author 数据结构练习
 * @version 1.0
 */

// 链栈节点类
class StackNode {
    int data;           // 数据域：存储数据元素
    StackNode next;     // 指针域：指向下一个节点
    
    /**
     * 构造函数：创建节点
     * 
     * @param data 节点存储的数据
     */
    StackNode(int data) {
        this.data = data;
        this.next = null;
    }
}

// 链栈类
public class LinkStack {
    private StackNode top;  // 栈顶指针（指向栈顶节点）
    private int size;       // 栈的大小
    
    /**
     * 构造函数：创建空的链栈
     */
    public LinkStack() {
        this.top = null;  // 空栈，栈顶指针为null
        this.size = 0;
    }
    
    /**
     * 入栈：在栈顶插入新元素
     * 
     * @param element 要插入的元素
     */
    public void push(int element) {
        StackNode newNode = new StackNode(element);
        newNode.next = top;  // 新节点指向原栈顶节点
        top = newNode;       // 栈顶指针指向新节点
        size++;
    }
    
    /**
     * 出栈：删除栈顶元素并返回
     * 
     * @return 栈顶元素，栈空返回null
     */
    public Integer pop() {
        if (isEmpty()) {
            System.out.println("栈为空，无法出栈！");
            return null;  // 栈空，无法出栈
        }
        
        int value = top.data;  // 保存栈顶元素的值
        top = top.next;        // 栈顶指针指向下一个节点
        size--;
        return value;
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
        return top.data;  // 返回栈顶元素的值，top不变
    }
    
    /**
     * 判空：判断栈是否为空
     * 
     * @return 栈空返回true，否则返回false
     */
    public boolean isEmpty() {
        return top == null;  // 栈顶指针为null表示空栈
    }
    
    /**
     * 获取大小：获取栈中元素个数
     * 
     * @return 栈中元素个数
     */
    public int size() {
        return size;  // 直接返回size变量
    }
    
    /**
     * 清空栈：清空栈中所有元素
     */
    public void clear() {
        top = null;  // 将栈顶指针置为null
        size = 0;    // 将大小置为0
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
        StackNode current = top;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" → ");
            }
            current = current.next;
        }
        System.out.println();
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("========== 链栈测试 ==========\n");
        
        // 创建链栈
        LinkStack stack = new LinkStack();
        System.out.println("创建链栈成功！");
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
        
        // 测试继续入栈
        System.out.println("【测试3：继续入栈】");
        stack.push(40);
        stack.push(50);
        stack.push(60);
        stack.print();
        System.out.println("当前大小：" + stack.size());
        System.out.println();
        
        // 测试多次出栈
        System.out.println("【测试4：多次出栈】");
        System.out.println("出栈元素：" + stack.pop());
        System.out.println("出栈元素：" + stack.pop());
        stack.print();
        System.out.println("当前大小：" + stack.size());
        System.out.println();
        
        // 测试清空
        System.out.println("【测试5：清空栈】");
        stack.clear();
        System.out.println("清空后栈是否为空：" + stack.isEmpty());
        System.out.println("当前大小：" + stack.size());
        System.out.println();
        
        // 测试栈空出栈
        System.out.println("【测试6：栈空出栈测试】");
        stack.pop();
        System.out.println();
        
        // 测试动态扩展（链栈无容量限制）
        System.out.println("【测试7：动态扩展测试】");
        for (int i = 1; i <= 10; i++) {
            stack.push(i * 10);
        }
        stack.print();
        System.out.println("当前大小：" + stack.size());
        System.out.println();
        
        System.out.println("========== 链栈测试完成 ==========");
    }
}

