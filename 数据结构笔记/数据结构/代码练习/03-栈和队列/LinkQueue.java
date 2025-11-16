/**
 * 链队列 Java实现
 * 
 * 链队列：使用链表实现的队列
 * 特点：先进先出（FIFO：First In First Out）、只能在队尾插入、只能在队头删除
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

// 链队列节点类
class QueueNode {
    int data;           // 数据域：存储数据元素
    QueueNode next;     // 指针域：指向下一个节点
    
    /**
     * 构造函数：创建节点
     * 
     * @param data 节点存储的数据
     */
    QueueNode(int data) {
        this.data = data;
        this.next = null;
    }
}

// 链队列类
public class LinkQueue {
    private QueueNode front;  // 队头指针（指向队头节点）
    private QueueNode rear;   // 队尾指针（指向队尾节点）
    private int size;         // 队列大小
    
    /**
     * 构造函数：创建空的链队列
     */
    public LinkQueue() {
        this.front = null;  // 空队列，队头指针为null
        this.rear = null;   // 空队列，队尾指针为null
        this.size = 0;
    }
    
    /**
     * 入队：在队尾插入新元素
     * 
     * @param element 要插入的元素
     */
    public void enqueue(int element) {
        QueueNode newNode = new QueueNode(element);
        
        if (isEmpty()) {
            // 空队列：新节点既是队头也是队尾
            front = newNode;
            rear = newNode;
        } else {
            // 非空队列：将新节点连接到队尾
            rear.next = newNode;
            rear = newNode;  // 更新队尾指针
        }
        size++;
    }
    
    /**
     * 出队：删除队头元素并返回
     * 
     * @return 队头元素，队列空返回null
     */
    public Integer dequeue() {
        if (isEmpty()) {
            System.out.println("队列为空，无法出队！");
            return null;  // 队列空，无法出队
        }
        
        int value = front.data;  // 保存队头元素的值
        front = front.next;       // 队头指针指向下一个节点
        
        if (front == null) {
            // 如果队列变空，队尾指针也要置为null
            rear = null;
        }
        
        size--;
        return value;
    }
    
    /**
     * 取队头：获取队头元素但不删除
     * 
     * @return 队头元素，队列空返回null
     */
    public Integer front() {
        if (isEmpty()) {
            System.out.println("队列为空，无法取队头！");
            return null;
        }
        return front.data;  // 返回队头元素的值，front不变
    }
    
    /**
     * 取队尾：获取队尾元素但不删除
     * 
     * @return 队尾元素，队列空返回null
     */
    public Integer rear() {
        if (isEmpty()) {
            System.out.println("队列为空，无法取队尾！");
            return null;
        }
        return rear.data;  // 返回队尾元素的值，rear不变
    }
    
    /**
     * 判空：判断队列是否为空
     * 
     * @return 队列空返回true，否则返回false
     */
    public boolean isEmpty() {
        return front == null;  // 队头指针为null表示空队列
    }
    
    /**
     * 获取大小：获取队列中元素个数
     * 
     * @return 队列中元素个数
     */
    public int size() {
        return size;  // 直接返回size变量
    }
    
    /**
     * 清空队列：清空队列中所有元素
     */
    public void clear() {
        front = null;  // 将队头指针置为null
        rear = null;   // 将队尾指针置为null
        size = 0;      // 将大小置为0
    }
    
    /**
     * 遍历输出队列：从队头到队尾输出所有元素
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("队列为空！");
            return;
        }
        
        System.out.print("队列（从队头到队尾）：");
        QueueNode current = front;
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
        System.out.println("========== 链队列测试 ==========\n");
        
        // 创建链队列
        LinkQueue queue = new LinkQueue();
        System.out.println("创建链队列成功！");
        System.out.println("当前大小：" + queue.size());
        System.out.println("队列是否为空：" + queue.isEmpty());
        System.out.println();
        
        // 测试入队
        System.out.println("【测试1：入队操作】");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.print();
        System.out.println("当前大小：" + queue.size());
        System.out.println("队头元素：" + queue.front());
        System.out.println("队尾元素：" + queue.rear());
        System.out.println();
        
        // 测试出队
        System.out.println("【测试2：出队操作】");
        Integer dequeued = queue.dequeue();
        if (dequeued != null) {
            System.out.println("出队元素：" + dequeued);
        }
        queue.print();
        System.out.println("当前大小：" + queue.size());
        System.out.println();
        
        // 测试继续入队
        System.out.println("【测试3：继续入队】");
        queue.enqueue(40);
        queue.enqueue(50);
        queue.enqueue(60);
        queue.print();
        System.out.println("当前大小：" + queue.size());
        System.out.println();
        
        // 测试多次出队
        System.out.println("【测试4：多次出队】");
        System.out.println("出队元素：" + queue.dequeue());
        System.out.println("出队元素：" + queue.dequeue());
        queue.print();
        System.out.println("当前大小：" + queue.size());
        System.out.println();
        
        // 测试清空
        System.out.println("【测试5：清空队列】");
        queue.clear();
        System.out.println("清空后队列是否为空：" + queue.isEmpty());
        System.out.println("当前大小：" + queue.size());
        System.out.println();
        
        // 测试队列空出队
        System.out.println("【测试6：队列空出队测试】");
        queue.dequeue();
        System.out.println();
        
        // 测试动态扩展（链队列无容量限制）
        System.out.println("【测试7：动态扩展测试】");
        for (int i = 1; i <= 10; i++) {
            queue.enqueue(i * 10);
        }
        queue.print();
        System.out.println("当前大小：" + queue.size());
        System.out.println("队头元素：" + queue.front());
        System.out.println("队尾元素：" + queue.rear());
        System.out.println();
        
        System.out.println("========== 链队列测试完成 ==========");
    }
}



