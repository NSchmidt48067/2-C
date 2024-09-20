// Author: Nathaniel Schmidt
// File: MyBoundedStack.java
// Purpose: To implement a bounded stack
// over a generic type for a text editor

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
  //Establish variables
  @SuppressWarnings("unchecked")
  private T[] elements;// The stack
  private int size = 0;// Total number of elements
  private int capacity = 0;// Allowed number of elements
  // head keeps track of where the start of the 
  // array is and tail, the end of the array 
  private int head = 0;
  private int tail = 0;

  // The general idea behind this stack is that
  //the tail points one above where it actually is.
  //This allows for the push method to add an element
  //where the tail is and not change it until after it
  //has been set.

  //Constructor
  public MyBoundedStack(int capacity) {
    @SuppressWarnings("unchecked")
    T[] elements = (T[]) new Object[capacity];
    this.elements = elements;
    this.capacity = capacity;
  }

  /** Adds a new element to the top of the stack.
   *
   * If the stack is already at its capacity, a single item is
   * removed from the BOTTOM of the stack.
   */
  public void push(T item) {
    //Base Case to prevent - indexes later on
    if (capacity == 1) {
      elements[0] = item;
      if (size == 0) {
        size++;
      }
    }
    else {
      //Add the new element
      elements[tail] = item;
      tail = (tail + 1) % capacity;
      //If the array is full, move the head
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
      //Tail points 1 above where it actually is
      //To pop, you MUST subtract from tail first
      tail = (tail - 1 + capacity) % capacity;
      T item = elements[tail];
      elements[tail] = null;
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
    //Creat a new array to temporarily add to
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Object[capacity];
    
    //The lines below prepare the variables
    //that will get the values from the array.
    //count is how many elements will be in the array
    int count;
    if (size >= capacity) {
      count = capacity - 1;
    }
    else {
      count = size - 1;
    }
    //i is tail because the loop stores values in reverse
    int i = tail;
    
    do {
      if (count == -1)
        break;
      //tail is 1 greater than what we need at the start
      i = (i - 1 + this.capacity) % this.capacity;
      temp[count] = elements[i];
      count--;
    }while(i != head);

    //Reset size to a new value if necessary
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

  // Removes all elements from the stack
  //by creating a new array.
  public void clear() {
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Object[capacity];
    elements = temp;
    head = 0;
    tail = 0;
    size = 0;
  }

}