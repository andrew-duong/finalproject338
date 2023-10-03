package com.example.test;

import com.example.datastructures.nodes.DNode;
import com.example.datastructures.Linear.QueueLL;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueLLTest {

    @Test // Testing constructor
    public void constrTest() {
        QueueLL test = new QueueLL();

        assertNotNull("Constructor returned null.", test);
    }

    @Test // Testing peek
    public void peekTest() {
        QueueLL test = new QueueLL();
        DNode node1 = new DNode(2);
        DNode node2 = new DNode(1);

        test.enqueue(node1);
        test.enqueue(node2);

        DNode peeked = test.peek();

        assertEquals("Peek did not return the first element in queue.", node1, peeked);
    }

    @Test // Testing enqueuing
    public void enqTest() {
        QueueLL test = new QueueLL();
        DNode node1 = new DNode(2);
        DNode node2 = new DNode(1);

        test.enqueue(node1);
        test.enqueue(node2);

        DNode first = test.peek();
        DNode second = test.peek().getNext();

        assertEquals("Enqueue did not queue first element to first slot.", node1, first);
        assertEquals("Enqueue did not queue second element to next slot.", node2, second);
    }

    @Test // Testing dequeuing
    public void deqTest() {
        QueueLL test = new QueueLL();
        DNode node1 = new DNode(2);
        DNode node2 = new DNode(1);

        test.enqueue(node1);
        test.enqueue(node2);

        test.dequeue();

        DNode newFirst = test.peek();

        test.dequeue();

        DNode newFirst2 = test.peek();

        assertEquals("Dequeue did not remove the first element.", node2, newFirst);
        assertNull("Dequeue did not remove the last element.", newFirst2);
    }

    @Test // Testing size
    public void sizeTest() {
        QueueLL test = new QueueLL();
        DNode node1 = new DNode(2);
        DNode node2 = new DNode(1);

        test.enqueue(node1);
        test.enqueue(node2);

        int expectedSize = 2;
        int actualSize = test.size();

        assertEquals("Size did not return the correct size of queue.", expectedSize, actualSize);
    }

    @Test // Testing isEmpty
    public void emptyTest() {
        QueueLL test = new QueueLL();
        QueueLL test2 = new QueueLL();
        DNode node1 = new DNode(2);

        test.enqueue(node1);

        boolean actualEmpty = test.isEmpty();
        boolean actualEmpty2 = test2.isEmpty();

        assertFalse("isEmpty returned true for non-empty queue.", actualEmpty);
        assertTrue("isEmpty returned false for empty queue.", actualEmpty2);
    }

    @Test // Testing non-used methods
    public void useless() {
        QueueLL test = new QueueLL();
        DNode node = new DNode(1);

        test.InsertHead(node);
        test.Insert(node, 0);
        test.Sort();
        test.SortedInsert(node);

        boolean actualEmpty = test.isEmpty();
        assertTrue("Unused insert/sort methods are actually used.", actualEmpty);

        test.Delete(node);
        test.DeleteHead();
        test.DeleteTail();

        actualEmpty = test.isEmpty();

        assertTrue("Unused delete methods are actually used.", actualEmpty);
    }

    @Test // Testing clear
    public void clearTest() {
        QueueLL test = new QueueLL();
        DNode node1 = new DNode(2);
        DNode node2 = new DNode(1);

        test.enqueue(node1);
        test.enqueue(node2);

        test.Clear();

        DNode head = test.peek();
        int actualSize = test.size();

        assertNull("Clear did not remove head element.", head);
        assertEquals(0, actualSize);
    }

    @Test // Test isSorted
    public void isTest() {
        QueueLL test = new QueueLL();
        DNode node1 = new DNode(2);
        DNode node2 = new DNode(3);

        test.enqueue(node1);
        test.enqueue(node2);
        
        boolean sorted = test.isSorted();

        assertFalse("isSorted returned true even though queues cannot be sorted.", sorted);
    }

    @Test // Testing positional search
    public void searchTest() {
        QueueLL test = new QueueLL();
        DNode node1 = new DNode(2);
        DNode node2 = new DNode(3);
        DNode node3 = new DNode(1);

        test.enqueue(node1);
        test.enqueue(node2);
        test.enqueue(node3);

        int expected = 1;
        int actual = test.search(node2);

        int nonexist = test.search(new DNode(5));

        assertEquals("Queue positional search did not return expected position for existing node.", expected, actual);
        assertEquals("Queue positional search did not return -1 for non-existent node.", -1, nonexist);
    }
}
