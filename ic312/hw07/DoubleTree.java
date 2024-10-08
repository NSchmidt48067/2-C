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
        height = Math.max((left != null ? left.height: -1),
          (right != null ? right.height: -1)) + 1;
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
    //Call update to change the height and balance
    cur.update();
    // Do nothing if the node is balanced
    if (isBalanced(cur)) {

    }
    // Rotations
    else {
      // Left leaning
      if (cur.balance < -1) {
        // Double rotation
        if (cur.left != null && cur.left.balance == 1) {
          // Left rotation on left child
          if (cur.left.left != null) {
            cur.left = leftRotate(cur.left);
          }
          // Right rotation on this node
          cur = rightRotate(cur);
        }
        // Single rotation
        else {
          // Right Rotation
          cur = rightRotate(cur);
        }
      }
      // Right leaning
      else if (cur.balance > 1) {
        // Double rotation
        if (cur.right != null && cur.right.balance == -1) {
          // Right rotation on right child
          if (cur.right.right != null) {
            cur.right = rightRotate(cur.right);
          }
          // Left rotation on this node
          cur = leftRotate(cur);
        }
        // Single rotation
        else {
          // Left rotation
          cur = leftRotate(cur);
        }
      }
    }
  }

  public boolean isBalanced(Node cur) {
    return (cur.balance <= 1 && cur.balance >= -1);
  }

  //Rotates the tree to the left
  public Node leftRotate(Node oldRoot) {
    Node newRoot = oldRoot.right; 
    Node middle = newRoot.left;   
    newRoot.left = oldRoot;
    oldRoot.right = middle;
    oldRoot.update();
    newRoot.update();
    return newRoot;
  }
  
  //Rotates the tree to the right
  public Node rightRotate(Node oldRoot) {
    Node newRoot = oldRoot.left; 
    Node middle = newRoot.right;   
    newRoot.right = oldRoot;    
    oldRoot.left = middle;
    oldRoot.update();
    newRoot.update();
    return newRoot;
  }

  //RemoveMax implementation
  public double removeMax() throws NoSuchElementException{
    if (root == null) {
      throw new NoSuchElementException("The tree is empty");
    }
    else {
      Node temp = removeMax(root);
      root = temp.left;
      return temp.data;
    }
  }

  public Node removeMax(Node cur) {
    // Found largest Number
    if (cur.right == null) {
      return cur;
    }
    cur.right = removeMax(cur.right);

    cur.update();

    return cur;
  }
}
