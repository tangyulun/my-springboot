package com.tyl.DCL;

/**
 * DCL单例模式
 */
public class Instance {
  private String str = "";
  private int a = 0;

  private volatile static Instance ins = null;

  /**
   * 构造方法私有化
   */
  private Instance(){
    str = "Hello";
    a = 20;
  }

  /**
   * DCL方式获取单例
   * @return
   */
  public static Instance getInstance(){
    if (ins == null){
      synchronized (Instance.class){
        if (ins == null){
          ins = new Instance();
        }
      }
    }
    return ins;
  }

}
