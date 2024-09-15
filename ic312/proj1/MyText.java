import java.util.NoSuchElementException;
import java.lang.NullPointerException;

//Linked List


public class MyText implements Text {
  private class Node {
    char data;
    int num;
    Node next;
    Node prev;

    public Node(char data, Node next, Node prev) {
      this.data = data;
      this.next = next;
      this.prev = prev;
      num = size;
      size++;
    }
  }


  //Blank head Node to make insertion and deletion easier
  private Node head = new Node('x', null, null);
  private Node cursor = head;
  private int size = 0;

  //Constructor
  public MyText() {}


    /** Returns the character at the current cursor position. */
  public char get() throws NoSuchElementException {
    if (cursor == null) {
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
    if (size == 0) {
      Node temp = new Node(data, null, head);
      head.next = temp;
      return temp;
    }
    else if (cur.next == null) {
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
      try {
        //Change node's 'prev' after deleted to current node
        cursor.next.next.prev = cursor;
        //Change current node's 'next' to deleted next's
        cursor.next = cursor.next.next;
      } catch(NullPointerException e) {
        //If there are fewer than 3 nodes, just delete the next node
        cursor.next = null;
      }
      size--;
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
    cursor = cursor.prev;
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
    cursor = cursor.next;
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
    for (Node temp = head; temp.next != null; temp = temp.next) {
      System.out.print(temp.next.data);
    }
    System.out.println();

    boolean carat = false;
    for (Node temp = head; temp.next != null; temp = temp.next ) {
      if (temp.num == cursor.num) {
        System.out.print("^");
        carat = true;
      }
      else {
        System.out.print(" ");
      }
    }
    if (!carat)
      System.out.print("^");
    System.out.println();
  }
}