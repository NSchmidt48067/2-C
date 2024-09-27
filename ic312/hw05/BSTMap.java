// Author: Nate Schmidt
// File: BSTMap.java
// Purpose: Use a BST to create a map
// data structure

import java.util.ArrayDeque;
import java.util.Deque;
import java.lang.Integer;
import java.util.NoSuchElementException;

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


  // BAD BAD BAD
  private void put(Node cur, K key, V value) {
    int temp = key.compareTo(cur.key);

    // Key is in tree, replace its value
    if (temp == 0) {
      cur.value = value;
      return;
    }

    // Key is not in tree, create a new node
    if (cur.left == null && temp < 0) {
      cur.left = new Node(key, value, null, null);
      size++;
    }
    else if (cur.right == null && temp > 0) {
      cur.right = new Node(key, value, null, null);
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
    root = remove(root, key);
  }

  private Node remove(Node cur, K key) {
    // Key is not in tree
    if (cur == null) {
      return cur;
    }
    int temp = key.compareTo(cur.key);
    
    // Go left or right to find key
    if (temp < 0) {
      cur.left = remove(cur.left, key);
    }
    else if (temp > 0) {
      cur.right = remove(cur.right, key);
    }
    // Key is in tree, DELETE IT
    else {
      // Does not have left child
      if (cur.left == null) {
        return cur.right;
      }
      // Does not have right child
      else if (cur.right == null) {
        return cur.left;
      }
      // Has two children
      else {
        Node t = findSmall(cur.right);
        cur.key = t.key;
        cur.value = t.value;
        cur.right = remove(cur.right, t.key);
      }
      size--;
    }
    return cur;
  }

  // Finds smallest node in right subtree for remove()
  // Node to right is passed in
  private Node findSmall(Node cur) {
    // If possible, go left      
    if (cur.left == null) {
      return cur;

    }
    // Elbow node, remove right
    else {
      return findSmall(cur.left);
    }
  }


  public static void main(String[] args) {
    // BSTMap<Integer, String> map = new BSTMap<Integer, String>();
    //  map.put(10, "bagel");
    //  //System.out.println(map.get(10));
    //  map.put(7, "muffin");
    //  //System.out.println(map.get(11));
    //  map.put(18, "toast");
    //  System.out.println(map.get(18));
    //  System.out.println(map.size());
    //  map.remove(18);
    //  System.out.println(map.get(18));
    //  Deque<Integer> deq = map.traverse();
    //  System.out.println(deq.size());
  }
}
