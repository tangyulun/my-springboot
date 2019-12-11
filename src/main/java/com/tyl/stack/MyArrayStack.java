package com.tyl.stack;

/**
 * 此栈实现基于数组，初始栈时已经设置栈大小
 */
public class MyArrayStack {

  private int size;
  private int top = -1;
  private int[] arr;

  MyArrayStack(){
    //默认栈大小为3
    arr = new int[3];
  }


  MyArrayStack(int size){
    if (size > 0 ) {
      this.size = size;
      arr = new int[size];
    }
    else throw new RuntimeException("数组长度值不正确！");
  }


  //入栈
  public void push(int data){
    if (top >= size - 1) {
      System.out.println("栈已满");
      return;
    }
    arr[++top] = data;
  }


  //出栈
  public int pop(){
    if (top < 0) {
      throw new RuntimeException("栈为空");
    }
    else {
      int temp = arr[top];
      arr[top] = 0;
      top--;
      return temp;
    }
  }


  //查看栈顶元素但不出栈
  public int peek(){
    if (top < 0 ) {
      throw new RuntimeException("栈为空");
    }
    else {
      return arr[top];
    }
  }


  public boolean isEmpty(){
    return top == -1;
  }


  public static void main(String[] args) {
    MyArrayStack myArrayStack = new MyArrayStack(3);
    myArrayStack.push(2);
    myArrayStack.push(5);
    myArrayStack.push(10);
    myArrayStack.push(11);
    System.out.println(myArrayStack.pop());
    myArrayStack.pop();
    System.out.println(myArrayStack.pop());
  }


}
