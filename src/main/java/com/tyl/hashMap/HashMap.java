package com.tyl.hashMap;


/**
 * hashMap的实现 数组+链表
 * @param <K>
 * @param <V>
 */
public class HashMap<K,V> implements Map<K,V>{
  /**
   * 数据存储的结构==>数组+链表
   */
  Node<K,V>[] array = null;
  /**
   * 数组初始化的长度
   */
  private static int defaultLength = 16;
  /**
   * 当数组沾满整个数组的75%的时候,触发扩容操作
   */
  private static double factor = 0.75D;
  /**
   * hashMap中的元素个数
   */
  private int size;

  /**
   * put元素方法
   * @param k
   * @param v
   * @return
   */
  @SuppressWarnings("unchecked")
  @Override
  public V put(K k,V v){
    if (array == null) {
      /**
       * 初始化一个数组,给的长度是defaultLength
       */
      array = new Node[defaultLength];
    }
    /**
     * 通过hash算法，计算出具体插入的位置,也就是数组的下标
     */
    int index = position(k,defaultLength);
    /**
     * 扩容,判断是否需要扩容
     * 扩容规则,元素的个数size 大于 桶的尺寸*加载因子
     */
    if (size>defaultLength * factor){
      resize();
    }
    /**
     * 放入要插入的元素(添加到链表)
     */
    Node<K, V> node = array[index];
    /**
     * 先判断一下这个链表的index位置是否为空
     */
    if (node == null ){
      /**
       * 链表的index这个位置是空的,直接新建该链表并将值放入该位置
       */
      array[index] = new Node<K, V>(k, v, null);
      /**
       * 元素的个数加自增1
       */
      size++;
    } else {
      if (k.equals(node.getKey()) || k == node.getKey()) {
        return node.setValue(v);
      } else {
        array[index] = new Node<K, V>(k, v, node);
        size++;
      }
    }
    return null;
  }

  /**
   * 扩容,并且重新排列元素
   */
  private void resize(){
    /**
     * 翻倍扩容
     * 创建新的array临时变量,相当于defaultlength*2
     */
    @SuppressWarnings("unchecked")
    Node<K, V>[] temp = new Node[defaultLength << 1];

    /**
     * 重新计算散列值，插入到新的array中去。 code=key % defaultLength ==> code=key %
     * defaultLength*2
     */
    Node<K, V> node = null;
    for (int i = 0; i < array.length; i++) {
      node = array[i];
      while (node != null) {
        // 重新散列
        int index = position(node.getKey(), temp.length);
        // 插入链表的头部
        Node<K, V> next = node.next;
        // 3
        node.next = temp[index];
        // 1
        temp[index] = node;
        // 2
        node = next;

      }
    }
    /**
     * 替换掉旧的array
     */
    array = temp;
    /**
     * 更新默认的扩容因子的值
     */
    defaultLength = temp.length;
    temp = null;
  }

  /**
   * 计算位置
   * @param k
   * @param length
   * @return
   */
  private int position(K k,int length){
    int code = k.hashCode();
    /**
     * 取模算法
     */
    return code % (length - 1);
    //求与算法
    //return code % (defaultLength - 1);
  }

  /**
   * 用K获取hashMap 的K对应的值
   * @param k
   * @return
   */
  public V get(K k) {
    if (array != null){
      int index = position(k,defaultLength);
      Node<K ,V> node = array[index];
      /**
       * 遍历链表
       */
      while (node != null){
        /**
         * 如果key值相同返回value
         */
        if (node.getKey() == k){
          return node.getValue();
        } else {
          /**
           * 如果key值不同则调到下一个元素
           */
          node = node.next;
        }
      }
    }
    return null;
  }

  /**
   * 获取hashMap元素个数
   * @return
   */
  public int size(){
    return size;
  }

  /**
   * 链表节点(链表类)
   * @param <K>
   * @param <V>
   */
  static class Node<K,V> implements Entry<K,V>{
    K key;
    V value;
    /**
     * 表示下一个节点
     */
    Node<K,V> next;

    /**
     * 构造一个包含当前节点和下一个节点的链表
     * @param key
     * @param value
     * @param next
     */
    public Node(K key,V value, Node<K,V> next){
      super();
      this.key= key;
      this.value = value;
      this.next = next;
    }

    /**
     * 链表的get和set方法
     * @return
     */
    @Override
    public K getKey(){
      return this.key;
    }
    @Override
    public V getValue() {
      return this.value;
    }

    @Override
    public V setValue(V v) {
      V oldValue = this.value;
      this.value = v;
      return oldValue;
    }
  }

  /**
   * 测试方法
   */
  public void print() {
    System.out.println("===============================");
    if (array != null) {
      Node<K, V> node = null;
      for (int i = 0; i < array.length; i++) {
        node = array[i];
        System.out.print("下标[" + i + "]");
        // 遍历链表
        while (node != null) {
          System.out.print("[" + node.getKey() + ":" + node.getValue() + "]");
          if (node.next != null) {
            node = node.next;
          } else {
            // 到尾部元素
            node = null;
          }
        }
        System.out.println();
      }

    }
  }
}
