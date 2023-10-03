package com.example.test;

import com.example.datastructures.nodes.DNode;
import org.junit.Test;
import static org.junit.Assert.*;

public class DNodeTest {

    @Test // Testing constructors
    public void constrTest() {
        DNode test1 = new DNode(1);
        DNode test2 = new DNode(2, test1);
        DNode test3 = new DNode(3, test2, test1);

        assertNotNull("Node constructor with 1 arg returned null", test1);
        assertNotNull("Node constructor with 2 args returned null", test2);
        assertNotNull("Node constructor with 3 args returned null", test3);
    }

    @Test // Testing getters
    public void getterTest() {

        DNode test1 = new DNode(1);
        DNode test2 = new DNode(2, test1);
        DNode test3 = new DNode(3, test2, test1);

        int expectedData = 1;
        int actualData = test1.getData();

        DNode expectedNext = test1;
        DNode actualNext = test2.getNext();

        DNode expectedPrev = test1;
        DNode actualPrev = test3.getPrevious();

        assertEquals("getData did not return expected value.", expectedData, actualData);
        assertEquals("getNext did not return expected next node.", expectedNext, actualNext);
        assertEquals("getPrevious did not return expected previous node", expectedPrev, actualPrev);
    }

    @Test // Testing setters
    public void setterTest() {
        DNode test1 = new DNode(1);
        DNode test2 = new DNode(2, test1);
        DNode test3 = new DNode(3, test2, test1);

        int expectedData = 1;
        test1.setData(expectedData);
        int actualData = test1.getData();

        DNode expectedNext = test2;
        test1.setNext(expectedNext);
        DNode actualNext = test1.getNext();

        DNode expectedPrev = test3;
        test1.setPrevious(expectedPrev);
        DNode actualPrev = test1.getPrevious();

        assertEquals("setData did not set expected value.", expectedData, actualData);
        assertEquals("setNext did not set expected next node.", expectedNext, actualNext);
        assertEquals("setPrevious did not set expected previous node", expectedPrev, actualPrev);
    }

}
