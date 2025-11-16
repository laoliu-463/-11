public class CircularLinkList {
    //普通单链表
int data;
CircularLinkList node;

public CircularLinkList(){
    this.data=0;
    this.node=null;
}
public void add(int data){
    CircularLinkList node = new CircularLinkList();
    node.data=data;
    node.node=this;
}
public void delete(int data){
    CircularLinkList node = this;
    while(node.node!=null){
        if(node.node.data==data){
            node.node=node.node.node;
            return;
        }
        node=node.node;
    }
    System.out.println("未找到该节点");
}
public void change(int data){
    CircularLinkList node = this;
    while(node.node!=null){
        if(node.node.data==data){
            node.node.data=data;
            return;
        }
        node=node.node;
    }
    System.out.println("未找到该节点");
}
public void find(int data){
    CircularLinkList node = this;
    while(node.node!=null){
        if(node.node.data==data){
            System.out.println("找到该节点");
            return;
        }
        node=node.node;
    }
    System.out.println("未找到该节点");
}
public void show(){
    CircularLinkList node = this.node;
    if(node==null){
        System.out.println("链表为空");
        return;
    }
    while(node!=null){
        System.out.print(node.data+" ");
        node=node.node;
    }
    System.out.println();
}
public static void main(String[] args){
    CircularLinkList circularLinkList = new CircularLinkList();
    circularLinkList.add(1);
    circularLinkList.add(2);
    circularLinkList.add(3);
    circularLinkList.add(4);
    circularLinkList.add(5);
    circularLinkList.show();
}
}

