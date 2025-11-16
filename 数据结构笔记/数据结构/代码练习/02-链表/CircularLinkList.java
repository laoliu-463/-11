/**
 * 循环链表 Java实现
 * 
 * 循环链表：链表的最后一个节点的指针指向头节点（或第一个节点），形成环形结构
 * 
 * 分类：
 * 1. 循环单链表：最后一个节点的next指向头节点
 * 2. 循环双链表：最后一个节点的next指向头节点，头节点的prev指向最后一个节点
 * 
 * 特点：
 * 1. 可以从任意节点开始遍历整个链表
 * 2. 不需要判断是否到达链表末尾（通过判断是否回到起始节点）
 * 3. 适合需要循环访问的场景
 * 
 * 本实现包含：
 * - 循环单链表（带头节点）
 * - 循环双链表（带头节点）
 * 
 * @author 数据结构练习
 * @version 1.0
 */

// ========== 循环单链表 ==========

// 循环单链表节点类
class CircularSingleListNode {
    int data;
    CircularSingleListNode next;
    
    CircularSingleListNode(int data) {
        this.data = data;
        this.next = null;
    }
    
    CircularSingleListNode() {
        this.data = 0;
        this.next = null;
    }
}

// 循环单链表类（带头节点）
class CircularSingleLinkList {
    private CircularSingleListNode head;  // 头指针（指向头节点）
    private int length;
    
    /**
     * 构造函数：创建空的循环单链表
     */
    public CircularSingleLinkList() {
        this.head = new CircularSingleListNode();
        this.head.next = head;  // 头节点的next指向自己，形成循环
        this.length = 0;
    }
    
    /**
     * 判断链表是否为空
     */
    public boolean isEmpty() {
        return head.next == head;  // 头节点的next指向自己表示空链表
    }
    
    /**
     * 尾插法：在链表尾部插入新节点
     * 
     * 时间复杂度：O(n) - 需要遍历到最后一个节点
     * 
     * @param data 要插入的数据
     */
    public void insertAtTail(int data) {
        CircularSingleListNode newNode = new CircularSingleListNode(data);
        
        // 找到最后一个节点（next指向head的节点）
        CircularSingleListNode current = head;
        while (current.next != head) {
            current = current.next;
        }
        
        // 插入新节点
        newNode.next = head;  // 新节点的next指向head（保持循环结构）
        current.next = newNode;  // 最后一个节点指向新节点
        length++;
    }
    
    /**
     * 头插法：在链表头部插入新节点
     * 
     * 时间复杂度：O(1) - 直接操作头节点
     * 
     * @param data 要插入的数据
     */
    public void insertAtHead(int data) {
        CircularSingleListNode newNode = new CircularSingleListNode(data);
        newNode.next = head.next;  // 新节点指向原第一个节点
        head.next = newNode;        // 头节点指向新节点
        length++;
    }
    
    /**
     * 在指定位置插入新节点
     * 
     * 时间复杂度：O(n) - 需要找到插入位置
     * 
     * @param i 插入位置（从1开始）
     * @param data 要插入的数据
     * @return 插入成功返回true，失败返回false
     */
    public boolean insert(int i, int data) {
        if (i < 1 || i > length + 1) {
            System.out.println("插入位置不合法！位置范围：1 ~ " + (length + 1));
            return false;
        }
        
        CircularSingleListNode newNode = new CircularSingleListNode(data);
        CircularSingleListNode prev = head;
        
        // 找到第i-1个节点
        for (int j = 0; j < i - 1; j++) {
            prev = prev.next;
        }
        
        // 插入新节点
        newNode.next = prev.next;
        prev.next = newNode;
        length++;
        return true;
    }
    
    /**
     * 删除指定位置的节点
     * 
     * 时间复杂度：O(n) - 需要找到删除位置
     * 
     * @param i 删除位置（从1开始）
     * @return 删除的元素值，失败返回null
     */
    public Integer delete(int i) {
        if (i < 1 || i > length || isEmpty()) {
            System.out.println("删除位置不合法或链表为空！");
            return null;
        }
        
        CircularSingleListNode prev = head;
        // 找到第i-1个节点
        for (int j = 0; j < i - 1; j++) {
            prev = prev.next;
        }
        
        int deletedValue = prev.next.data;
        prev.next = prev.next.next;  // 跳过要删除的节点
        length--;
        return deletedValue;
    }
    
    /**
     * 按位置查找元素
     * 
     * 时间复杂度：O(n) - 需要从头遍历
     * 
     * @param i 查找位置（从1开始）
     * @return 元素值，位置不合法返回null
     */
    public Integer get(int i) {
        if (i < 1 || i > length || isEmpty()) {
            System.out.println("查找位置不合法或链表为空！");
            return null;
        }
        
        CircularSingleListNode current = head.next;
        for (int j = 1; j < i; j++) {
            current = current.next;
        }
        return current.data;
    }
    
    /**
     * 按值查找元素位置
     * 
     * 时间复杂度：O(n) - 需要遍历查找
     * 
     * @param data 要查找的元素值
     * @return 元素位置（从1开始），未找到返回0
     */
    public int locate(int data) {
        CircularSingleListNode current = head.next;
        int position = 1;
        
        // 遍历查找（注意循环终止条件）
        while (current != head) {
            if (current.data == data) {
                return position;
            }
            current = current.next;
            position++;
        }
        return 0;  // 未找到
    }
    
    /**
     * 遍历输出链表（循环一次）
     * 
     * 时间复杂度：O(n) - 需要访问所有节点
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("循环单链表为空！");
            return;
        }
        
        CircularSingleListNode current = head.next;
        System.out.print("循环单链表：");
        while (current != head) {  // 关键：当回到head时停止（避免无限循环）
            System.out.print(current.data);
            if (current.next != head) {
                System.out.print(" → ");
            }
            current = current.next;
        }
        System.out.println(" ↺ (循环)");
    }
    
    /**
     * 清空链表：清空链表中所有元素
     */
    public void clear() {
        head.next = head;  // 将头节点的next指向自己
        length = 0;
    }
    
    /**
     * 获取链表长度
     */
    public int getLength() {
        return length;
    }
}

// ========== 循环双链表 ==========

// 循环双链表节点类
class CircularDoubleListNode {
    int data;
    CircularDoubleListNode prev;
    CircularDoubleListNode next;
    
    CircularDoubleListNode(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
    
    CircularDoubleListNode() {
        this.data = 0;
        this.prev = null;
        this.next = null;
    }
}

// 循环双链表类（带头节点）
class CircularDoubleLinkList {
    private CircularDoubleListNode head;  // 头指针（指向头节点）
    private int length;
    
    /**
     * 构造函数：创建空的循环双链表
     * 
     * 关键：头节点的next和prev都指向自己，表示空链表
     */
    public CircularDoubleLinkList() {
        this.head = new CircularDoubleListNode();
        this.head.next = head;  // 头节点的next指向自己（形成循环）
        this.head.prev = head;  // 头节点的prev指向自己（形成循环）
        this.length = 0;
    }
    
    /**
     * 判空：判断链表是否为空
     * 
     * 时间复杂度：O(1)
     * 
     * @return 为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return head.next == head;  // 头节点的next指向自己表示空链表
    }
    
    /**
     * 尾插法：在链表尾部插入新节点
     * 
     * 时间复杂度：O(1) - 利用head.prev快速找到最后一个节点（循环双链表的优势）
     * 
     * @param data 要插入的数据
     */
    public void insertAtTail(int data) {
        CircularDoubleListNode newNode = new CircularDoubleListNode(data);
        
        // 利用head.prev快速找到最后一个节点（循环双链表的优势）
        CircularDoubleListNode last = head.prev;
        
        // 插入新节点（需要更新4个指针）
        newNode.next = head;         // 新节点的next指向head（保持循环结构）
        newNode.prev = last;         // 新节点的prev指向最后一个节点
        last.next = newNode;         // 最后一个节点指向新节点
        head.prev = newNode;         // head的prev指向新节点（保持循环结构）
        length++;
    }
    
    /**
     * 头插法：在链表头部插入新节点
     * 
     * 时间复杂度：O(1) - 直接操作头节点
     * 
     * @param data 要插入的数据
     */
    public void insertAtHead(int data) {
        CircularDoubleListNode newNode = new CircularDoubleListNode(data);
        
        // 如果链表为空
        if (isEmpty()) {
            newNode.next = head;
            newNode.prev = head;
            head.next = newNode;
            head.prev = newNode;
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
     * 在指定位置插入新节点
     * 
     * 时间复杂度：O(n) - 需要找到插入位置
     * 
     * @param i 插入位置（从1开始）
     * @param data 要插入的数据
     * @return 插入成功返回true，失败返回false
     */
    public boolean insert(int i, int data) {
        if (i < 1 || i > length + 1) {
            System.out.println("插入位置不合法！位置范围：1 ~ " + (length + 1));
            return false;
        }
        
        // 如果插入位置在尾部，使用尾插法优化
        if (i == length + 1) {
            insertAtTail(data);
            return true;
        }
        
        CircularDoubleListNode newNode = new CircularDoubleListNode(data);
        
        // 找到第i个节点
        CircularDoubleListNode current = head.next;
        for (int j = 1; j < i; j++) {
            current = current.next;
        }
        
        // 插入新节点（需要更新4个指针）
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
     * 时间复杂度：O(n) - 需要找到删除位置
     * 
     * @param i 删除位置（从1开始）
     * @return 删除的元素值，失败返回null
     */
    public Integer delete(int i) {
        if (i < 1 || i > length || isEmpty()) {
            System.out.println("删除位置不合法或链表为空！");
            return null;
        }
        
        // 找到要删除的节点
        CircularDoubleListNode current = head.next;
        for (int j = 1; j < i; j++) {
            current = current.next;
        }
        
        int deletedValue = current.data;
        
        // 删除节点（需要更新2个指针，循环结构会自动保持）
        current.prev.next = current.next;
        current.next.prev = current.prev;
        
        length--;
        return deletedValue;
    }
    
    /**
     * 按位置查找元素（正向）
     * 
     * 时间复杂度：O(n) - 需要从头遍历
     * 
     * @param i 查找位置（从1开始）
     * @return 元素值，位置不合法返回null
     */
    public Integer get(int i) {
        if (i < 1 || i > length || isEmpty()) {
            System.out.println("查找位置不合法或链表为空！");
            return null;
        }
        
        CircularDoubleListNode current = head.next;
        for (int j = 1; j < i; j++) {
            current = current.next;
        }
        return current.data;
    }
    
    /**
     * 按值查找元素位置
     * 
     * 时间复杂度：O(n) - 需要遍历查找
     * 
     * @param data 要查找的元素值
     * @return 元素位置（从1开始），未找到返回0
     */
    public int locate(int data) {
        CircularDoubleListNode current = head.next;
        int position = 1;
        
        // 遍历查找（注意循环终止条件）
        while (current != head) {
            if (current.data == data) {
                return position;
            }
            current = current.next;
            position++;
        }
        return 0;  // 未找到
    }
    
    /**
     * 正向遍历输出链表
     * 
     * 时间复杂度：O(n) - 需要访问所有节点
     */
    public void printForward() {
        if (isEmpty()) {
            System.out.println("循环双链表为空！");
            return;
        }
        
        CircularDoubleListNode current = head.next;
        System.out.print("循环双链表（正向）：");
        while (current != head) {  // 关键：当回到head时停止（避免无限循环）
            System.out.print(current.data);
            if (current.next != head) {
                System.out.print(" ⇄ ");
            }
            current = current.next;
        }
        System.out.println(" ↺ (循环)");
    }
    
    /**
     * 反向遍历输出链表
     * 
     * 时间复杂度：O(n) - 需要访问所有节点
     */
    public void printBackward() {
        if (isEmpty()) {
            System.out.println("循环双链表为空！");
            return;
        }
        
        CircularDoubleListNode current = head.prev;  // 从最后一个节点开始
        System.out.print("循环双链表（反向）：");
        while (current != head) {  // 关键：当回到head时停止（避免无限循环）
            System.out.print(current.data);
            if (current.prev != head) {
                System.out.print(" ⇄ ");
            }
            current = current.prev;
        }
        System.out.println(" ↺ (循环)");
    }
    
    /**
     * 遍历输出链表（默认正向）
     */
    public void print() {
        printForward();
    }
    
    /**
     * 清空链表：清空链表中所有元素
     */
    public void clear() {
        head.next = head;  // 将头节点的next指向自己
        head.prev = head;  // 将头节点的prev指向自己
        length = 0;
    }
    
    /**
     * 获取链表长度
     * 
     * 时间复杂度：O(1) - 直接返回length变量
     * 
     * @return 链表长度
     */
    public int getLength() {
        return length;
    }
    
    /**
     * 打印链表信息
     */
    public void printInfo() {
        System.out.println("循环双链表信息：");
        System.out.println("  长度：" + length);
        System.out.println("  是否为空：" + isEmpty());
        printForward();
    }
}

// ========== 主类：包含测试代码 ==========
public class CircularLinkList {
    
    /**
     * 测试循环单链表
     */
    public static void testCircularSingleLinkList() {
        System.out.println("========== 循环单链表测试 ==========\n");
        
        CircularSingleLinkList list = new CircularSingleLinkList();
        System.out.println("创建循环单链表成功！");
        System.out.println("当前长度：" + list.getLength());
        System.out.println("链表是否为空：" + list.isEmpty());
        System.out.println();
        
        // 插入元素
        System.out.println("【测试1：尾插法插入元素】");
        list.insertAtTail(10);
        list.insertAtTail(20);
        list.insertAtTail(30);
        list.print();
        System.out.println("当前长度：" + list.getLength());
        System.out.println();
        
        // 在指定位置插入
        System.out.println("【测试2：在位置2插入99】");
        list.insert(2, 99);
        list.print();
        System.out.println();
        
        // 删除元素
        System.out.println("【测试3：删除位置3的元素】");
        Integer deleted = list.delete(3);
        if (deleted != null) {
            System.out.println("删除的元素：" + deleted);
        }
        list.print();
        System.out.println();
        
        // 测试查找
        System.out.println("【测试4：查找测试】");
        System.out.println("位置2的元素：" + list.get(2));
        System.out.println("元素99的位置：" + list.locate(99));
        System.out.println();
        
        // 测试清空
        System.out.println("【测试5：清空链表】");
        list.clear();
        System.out.println("清空后是否为空：" + list.isEmpty());
        System.out.println("当前长度：" + list.getLength());
        System.out.println();
        
        System.out.println("========== 循环单链表测试完成 ==========\n");
    }
    
    /**
     * 测试循环双链表
     */
    public static void testCircularDoubleLinkList() {
        System.out.println("========== 循环双链表测试 ==========\n");
        
        CircularDoubleLinkList list = new CircularDoubleLinkList();
        System.out.println("创建循环双链表成功！");
        System.out.println("当前长度：" + list.getLength());
        System.out.println("链表是否为空：" + list.isEmpty());
        System.out.println();
        
        // 插入元素
        System.out.println("【测试1：尾插法插入元素】");
        list.insertAtTail(10);
        list.insertAtTail(20);
        list.insertAtTail(30);
        list.printForward();
        System.out.println("当前长度：" + list.getLength());
        System.out.println();
        
        // 反向遍历
        System.out.println("【测试2：反向遍历】");
        list.printBackward();
        System.out.println();
        
        // 在指定位置插入
        System.out.println("【测试3：在位置2插入99】");
        list.insert(2, 99);
        list.printForward();
        System.out.println();
        
        // 删除元素
        System.out.println("【测试4：删除位置3的元素】");
        Integer deleted = list.delete(3);
        if (deleted != null) {
            System.out.println("删除的元素：" + deleted);
        }
        list.printForward();
        System.out.println();
        
        // 测试查找
        System.out.println("【测试5：查找测试】");
        System.out.println("位置2的元素：" + list.get(2));
        System.out.println("元素99的位置：" + list.locate(99));
        System.out.println();
        
        // 测试清空
        System.out.println("【测试6：清空链表】");
        list.clear();
        System.out.println("清空后是否为空：" + list.isEmpty());
        System.out.println("当前长度：" + list.getLength());
        System.out.println();
        
        System.out.println("========== 循环双链表测试完成 ==========\n");
    }
    
    /**
     * 主函数
     */
    public static void main(String[] args) {
        // 测试循环单链表
        testCircularSingleLinkList();
        
        // 测试循环双链表
        testCircularDoubleLinkList();
        
        System.out.println("========== 所有测试完成 ==========");
    }
}





