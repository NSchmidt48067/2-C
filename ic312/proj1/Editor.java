import java.util.Scanner;

public class Editor {
  private Text txt = new MyText();
  private BoundedStack<Action> stk = new MyBoundedStack<>(5);

  /** Displays information on available commands.
   * THis will NOT be part of any autotesting.
   * You can (and should!) update as new commands are enabled.
   */
  public static void showHelp() {
    // Note to students: we won't test this function with autotesting.
    // You should keep it up to date if you add new commands, but
    // the exact wording etc. is up to you!
    System.out.println("HELP");
    System.out.println("  iX   insert letter X before the cursor");
    System.out.println("  d    delete letter at current position, then move cursor right");
    System.out.println("  <    move cursor left");
    System.out.println("  >    move cursor right");
    System.out.println("  p    print the entire txt on one line, with the cursor on the next line");
    System.out.println("  h    show this help message");
    System.out.println("  q    quit");
  }

  /** A simple helper function to display if anything goes wrong.
   * Do NOT modify this to make more descriptive error messages,
   * as that will break the autotests.
   */
  public static void showError() {
    System.out.println("ERROR");
  }

  /** Runs the command specified by the given input line.
   * @return true if the command was not "quit".
   */
  public boolean processLine(String line) {
    if (line.length() == 0) line = "h";
    switch (line.charAt(0)) {
      case 'i':
        if (line.length() == 2){
          store(line);
          txt.insert(line.charAt(1));
        }
        else {showError();}
        break;
      case 'd':
        if (txt.canMoveRight()){
          store(line);
          txt.delete();
        }
        else showError();
        break;
      case '<':
        if (txt.canMoveLeft()) {
          store(line);
          txt.moveLeft();
        }
        else showError();
        break;
      case '>':
        if (txt.canMoveRight()) {
          store(line);
          txt.moveRight();
        }
        else showError();
        break;
      case 'p':
        txt.print();
        break;
      case 'q':
        return false;
      //Undo case: pop action off of stack and call undo
      //undo: Runs commands to undo previous actions
      case 'u':
        try {
          Action a = stk.pop();
          a.undo();
        } catch (Exception e) {
          showError();
        }
        break;
      //Capacity case: Changes undo capacity
      case 'c':
        if (line.length() > 1) {
          String temp = line.substring(1);
          int num = 0;
          try {//In case user does not input numbers
            num = Integer.parseInt(temp);
          } catch (NumberFormatException e) {
            showError();
            break;
          }
          stk.setCapacity(num);
        }
        else {
          showError();
        }
        break;
      default:
        showHelp();
    }
    return true;
  }

  //Method to make processLine cleaner
  //Sets action parameters to be able to undo
  //what the user did
  public void store(String line) {
    Action act;
    if (line.charAt(0) == 'd') {
          act = new Action(line.charAt(0), txt.get(), txt);
    }
    else {
      act = new Action(line.charAt(0), 'x', txt);
    }
    stk.push(act);
  }

  public static void main(String[] args) {
    Editor editor = new Editor();
    Scanner in = new Scanner(System.in);
    java.io.Console cons = System.console();
    do {
      if (cons != null) {
        // Only print the "command: " prompt if the output is to a
        // live terminal window (so it won't show up in autotesting).
        cons.printf("command: ");
        cons.flush();
      }
    } while (editor.processLine(in.nextLine()));
  }
}
