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

    private QueueNode<T> first;
    private QueueNode<T> last;

    /**
     * Add an item to the end of the list.
     * @param item
     */
    public void add(T item) {
        QueueNode<T> t = new QueueNode(item);
        if (last != null) {
            last.next = t;
        }
        last = t;
        if (first == null) {
            first = last;
        }
    }

    /**
     * Remove the first item in the list.
     * @return
     */
    public T remove() {
        if (first == null) throw new NoSuchElementException();
        T data = first.data;
        first = first.next;
        if (first == null) {
            last = null;
        }
        return data;
    }

    /**
     * Return the top of the queue
     * @return
     */
    public T peek() {
        if (first == null) throw new NoSuchElementException();
        return first.data;
    }

    /**
     * Return true if and only if the queue is empty.
     * @return
     */
    public boolean isEmpty() {
        return first == null;
    }
}
