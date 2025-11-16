public class DoubleLinkList {

    private static class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data;
        }

        Node() {
        }
    }

    private final Node head; // 头节点（哨兵）
    private Node tail;       // 尾指针，指向最后一个数据节点
    private int size;

    public DoubleLinkList() {
        head = new Node();
        head.next = null;
        head.prev = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(int data) {
        Node node = new Node(data);
        Node first = head.next;
        node.next = first;
        node.prev = head;
        head.next = node;
        if (first != null) {
            first.prev = node;
        } else {
            tail = node;
        }
        size++;
    }

    public void addLast(int data) {
        Node node = new Node(data);
        if (tail == null) {
            node.prev = head;
            head.next = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    public boolean insertAt(int index, int data) {
        if (index < 0 || index > size) {
            System.out.println("插入位置越界");
            return false;
        }
        if (index == size) {
            addLast(data);
            return true;
        }
        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node node = new Node(data);
        Node next = prev.next;
        node.prev = prev;
        node.next = next;
        prev.next = node;
        if (next != null) {
            next.prev = node;
        }
        if (index == size) {
            tail = node;
        }
        size++;
        return true;
    }

    public boolean deleteValue(int data) {
        Node curr = head.next;
        while (curr != null) {
            if (curr.data == data) {
                Node prev = curr.prev;
                Node next = curr.next;
                prev.next = next;
                if (next != null) {
                    next.prev = prev;
                } else {
                    tail = prev == head ? null : prev;
                }
                size--;
                System.out.println("已删除节点: " + data);
                return true;
            }
            curr = curr.next;
        }
        System.out.println("未找到该节点");
        return false;
    }

    public boolean find(int data) {
        Node curr = head.next;
        while (curr != null) {
            if (curr.data == data) {
                System.out.println("找到该节点: " + data);
                return true;
            }
            curr = curr.next;
        }
        System.out.println("未找到该节点");
        return false;
    }

    public void showForward() {
        Node curr = head.next;
        if (curr == null) {
            System.out.println("链表为空");
            return;
        }
        while (curr != null) {
            System.out.print(curr.data);
            if (curr.next != null) {
                System.out.print(" <-> ");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    public void showBackward() {
        if (tail == null) {
            System.out.println("链表为空");
            return;
        }
        Node curr = tail;
        while (curr != head) {
            System.out.print(curr.data);
            if (curr.prev != head) {
                System.out.print(" <-> ");
            }
            curr = curr.prev;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoubleLinkList list = new DoubleLinkList();
        System.out.println("初始化链表：");
        list.showForward();

        list.addFirst(20);
        list.addFirst(10);
        list.addLast(30);
        list.addLast(40);
        System.out.println("插入元素后（正序）：");
        list.showForward();
        System.out.println("插入元素后（逆序）：");
        list.showBackward();

        list.insertAt(2, 25);
        list.insertAt(list.size(), 50);
        System.out.println("按位置插入后：");
        list.showForward();

        list.deleteValue(25);
        list.deleteValue(99);
        System.out.println("删除操作后：");
        list.showForward();

        list.find(30);
        list.find(100);
    }
}
