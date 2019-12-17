package com.tyl.stacksQueue;

import java.util.Stack;

/**
 * 用两个栈实现一个队列
 * 思路：左边A栈，右边B栈
 * 1.A栈往B栈压入数据，要一次性把A栈数据一个一个都压入B栈
 * 2.如果B栈不为空，A栈绝对不能向B栈压入数据。
 */
public class TwoStacksQueue {

  public Stack<Integer> stackPush;
  public Stack<Integer> stackPop;

  public TwoStacksQueue(){
    stackPush = new Stack<Integer>();
    stackPop = new Stack<Integer>();
  }


  //元素进栈
  public void add(int pushInt){
    stackPush.push(pushInt);
  }


  public int poll() {
    if (stackPop.empty() && stackPush.empty()) {
      throw new RuntimeException("Queue is empty!");
    } else if (stackPop.empty()) {
      while (!stackPush.empty()) {
        stackPop.push(stackPush.pop());
      }
    }
    return stackPop.pop();
  }


  public static void main(String[] args) {
    TwoStacksQueue queue = new TwoStacksQueue();
    queue.add(2);
    queue.add(4);
    queue.add(7);
    queue.add(11);
    System.out.println(queue.poll());
    System.out.println(queue.poll());
  }


}
