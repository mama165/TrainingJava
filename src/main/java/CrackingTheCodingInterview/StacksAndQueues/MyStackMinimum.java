package CrackingTheCodingInterview.StacksAndQueues;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Hashtable;

public class MyStackMinimum{
    private class StackNode {
        private int item;
        private StackNode next;
        private Hashtable ht;
        private int minimum;

        public StackNode(int item) {
            this.item = item;
            minimum = item;
        }
    }

    public StackNode top;
    static ArrayList list;

    public int pop() {
        if (top == null) throw new EmptyStackException();
        int item = top.item;
        top = top.next;

        if(item  == top.minimum) {
            // On va chercher la valeur minimum dans un tableau

        }

        return item;
    }


    public void push(int item) {
        if (top == null) throw new EmptyStackException();
        StackNode t = new StackNode(item);
        t.next = top;
        top = t;
        if (item < top.minimum) {
            top.minimum = item;
        }
    }

    public int min() {
        if (top == null) throw new EmptyStackException();
        return top.minimum;
    }
}

