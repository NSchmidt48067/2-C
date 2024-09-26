// Author: Nate Schmidt
// File: BSTMap.java
// Purpose: Use a BST to create a map
// data structure

import java.util.ArrayDeque;
import java.util.Deque;
import java.lang.Integer;

public class BSTMap<K extends Comparable<K>, V> implements Map<K,V> {
  //Node class for the BST
  private class Node {
    K key;
    V value;
    Node left;
    Node right;

    public Node(K k, V v, Node l, Node r) {
      key = k;
      value = v;
      left = l; 
      right = r;
    }
  }

  // root is the top of the tree
  private Node root = null;
  private int size = 0;
  private Deque<K> deque = null;

  public BSTMap() {}

  @Override
  public V get(K key) {
    //tree = empty
    if (size == 0) {
      return null;
    }
    //Find value from key
    return get(root, key);
  }

  //Helper class for implementation
  public V get(Node cur, K key) {
    // Key does not exist in tree
    if (cur == null) {
      return null;
    }

    int temp = key.compareTo(cur.key);
    // If key is smaller, go left
    // Otherwise go right
    if (temp < 0) {
      return get(cur.left, key);
    }
    else if (temp > 0) {
      return get(cur.right, key);
    }
    else {
      return cur.value;
    }
  }

  @Override
  public boolean containsKey(K key) {
    if (size == 0) {
      return false;
    }
    return containsKey(root, key);
  }

  //Helper class for implementation
  public boolean containsKey(Node cur, K key) {
    // Key does not exist in tree
    if (cur == null) {
      return false;
    }

    int temp = key.compareTo(cur.key);
    // If key is smaller, go left
    // Otherwise go right
    if (temp < 0) {
      return containsKey(cur.left, key);
    }
    else if (temp > 0) {
      return containsKey(cur.right, key);
    }
    else {
      return true;
    }
  }

  @Override
  public void put(K key, V value) {
    if (root == null) {
      root = new Node(key, value, null, null);
      size++;
    }
    else {
      put(root, key, value);
    }

  }

  private void put(Node cur, K key, V value) {
    // Key is in tree, replace its value
    if (cur.key == key) {
      cur.value = value;
      return;
    }
    int temp = key.compareTo(cur.key);

    // Key is not in tree, create a new node
    if (cur.left == null && cur.right == null) {
      if (temp < 0) {
        cur.left = new Node(key, value, null, null);
      }
      else {
        cur.right = new Node(key, value, null, null);
      }
      size++;
    }

    // If key is smaller, go left
    // Otherwise go right
    if (temp < 0) {
      put(cur.left, key, value);
    }
    else if (temp > 0) {
      put(cur.right, key, value);
    }

  }


  // Return size
  public int size() {
    return size;
  }


  // Use the class deque to make a new deque
  public Deque<K> traverse() {
    if (root == null) {
      return null;
    }
    else {
      deque = new ArrayDeque<>(size);
      traverse(root);
      return deque;
    }
  }

  // Use an in-order recursion to traverse
  // the tree. 
  private void traverse(Node cur) {
    if (cur.left != null) {
      traverse(cur.left);
    }
    deque.add(cur.key);
    if (cur.right != null) {
      traverse(cur.right);
    }
  }

  @Override
  public void remove(K key) {
    // requirement: O(size)
    throw new UnsupportedOperationException("implement remove(k) last!");
  }



  public static void main(String[] args) {
    BSTMap<Integer, String> map = new BSTMap<Integer, String>();
    //  map.put(10, "bagel");
    //  //System.out.println(map.get(10));
    //  map.put(11, "muffin");
    //  //System.out.println(map.get(11));
    //  map.put(18, "toast");
    //  System.out.println(map.get(18));
    //  System.out.println(map.size());
    //  Deque<Integer> deq = map.traverse();
    //  System.out.println(deq.size());
  }
}
