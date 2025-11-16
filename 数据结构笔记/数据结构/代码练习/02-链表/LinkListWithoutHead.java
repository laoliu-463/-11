public class LinkListWithoutHead {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(int data) {
        Node node = new Node(data);
        node.next = head;
        head = node;
        size++;
    }

    public void addLast(int data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
        } else {
            Node tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = node;
        }
        size++;
    }

    public boolean insertAt(int index, int data) {
        if (index < 0 || index > size) {
            System.out.println("插入位置越界");
            return false;
        }
        if (index == 0) {
            addFirst(data);
            return true;
        }
        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        Node node = new Node(data);
        node.next = prev.next;
        prev.next = node;
        size++;
        return true;
    }

    public boolean deleteValue(int data) {
        if (head == null) {
            System.out.println("链表为空，无法删除");
            return false;
        }
        if (head.data == data) {
            head = head.next;
            size--;
            System.out.println("已删除节点: " + data);
            return true;
        }
        Node prev = head;
        Node curr = head.next;
        while (curr != null) {
            if (curr.data == data) {
                prev.next = curr.next;
                size--;
                System.out.println("已删除节点: " + data);
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        System.out.println("未找到该节点");
        return false;
    }

    public boolean find(int data) {
        Node curr = head;
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

    public void show() {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data);
            if (curr.next != null) {
                System.out.print(" -> ");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkListWithoutHead list = new LinkListWithoutHead();
        System.out.println("初始化链表：");
        list.show();

        list.addFirst(30);
        list.addFirst(20);
        list.addFirst(10);
        list.addLast(40);
        list.addLast(50);
        System.out.println("插入元素后：");
        list.show();

        list.insertAt(3, 35);
        list.insertAt(0, 5);
        System.out.println("按位置插入后：");
        list.show();

        list.deleteValue(35);
        list.deleteValue(99);
        System.out.println("删除操作后：");
        list.show();

        list.find(20);
        list.find(100);
    }
}
