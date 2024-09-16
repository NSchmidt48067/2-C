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
  private T[] elements = (T[]) new Object[10];
  private int size = 0;
  private int capacity = 10;
  //head keeps track of where the start and end of the array is
  private int head = 0;
  private int tail = 0;

  public MyBoundedStack() {}

  /** Adds a new element to the top of the stack.
   *
   * If the stack is already at its capacity, a single item is
   * removed from the BOTTOM of the stack.
   */
  public void push(T item) {
    //Reached capacity
    if (tail == capacity) {
      head = 1;
      tail = 0;
      elements[tail] = item;
    }
    //If the head is at the last spot in the array
    else if (head == capacity - 1) {
      head = 0;
      tail = capacity - 1;
      elements[tail] = item;
    }
    //If the tail is at a position before the head in the array
    else if (tail < head) {
      tail++;
      head++;
      elements[tail] = item;
    }
    //When the tail is behind the head
    else {
        tail++;
        elements[tail] = item;
    }
    size++;
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
      T item = elements[tail];
      elements[tail] = null;
      tail--;
      size--;
      return item;
    }
  }

  /** Changes the capacity to the given value.
   *
   * If the current capacity is greater than the given capacity,
   * then elements may need to be removed from the BOTTOM of the
   * stack so that it reaches the desired capacity.
   *
   * This affects all future push/pop operations, but not past
   * ones. That is, increasing the capacity does not make
   * the stack magically "remember" things which have already been
   * removed.
   */
  public void setCapacity(int capacity) {
    T[] temp = (T[]) new Object[capacity];
    int count = 0;
    for (int i = head; i < capacity; i = (i + 1) % capacity) {
        temp[count] = elements[i];
        count++;
    }
    elements = temp;
  }

  /** Returns whether the stack is currently empty. */
  public boolean isEmpty() {
    return size == capacity;
  }

  /** Removes all elements from the stack.
   *
   * The capacity should remain unchanged.
   */
  public void clear() {
    elements = (T[]) new Object[capacity];
    head = 0;
    tail = 0;
  }


  public static void main(String args[]) {
    //Main is not necessary. It is just for me to test my own code
    MyBoundedStack<Character> stack = new MyBoundedStack<Character>();

  }
}
