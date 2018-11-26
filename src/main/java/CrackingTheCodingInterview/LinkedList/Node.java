package CrackingTheCodingInterview.LinkedList;

public class Node {

    public Node next;
    int data;
    int myId;

    public Node(int data, int myId) {
        this.data = data;
        this.myId = myId;
    }



//     public void appendToTail(int d) {
//         Node n = this;
//         Node end = new Node(d, 0);
//         while (n.next != null) {
//             n = n.next;
//         }
//         n.next = end;
//         end.myId = ++n.next.myId;
//     }

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

    /*
    
    1-> 2 -> 5 -> 7 -> 4 -> 1 -> 6 -> 7 -> 8   valeur
    1   2    3    4    5    6    7    8    9   id
    U                                        
    P        L
             L-1                           
    */
    public Node removeDups() {

        Node upperNodeToCompare = this;
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
        return this;
    }

    public Node rmD(Node head) {

        Node node = head;
        while (node != null) {
            Node subnode = node.next;
            Node tmp = node; //yes

            while (subnode != null) {
                if (node.data == subnode.data) {
                    tmp.next = subnode.next; // On ne veut plus de référence vers le noeud "subnode"
                    tmp = node; // On stocke le "node" parent en cours car "subnode" a disparu !
                } else {
                    tmp = subnode; // On stocke le "subnode" car il est toujours présent
                }
                subnode = subnode.next;
            }
            node = node.next;
        }
        return head;
    }

    public Node removeDOldups(Node head) {

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

    public Node deleteMiddleNode() {
        Node node = this;
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
        tmp.next = subNode.next;
        return this;
    }

    public String displayNodes() {
        Node node = this;
        StringBuilder output = new StringBuilder();

        while (node != null) {
            System.out.println(node.data);
            output.append(String.valueOf(node.data));
            node = node.next;
        }
        return  output.toString();
    }
}