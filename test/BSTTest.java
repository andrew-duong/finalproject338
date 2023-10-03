package test;

import myLib.datastructures.trees.BST;
import org.junit.*;
import static org.junit.Assert.*;
import myLib.datastructures.nodes.TNode;

public class BSTTest {
    BST tree;

    @Before
    public void setUp() {
        tree = new BST();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(12);
        tree.insert(17);
    }

    @Test
    public void testConstructors() {
        // Test default constructor
        BST bst1 = new BST();
        assertNull("Constructor with no args does not have null value for root.", bst1.getRoot());

        // Test constructor with initial value
        BST bst2 = new BST(5);
        assertEquals("Expected value was: ", 5, bst2.getRoot().getData());
        assertNull("getParent for int arg constructor did not return null", bst2.getRoot().getParent());
        assertNull("getLeft for int arg constructor did not return null", bst2.getRoot().getLeft());
        assertNull("getRight for int arg constructor did not return null", bst2.getRoot().getRight());

        // Test constructor with TNode object
        TNode node = new TNode(10, 0, null, null, null);
        BST bst3 = new BST(node);
        assertEquals("getRoot for TNode constructor does not return correct node", node, bst3.getRoot());
        assertEquals("getData for for TNode constructor does not return correct value", 10, bst3.getRoot().getData());
        assertEquals("getBalance for for TNode constructor does not return correct value", 0,
                bst3.getRoot().getBalance());
    }

    @Test
    public void testSettersAndGetters() {
        BST bst = new BST();

        // Test setRoot and getRoot
        TNode node = new TNode(5, 0, null, null, null);
        bst.setRoot(node);
        assertEquals(node, bst.getRoot());

        // Test insert and getRoot
        bst.insert(10);
        assertEquals("getData when a new value is inserted did not return the correct value", 5,
                bst.getRoot().getData());
        assertEquals("getRight when a new value is inserted did not return the correct value", 10,
                bst.getRoot().getRight().getData());

        // Test insert(TNode) and getRoot
        TNode node2 = new TNode(3, 0, null, null, null);
        bst.insert(node2);
        assertEquals("getData when a new node is inserted did not return the correct value", 5,
                bst.getRoot().getData());
        assertEquals("getLeft when a new node is inserted did not return the correct value", 3,
                bst.getRoot().getLeft().getData());

        // Test delete and getRoot
        bst.delete(5);
        assertEquals("getData did not return the correct value when a node is deleted", 3, bst.getRoot().getData());
        // Test
    }

    @Test
    public void testGetParentGetLeftGetRight() {
        // Insert values into BST tree
        BST tree = new BST();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);
        // get the root, left and right node
        TNode root = tree.getRoot();
        TNode left = root.getLeft();
        TNode right = root.getRight();
        // Test getRight and getLeft for the above nodes.
        assertEquals("A parent for the root node was found", null, root.getParent());
        assertEquals("Left node of root did not return the root node using getParent", root, left.getParent());
        assertEquals("Right node of root did not return the root node using getParent", root, right.getParent());
        assertEquals("Left node of root did not return the correct value using getData", 3, left.getData());
        assertEquals("Right node of root did not return the correct value using getData", 7, right.getData());
        assertEquals("getParent of left node of root did not return the correct node", left,
                left.getLeft().getParent());
        assertEquals("getParent of left node of root did not return the correct node", left,
                left.getRight().getParent());
        assertEquals("getLeft of right node of root did not return the correct value using getData", 6,
                right.getLeft().getData());
        assertEquals("getRight of right node of root did not return the correct value using getData", 8,
                right.getRight().getData());
        assertEquals("getParent of right node of root did not return the correct node", right,
                right.getLeft().getParent());
        assertEquals("getParent of right node of root did not return the correct node", right,
                right.getRight().getParent());
    }

    @Test
    public void testFindSuccessor() {
        // Insert values into BST tree
        BST bst = new BST();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        // search for an arbitrary node and find its successor
        TNode node = bst.Search(50);
        TNode successor = bst.findSuccessor(node);

        assertEquals("findSuccessor did not find the correct node:", 40, successor.getData());
    }

    @Test
    // Test if the node has been inserted into the tree.
    public void testInsert() {
        assertTrue("Search function did not find a node with the value 10.", tree.Search(10) != null);
        assertTrue("Search function did not find a node with the value 5.", tree.Search(5) != null);
        assertTrue("Search function did not find a node with the value 15.", tree.Search(15) != null);
        assertTrue("Search function did not find a node with the value 3.", tree.Search(3) != null);
        assertTrue("Search function did not find a node with the value 7.", tree.Search(7) != null);
        assertTrue("Search function did not find a node with the value 12.", tree.Search(12) != null);
        assertTrue("Search function did not find a node with the value 17.", tree.Search(17) != null);
    }

    @Test
    // Test if the node has been deleted from the tree.
    public void testDelete() {
        tree.delete(5);
        assertNull("Search function found a node with the value 5 after deletion.", tree.Search(5));
        assertEquals("Left node of root is incorrect after deletion of 5", 3, tree.getRoot().getLeft().getData());
        tree.delete(10);
        assertNull("Search function found a node with the value 10 after deletion.", tree.Search(10));
        assertEquals("Root is incorrect after deletion of root", 7, tree.getRoot().getData());
        assertEquals("Left node of root is incorrect after deletion of root", 3, tree.getRoot().getLeft().getData());
        tree.delete(15);
        assertNull("Search function found a node with the value 15 after deletion.", tree.Search(15));
        assertEquals("Right node of root is incorrect after deletion of 15", 12, tree.getRoot().getRight().getData());
        assertEquals("Right node of right node of root is incorrect after deletion of 15", 17,
                tree.getRoot().getRight().getRight().getData());
        tree.delete(3);
        assertNull("Search function found a node with the value 3 after deletion.", tree.Search(3));
        assertEquals("Left node of root is incorrect after deletion of 3", null, tree.getRoot().getLeft());
        tree.delete(7);
        assertNull("Search function found a node with the value 7 after deletion.", tree.Search(7));
        assertEquals("Root is incorrect after deletion of root", 12, tree.getRoot().getData());
        tree.delete(12);
        assertEquals("Root is incorrect after deletion of root", 17, tree.getRoot().getData());
        assertNull("Search function found a node with the value 12 after deletion.", tree.Search(12));
        tree.delete(17);
        assertNull("Search function found a node with the value 17 after deletion.", tree.Search(17));
        assertEquals("Root is incorrect after deletion of root", null, tree.getRoot());
    }

    @Test
    public void testPrintInOrder() {
        System.out.println("In-order traversal:");
        tree.printInOrder(tree.getRoot());
    }

    @Test
    public void testPrintBF() {
        System.out.println("Breadth-first traversal:");
        tree.printBF();
    }

    @Test
    public void testInsertNode() {

        BST tree = new BST();

        TNode node = new TNode(2, 0, null, null, null);
        TNode node2 = new TNode(1, 0, null, null, null);
        TNode node3 = new TNode(3, 0, null, null, null);

        tree.insert(node);
        tree.insert(node2);
        tree.insert(node3);

        TNode root = tree.getRoot();
        TNode left = root.getLeft();
        TNode right = root.getRight();

        assertEquals("The inserted node was not found.", node, root);
        assertEquals("Left node was not set correctly after insertion", node2, left);
        assertEquals("Right node was not set correctly after insertion", node3, right);

        TNode parentTest = left.getParent();
        TNode parentTest2 = right.getParent();

        assertEquals("Parent for left node was not set correctly after insertion.", node, parentTest);
        assertEquals("Parent for right node was not set correctly after insertion.", node, parentTest2);
    }
}
