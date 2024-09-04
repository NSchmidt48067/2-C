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
  private Node tail = null;
  private int size = 0;

  //Moves throughout the list
  public void traverse(Node cur){
    if (cur.next == null)
      return;

    //Do something with cur

    traverse(cur.next);
  }

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
    if (index == 0) {
      return cur;
    }
    else {
      cur = get(cur.next, index - 1);
      return cur;
    }
  }

  @Override
  public void set(int index, T data) throws IndexOutOfBoundsException {
    throw new UnsupportedOperationException(); // TODO erase this and get it working
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

    if (cur == null) {//If list is empty
        tail =  new Node(data, null);
      }
    else if (index > 1){//Move through the list until you get to the right node
        add(cur.next, index - 1, data);
      }
    else {//Create the node
    //Possibly add if statement to handle
    //case where adding to the end and thus need a null in next position
        return new Node(data, cur.next);
      }
      return cur;
        
  }
  

  @Override
  public void remove(int index) throws IndexOutOfBoundsException {
    throw new UnsupportedOperationException(); // TODO erase this and get it working
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
    add(0, 1);
  }
}
