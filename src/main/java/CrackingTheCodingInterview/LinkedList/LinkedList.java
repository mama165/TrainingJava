package CrackingTheCodingInterview.LinkedList;

public class LinkedList {

    public static void main(String [] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

//        Node.removeDups(node1);
        Node.test(node1);
//        Node.displayNodes(node1);
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
        public static Node deleteNode(Node head, int data) {
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

        public static void removeDups(Node head) {
            Node node = head;

            while (node.next != null) {
//                displayNodes(node);
                Node subNode = node.next;
                while (subNode.next != null) {
                    if (node.data == subNode.data) {
                        // We need to delete the sub-node
//                        Node subNode = node.next.next;
                        node.next = node.next.next.next;
                    }
                    node.next = node.next.next;
                }
                node = node.next;
            }
        }

        public static void test(Node head) {
            Node node = head;

            while (node.next != null) {
                System.out.println("--");
                System.out.println(node.data);
                System.out.println("--");

                Node subNode = node.next;
                while (subNode.next != null) {
                    //displayNodes(node);
                    System.out.println(subNode.data);
                    subNode = subNode.next;
                }
                node = node.next;
            }
        }

        public static void displayNodes(Node head) {
            Node node = head;

            while (node != null) {
                System.out.println(node.data);
                node = node.next;
            }
        }
    }
}
