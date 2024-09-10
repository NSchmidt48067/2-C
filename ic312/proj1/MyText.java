import java.util.NoSuchElementException;


//ARRAY IMPLEMENTATION


public class MyText implements Text {
    /** Returns the character at the current cursor position. */
  public char get() throws NoSuchElementException {

  }

  /** Inserts a new character before the current cursor position. */
  public void insert(char c) {

  }

  /** Deletes the character at the current cursor position.
   * The cursor should move to the right of what was just deleted.
   * @throws NoSuchElementException if the cursor is at the end.
   */
  public void delete() throws NoSuchElementException {

  }

  /** Returns whether there is another character to the left of the cursor. */
  public boolean canMoveLeft() {

  }

  /** Moves the cursor one character to the left.
   * @throws NoSuchElementException if the cursor is already at the beginning.
   */
  public void moveLeft() throws NoSuchElementException {

  }

  /** Returns whether the cursor is NOT at the end. */
  public boolean canMoveRight() {

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
    
  }
}