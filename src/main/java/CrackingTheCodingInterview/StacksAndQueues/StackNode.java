package CrackingTheCodingInterview.StacksAndQueues;

import java.util.EmptyStackException;

/**
 * LIFO (Last In First Out)
 * @param <T>
 */
public class StackNode<T> {

    public T data;
    public StackNode<T> next;

    public StackNode(T data) {
        this.data = data;
    }

    /**
     * Remove the top item from the stack.
     *
     * @return
     */
    public T pop() {
        StackNode<T> top = this;
        if (top == null) throw new EmptyStackException();
        T item = top.data;
        top.data = top.next.data;
        top = top.next;
        return item;
    }

    /**
     * Add an item to the top of the stack.
     * @param item
     */
    public void push(T item) {
        if (this == null) throw new EmptyStackException();
        StackNode<T> top = this;
        StackNode<T> t = new StackNode(item);
        t.next = top;
        top.next = t;
    }

    /**
     * Return the top of the stack.
     * @return
     */
    public T peek() {
        if (this == null) throw new EmptyStackException();
        return this.data;
    }

    /**
     * Return true if and only if the stack is empty.
     * @return
     */
    public boolean isEmpty() {
        return this == null;
    }

    public int size() {
        StackNode stackNode = this;
        int size = 0;

        while (stackNode != null) {
            size++;
            stackNode = stackNode.next;
        }
        return size;
    }
}
