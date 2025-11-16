public class SqList <T> {
    int maxnumber = 1000;//顺序表固定大小
    int data[];//存储元素的数组
    int length;//顺序表长度

    public SqList() {
        //构造函数用于初始化
        data = new int[maxnumber];
        length = 0;

    }
    public boolean insert(int i,T x){
        if(i<1||i>length+1){
            return false;
        }
        if(length>maxnumber){
            return false;
        }
        for(int j=length,j>=length-1,j--){

        }

        return false;
    }
}


