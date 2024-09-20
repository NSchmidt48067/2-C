// Author: Nathaniel Schmidt
// File: MyText.java
// Purpose: To use a Linked List
//that acts as a text editor
// Makes use of a doubly linked list

import java.util.NoSuchElementException;
import java.lang.NullPointerException;

public class MyText implements Text {
  //Establish Node class
  private class Node {
    char data;
    //next and prev allow for a cursor
    //to move between nodes freely
    Node next;
    Node prev;

    public Node(char data, Node next, Node prev) {
      this.data = data;
      this.next = next;
      this.prev = prev;
    }
  }


  // Blank head Node to make insertion and deletion easier
  private Node head = new Node('x', null, null);
  private Node cursor = head;

  // This List will hold the cursor 1 node before the actual placement
  // Ex) blank header node means inserting the first char will be 
  //at node 2

  //Constructor
  public MyText() {}


    /** Returns the character at the current cursor position. */
  public char get() throws NoSuchElementException {
    if (cursor.next == null) {
      throw new NoSuchElementException("There is no data to get");
    }
    else {
      //REMINDER: cursor is 1 before actual placement
    return cursor.next.data;
    }
  }

  /** Inserts a new character before the current cursor position. */
  public void insert(char c) {
    cursor = insert(cursor, c);
  }

  //Helper method so user doesn't know the imlpementation
  private Node insert(Node cur, char data) {
    if (cur.next == null) {//At end of list
      //Create new node and move cursor to the next node
      cur.next = new Node(data, null, cur);
      return cur.next;
    } 
    else {//Insert node between two nodes
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
    if (cursor.next == null) {//At end of list
      throw new NoSuchElementException("There is no data to delete");
    }
    else {//temp is the node to be deleted
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

  //Prints out the input text and cursor placement
  public void print() {
    //Print out text
    for (Node temp = head; temp.next != null; temp = temp.next) {
      System.out.print(temp.next.data);
    }
    System.out.println();
    //Print out spaces until cursor goes in
    for (Node temp = head; temp != cursor; temp = temp.next ) {
        System.out.print(" ");
    }
    System.out.println("^");
  }
}