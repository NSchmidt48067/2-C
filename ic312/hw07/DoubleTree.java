import java.util.NoSuchElementException;

// Nathaniel Schmidt
// Talked to George Prielipp about this

public class DoubleTree implements AddMax {
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
      balance = (right != null ? right.height: -1) - (left != null ? left.height: -1);
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
  private double value = 0;

  //Add implementation
  public void add(double x) {
    if (root == null) {
      root = new Node(x, null, null);
    }
    else {
      root = add(x, root);
    }
  }

  // Adds number if it is not in the list already
  public Node add(double x, Node cur) {
    // Value is not in the tree, add it
    if (cur == null) {
      return new Node(x, null, null);
    }
    int temp = Double.compare(x, cur.data);
    // Number is in tree, do nothing
    if (temp == 0) {
      return cur;
    }
    // If key is smaller, go left
    // Otherwise go right
    if (temp < 0) {
      cur.left = add(x, cur.left);
    }
    else if (temp > 0) {
      cur.right = add(x, cur.right);
    }
    //Update the balance factor and height
    cur.update();
    // If rotations are necessary, perform them
    cur =  Rebalance(cur);
    return cur;

  }

  // Checks if the node needs to be rebalanced
  public Node Rebalance(Node cur) {
    // Left leaning
    if (cur.balance < -1) {
      // Double rotation
      if (cur.left != null && cur.left.balance == 1) {
          cur.left = leftRotate(cur.left);
      }
      // Right rotation on this node
      return rightRotate(cur);
    }
    // Right leaning
    else if (cur.balance > 1) {
      // Double rotation
      if (cur.right != null && cur.right.balance == -1) {
          // Right rotation on right child
          cur.right = rightRotate(cur.right);
        }
        // Left rotation
        return leftRotate(cur);
    }
    // No rotations
    return cur;
  }

  public boolean isBalanced(Node cur) {
    return (cur.balance <= 1 && cur.balance >= -1);
  }

  //Rotates the tree to the left
  public Node leftRotate(Node oldRoot) {
    Node newRoot = oldRoot.right; 
    Node middle = (newRoot == null ? null: newRoot.left);   
    newRoot.left = oldRoot;
    oldRoot.right = middle;
    // Update everything
    oldRoot.update();
    newRoot.update();
    if (newRoot.left != null) newRoot.left.update();
    if (newRoot.right != null) newRoot.right.update();
    return newRoot;
  }
  
  //Rotates the tree to the right
  public Node rightRotate(Node oldRoot) {
    Node newRoot = oldRoot.left; 
    Node middle = (newRoot == null ? null : newRoot.right);   
    newRoot.right = oldRoot;    
    oldRoot.left = middle;
    // Update everything
    oldRoot.update();
    newRoot.update();
    if (newRoot.left != null) newRoot.left.update();
    if (newRoot.right != null) newRoot.right.update();
    return newRoot;
  }

  //RemoveMax implementation
  public double removeMax() throws NoSuchElementException{
    if (root == null) {
      throw new NoSuchElementException("The tree is empty");
    }
    // Removal looks 2 nodes ahead
    // Thus, this checks that there is at least
    // One node to the right
    else if (root.right == null) {
      value = root.data;
      root = root.left;
      if (root != null) {
        root.update();
        root = Rebalance(root);
      }
      return value;
    }
    else {
      root = removeMax(root);
      return value;
    }
  }

  public Node removeMax(Node cur) {
    // Found largest Number
    if (cur.right.right == null) {
      value = cur.right.data;
      Node temp = cur.right.left; // Handles possility of elbow node
      cur.right = temp;
      cur.update();
      cur = Rebalance(cur);
      return cur;
    }
    cur.right = removeMax(cur.right);
    cur.update();
    return cur;
  }

  // Helper mmethod that prints out 
  // the tree in a preorder traversal
  public void printAll(Node cur) {
    if (cur == null) {
      return;
    }
    System.out.println(cur.data);
    System.out.println(cur.height);
    System.out.println(cur.balance);
    System.out.println();

    printAll(cur.left);
    printAll(cur.right);
  }

  public void printer() {
    printAll(root);
  }

  //   public static void main(String[] args) {
  //   DoubleTree tree = new DoubleTree();
  //   tree.add(10.0);
  //   tree.add(11.0);
  //   tree.add(12.0);
  //   tree.add(9.0);
  //   tree.add(10.5);
  //   tree.add(12.5);
  //   tree.add(12.1);
  //   tree.add(900);
  //   tree.add(1000);
  //       System.out.println();
  //   System.out.println(tree.removeMax());
  //   System.out.println();
  //   tree.add(13);

  //   tree.printer();
  //   //System.out.println(tree.get(12.0));

  // }
}
