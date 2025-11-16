/**
 * 双链表（带头节点）Java实现
 * 
 * 双链表：每个节点包含数据域和两个指针域（前驱指针prev和后继指针next）
 * 特点：
 * 1. 可以双向遍历，访问前驱节点方便
 * 2. 插入和删除操作更方便，不需要找前驱节点
 * 3. 需要维护两个指针，空间开销稍大
 * 
 * 带头节点：头指针指向一个不存储数据的头节点
 * 优点：统一操作，插入删除不需要特殊处理头节点
 * 
 * @author 数据结构练习
 * @version 1.0
 */

// 双链表节点类
class DoubleListNode {
    int data;                    // 数据域：存储数据元素
    DoubleListNode prev;         // 前驱指针：指向前一个节点
    DoubleListNode next;         // 后继指针：指向下一个节点
    
    /**
     * 构造函数：创建节点
     * 
     * @param data 节点存储的数据
     */
    DoubleListNode(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
    
    /**
     * 无参构造函数：创建头节点（数据域可为任意值，通常为0或不使用）
     */
    DoubleListNode() {
        this.data = 0;  // 头节点数据域通常不使用
        this.prev = null;
        this.next = null;
    }
}

// 双链表类（带头节点）
public class DoubleLinkList {
    private DoubleListNode head;  // 头指针（指向头节点，头节点不存储数据）
    private DoubleListNode tail;  // 尾指针（指向最后一个数据节点，方便尾插）
    private int length;           // 链表长度
    
    /**
     * 构造函数：创建一个空的双链表（带头节点）
     */
    public DoubleLinkList() {
        this.head = new DoubleListNode();  // 创建头节点
        this.head.next = null;             // 头节点的next为null表示空链表
        this.head.prev = null;             // 头节点的prev为null
        this.tail = null;                  // 尾指针初始为null
        this.length = 0;
    }
    
    /**
     * 头插法：在链表头部插入新节点（在头节点之后插入）
     * 
     * @param data 要插入的数据
     */
    public void insertAtHead(int data) {
        DoubleListNode newNode = new DoubleListNode(data);
        
        // 如果链表为空
        if (head.next == null) {
            head.next = newNode;
            newNode.prev = head;
            tail = newNode;  // 第一个节点也是尾节点
        } else {
            // 链表不为空
            newNode.next = head.next;
            newNode.prev = head;
            head.next.prev = newNode;
            head.next = newNode;
        }
        length++;
    }
    
    /**
     * 尾插法：在链表尾部插入新节点
     * 
     * @param data 要插入的数据
     */
    public void insertAtTail(int data) {
        DoubleListNode newNode = new DoubleListNode(data);
        
        // 如果链表为空
        if (head.next == null) {
            head.next = newNode;
            newNode.prev = head;
            tail = newNode;
        } else {
            // 链表不为空，利用tail指针快速插入
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        length++;
    }
    
    /**
     * 在指定位置插入新节点
     * 
     * @param i 插入位置（从1开始）
     * @param data 要插入的数据
     * @return 插入成功返回true，失败返回false
     */
    public boolean insert(int i, int data) {
        if (i < 1 || i > length + 1) {
            return false;
        }
        
        // 如果插入位置在尾部，使用尾插法
        if (i == length + 1) {
            insertAtTail(data);
            return true;
        }
        
        DoubleListNode newNode = new DoubleListNode(data);
        
        // 找到第i个节点（要插入位置的前一个节点）
        DoubleListNode current = head.next;
        for (int j = 1; j < i; j++) {
            current = current.next;
        }
        
        // 插入新节点
        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
        
        length++;
        return true;
    }
    
    /**
     * 删除指定位置的节点
     * 
     * @param i 删除位置（从1开始）
     * @return 删除的元素值，失败返回null
     */
    public Integer delete(int i) {
        if (i < 1 || i > length || head.next == null) {
            return null;
        }
        
        // 找到要删除的节点
        DoubleListNode current = head.next;
        for (int j = 1; j < i; j++) {
            current = current.next;
        }
        
        int deletedValue = current.data;
        
        // 更新前驱节点的next指针
        current.prev.next = current.next;
        
        // 更新后继节点的prev指针（如果不是最后一个节点）
        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            // 如果删除的是最后一个节点，更新tail指针
            tail = current.prev;
        }
        
        length--;
        return deletedValue;
    }
    
    /**
     * 按位置查找元素（正向查找）
     * 
     * @param i 查找位置（从1开始）
     * @return 元素值，位置不合法返回null
     */
    public Integer get(int i) {
        if (i < 1 || i > length || head.next == null) {
            return null;
        }
        
        DoubleListNode current = head.next;
        for (int j = 1; j < i; j++) {
            current = current.next;
        }
        return current.data;
    }
    
    /**
     * 按位置查找元素（反向查找，从尾部开始）
     * 
     * @param i 查找位置（从1开始，从尾部算起）
     * @return 元素值，位置不合法返回null
     */
    public Integer getFromTail(int i) {
        if (i < 1 || i > length || tail == null) {
            return null;
        }
        
        DoubleListNode current = tail;
        for (int j = 1; j < i; j++) {
            current = current.prev;
        }
        return current.data;
    }
    
    /**
     * 按值查找元素位置（正向查找）
     * 
     * @param data 要查找的元素值
     * @return 元素位置（从1开始），未找到返回0
     */
    public int locate(int data) {
        DoubleListNode current = head.next;
        int position = 1;
        while (current != null) {
            if (current.data == data) {
                return position;
            }
            current = current.next;
            position++;
        }
        return 0;
    }
    
    /**
     * 正向遍历输出链表
     */
    public void printForward() {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        
        DoubleListNode current = head.next;
        System.out.print("双链表（正向）：");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" ⇄ ");
            }
            current = current.next;
        }
        System.out.println();
    }
    
    /**
     * 反向遍历输出链表
     */
    public void printBackward() {
        if (tail == null) {
            System.out.println("链表为空！");
            return;
        }
        
        DoubleListNode current = tail;
        System.out.print("双链表（反向）：");
        while (current != head) {  // 遍历到head为止（不输出head）
            System.out.print(current.data);
            if (current.prev != head) {
                System.out.print(" ⇄ ");
            }
            current = current.prev;
        }
        System.out.println();
    }
    
    /**
     * 遍历输出链表（默认正向）
     */
    public void print() {
        printForward();
    }
    
    /**
     * 判断链表是否为空
     * 
     * @return 为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return head.next == null;
    }
    
    /**
     * 获取链表的当前长度
     * 
     * @return 当前长度
     */
    public int getLength() {
        return length;
    }
    
    /**
     * 测试主函数
     */
    public static void main(String[] args) {
        System.out.println("========== 双链表测试（带头节点）==========\n");
        
        // 创建链表
        DoubleLinkList list = new DoubleLinkList();
        System.out.println("创建双链表成功！");
        System.out.println("当前长度：" + list.getLength());
        System.out.println("链表是否为空：" + list.isEmpty());
        System.out.println();
        
        // 插入元素
        System.out.println("【测试1：尾插法插入元素】");
        list.insertAtTail(10);
        list.insertAtTail(20);
        list.insertAtTail(30);
        list.printForward();  // 输出：双链表（正向）：10 ⇄ 20 ⇄ 30
        System.out.println("当前长度：" + list.getLength());
        System.out.println();
        
        // 反向遍历
        System.out.println("【测试2：反向遍历】");
        list.printBackward();  // 输出：双链表（反向）：30 ⇄ 20 ⇄ 10
        System.out.println();
        
        // 在指定位置插入
        System.out.println("【测试3：在位置2插入99】");
        list.insert(2, 99);
        list.printForward();  // 输出：双链表（正向）：10 ⇄ 99 ⇄ 20 ⇄ 30
        System.out.println();
        
        // 删除元素
        System.out.println("【测试4：删除位置3的元素】");
        Integer deleted = list.delete(3);
        if (deleted != null) {
            System.out.println("删除的元素：" + deleted);  // 输出：删除的元素：20
        }
        list.printForward();  // 输出：双链表（正向）：10 ⇄ 99 ⇄ 30
        System.out.println();
        
        // 反向查找
        System.out.println("【测试5：反向查找（从尾部第1个元素）】");
        Integer value = list.getFromTail(1);
        if (value != null) {
            System.out.println("从尾部第1个元素：" + value);  // 输出：从尾部第1个元素：30
        }
        System.out.println();
        
        // 测试边界情况
        System.out.println("【测试6：边界情况测试】");
        System.out.println("链表是否为空：" + list.isEmpty());
        System.out.println("当前长度：" + list.getLength());
        
        System.out.println("\n========== 测试完成 ==========");
    }
}






