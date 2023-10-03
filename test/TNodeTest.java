package test;

import myLib.datastructures.nodes.TNode;
import org.junit.Test;
import static org.junit.Assert.*;

public class TNodeTest {
    @Test // Testing constructors
    public void constrTest() {
        TNode test1 = new TNode();
        TNode test2 = new TNode();
        TNode test3 = new TNode();
        TNode test4 = new TNode(1, 2, test1, test2, test3);

        assertNotNull("Node constructor with default args returned null", test1);
        assertNotNull("Node constructor with args returned null", test4);
    }

    @Test // Testing default values
    public void getterTestDef() {
        TNode test1 = new TNode();

        int expectedData = 0;
        int actualData = test1.getData();

        int expectedBal = 0;
        int actualBal = test1.getBalance();

        TNode actualParent = test1.getParent();

        TNode actualLeft = test1.getLeft();

        TNode actualRight = test1.getLeft();

        assertEquals("getData did not return expected value.", expectedData, actualData);
        assertEquals("getLeft did not return expected previous node", expectedBal, actualBal);
        assertNull("getParent did not return expected next node.", actualParent);
        assertNull("getLeft did not return expected previous node", actualLeft);
        assertNull("getLeft did not return expected previous node", actualRight);
    }

    @Test // Testing getters
    public void getterTest() {

        TNode test1 = new TNode();
        TNode test2 = new TNode();
        TNode test3 = new TNode();
        TNode test4 = new TNode(1, 2, test1, test2, test3);

        int expectedData = 1;
        int actualData = test4.getData();

        int expectedBal = 2;
        int actualBal = test4.getBalance();

        TNode expectedParent = test1;
        TNode actualParent = test4.getParent();

        TNode expectedLeft = test2;
        TNode actualLeft = test4.getLeft();

        TNode expectedRight = test2;
        TNode actualRight = test4.getLeft();

        assertEquals("getData did not return expected value.", expectedData, actualData);
        assertEquals("getLeft did not return expected previous node", expectedBal, actualBal);
        assertEquals("getParent did not return expected next node.", expectedParent, actualParent);
        assertEquals("getLeft did not return expected previous node", expectedLeft, actualLeft);
        assertEquals("getLeft did not return expected previous node", expectedRight, actualRight);
    }

    @Test // Testing setters
    public void setterTest() {
        TNode test1 = new TNode();
        TNode test2 = new TNode();
        TNode test3 = new TNode();
        TNode test4 = new TNode(1, 2, test1, test2, test3);

        int expectedData = 2;
        test4.setData(expectedData);
        int actualData = test4.getData();

        int expectedBal = 2;
        test4.setBalance(expectedBal);
        int actualBal = test4.getBalance();

        TNode expectedParent = test1;
        test4.setParent(expectedParent);
        TNode actualParent = test4.getParent();

        TNode expectedLeft = test2;
        test4.setLeft(expectedLeft);
        TNode actualLeft = test4.getLeft();

        TNode expectedRight = test2;
        test4.setRight(expectedRight);
        TNode actualRight = test4.getLeft();

        assertEquals("setData did not set expected value.", expectedData, actualData);
        assertEquals("setLeft did not set expected previous node", expectedBal, actualBal);
        assertEquals("setParent did not set expected next node.", expectedParent, actualParent);
        assertEquals("setLeft did not set expected previous node", expectedLeft, actualLeft);
        assertEquals("setLeft did not set expected previous node", expectedRight, actualRight);
    }

    @Test // Testing toString
    public void toStringTest() {
        TNode test = new TNode();
        test.setData(21);

        String expected = "21";
        String actual = test.toString();

        assertEquals("toString did not return expected value.", expected, actual);
    }
}
