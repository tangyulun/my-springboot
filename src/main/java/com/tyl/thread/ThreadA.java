package com.tyl.thread;

/**
 * 有三个线程分别打印A、B、C,?循环打印10次?ABC、ABC...
 * 使用wait()、notify()控制打印次序
 */
public class ThreadA {

  public static Object a = new Object();
  public static Object b = new Object();
  public static Object c = new Object();

  public class Runner1 implements Runnable{

    @Override
    public void run(){
      for (int i = 0; i < 10; i++ ){
        try {
          synchronized (a){
            //System.out.println("a is locked by t1");
            synchronized (b){
              //System.out.println("b is locked by t1");
              System.out.println("A");
              b.notify();
              //System.out.println("t1 notify b");
            }
            if (i < 9 ){
              a.wait();
            }
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }


  }

  public class Runner2 implements Runnable{

    @Override
    public void run(){
      for (int i = 0; i < 10; i++){
        try {
          synchronized (b){
            //System.out.println("b is locked by t2");
            synchronized (c){
              //System.out.println("c is locked by t2");
              System.out.println("B");
              c.notify();
            }
            if (i < 9){
              b.wait();
            }
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }


  }

  public class Runner3 implements Runnable{

    @Override
    public void run(){
      for (int i = 0; i < 10; i++){
        try {
          synchronized (c){
            //System.out.println("c is locked by t3");
            synchronized (a){
              //System.out.println("a is locked by t3");
              System.out.println("C");
              a.notify();
            }
            if (i < 9){
              c.wait();
            }
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }


  }

  public static void main(String[] args) {
    ThreadA threadA = new ThreadA();
    Thread t1 = new Thread(threadA.new Runner1(),"t1");
    Thread t2 = new Thread(threadA.new Runner2(),"t2");
    Thread t3 = new Thread(threadA.new Runner3(),"t3");
    t1.start();
    try {
      Thread.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    t2.start();
    try {
      Thread.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    t3.start();
  }
}
