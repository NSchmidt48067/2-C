// Nathaniel Schmidt

import java.util.NoSuchElementException;
import java.util.*;

public class LinkedList<T> implements List<T> {
  // TODO your private inner classes and fields...

  private class Node {
    T data;
    Node next;

    public Node(T data, Node next) {
      this.data = data;
      this.next = next;
    }
  }

  private Node head = null;
  private Node temp = null;
  private Node tail = null;
  private int size = 0;

  public LinkedList() {}

  @Override
  public T get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("outside of current list size");
    }
    else {
      return get(head, index).data;
    }
  }

  private Node get(Node cur, int index) {
    if (index == 0) {//It's recursed where the data is, return
      
    }
    else {//Recurse with index - 1
      cur = get(cur.next, index - 1);
    }
    return cur;
  }

  @Override
  public void set(int index, T data) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
    throw new IndexOutOfBoundsException("outside of current list size");
    }
    else {
      //head = 
      set(head, index, data);
    }
  }

  private void set(Node cur, int index, T data) {
    if (index == 0) {
      cur.data = data;
    }
    else {
      set(cur.next, index - 1, data);
    }
  }

  @Override
  public void add(int index, T data) throws IndexOutOfBoundsException {
    if (index < 0) {
      throw new IndexOutOfBoundsException("negative index");
    }
    else if (index > size) {
      throw new IndexOutOfBoundsException("adding more than +1 the current size");
    }
    else {
      head = add(head, index, data);
      size++;
    }

  }
  //Helper method so user doesn't know the imlpementation
  private Node add(Node cur, int index, T data) {
    if (cur == null) {
      // we've reached the end of the list, so make a new tail
      tail = new Node(data, null);
      return tail;
    } else if (index == 0) {
      // the new element comes before the current node, so insert it here.
      // Note that this constructor sets the "next" of the new node to "cur".
      return new Node(data, cur);
    } else {
      // otherwise the new element comes after cur, so use recursion!
      cur.next = add(cur.next, index - 1, data);
      return cur;
    }
  }
  

  @Override
  public void remove(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("outside of current list size");
    }
    else {
      remove(head, index);
      size--;
    }
  }
  private void remove(Node cur, int index) {
    temp = cur;
    if (index == 0) {
      cur = cur.next;
    }
    else {
      remove(cur.next, index - 1);
    }
  }

  @Override
  public int size() {
    throw new UnsupportedOperationException(); // TODO erase this and get it working
  }

  /** Removes ALL elements matching the given one using .equals().
   *
   * @param element The element that should be removed
   */
  public void removeAll(T element) {
    throw new UnsupportedOperationException(); // TODO erase this and get it working
  }

  /** Gets the 2nd-to-last element.
   *
   * @return The data in the second-to-last node in the list (if any)
   * @throws NoSuchElementException if the list size is less than 2
   */
  public T penultimate() throws NoSuchElementException {
    throw new UnsupportedOperationException(); // TODO erase this and get it working
  }


  public static void main(String args[]) {
    //Main is not necessary. It is just for me to test my own code
    LinkedList<Integer> lst = new LinkedList<Integer>();
    lst.add(0,1);
    lst.add(0,2);
    lst.add(0,3);
    lst.set(2, 69);
    lst.remove(1);
    System.out.println(lst.head.data);
    System.out.println(lst.head.next.data);
    //System.out.println(lst.head.next.next.data);
  }
}
