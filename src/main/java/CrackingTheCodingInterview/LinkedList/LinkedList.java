package CrackingTheCodingInterview.LinkedList;

public class LinkedList {

    public static void main(String [] args) {
        Node head = new Node(0);
        Node node1 = new Node(3);
        Node node2 = new Node(3);
        Node node3 = new Node(3);
        Node node4 = new Node(3);
        Node node5 = new Node(3);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

//        Node.deleteNodes(head, 0);
//        Node.deleteNodes(head, 3);
//        Node n = Node.deleteNode(head, 3);
//        Node n = Node.deleteNodes(head, 1);
//        Node.removeDups(head);
//        Node.displayNodes(n);

        Node.removeDupsBis(head);
        Node.displayNodes(head);
//        Node.test(head);
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

        public static Node deleteNodes(Node head, int data) {
            Node node = head;

            if (node.data == data) {
                head = head.next;  /* moved head */
            }

            while (node != null && node.next != null) {
                if (node.next.data == data) {
                    node.next = node.next.next;
                }
                node = node.next;
            }
            return head;
        }

        public static void removeDups(Node head) {
            Node node = head;

            while (node.next != null) {
//                displayNodes(node);
                Node current = node;
                Node subNode = current.next;
                while (subNode.next != null) {
                    if (node.data == subNode.data) {
                        // We need to delete the sub-node
//                        Node subNode = node.next.next;
                        deleteNode(subNode, node.data);
                    }
                    subNode = subNode.next;
                }
                node = node.next;
            }
        }

        public static void removeDupsBis(Node head) {
            Node node = head;

            while (node != null && node.next != null) {
//                displayNodes(node);
//                Node subNode = node.next;
//                Node subNode = node.next;
                Node n = deleteNodes(node.next, node.data);

                node.next = n;
                node = n;
            }
//            displayNodes(node);
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
