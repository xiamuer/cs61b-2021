package deque;

import edu.princeton.cs.algs4.In;

import java.rmi.UnexpectedException;

public class LinkedListDeque <Item>{
    private class IntNode{
        public Item item;
        public IntNode pre;
        public IntNode next;  //类似于结构体
        public IntNode(IntNode p,Item i, IntNode n){
            item=i;
            pre=p;
            next=n;
        }
    }
    private IntNode sentFront; //头部哨兵
    private IntNode sentBack; //尾部哨兵
    private  int size;
    //这里哨兵节点可以看做头结点和尾结点
    //哨兵数据结构可以与item相同，也可以不相同
    //创建一个空的双向队列
    public LinkedListDeque(){
        sentFront=new IntNode(null,null,null);
        sentBack=new IntNode(null,null,null);
        size=0;
    }
    //如果初始的时候传值了
    public LinkedListDeque(Item x){
        sentFront=new IntNode(null,null,null);
        sentBack=new IntNode(null,null,null);
        IntNode p=new IntNode(null,x,null);
        sentFront.next=p;
        sentBack.pre=p;
        size=1;
    }
    //
    public void addFirst(Item x){
        //有节点加进来肯定新创建一个节点
        IntNode newNode=new IntNode(null,x,null);
        //找到sentFront指向的结点，也就是第一个不为null的节点
        IntNode First=sentFront.next;
        //如果此时队列里没有元素,新来了一个，首尾指针都要指向它
        if(First==null){
            sentFront.next=newNode;
            sentBack.pre=newNode;
        }
        else {
            newNode.next=First;
            First.pre=newNode;
            //哨兵节点指向新同学；
            sentFront.next=newNode;
        }
        size+=1;
        //当然以上肯定可以简写，我自己这样写方便自己理解
    }
    //返回首部元素，也就是首部指针指向的元素
    public Item getFirst(){
        return sentFront.next.item;
    }
    //在尾部添加新节点
    public void addLast(Item x){
        //创建新节点
        IntNode NewNode=new IntNode(null,x,null);
        //获取尾部指针指向的最后一个节点
        IntNode Last=sentBack.pre;
        if(Last==null){
            sentFront.next=NewNode;
            sentBack.pre=NewNode;
        }
        else {
            NewNode.pre=Last;
            Last.next=NewNode;
            //尾部哨兵指针指向新同学
            sentBack.pre=NewNode;
        }
        size=size+1;
    }
    //返回链表最后一个元素
    public Item getLast(){
        return sentBack.pre.item;
    }
    //返回该循环链表有多少元素
    public int size(){
        return size;
    }
    //判断循环队列是否为空
    public boolean isEmpty(){
        return size==0;
    }
    //从头打印队列(链表)
    public void printDeque(){
        IntNode First=sentFront.next;
        while(First!=null){
            System.out.print(First.item+" ");
            First=First.next;
        }
        System.out.println();
    }
    //出队列，并且返回队头元素
    public Item removeFirst(){
        //链式队列出队的唯一注意事项就是当队列有且仅有一个元素时
        //首尾指针都要赋值为null;
        if(size==0) return null;
        //先找到头部节点(也就是要移除的节点)
        IntNode RemoveNode=sentFront.next;
        Item T=RemoveNode.item;
        size=size-1;    //数量减一
        //有且仅有一个元素时为空
        if(size==0){
            sentFront.next=sentBack.pre=null;
            return T;
        }
        sentFront.next=RemoveNode.next;
        RemoveNode.next.pre=null;
        return T;
    }
    //移除尾部元素节点，与首部类似
    public Item removeLast(){
        IntNode RemoveLast=sentBack.pre;
        if(RemoveLast==null)
            return null;
        Item T=RemoveLast.item;
        size--;
        if(size==0){
            sentBack.pre=sentFront.next=null;
            return T;
        }
        sentBack.pre=RemoveLast.pre;
        RemoveLast.pre.next=null;
        return T;
    }
    //索引处的值，由左往右
    public Item get(int index){
        if(index<0 || size==0 ||index>=size) return null;
        IntNode First=sentFront.next;
        while(index!=0 && First!=null){
            index-=1;
            First=First.next;
        }
        return First.item;
    }
    //递归实现索引值
    public Item getRecursive(int index){
        return get(index);
    }
}
