public class CircularLinkList {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node tail; // 指向尾节点，tail.next 即为头节点
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(int data) {
        Node node = new Node(data);
        if (tail == null) {
            tail = node;
            node.next = node;
        } else {
            node.next = tail.next;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public boolean delete(int data) {
        if (tail == null) {
            System.out.println("链表为空，无法删除");
            return false;
        }

        Node prev = tail;
        Node curr = tail.next;
        for (int i = 0; i < size; i++) {
            if (curr.data == data) {
                if (size == 1) {
                    tail = null;
                } else {
                    prev.next = curr.next;
                    if (curr == tail) {
                        tail = prev;
                    }
                }
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

    public boolean change(int oldData, int newData) {
        if (tail == null) {
            System.out.println("链表为空，无法修改");
            return false;
        }

        Node curr = tail.next;
        for (int i = 0; i < size; i++) {
            if (curr.data == oldData) {
                curr.data = newData;
                System.out.println("已将节点 " + oldData + " 修改为 " + newData);
                return true;
            }
            curr = curr.next;
        }
        System.out.println("未找到需要修改的节点");
        return false;
    }

    public boolean find(int data) {
        if (tail == null) {
            System.out.println("链表为空");
            return false;
        }

        Node curr = tail.next;
        for (int i = 0; i < size; i++) {
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
        if (tail == null) {
            System.out.println("链表为空");
            return;
        }
        Node curr = tail.next;
        for (int i = 0; i < size; i++) {
            System.out.print(curr.data);
            if (i < size - 1) {
                System.out.print(" -> ");
            }
            curr = curr.next;
        }
        System.out.println(" -> (回到头节点)");
    }

    public static void main(String[] args) {
        CircularLinkList list = new CircularLinkList();

        System.out.println("初始化链表：");
        list.show();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        System.out.println("添加节点后：");
        list.show();

        list.delete(20);
        list.delete(50);
        System.out.println("删除操作后：");
        list.show();

        list.change(30, 35);
        list.change(100, 200);
        System.out.println("修改操作后：");
        list.show();

        list.find(35);
        list.find(100);

        list.add(50);
        System.out.println("再次添加节点后：");
        list.show();
    }
}

