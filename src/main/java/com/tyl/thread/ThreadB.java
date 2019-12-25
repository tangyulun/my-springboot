package com.tyl.thread;

import java.io.*;

/**
 * 有三个线程分别打印A、B、C,?循环打印10次?ABC、ABC...
 * 使用管道流在进程间传递消息控制打印次序
 */
public class ThreadB {

  public class RunnerA implements Runnable{

    DataInputStream disA = null;
    DataOutputStream dosA = null;

    public RunnerA(PipedInputStream pisA, PipedOutputStream posA){
      disA = new DataInputStream(pisA);
      dosA = new DataOutputStream(posA);
    }


    @Override
    public void run(){
      try {
        for (int i = 0; i < 10 ; i++ ){
          if (i == 0 ){
            System.out.println("A");
            dosA.writeChar('A');
          } else if (i == 9){
            char c = disA.readChar();
            System.out.println("A");
            dosA.writeChar('O');
          } else {
            char c = disA.readChar();
            System.out.println("A");
            dosA.writeChar('A');
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }


  }

  public class RunnerB implements Runnable{

    DataInputStream disB=null;
    DataOutputStream dosB=null;

    public RunnerB(PipedInputStream pisB,PipedOutputStream posB){
      disB=new DataInputStream(pisB);
      dosB=new DataOutputStream(posB);
    }


    @Override
    public void run(){
      try {
        char c = disB.readChar();
        while (true){
          if (c == 'O'){
            System.out.println("B");
            dosB.writeChar('O');
            break;
          }
          if (c == 'A'){
            System.out.println("B");
            dosB.writeChar('B');
            c = disB.readChar();
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }


  }

  public class RunnerC implements Runnable{

    DataInputStream disC=null;
    DataOutputStream dosC=null;

    public RunnerC(PipedInputStream pisC,PipedOutputStream posC){
      disC=new DataInputStream(pisC);
      dosC=new DataOutputStream(posC);
    }


    @Override
    public void run (){
      try {
        char c=disC.readChar();
        while (true){
          if (c=='O'){
            System.out.println("C");
            break;
          }
          if (c=='B'){
            System.out.println("C ");
            dosC.writeChar('C');
            c= disC.readChar();
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

    }


  }

  public static void main(String[] args) {
    PipedOutputStream posA=new PipedOutputStream();
    PipedInputStream pisA=new PipedInputStream();
    PipedOutputStream posB=new PipedOutputStream();
    PipedInputStream pisB=new PipedInputStream();
    PipedOutputStream posC=new PipedOutputStream();
    PipedInputStream pisC=new PipedInputStream();
    try {
      pisA.connect(posC);
      pisB.connect(posA);
      pisC.connect(posB);
    } catch (IOException e) {
      e.printStackTrace();
    }

//    Thread ta=new Thread(new RunnerA(pisA,posA),"ta");
//    Thread tb=new Thread(new RunnerB(pisB,posB),"tb");
//    Thread tc=new Thread(new RunnerC(pisC,posC),"tc");
//    ta.start();
//    tb.start();
//    tc.start();
  }

}
