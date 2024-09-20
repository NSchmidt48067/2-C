import java.util.NoSuchElementException;
import java.lang.NullPointerException;

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


  //Blank head Node to make insertion and deletion easier
  private Node head = new Node('x', null, null);
  private Node cursor = head;

  //Constructor
  public MyText() {}


    /** Returns the character at the current cursor position. */
  public char get() throws NoSuchElementException {
    if (cursor.next == null) {
      throw new NoSuchElementException("There is no data to get");
    }
    else {
    return cursor.next.data;
    }
  }

  /** Inserts a new character before the current cursor position. */
  public void insert(char c) {
    cursor = insert(cursor, c);
  }
  //Helper method so user doesn't know the imlpementation
  private Node insert(Node cur, char data) {
    if (cur.next == null) {
      //Create new node and move cursor to the next node
      cur.next = new Node(data, null, cur);
      return cur.next;
    } 
    else {
      Node temp = new Node(data, null, null);
      temp.next = cur.next;
      cur.next.prev = temp;
      cur.next = temp;
      temp.prev = cur;
      return temp;
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
      Node temp = cursor.next;
      if (temp.next != null) {
        temp.next.prev = cursor;
      }
      cursor.next = temp.next;
    }
  }

  /** Returns whether there is another character to the left of the cursor. */
  public boolean canMoveLeft() {
    if (cursor.prev != null){
      return true;
    }
    else {
      return false;
    }
  }

  /** Moves the cursor one character to the left.
   * @throws NoSuchElementException if the cursor is already at the beginning.
   */
  public void moveLeft() throws NoSuchElementException {
    if (cursor.prev == null) {
      throw new NoSuchElementException("Cannot move left");
    }
    else {
      cursor = cursor.prev;
    }
  }

  /** Returns whether the cursor is NOT at the end. */
  public boolean canMoveRight() {
    if (cursor.next != null){
      return true;
    }
    else {
      return false;
    }
  }

  /** Moves the cursor one character to the right.
   * @throws NoSuchElementException if the cursor is already at the end.
   */
  public void moveRight() throws NoSuchElementException {
    if (cursor.next == null) {
      throw new NoSuchElementException("Cannot move right");
    }
    else {
      cursor = cursor.next;
    }  }

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
    for (Node temp = head; temp.next != null; temp = temp.next) {
      System.out.print(temp.next.data);
    }
    System.out.println();

    for (Node temp = head; temp != cursor; temp = temp.next ) {
        System.out.print(" ");
    }
    System.out.println("^");
  }
}