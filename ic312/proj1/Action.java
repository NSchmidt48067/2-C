// Author: Nathaniel Schmidt
// File: Action.java
// Purpose: To facilitate the undo
// and redo feature in Editor.java
public class Action {
    //cmd = the command from user
    private char cmd;
    
    //val is only needed to undo a delete
    //It holds the deleted chat
    private char val;

    //txt allows for methods in MyText to 
    //be called from here
    private Text txt;

    //Constructor
    public Action(char c, char v, Text t) {
        cmd = c;
        val = v;
        txt = t;
    }

    //undo does the inverse of the command
    //It effectively issues the undo
    public void undo() {
        if (cmd == '>') {
            txt.moveLeft();
        }
        else if (cmd == '<') {
            txt.moveRight();
        }
        else if (cmd == 'i') {
            txt.moveLeft();
            txt.delete();
        }
        else if (cmd == 'd') {
            txt.insert(val);
            txt.moveLeft();
        }
    }

    //Undoes the redo
    //Is empty until an undo
    public void redo() {
        if (cmd == '>') {
            txt.moveRight();
        }
        else if (cmd == '<') {
            txt.moveLeft();
        }
        else if (cmd == 'i') {
            txt.insert(val);
        }
        else if (cmd == 'd') {
            txt.delete();
        }
    }
}