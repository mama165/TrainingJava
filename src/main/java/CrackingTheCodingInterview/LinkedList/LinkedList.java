package CrackingTheCodingInterview.LinkedList;

public class LinkedList {

    public static void main(String [] args) {
        Node head = new Node(1);
        Node node1 = new Node(4);
        Node node2 = new Node(4);
        Node node3 = new Node(5);
        Node node4 = new Node(4);
        Node node5 = new Node(4);
        Node node6 = new Node(6);
        Node node7 = new Node(4);
        Node node8= new Node(4);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;


//        Node.displayNodes(head);

//        Node.displayNodes(Node.removeDups(head));
        Node.displayNodes(Node.removeDups(head));

//        Node.displayNodes(Node.deleteMiddleNode(head));


//        Node.displayNodes(Node.deleteNodes(head, 3));
//        Node.displayNodes(Node.removeDups(head));
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

        public static Node removeDups(Node head) {
            Node node = head;

            while (node != null) {
                Node subNode = node.next;
                Node tmp = null;
                while (subNode != null) {
                    if (subNode.data == node.data) {
                        subNode = deleteNode(subNode, node.data);
                        if(tmp == null) {
                            tmp = subNode;
                        } else {
                            tmp.next = subNode;
                        }
                        node.next = subNode;
                    } else {
                        tmp = subNode;
                        subNode = subNode.next;
                    }
                }
//                node.next = subNode;
                node = node.next;
            }
            return head;
        }

        /*
          Head =>  {3 -> 3 -> 3 -> 4 -> 5 -> 6 -> 3 -> 8}   Fin .next = null
         */
        public void ReturnKthtoLast(Node head, int k) {
            Node node = head;
            while (node != null) {

                node = node.next;
            }
        }

        public static Node deleteMiddleNode(Node head) {
            Node node = head;
            Node subNode = node;
            Node tmp = subNode;
            int move = 0;

            while (node != null) {
                if (move > 0 && move % 2 == 0) {
                    tmp = subNode;
                    subNode = subNode.next;
                }
                node = node.next;
                move++;
            }
            tmp.next = deleteNode(subNode, subNode.data);
            return head;
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
