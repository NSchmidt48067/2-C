import java.util.NoSuchElementException;
import java.util.*;

/** A stack with limited (but changeable) capacity.
 *
 * When new items pushed, the go on the "top" of the stack.
 * Calling pop() also removes from the "top" of the stack,
 * so that push/pop are LIFO.
 *
 * But stacks also have a fixed capacity. WHen calling push(),
 * if the stack is already at its capacity, the oldest item
 * ("bottom" of the stack) is silently removed.
 */
public class MyBoundedStack<T> implements BoundedStack<T> {
  @SuppressWarnings("unchecked")
  private T[] elements;
  private int size = 0;
  private int capacity = 0;
  //head keeps track of where the start and end of the array is
  private int head = 0;
  private int tail = 0;

  public MyBoundedStack(int capacity) {
    @SuppressWarnings("unchecked")
    T[] elements = (T[]) new Object[capacity];
    this.elements = elements;
    this.capacity = capacity;
  }

  public void push(T item) {
    if (capacity == 1) {
      elements[0] = item;
      size = 1;
      }
    
    else {
      elements[tail] = item;
      tail = (tail + 1) % capacity;
    if (size == capacity) {
      head = (head + 1) % capacity;
    }
    else {
      size++;
    }
    }


  }

  /** Removes and returns the element at the top of the stack.
   * @throws NoSuchElementException if the stack is empty.
   */
  @Override
  public T pop() throws NoSuchElementException {
    if (isEmpty()) {
        throw new NoSuchElementException("The Stack is empty");
    }
    else {
      tail = (tail - 1 + capacity) % capacity;
      T item = elements[tail];
      elements[tail] = null;
      size--;
      return item;
    }
  }

  public void setCapacity(int newCapacity) {
      @SuppressWarnings("unchecked")
      T[] temp = (T[]) new Object[capacity];
      int count;
      
      if (size >= capacity) {
        count = capacity - 1;
        }
      else {
        count = size - 1;
      }
      int i = tail;
      do {
        i = (i - 1 + this.capacity) % this.capacity;
        temp[count] = elements[i];
        count--;
        if (count == -1)
          break;
      }while(i != head);

      if (size >= capacity){
        size = capacity;
      }
      head = 0;
      tail = size;
      this.capacity = capacity;

      elements = temp;
  }

  /** Returns whether the stack is currently empty. */
  public boolean isEmpty() {

    return size == 0;
  }

  /** Removes all elements from the stack.
   *
   * The capacity should remain unchanged.
   */
  public void clear() {
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Object[capacity];
    elements = temp;
    head = 0;
    tail = 0;
    size = 0;
  }


    public T get(int num) {
        return elements[(num + this.head) % this.capacity];
    }

    public String getAll(){
      return size + " " + head + " " + tail;
    }



      @Override
  // this produces a string like "[ 1 2 3 4 ]"
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    for (int i = 0; i < size; i++) {
      sb.append(get(i).toString());
      sb.append(' ');
    }
    sb.append(']');
    return sb.toString();
  }
}
