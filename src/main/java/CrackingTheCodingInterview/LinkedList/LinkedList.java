package CrackingTheCodingInterview.LinkedList;

public class LinkedList {

    public static void main(String [] args) {
        Node head = new Node(1);
        Node node1 = new Node(4);
        Node node2 = new Node(4);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        Node node7 = new Node(8);
        Node node8= new Node(9);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;


//        Node.displayNodes(head);

        Node.displayNodes(Node.deleteMiddleNode(head));


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

        public static Node deleteNodes(Node head, int data) {
            Node node = head;
//            boolean found = false;

//            if (node.data == data) {
//                head = head.next;  /* moved head */
//                node = head.next;  /* moved head */
//            }

            while (node != null && node.next != null) {
//                Node n = deleteNode(node.next, data);
                Node n = deleteNode(node.next, data);
                node.next = n;
                node = node.next;
            }
            return head;
        }

        public static Node removeDups(Node head) {
            Node node = head;

            // node vaut head !
            while (node != null && node.next != null) {



                Node tmp = null;
                // On cherche parmi les sous-noeuds
                Node subNode = node.next;

                //-----------------
                while (subNode != null) {


                    if (subNode.data == node.data) {
                        if(tmp == null) {
                            subNode = deleteNode(subNode, node.data);
//                            subNode = subNode.next;
                        } else {
//                     node.next = deleteNode(subNode, node.data);
                            tmp.next = deleteNode(subNode, node.data);
                            subNode = tmp; // Mon noeud temporaire devient le nouveau sous-noeud de reference
                            subNode = subNode.next;
                        }
                    } else {
                        tmp = subNode;
                        subNode = subNode.next;
                    }


                }

                node = node.next;
            }

            return node;
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
