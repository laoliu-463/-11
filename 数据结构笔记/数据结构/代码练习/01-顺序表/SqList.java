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
        if(length>=maxnumber){
            return false;
        }
        for(int j=length-1; j>=i-1; j--){
            data[j+1] = data[j];
        }
        data[i-1] = (int)x;
        length++;
        return true;
    }
    public boolean delete(int i){
        if(i<1||i>length){
            return false;
        }
        int x =data[i-1];
        for(int j=i; j<length; j++){
            data[j-1] = data[j];
        }
        length--;
        return true;
    }
    public boolean find(int i){
        if(i<1||i>length){
            return false;
        }
        for(int j=0;j<=length;j++){
            if(data[j]==i){
                System.out.println("找到元素是"+data[j]);
                return true;
            }

        }
        System.out.println("未找到元素");
        return false;

    }
    public void showall(){
        for(int j=0;j<=length;j++){
            System.out.println(data[j]);
        }


    }

    public static void main(String[] args){
        SqList sqList = new SqList();
        sqList.insert(1,1);
        sqList.insert(2,2);
        sqList.insert(3,3);
        sqList.insert(4,4);
        sqList.insert(5,5);
        sqList.showall();
        sqList.delete(2);
        sqList.showall();
        sqList.find(2);
    }
}
