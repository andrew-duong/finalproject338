package com.example.test;

import com.example.datastructures.Linear.SLL;
import com.example.datastructures.nodes.DNode;

import org.junit.Test;
import static org.junit.Assert.*;

public class SLLTest {

    @Test // Testing constructors
    public void constrTest() {
        SLL test1 = new SLL();
        SLL test2 = new SLL(new DNode(0));

        assertNotNull("Constructor with no args returns null.", test1);
        assertNotNull("Constructor with arg returns null.", test2);
    }

    @Test // Testing getters & values from constructors
    public void getTest() {
        SLL test1 = new SLL();
        DNode node = new DNode(0);
        SLL test2 = new SLL(node);

        DNode actualHead1 = test1.getHead();
        DNode actualTail1 = test1.getTail();
        int expectedSize1 = 0;
        int actualSize1 = test1.getSize();

        DNode actualHead2 = test2.getHead();
        DNode actualTail2 = test2.getTail();

        assertNull("getHead for no arg constructor did not return null.", actualHead1);
        assertNull("getTail for no arg constructor did not return null.", actualTail1);
        assertEquals("getSize did not return expected value.", expectedSize1, actualSize1);
        assertEquals("getHead for arg constructor did not return expected value.", node, actualHead2);
        assertEquals("getTail for arg constructor did not return expected value.", node, actualTail2);
    }

    @Test // Testing inserts
    public void insertTests() {
        SLL test1 = new SLL();
        DNode node1 = new DNode(1);
        DNode node2 = new DNode(2);
        DNode node3 = new DNode(3);

        test1.InsertHead(node1);
        test1.InsertTail(node2);
        test1.Insert(node3, 1);

        DNode insertedNode = test1.getHead().getNext();
        DNode actualHead1 = test1.getHead();
        DNode actualTail1 = test1.getTail();
        int expectedSize1 = 3;
        int actualSize1 = test1.getSize();

        assertEquals("InsertHead did not insert the correct value.", node1, actualHead1);
        assertEquals("InsertTail did not insert the correct value.", node2, actualTail1);
        assertEquals("Insert to specific position did not correctly insert.", node3, insertedNode);
        assertEquals("getSize did not return expected value.", expectedSize1, actualSize1);

        boolean exceptionThrown = false;

        try {
            test1.Insert(node2, 4);
        } catch (Exception e) {
            exceptionThrown = true;
        }

        assertTrue("Exception was not thrown when inserting to out of bounds position.", exceptionThrown);
    }

    @Test // Testing sort
    public void sortTest() {
        SLL test1 = new SLL();
        DNode node1 = new DNode(1);
        DNode node2 = new DNode(2);
        DNode node3 = new DNode(3);
        DNode node4 = new DNode(4);

        // Here, the SLL is unordered, Sort() should order from least to
        // greatest
        test1.InsertHead(node4); // 4,3,1,2
        test1.InsertTail(node2);
        test1.Insert(node1, 1);
        test1.Insert(node3, 1);

        test1.Sort();

        DNode actualHead = test1.getHead();
        DNode actualTail = test1.getTail();
        DNode actualMiddle = actualHead.getNext();

        boolean actualResult = test1.isSorted();

        assertEquals("The head node is incorrect.", node1, actualHead);
        assertEquals("A middle node is incorrect.", node2, actualMiddle);
        assertEquals("The tail node is incorrect.", node4, actualTail);
        assertTrue("isSorted did not return true when SLL was sorted using Sort().", actualResult);

        // Should already be sorted
        SLL test2 = new SLL();
        test2.InsertHead(node1);
        test2.InsertTail(node2);
        boolean actualSort = test2.isSorted();

        assertTrue("isSorted did not return true for pre-sorted SLL.", actualSort);
    }

    @Test // Testing SortedInsert
    public void sortedInsertTest() {
        SLL test1 = new SLL();
        DNode node1 = new DNode(1);
        DNode node2 = new DNode(2);
        DNode node3 = new DNode(3);

        // Calling the sorted insert in random order
        test1.SortedInsert(node3);
        test1.SortedInsert(node1);
        test1.SortedInsert(node2);

        DNode actualHead = test1.getHead();
        DNode actualTail = test1.getTail();
        DNode actualMid = test1.getHead().getNext();

        assertEquals("SortedInsert did not insert the correct head node.", node1, actualHead);
        assertEquals("SortedInsert did not insert the correct tail node.",node3, actualTail);
        assertEquals("SortedInsert did not set the correct next pointer.", node2, actualMid);
    }

    @Test // Testing search
    public void searchTest() {
        SLL test1 = new SLL();
        DNode node1 = new DNode(1);
        DNode node2 = new DNode(2);
        DNode node3 = new DNode(3);
        DNode node4 = new DNode(4);

        test1.InsertHead(node1);
        test1.InsertHead(node2);
        test1.InsertHead(node3);

        DNode realVal = test1.Search(node3);
        DNode nullVal = test1.Search(node4);

        assertEquals("Search for existing node in SLL returned incorrect node.", node3, realVal);
        assertNull("Search for non-exising node in SLL returned non-null.", nullVal);
    }

    @Test // Testing deletes
    public void delTest() {
        SLL test1 = new SLL();
        DNode node1 = new DNode(1);
        DNode node2 = new DNode(2);
        DNode node3 = new DNode(3);
        DNode node4 = new DNode(4);
        DNode node5 = new DNode(5);

        test1.InsertHead(node1);
        test1.InsertTail(node2);
        test1.InsertTail(node3);
        test1.InsertTail(node4);
        test1.InsertTail(node5);

        // SLL has 1,2,3,4,5

        test1.DeleteHead();
        DNode actualHead = test1.getHead(); // 2, 3, 4, 5

        test1.DeleteTail();
        DNode actualTail = test1.getTail(); // 2, 3, 4

        test1.Delete(node3); 
        int expectedSize = 2; // 2, 4
        int actualSize = test1.getSize(); 

        assertEquals("Head node was not updated properly after deletion.",node2, actualHead);
        assertEquals("Tail node was not updated properly after deletion.",node4, actualTail);
        assertEquals("Size of SLL is not correct after the deletions.",expectedSize, actualSize);
    }

    @Test // Testing clear
    public void clearTest() {
        SLL test1 = new SLL();
        DNode node1 = new DNode(1);
        DNode node2 = new DNode(2);
        DNode node3 = new DNode(3);

        test1.InsertHead(node1);
        test1.InsertTail(node2);
        test1.Insert(node3, 1);

        test1.Clear();

        assertNull("Head was not cleared.", test1.getHead());
        assertNull("Tail was not cleared.", test1.getTail());
        assertEquals("Size was not cleared.", 0, test1.getSize());
    }
}
