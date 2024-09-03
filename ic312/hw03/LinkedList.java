// Nathaniel Schmidt

import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
  // TODO your private inner classes and fields...

  @Override
  public T get(int index) throws IndexOutOfBoundsException {
    throw new UnsupportedOperationException(); // TODO erase this and get it working
  }

  @Override
  public void set(int index, T data) throws IndexOutOfBoundsException {
    throw new UnsupportedOperationException(); // TODO erase this and get it working
  }

  @Override
  public void add(int index, T data) throws IndexOutOfBoundsException {
    throw new UnsupportedOperationException(); // TODO erase this and get it working
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
}
