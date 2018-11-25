package CrackingTheCodingInterview.LinkedList;

public class Node {

    public Node next;
    int data;
    int myId;

    public Node(int data, int myId) {
        this.data = data;
        this.myId = myId;
    }

    // public void appendToTail(int d) {
    //     Node n = this;
    //     Node end = new Node(d);
    //     while (n.next != null) {
    //         n = n.next;
    //     }
    //     n.next = end;
    // }

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

    /*
    
    1-> 2 -> 5 -> 7 -> 4 -> 1 -> 6 -> 7 -> 8   valeur
    1   2    3    4    5    6    7    8    9   id
    U                                        
    P        L
             L-1                           
    */
    public static Node removeDups(Node head) {

        Node upperNodeToCompare = head;
        while (upperNodeToCompare != null) {
            Node lowerNodeToCompareWith = upperNodeToCompare.next;
            Node prevLower = upperNodeToCompare; //yes

            while (lowerNodeToCompareWith != null) {
                if (upperNodeToCompare.data == lowerNodeToCompareWith.data) {
                    prevLower.next = lowerNodeToCompareWith.next; // On ne veut plus de référence vers le noeud "lowerNodeToCompareWith"
                    prevLower = upperNodeToCompare; // On stocke le "upperNodeToCompare" car "lowerNodeToCompareWith" a disparu !
                } else {
                    prevLower = lowerNodeToCompareWith; // On stocke le "lowerNodeToCompareWith" car il est toujours présent
                }
                lowerNodeToCompareWith = lowerNodeToCompareWith.next;
            }
            upperNodeToCompare = upperNodeToCompare.next;
        }
        return head;
    }

    public static Node rmD(Node head) {

        Node node = head;
        while (node != null) {
            Node subnode = node.next;
            Node tmp = node; //yes

            while (subnode != null) {
                if (node.data == subnode.data) {
                    tmp.next = subnode.next; // On ne veut plus de référence vers le noeud "lowerNodeToCompareWith"
                    tmp = node; // On stocke le "upperNodeToCompare" car "lowerNodeToCompareWith" a disparu !
                } else {
                    tmp = subnode; // On stocke le "lowerNodeToCompareWith" car il est toujours présent
                }
                subnode = subnode.next;
            }
            node = node.next;
        }
        return head;
    }

    public static Node removeDOldups(Node head) {

        Node upperNodeToCompare = head;
        while(upperNodeToCompare != null)
        {
            Node lowerNodeToCompareWith = upperNodeToCompare.next;
            Node prevLower = upperNodeToCompare; //yes
            
            while(lowerNodeToCompareWith != null)
            {
                if(upperNodeToCompare.data == lowerNodeToCompareWith.data)
                {
                    prevLower.next = lowerNodeToCompareWith.next;
                }
                prevLower = lowerNodeToCompareWith; // yes
                lowerNodeToCompareWith = lowerNodeToCompareWith.next;
            }

            upperNodeToCompare = upperNodeToCompare.next;
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