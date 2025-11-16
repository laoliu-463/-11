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
}