/** A List implementation with fixed, bounded capacity.
 *
 * When an instance of this class is constructed, the chosen capacity
 * represents the maximum size the list can ever grow to.
 */
import java.util.*;

public class BoundedList<T> implements List<T> {
  private T[] elements;
  // TODO you might want another private field...

  /** Create a new BoundedList with the given maximum capacity.
   */
  public BoundedList(int capacity) {
    @SuppressWarnings("unchecked")
    T[] elements = (T[]) new Object[capacity];
    this.elements = elements;
  }

  @Override
  public T get(int index) throws IndexOutOfBoundsException {
    throw new UnsupportedOperationException("DELETE THIS!");
  }

  @Override
  public void set(int index, T data) throws IndexOutOfBoundsException {
    throw new UnsupportedOperationException("DELETE THIS!");
  }

  @Override
  public void add(int index, T data) throws IndexOutOfBoundsException, IllegalStateException {
    throw new UnsupportedOperationException("DELETE THIS!");
  }

  @Override
  public void remove(int index) throws IndexOutOfBoundsException {
    throw new UnsupportedOperationException("DELETE THIS!");
  }

  @Override
  public int size() {
    throw new UnsupportedOperationException("DELETE THIS!");
  }

  @Override
  // this produces a string like "[ 1 2 3 4 ]"
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    for (int i = 0; i < size(); ++i) {
      sb.append(get(i).toString());
      sb.append(' ');
    }
    sb.append(']');
    return sb.toString();
  }
  public static void main(String args[]) {
    //Main is not necessary. It is just for me to test my own code
    List<Integer> lst = new BoundedList<Integer>(5);
  }
}
