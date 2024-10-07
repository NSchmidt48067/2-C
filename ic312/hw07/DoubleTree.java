import java.util.NoSuchElementException;

/* Nathaniel Schmidt
 * This is the place to write any sources you used etc.
 */
public class DoubleTree implements AddMax {
  // Thought
  // Track left height and right height and parent node
  // In order traversal down to find duplicate
  // if not found, add node and then change heights
  // and check node balance. Rotate as necessary
  // Could work


  
 //Node class for the BST
  private class Node {
    int height = 0;
    int balance = 0;
    double data;
    Node left;
    Node right;

    public Node(double d, Node l, Node r) {
      data = d;
      left = l; 
      right = r;
    }
    public void update() {
      balance = (left != null ? left.height: -1) - (right != null ? right.height: -1);
      if (left == null && right == null) {
        height = 0;
      }
      else {
        height = Math.max((left != null ? left.height: -1) - (right != null ? right.height: -1)) + 1;
      }
    }
  }

  //Constructor
  public DoubleTree() {}

  //Class variables
  private Node root = null;




  //Add implementation
  public void add(double x) {
    if (root == null) {
      root = new Node(x, null, null);
    }
    else {
      add(x, root);
    }
  }

  public void add(double x, Node cur) {
    // Base case
    if (cur == null) {
      return;
    }
    int temp = Double.compare(x, cur.data);

    // Number is in tree, do nothing
    if (temp == 0) {
      return;
    }

    // Key is not in tree, create a new node
    if (cur.left == null && temp < 0) {
      cur.left = new Node(x, null, null);
      
    }
    else if (cur.right == null && temp > 0) {
      cur.right = new Node(x, null, null);
      
    }
    // If key is smaller, go left
    // Otherwise go right
    if (temp < 0) {
      add(x, cur.left);
    }
    else if (temp > 0) {
      add(x, cur.right);
    }
  }

  //Rotates the tree to the left
  public Node leftRotate(Node cur) {
    return null;
  }
  
  //Rotates the tree to the right
  public Node rightRotate(Node cur) {
    return null;
  }

  //RemoveMax implementation
  public double removeMax() throws NoSuchElementException{
    return 2.1;
  }

  public double removeMax(Node cur) {
    return 2.1;
  }
}
