package test;

import myLib.datastructures.nodes.DNode;
import myLib.datastructures.Linear.StackLL;
import org.junit.Test;
import static org.junit.Assert.*;

public class StackLLTest {
    @Test // Testing constructor
    public void constrTest() {
        StackLL test = new StackLL();

        assertNotNull("Constructor returned null.", test);
    }

    @Test // Testing peek
    public void peekTest() {
        StackLL test = new StackLL();
        DNode node1 = new DNode(2);
        DNode node2 = new DNode(1);

        test.push(node1);
        test.push(node2);

        DNode peeked = test.peek();

        assertEquals("Peek did not return the most recent element added to the stack.", node2, peeked);
    }

    @Test // Testing enqueuing
    public void enqTest() {
        StackLL test = new StackLL();
        DNode node1 = new DNode(2);
        DNode node2 = new DNode(1);

        test.push(node1);
        test.push(node2);

        DNode first = test.peek();
        DNode second = test.peek().getNext();

        assertEquals("Push did not push first element to first slot.", node2, first);
        assertEquals("Push did not set second element to be next.", node1, second);
    }

    @Test // Testing dequeuing
    public void deqTest() {
        StackLL test = new StackLL();
        DNode node1 = new DNode(2);
        DNode node2 = new DNode(1);

        test.push(node1);
        test.push(node2);

        test.pop();

        DNode newFirst = test.peek();

        test.pop();

        DNode newFirst2 = test.peek();

        assertEquals("Pop did not remove the most recent element.", node1, newFirst);
        assertNull("Pop did not remove the last element.", newFirst2);
    }

    @Test // Testing isEmpty
    public void emptyTest() {
        StackLL test = new StackLL();
        StackLL test2 = new StackLL();
        DNode node1 = new DNode(2);

        test.push(node1);

        boolean actualEmpty = test.isEmpty();
        boolean actualEmpty2 = test2.isEmpty();

        assertFalse("isEmpty returned true for non-isEmpty queue.", actualEmpty);
        assertTrue("isEmpty returned false for isEmpty queue.", actualEmpty2);
    }

    @Test // Testing non-used methods
    public void useless() {
        StackLL test = new StackLL();
        DNode node = new DNode(1);

        test.InsertTail(node);
        test.Insert(node, 0);
        test.Sort();
        test.SortedInsert(node);

        boolean actualEmpty = test.isEmpty();
        assertTrue("Unused insert/sort methods are actually used.", actualEmpty);

        test.Delete(node);
        test.DeleteTail();

        actualEmpty = test.isEmpty();

        assertTrue("Unused delete methods are actually used.", actualEmpty);
    }

    @Test // Testing clear
    public void clearTest() {
        StackLL test = new StackLL();
        DNode node1 = new DNode(2);
        DNode node2 = new DNode(1);

        test.push(node1);
        test.push(node2);

        test.Clear();

        DNode head = test.peek();
        int actualSize = test.getSize();

        assertNull("Clear did not remove head element.", head);
        assertEquals(0, actualSize);
    }

    @Test // Test isSorted
    public void isTest() {
        StackLL test = new StackLL();
        DNode node1 = new DNode(2);
        DNode node2 = new DNode(3);

        test.push(node1);
        test.push(node2);

        boolean sorted = test.isSorted();

        assertFalse("isSorted returned true even though stacks cannot be sorted.", sorted);
    }

    @Test // Testing positional search
    public void searchTest() {
        StackLL test = new StackLL();
        DNode node1 = new DNode(2);
        DNode node2 = new DNode(3);
        DNode node3 = new DNode(1);

        test.push(node1);
        test.push(node2);
        test.push(node3);

        int expected = 1;
        int actual = test.search(node2);

        int nonexist = test.search(new DNode(5));

        assertEquals("Stack positional search did not return expected position for existing node.", expected, actual);
        assertEquals("Stack positional search did not return -1 for non-existent node.", -1, nonexist);
    }
}