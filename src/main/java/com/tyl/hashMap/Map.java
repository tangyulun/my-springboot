package com.tyl.hashMap;

/**
 * Map接口
 * @param <K>
 * @param <V>
 */
public interface Map<K,V> {
  /**
   * 向Map中插入值
   * @param k
   * @param v
   * @return
   */
  public V put(K k,V v);

  /**
   * 根据key获取hashMap中的值
   * @param k
   * @return
   */
  public V get(K k);

  /**
   * 获得Map中元素的个数
   * @return
   */
  public int size();

  /**
   * 获取Map中，键值对的对象
   * @param <K>
   * @param <V>
   */
  interface Entry<K,V>{
    /**
     * getKey
     * @return
     */
    K getKey();

    /**
     * getValue
     * @return
     */
    V getValue();

    /**
     * setValue
     * @param v
     * @return
     */
    V setValue(V v);
  }

}
