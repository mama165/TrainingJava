package fr.coding.cracking.StacksAndQueues;

import java.util.EmptyStackException;

public class MyStack<T> {
    private static class StackNode<T> {

        private T data;
        private StackNode<T> next;

        public StackNode() {}

        public StackNode(T data) {
            this.data = data;
        }
    }

    private StackNode<T> top;

    public T pop() {
        if (top == null) throw new EmptyStackException();
        T item = top.data;
        top = top.next;
        return item;
    }

    /**
     * Add an item to the top of the stack.
     * @param item
     */
    public void push(T item) {
        StackNode<T> t = new StackNode(item);
        t.next = top;
        top = t;
    }

    /**
     * Return the top of the stack.
     * @return
     */
    public T peek() {
        if (top == null) throw new EmptyStackException();
        return top.data;
    }

    /**
     * Return true if and only if the stack is empty.
     * @return
     */
    public boolean isEmpty() {
        return top == null;
    }


    /**
     * Reverse a string using a stack
     * @param s
     */
    public void reverse(String s) {
        StackNode<Character> top = new StackNode<>();
         char [] c = s.toCharArray();

         for(int i = 0; i < c.length; i++) {
             top.data =  c[i];
             top = top.next;
         }

         StringBuilder sb = new StringBuilder();
         while (top != null) {
             sb.append(top.data);
         }
    }
}