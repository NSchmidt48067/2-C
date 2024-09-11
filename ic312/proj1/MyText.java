import java.util.NoSuchElementException;


//Linked List


public class MyText implements Text {
  private class Node {
    char data;
    Node next;
    Node prev;

    public Node(char data, Node next, Node prev) {
      this.data = data;
      this.next = next;
      this.prev = prev;
    }
  }

  private Node cursor = null;
  //Blank head Node to make insertion and deletion easier
  private Node head = new Node('x', null, null);
  private int size = 0;


    /** Returns the character at the current cursor position. */
  public char get() throws IndexOutOfBoundsException {
    if (cursor == null) {
      throw new IndexOutOfBoundsException("There is no data to get");
    }
    else {
    return cursor.next.data;
    }
  }

  /** Inserts a new character before the current cursor position. */
  public void insert(char c) {
    insert(cursor, c);
  }
  //Helper method so user doesn't know the imlpementation
  private Node insert(Node cur, char data) {
    if (size == 0) {
      Node temp = new Node(data, null, head);
      head.next = temp;
      return temp;
    } else {
      return new Node(data, cur.next, cur);
    }
  }
  /** Deletes the character at the current cursor position.
   * The cursor should move to the right of what was just deleted.
   * @throws NoSuchElementException if the cursor is at the end.
   */
  public void delete() throws NoSuchElementException {
    if (cursor.next == null) {//At end of list and not 
      throw new NoSuchElementException("There is no data to delete");
    }
    else {
      cursor = cursor.next;
    }
  }

  /** Returns whether there is another character to the left of the cursor. */
  public boolean canMoveLeft() {

    return true;
  }

  /** Moves the cursor one character to the left.
   * @throws NoSuchElementException if the cursor is already at the beginning.
   */
  public void moveLeft() throws NoSuchElementException {

  }

  /** Returns whether the cursor is NOT at the end. */
  public boolean canMoveRight() {

    return true;
  }

  /** Moves the cursor one character to the right.
   * @throws NoSuchElementException if the cursor is already at the end.
   */
  public void moveRight() throws NoSuchElementException {

  }

  /** Displays the current sequence of characters one one line, with the cursor underneath.
   *
   * Two lines should always be printed to System.out.
   *
   * For example, if the current characters are a, b, c, d, and the cursor is at the end,
   * we should see:
   *     abcd
   *         ^
   *
   * With the same characters, but the cursor under the 'b', we would see:
   *     abcd
   *      ^
   */
  public void print() {
    print(head, size);
  }
  public void print(Node cur, int num) {
    if (num == 0) {
      System.out.println(cur.data);
    }
    else {
      print(cur.next, num--);
    }
  }
}