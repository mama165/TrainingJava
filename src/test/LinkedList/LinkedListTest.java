package LinkedList;

import CrackingTheCodingInterview.LinkedList.Node;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LinkedListTest
{
    @Test
    void myFirstTest() {
        assertEquals(2, 1 + 1);
    }

    // private Node processHeadNode(String inputs) //"16485485516"
    // {
    //     int idNb = 1
    //     Node head = new Node(inputs[0], 1);
    //     Node currentNode = head;
    //     for (int i = 1; i < inputs.length(); i++)
    //     {
    //         idNb++;
    //         currentNode = new Node(inputs[i], 1);
    //         currentNode = currentNode.next;
    //     }
    // }

    public void testThat1235123Returns1235()
    {
        String expectedOutput = "1235";

        //Arrange
        Node head = new Node(1, 1);
        Node node1 = new Node(2, 2);
        Node node2 = new Node(3, 3);
        Node node3 = new Node(5, 4);
        Node node4 = new Node(1, 5);
        Node node5 = new Node(2, 6);
        Node node6 = new Node(3, 7);
  
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        //Act
        Node.removeDups(head);
//        String output = head.displayNodes();

        //Assert
//        assertEquals(expectedOutput, output);
    }

    public void testThat1275123Returns12753()
    {
        String expectedOutput = "12753";

        //Arrange
        Node head = new Node(1, 1);
        Node node1 = new Node(1, 2);
        Node node2 = new Node(7, 2);
        Node node3 = new Node(5, 4);
        Node node4 = new Node(1, 5);
        Node node5 = new Node(2, 6);
        Node node6 = new Node(3, 7);
  
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        //Act
        Node.removeDups(head);
//        String output = head.displayNodes();

        //Assert
//        assertEquals(expectedOutput, output);
    }

    

    public void testCaDoitFalse()
    {
        assertTrue(false, "Les tests ne fonctionnent pas !");
    }
}
