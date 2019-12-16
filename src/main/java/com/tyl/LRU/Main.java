package com.tyl.LRU;

/**
 * LRU缓存测试主类
 */
public class Main {

  public static void main(String[] args) {
    LRU<Integer,String> lru = new LRU<>(5);
    lru.put(1,"String1");
    lru.put(2,"String2");
    lru.put(3,"String3");
    lru.put(4,"String4");
    lru.put(5,"String5");
    lru.put(6,"String6");
    lru.put(7,"String7");
    lru.put(8,"String8");
    System.out.println(lru.get(1));
    System.out.println(lru.get(2));
    System.out.println(lru.get(3));
    System.out.println(lru.get(4));
    System.out.println(lru.get(5));
    System.out.println(lru.get(6));
    System.out.println(lru.get(7));
    System.out.println(lru.get(8));

  }


}
