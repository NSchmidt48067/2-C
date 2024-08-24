/** A List implementation with fixed, bounded capacity.
 *
 * When an instance of this class is constructed, the chosen capacity
 * represents the maximum size the list can ever grow to.
 */
import java.util.*;

public class BoundedList<T> implements List<T> {
  private T[] elements;
  private int size = 0;
  private int capacity;

  /** Create a new BoundedList with the given maximum capacity.
   */
  public BoundedList(int capacity) {
    @SuppressWarnings("unchecked")
    T[] elements = (T[]) new Object[capacity];
    this.elements = elements;
    this.capacity = capacity;
  }

  @Override
  public T get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
    throw new IndexOutOfBoundsException("outside of current list size");
    }
    else {
      return elements[index];
    }
  }

  @Override
  public void set(int index, T data) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
    throw new IndexOutOfBoundsException("outside of current list size");
    }
    else {
      elements[index] = data;
    }
  }

  @Override
  public void add(int index, T data) throws IndexOutOfBoundsException, IllegalStateException {
    if (size == capacity) {
      throw new IllegalStateException("list is full");     
    }
    else if (index < 0) {
      throw new IndexOutOfBoundsException("negative index");
    }
    else if (index >= capacity) {
      throw new IndexOutOfBoundsException("outside of bounds of fixedList at size " + capacity);
    }
    else if (index > size) {
      throw new IndexOutOfBoundsException("adding more than +1 the current size");
    }
    else {
      if (size == 0) {
        elements[0] = data;
      }
      else if (index == size) {
        elements[index] = data;
      }
      else {
        for (int i = size; i > index; i--) {
          elements[i] = elements[i - 1];
          if (i == index + 1) {
            elements[index] = data;
          }
        }
      }
      size++;
    }
  }

  @Override
  public void remove(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("outside of current list size");
    }
    else {
      for (int i = index; i < size; i++) {
        if (i + 1 == size) {
          elements[i] = null;
        }
        else {
          elements[i] = elements[i + 1];
        }
      }
      size--;
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  // this produces a string like "[ 1 2 3 4 ]"
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    for (int i = 0; i < size; ++i) {
      sb.append(get(i).toString());
      sb.append(' ');
    }
    sb.append(']');
    return sb.toString();
  }
  public static void main(String args[]) {
    //Main is not necessary. It is just for me to test my own code
    List<Integer> lst = new BoundedList<Integer>(5);
    lst.add(0,6);
    lst.add(1,7);
    lst.add(2,8);
    lst.add(1,9);
    lst.add(0,10);
    lst.set(0,69);
        System.out.println(lst.get(2));
    //System.out.println();
    System.out.println(lst.toString());
  }
}
