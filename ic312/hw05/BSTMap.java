// Author: Nate Schmidt
// File: BSTMap.java
// Purpose: Use a BST to create a map
// data structure

import java.util.Deque;

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
    // Found the key, return value
    if (cur.key == key) {
      return cur.value;
    }

    // If there are children, 
    // go to them looking for the key
    if (cur.left != null) {
      get(cur.left, key);
    }
    if (cur.right != null) {
      get(cur.right, key);
    }
    
    // Key was not found
    return null;
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
    // Found the key, return value
    if (cur.key == key) {
      return true;
    }

    // If there are children, 
    // go to them looking for the key
    if (cur.left != null) {
      get(cur.left, key);
    }
    if (cur.right != null) {
      get(cur.right, key);
    }
    
    // Key was not found
    return false;
  }

  @Override
  public void put(K key, V value) {
    // requirement: O(size)
    if (root == null) {
      root = new Node(key, value, null, null);
    }
    else {

    }


  }

  @Override
  public int size() {
    // requirement: O(1)
    throw new UnsupportedOperationException("you need to implement size()");
  }

  @Override
  public Deque<K> traverse() {
    // requirement: O(n)
    throw new UnsupportedOperationException("you need to implement traverse()");
  }

  @Override
  public void remove(K key) {
    // requirement: O(size)
    throw new UnsupportedOperationException("implement remove(k) last!");
  }



  public static void main(String[] args) {
    BSTMap<String, Integer> map = new BSTMap<String, Integer>();

  }
}
