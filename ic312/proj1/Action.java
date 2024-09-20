public class Action {
    private char cmd;
    private char val;
    private Text txt;

    public Action(char c, char v, Text t) {
        cmd = c;
        val = v;
        txt = t;
    }

    public void Decipher() {
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
            //txt.moveLeft();
            txt.insert(val);
            txt.moveLeft();
        }
    }


}