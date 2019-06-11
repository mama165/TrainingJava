package fr.coding.cracking.LinkedList;

public class Main {

    public static void main(String [] args) {

        Main main = new Main();
        main.start();
    }

    public void start() {
//        Node head = new Node(1, 1);
//        Node node1 = new Node(2, 2);
//        Node node2 = new Node(1, 3);
//        Node node3 = new Node(7, 4);
//        Node node4 = new Node(4, 5);//upper
//        Node node5 = new Node(4, 6);//prev
//        Node node6 = new Node(4, 7);//lower
//        Node node7 = new Node(8, 8);
//        Node node8 = new Node(8, 9);
//        Node node9 = new Node(8, 10);

        Node head = new Node(3, 1);
        Node node1 = new Node(3, 2);
        Node node2 = new Node(3, 3);
        Node node3 = new Node(4, 4);
        Node node4 = new Node(4, 5);
        Node node5 = new Node(4, 6);

        // 3->3->3->4->4->4
        // 3->4->3->4->3->4

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

//        head.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
//        node6.next = node7;
//        node7.next = node8;
//        node8.next = node9;

        head.runnerTechnique();
        head.displayNodes();
        System.exit(1);

        head.deleteMiddleNode().displayNodes();
//        System.exit(1);

       head.displayNodes(); //Res
        System.out.println("------");
        head.removeDups().displayNodes(); // Res
    }
}