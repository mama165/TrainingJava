package StacksAndQueues;

import CrackingTheCodingInterview.StacksAndQueues.StackNode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StacksAndQueuesTest {

    @Test
    public void testEmptyStack() {
        StackNode stackNode;
        StackNode sn1 = new StackNode(3);
        StackNode sn2= new StackNode(4);

//        StackNode stackNode = new StackNode();
//        assertEquals(true, stackNode.isEmpty());

        /*
Create an empty Stack. Test that its size is 0.
Push an element onto the stack. Test that its size is now 1.
Push another element onto the stack. Test that its size is now 2.
Pop an element from the stack. Test that it matches the 2nd pushed value. Check that the size of the stack is now 1.
Pop an element from the stack. Test that it matches the 1st pushed value. Check that the size of the stack is 0.
Attempt to pop an element from the stack. You should receive an ArrayIndexOutOfBounds exception..
         */



    }

    /**
     * Push an element onto the stack. Test that its size is now 1.
     */
    @Test
    public void testThatSizeReturn1() {
        StackNode stackNode = new StackNode(1);
        assertEquals(1, stackNode.size());
    }

    @Test
    public void testInit() {
        StackNode stackNode = new StackNode(1);
        assertEquals(2, stackNode.peek());
    }

    /**
     * Pop an element from the stack. Test that it matches the 2nd pushed value. Check that the size of the stack is now 1.
     */
    @Test
    public void testPop() {
        Integer  i = new Integer(1);
        Integer  j = new Integer(2);
        StackNode<Integer> si = new StackNode(i);
        si.push(j);

        Integer value = si.pop();

        System.out.println(value);
//        assertEquals(new Integer(2), (Integer) stackNode.pop());

//        assertTrue(j.equals(stackNode.pop()));
    }
}
