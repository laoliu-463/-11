/**
 * 循环队列 Java实现
 * 
 * 循环队列：使用数组实现的队列，通过取模运算实现循环
 * 特点：先进先出（FIFO：First In First Out）、只能在队尾插入、只能在队头删除
 * 
 * 优点：
 * - 访问速度快（数组访问效率高）
 * - 空间效率高（不需要额外的指针空间）
 * - 解决了普通顺序队列的假溢出问题
 * 
 * 缺点：
 * - 容量固定，需要预先分配空间
 * - 需要多分配一个空间来区分空和满的状态
 * - 扩容困难
 * 
 * @author 数据结构练习
 * @version 1.0
 */

public class CircularQueue {
    private int[] data;      // 存储队列元素的数组
    private int front;       // 队头指针（指向队头元素）
    private int rear;        // 队尾指针（指向队尾元素的下一个位置）
    private int capacity;    // 队列的容量（实际分配capacity+1个空间）
    
    /**
     * 构造函数：创建指定容量的循环队列
     * 
     * @param capacity 队列的容量
     */
    public CircularQueue(int capacity) {
        this.capacity = capacity + 1;  // 多分配一个空间用于区分空和满
        this.data = new int[this.capacity];
        this.front = 0;
        this.rear = 0;
    }
    
    /**
     * 构造函数：创建默认容量为10的循环队列
     */
    public CircularQueue() {
        this(10);  // 默认容量为10
    }
    
    /**
     * 入队：在队尾插入新元素
     * 
     * @param element 要插入的元素
     * @return 成功返回true，队列满返回false
     */
    public boolean enqueue(int element) {
        // 检查队列是否已满
        if (isFull()) {
            System.out.println("队列已满，无法入队！");
            return false;  // 队列满，无法入队
        }
        
        // 将元素放入队尾，然后队尾指针循环加1
        data[rear] = element;
        rear = (rear + 1) % capacity;
        return true;
    }
    
    /**
     * 出队：删除队头元素并返回
     * 
     * @return 队头元素，队列空返回null
     */
    public Integer dequeue() {
        // 检查队列是否为空
        if (isEmpty()) {
            System.out.println("队列为空，无法出队！");
            return null;  // 队列空，无法出队
        }
        
        // 保存队头元素，然后队头指针循环加1
        int value = data[front];
        front = (front + 1) % capacity;
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
        return data[front];  // 返回队头元素，front不变
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
        // rear指向队尾元素的下一个位置，所以队尾元素在(rear-1+capacity)%capacity
        return data[(rear - 1 + capacity) % capacity];
    }
    
    /**
     * 判空：判断队列是否为空
     * 
     * @return 队列空返回true，否则返回false
     */
    public boolean isEmpty() {
        return front == rear;  // 队头指针等于队尾指针表示空队列
    }
    
    /**
     * 判满：判断队列是否已满
     * 
     * @return 队列满返回true，否则返回false
     */
    public boolean isFull() {
        return (rear + 1) % capacity == front;  // 队尾指针的下一个位置等于队头指针表示满队列
    }
    
    /**
     * 获取大小：获取队列中元素个数
     * 
     * @return 队列中元素个数
     */
    public int size() {
        return (rear - front + capacity) % capacity;  // 循环计算元素个数
    }
    
    /**
     * 获取容量：获取队列的最大容量
     * 
     * @return 队列的最大容量（实际可用容量为capacity-1）
     */
    public int getCapacity() {
        return capacity - 1;  // 返回实际可用容量
    }
    
    /**
     * 清空队列：清空队列中所有元素
     */
    public void clear() {
        front = 0;  // 将队头指针置为0
        rear = 0;   // 将队尾指针置为0
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
        int current = front;
        int count = 0;
        while (current != rear) {
            System.out.print(data[current]);
            current = (current + 1) % capacity;
            count++;
            if (current != rear) {
                System.out.print(" → ");
            }
        }
        System.out.println();
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("========== 循环队列测试 ==========\n");
        
        // 创建循环队列
        CircularQueue queue = new CircularQueue(5);
        System.out.println("创建循环队列成功！容量：" + queue.getCapacity());
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
        
        // 测试队列满
        System.out.println("【测试3：队列满测试】");
        queue.enqueue(40);
        queue.enqueue(50);
        queue.enqueue(60);  // 尝试超过容量
        queue.print();
        System.out.println("队列是否已满：" + queue.isFull());
        System.out.println();
        
        // 测试循环特性
        System.out.println("【测试4：循环特性测试】");
        queue.dequeue();  // 出队一个，前面有空位
        queue.dequeue();  // 再出队一个
        queue.enqueue(70);  // 入队，应该能利用前面的空位
        queue.print();
        System.out.println("当前大小：" + queue.size());
        System.out.println("队头元素：" + queue.front());
        System.out.println("队尾元素：" + queue.rear());
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
        
        System.out.println("========== 循环队列测试完成 ==========");
    }
}



