package com.tyl.stack;

/**
 * 此栈实现基于链表，初始栈时未设置栈大小（栈大小可变）
 */
public class MyLinkStack {

  //记录栈顶元素
  private Node top;
  //记录栈大小
  private int size;

  public MyLinkStack(){
    top = null;
  }

  class Node{

    //记录数据
    public int data;
    public Node next;

    Node(int data){
      this.data = data;
    }


  }

  //入栈
  public void push(int data){
    Node newNode = new Node(data);
    if (top == null) {
      top = newNode;


    }
    else {
      //将新元素插到链表头部
      newNode.next = top;
      top = newNode;
    }
    size++;
  }


  //出栈
  public Integer pop(){
    if (top == null) throw new RuntimeException("栈为空");
    else {
      int val = top.data;
      top = top.next;
      size--;
      return val;
    }

  }


  //查看栈顶元素但不出栈
  public Integer peek(){
    if (top == null) throw new RuntimeException("栈为空");
    else {
      return top.data;
    }

  }


  public boolean isEmpty(){
    return top == null;
  }


  public int getSize(){
    return size;
  }


  public static void main(String[] args) {
    MyLinkStack stack = new MyLinkStack();
    stack.push(3);
    stack.push(6);
    stack.push(8);
    System.out.println("当前size"+stack.getSize());
    System.out.println(stack.isEmpty());
    System.out.println(stack.pop());
    System.out.println("当前size"+stack.getSize());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.isEmpty());
    stack.push(2);
    System.out.println();
    System.out.println("当前size"+stack.getSize());
    System.out.println(stack.pop());
    System.out.println("当前size"+stack.getSize());


  }


}
