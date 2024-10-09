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




  //Add implementation
  public void add(double x) {
    if (root == null) {
      root = new Node(x, null, null);
    }
    else {
      root = add(x, root);
    }
  }

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

  public Node Rebalance(Node cur) {
        //System.out.println(root.left.data);

    
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
    else {
      Node [] temp = new Node[1];
      root = removeMax(root, temp);
      return temp[0].data;
    }
  }

  public Node removeMax(Node cur, Node[] temp) {
    // Found largest Number
    if (cur.right == null) {
      temp[0] = cur;
      return cur;
    }
    cur.right = removeMax(cur.right, temp);

    cur.update();

    return cur;
  }

    public Double get(Double x) {
    //Find value from key
    return get(root, x);
  }

    //Helper class for implementation
  public Double get(Node cur, Double x) {
    // Key does not exist in tree
    if (cur == null) {
      return null;
    }
    int temp = Double.compare(x, cur.data);

    // Number is in tree, do nothing
    if (temp == 0) {
      return cur.data;
    }
    // If key is smaller, go left
    // Otherwise go right
    if (temp < 0) {
      return get(cur.left, x);
    }
    else if (temp > 0) {
      return get(cur.right, x);
    }
    else {
      return cur.data;
    }
  }

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

    public static void main(String[] args) {
    DoubleTree tree = new DoubleTree();
    tree.add(10.0);
    tree.add(11.0);
    tree.add(12.0);
    tree.add(9.0);
    tree.add(10.5);
    tree.add(12.1);
    tree.add(12.05);
    tree.printer();
    //System.out.println(tree.get(12.0));

  }
}
