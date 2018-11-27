package CrackingTheCodingInterview.StacksAndQueues;

import java.util.EmptyStackException;

/**
 * LIFO (Last In First Out)
 * @param <T>
 */
public class StackNode<T> {

    private T data;
    private StackNode<T> next;

    public StackNode(T data) {
        this.data = data;
    }

    /**
     * Remove the top item from the stack.
     *
     * @return
     */
    public T pop() {
        StackNode<T> stackNode = this;
        if (stackNode == null) throw new EmptyStackException();
        T item = stackNode.data;
        stackNode = stackNode.next;
        return item;
    }

    /**
     * Add an item to the top of the stack.
     * @param item
     */
    public void push(T item) {
        if (this == null) throw new EmptyStackException();
        StackNode stackNode = new StackNode(item);
        stackNode.next = this;
        this.data = (T) stackNode.data;
        this.next = stackNode.next;
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
}
