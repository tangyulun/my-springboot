package com.tyl.LRU;

import java.util.HashMap;

/**
 * LRU缓存
 * @param <K>
 * @param <V>
 */
public class LRU<K,V> {
  private final int MAX_CACHE_SIZE;
  private Entry first;
  private Entry last;

  private HashMap<K, Entry<K, V>> hashMap;

  /**
   *
   * @param cacheSize 初始的缓存的大小
   */
  public LRU(int cacheSize) {
    this.MAX_CACHE_SIZE = cacheSize;
    hashMap = new HashMap<K, Entry<K, V>>();
  }

  public void put(K key, V value) {
    Entry entry = getEntry(key);
    if (entry == null) {
      if (hashMap.size() >= MAX_CACHE_SIZE) {
        hashMap.remove(last.key);
        removeLast();
      }
      entry = new Entry();
      entry.key = key;
    }
    entry.value = value;
    moveToFirst(entry);
    hashMap.put(key, entry);
  }


  /**
   * 获取key键对应的值
   * @param key
   * @return
   */
  public V get(K key) {
    Entry<K, V> entry = getEntry(key);
    if (entry == null) {
      return null;
    }
    // 将当前entry移动到开头
    moveToFirst(entry);
    return entry.value;
  }

  /**
   * 将最近访问的entry移动到开头
   * @param entry
   */
  private void moveToFirst(Entry entry) {
    /**
     * 1、如果是第一个元素，则不需要移动
     */
    if (entry == first) {
      return;
    }

    /**
     * 2、如果entry节点的前面的节点存在，则改变前面的节点的指向
     */
    if (entry.pre != null) {
      entry.pre.next = entry.next;
    }

    /**
     * 3、如果entry节点的后面存在，则改变后面节点的前向指向
     */
    if (entry.next != null) {
      entry.next.pre = entry.pre;
    }

    /**
     * 4、如果当前结点是最后一个结点，那么该变last指向
     */
    if (entry == last) {
      last = last.pre;
    }

    /**
     * 5、
     */
    if (first == null || last == null) {
      first = last = entry;
      return ;
    }

    /**
     * 移动entry到队头
     */
    entry.next = first;
    first.pre = entry;
    entry.pre = null;
  }

  /**
   * 删除
   */
  private void removeLast() {
    if (last != null) {
      last = last.pre;
      if (last == null) {
        first = null;
      } else {
        last.next = null;
      }

    }
  }

  private Entry<K, V> getEntry(K key) {
    return hashMap.get(key);
  }

  /**
   * 存储键值的节点
   * @param <K>
   * @param <V>
   */
  class Entry<K, V> {
    public Entry pre;
    public Entry next;
    public K key;
    public V value;
  }
}
