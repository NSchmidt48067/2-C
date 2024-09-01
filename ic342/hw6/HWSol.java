/*********************************
 * MIDN Schmidt 265646
 * HW6
 * HWSol.java
 ********************************/

import java.util.*;
import java.io.*;

public class HWSol
{
    private int state = 0;
    private boolean accept = false;
    private ArrayList<String> al;;

    public static void main(String args[]) {
        HWSol hw = new HWSol();

    }

    public HWSol() {
        Scanner in = new Scanner(System.in);
        this.al = new ArrayList<String>(Arrays.asList((in.next()).split("", 0))); 

        while (!al.isEmpty()) {
            if (state == 0){
                state0();
            }
            else if (state == 1){
                state1();
            }
            else if (state == 2){
                state2();
            }
            else if (state == 3){
                state3();
            }
            else if (state == 4){
                state4();
            }
            al.remove(0);
        }

        if (accept) {
            System.out.println("accept");
        }
        else {
            System.out.println("reject");
        }
    }

    private void state0() {
        if (al.get(0).equals("a")) {
            state = 1;
            accept = true;
        }
        else {
            state = 3;
            accept = false;
        }
    }

    private void state1() {
        if (al.get(0).equals("a")) {
            state = 1;
            accept = true;
        }
        else {
            state = 2;
            accept = false;
        }
    }

    private void state2() {
        accept = false;
    }

    private void state3() {
        if (al.get(0).equals("a")) {
            state = 4;
            accept = true;
        }
        else {
            state = 2;
            accept = false;
        }
    }

    private void state4() {
        if (al.get(0).equals("a")) {
            state = 2;
            accept = false;
        }
        else {
            state = 4;
            accept = true;
        }
    }

}