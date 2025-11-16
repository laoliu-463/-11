/**
 * 单链表（不带头节点）Java实现
 * 
 * 单链表是线性表的链式存储结构，使用节点和指针实现
 * 特点：动态分配、插入删除快 O(1)，但访问慢 O(n)
 * 
 * 不带头节点：头指针直接指向第一个数据节点
 * 
 * @author 数据结构练习
 * @version 1.0
 */

// 节点类
class ListNode {
    int data;           // 数据域：存储数据元素
    ListNode next;      // 指针域：指向下一个节点
    
    /**
     * 构造函数：创建节点
     * 
     * @param data 节点存储的数据
     */
    ListNode(int data) {
        this.data = data;
        this.next = null;
    }
}

// 单链表类（不带头节点）
public class LinkListWithoutHead {
    private ListNode head;  // 头指针（直接指向第一个数据节点，空链表时为null）
    private int length;     // 链表长度
    
    /**
     * 构造函数：创建一个空的单链表（不带头节点）
     */
    public LinkListWithoutHead() {
        this.head = null;   // 空链表，头指针为null
        this.length = 0;
    }
    
    /**
     * 头插法：在链表头部插入新节点
     * 
     * @param data 要插入的数据
     */
    public void insertAtHead(int data) {
        ListNode newNode = new ListNode(data);
        newNode.next = head;
        head = newNode;
        length++;
    }
    
    /**
     * 尾插法：在链表尾部插入新节点
     * 
     * @param data 要插入的数据
     */
    public void insertAtTail(int data) {
        ListNode newNode = new ListNode(data);
        
        // 不带头节点：需要特殊处理空链表情况
        if (head == null) {
            head = newNode;
        } else {
            ListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
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
        
        ListNode newNode = new ListNode(data);
        // 不带头节点：插入位置1需要特殊处理
        if (i == 1) {
            newNode.next = head;
            head = newNode;
        } else {
            ListNode prev = head;
            for (int j = 1; j < i - 1; j++) {
                prev = prev.next;
            }
            newNode.next = prev.next;
            prev.next = newNode;
        }
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
        if (i < 1 || i > length || head == null) {
            return null;
        }
        
        int deletedValue;
        // 不带头节点：删除位置1需要特殊处理
        if (i == 1) {
            deletedValue = head.data;
            head = head.next;
        } else {
            ListNode prev = head;
            for (int j = 1; j < i - 1; j++) {
                prev = prev.next;
            }
            deletedValue = prev.next.data;
            prev.next = prev.next.next;
        }
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
        if (i < 1 || i > length || head == null) {
            return null;
        }
        
        ListNode current = head;
        for (int j = 1; j < i; j++) {
            current = current.next;
        }
        return current.data;
    }
    
    /**
     * 按值查找元素位置
     * 
     * @param data 要查找的元素值
     * @return 元素位置（从1开始），未找到返回0
     */
    public int locate(int data) {
        ListNode current = head;
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
     * 遍历输出链表
     */
    public void print() {
        if (head == null) {
            System.out.println("链表为空！");
            return;
        }
        
        ListNode current = head;
        System.out.print("链表内容（不带头节点）：");
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
     * 判断链表是否为空
     * 
     * @return 为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return head == null;
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
        System.out.println("========== 单链表测试（不带头节点）==========\n");
        
        // 创建链表
        LinkListWithoutHead list = new LinkListWithoutHead();
        System.out.println("创建链表成功！");
        System.out.println("当前长度：" + list.getLength());
        System.out.println();
        
        // 插入元素
        System.out.println("【测试1：尾插法插入元素】");
        list.insertAtTail(10);
        list.insertAtTail(20);
        list.insertAtTail(30);
        list.print();  // 输出：链表内容：10 → 20 → 30
        System.out.println("当前长度：" + list.getLength());
        System.out.println();
        
        // 在指定位置插入
        System.out.println("【测试2：在位置2插入99】");
        list.insert(2, 99);
        list.print();  // 输出：链表内容：10 → 99 → 20 → 30
        System.out.println();
        
        // 删除元素
        System.out.println("【测试3：删除位置3的元素】");
        Integer deleted = list.delete(3);
        if (deleted != null) {
            System.out.println("删除的元素：" + deleted);  // 输出：删除的元素：20
        }
        list.print();  // 输出：链表内容：10 → 99 → 30
        System.out.println();
        
        // 测试边界情况
        System.out.println("【测试4：边界情况测试】");
        System.out.println("链表是否为空：" + list.isEmpty());
        System.out.println("当前长度：" + list.getLength());
        
        System.out.println("\n========== 测试完成 ==========");
    }
}

