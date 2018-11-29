package CrackingTheCodingInterview.StacksAndQueues;

import java.util.NoSuchElementException;

public class MyQueue<T> {
    private static class QueueNode<T> {
        T data;
        QueueNode<T> next;

        public QueueNode(T data) {
            this.data = data;
        }
    }

    private QueueNode<T> head;
    private QueueNode<T> tail;

    /**
     * Add an item to the end of the list.
     * @param item
     */
    public void add(T item) {
        QueueNode<T> t = new QueueNode(item);
        if (tail != null) {
            tail.next = t;
        }
        tail = t;
        if (head == null) {
            head = tail;
        }
    }

    /**
     * Remove the head item in the list.
     * @return
     */
    public T remove() {
        if (head == null) throw new NoSuchElementException();
        T data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return data;
    }

    /**
     * Return the top of the queue
     * @return
     */
    public T peek() {
        if (head == null) throw new NoSuchElementException();
        return head.data;
    }

    /**
     * Return true if and only if the queue is empty.
     * @return
     */
    public boolean isEmpty() {
        return head == null;
    }
}
