package CrackingTheCodingInterview.LinkedList;

public class LinkedList {

    public static void main(String [] args) {
        Node node1 = new Node(10);
        Node node2 = new Node(10);
        Node node3 = new Node(10);
        Node node4 = new Node(10);
        Node node5 = new Node(10);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
    }
    public static class Node {

        Node next;
        int data;

        public Node(int data) {
            this.data = data;
        }

        public void appendToTail(int d) {
            Node n = this;
            Node end = new Node(d);
            while (n.next != null) {
                n = n.next;
            }
            n.next = end;
        }

        /**
         * Return new head after deletion
         * @param head
         * @param data
         * @return
         */
        public Node deleteNode(Node head, int data) {
            Node node = head;

            if (node.data == data) {
                return head.next;  /* moved head */
            }

            while (node.next != null) {
                if (node.next.data == data) {
                    node.next = node.next.next;
                    return head; /* head didn't change */
                }
                node = node.next;
            }
            return head;
        }
    }
}
