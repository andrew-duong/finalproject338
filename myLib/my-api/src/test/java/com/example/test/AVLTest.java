package com.example.test;

import com.example.datastructures.trees.AVL;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.datastructures.nodes.TNode;

public class AVLTest {

    @Test
    public void constrTest() {
        AVL test = new AVL();

        AVL test2 = new AVL(1);

        TNode node = new TNode();
        AVL test3 = new AVL(node);

        assertNotNull("AVL constructor with no args returned null.", test);
        assertNotNull("AVL constructor with integer argument returned null.", test2);
        assertNotNull("AVL constructor with node argument returned null.", test3);
    }

    @Test
    public void testInsertNode() {

        AVL tree = new AVL();

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

    @Test
    public void testBalance() {
        AVL tree = new AVL();

        TNode node = new TNode(2, 0, null, null, null);
        TNode node2 = new TNode(1, 0, null, null, null);
        TNode node3 = new TNode(3, 0, null, null, null);
        TNode node4 = new TNode(4, 0, null, null, null);
        TNode node5 = new TNode(5, 0, null, null, null);

        tree.insert(node);
        tree.insert(node2);
        tree.insert(node3);

        int expected = 0;
        int actual = tree.getBalance(tree.getRoot());

        assertEquals("Balacend root did not return balanced", expected, actual);

        tree.insert(node4);

        int expectedBalance = 1;
        int actualBalance = tree.getBalance(tree.getRoot());

        assertEquals("Root was not balanced correctly after insertion.", expectedBalance, actualBalance);

        tree.insert(node5);

        int expectedBalance1 = 1;
        int actualBalance1 = tree.getBalance(tree.getRoot());
        
        assertEquals("Root was not balanced correctly after insertion and rebalancing of tree.", expectedBalance1, actualBalance1);

    }

    @Test
    public void testInsertThenGetters() {
        // Insert values into BST tree
        AVL tree = new AVL();
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
        // Test if the nodes have been instarted into the AVL
        assertNotNull("Search function did not find a node with the value 5.", tree.Search(5));
        assertNotNull("Search function did not find a node with the value 3.", tree.Search(3));
        assertNotNull("Search function did not find a node with the value 7.", tree.Search(7));
        assertNotNull("Search function did not find a node with the value 2.", tree.Search(2));
        assertNotNull("Search function did not find a node with the value 4.", tree.Search(4));
        assertNotNull("Search function did not find a node with the value 6.", tree.Search(6));
        assertNotNull("Search function did not find a node with the value 8.", tree.Search(8));
        // Test getRight and getLeft for the above nodes.
        assertEquals("A parent for the root node was found", null, root.getParent());
        assertEquals("Left node of root did not return the root node using getParent", root, left.getParent());
        assertEquals("Right node of root did not return the root node using getParent", root, right.getParent());
        assertEquals("Left node of root did not return the correct value using getData", 3, left.getData());
        assertEquals("Right node of root did not return the correct value using getData", 7, right.getData());
        assertEquals("getLeft of left node of root did not return the correct value using getData", 2,
                left.getLeft().getData());
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
    public void testInsertWithRotatesAndBalance() {
        AVL tree = new AVL();

        // Insert nodes into the tree
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        // This was a right rotate
        // These tests if the right rotate was properly done
        assertEquals("The root node is incorrect after a right rotate", 2, tree.getRoot().getData());
        assertEquals("The left node is incorrect after a right rotate", 1, tree.getRoot().getLeft().getData());
        assertNull("The left node should have no left node", tree.getRoot().getLeft().getLeft());
        assertNull("The left node should have no right node", tree.getRoot().getLeft().getRight());
        assertEquals("The left node does not have the root as it's parent", tree.getRoot(),
                tree.getRoot().getLeft().getParent());
        assertEquals("The right node is incorrect after a right rotate", 3, tree.getRoot().getRight().getData());
        assertNull("The right node should have no left node", tree.getRoot().getRight().getLeft());
        assertNull("The right node should have no right node", tree.getRoot().getRight().getRight());
        assertEquals("The right node does not have the root as it's parent", tree.getRoot(),
                tree.getRoot().getRight().getParent());
        // Check if the tree is balanced
        assertEquals("The tree was unbalanced after a right rotate.", 0, tree.getBalance(tree.getRoot()));

        // Insert additional nodes to unbalance the tree
        tree.insert(4);
        tree.insert(5);
        // This should have resulted in a right rotate
        // These tests if the left rotate was properly done
        assertEquals("The root node is incorrect after a right rotate", 2, tree.getRoot().getData());
        assertEquals("The left node is incorrect after a right rotate", 1, tree.getRoot().getLeft().getData());
        assertNull("The left node should have no left node", tree.getRoot().getLeft().getLeft());
        assertNull("The left node should have no right node", tree.getRoot().getLeft().getRight());
        assertEquals("The left node does not have the root as it's parent", tree.getRoot(),
                tree.getRoot().getLeft().getParent());
        assertEquals("The right node is incorrect after a left rotate", 4, tree.getRoot().getRight().getData());
        assertEquals("The right node should have 3 as it's left node", 3,
                tree.getRoot().getRight().getLeft().getData());
        assertEquals("The right node should have 5 as it's right node", 5,
                tree.getRoot().getRight().getRight().getData());
        assertEquals("The right node does not have the root as it's parent", tree.getRoot(),
                tree.getRoot().getRight().getParent());
        assertNull("The left node of the right node of the root should have no left node",
                tree.getRoot().getRight().getLeft().getLeft());
        assertNull("The left node of the right node of the root should have no right node",
                tree.getRoot().getRight().getLeft().getRight());
        assertNull("The right node of the right node of the root should have no right node",
                tree.getRoot().getRight().getRight().getRight());
        assertNull("The right node of the right node of the root should have no right node",
                tree.getRoot().getRight().getRight().getLeft());
        assertEquals("The left node of the right node of the root should have 4 as it's parent", 4,
                tree.getRoot().getRight().getLeft().getParent().getData());
        assertEquals("The right node of the right node of the root should have 4 as it's parent", 4,
                tree.getRoot().getRight().getRight().getParent().getData());
        // balance should be 1
        assertEquals(1, tree.getBalance(tree.getRoot()));
    }

    @Test
    public void testDelete() {
        AVL tree = new AVL();

        // Insert nodes into the tree
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(4);
        tree.insert(5);

        // Check if the tree is unbalanced
        assertEquals(1, tree.getBalance(tree.getRoot()));

        // Delete a node to balance the tree
        tree.delete(5);
        assertNull("Search function found a node with the value 5 after deletion.", tree.Search(5));
        assertNull("The right node of the root should have no right node", tree.getRoot().getRight().getRight());
        assertEquals("The right node should have 3 as it's left node", 3,
                tree.getRoot().getRight().getLeft().getData());
        tree.delete(4);
        assertNull("Search function found a node with the value 4 after deletion.", tree.Search(4));
        assertEquals("The right node is incorrect after the deletion of 4", 3, tree.getRoot().getRight().getData());
        assertEquals("The right node does not have the root as it's parent after 4 was deleted", tree.getRoot(),
                tree.getRoot().getRight().getParent());
        assertNull("The right node should have no left node", tree.getRoot().getRight().getLeft());
        assertNull("The right node should have no right node", tree.getRoot().getRight().getRight());
        // Check if the tree is balanced
        assertEquals(0, tree.getBalance(tree.getRoot()));
    }

    @Test
    public void testSearch() {
        AVL tree = new AVL();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);
        TNode node = tree.Search(7);
        assertNotNull("Search should return a non-null node for a value that exists in the tree", node);
        assertEquals("Search should return the correct node for a value that exists in the tree", 7, node.getData());
        node = tree.Search(1);
        assertNull("Search should return null for a value that does not exist in the tree", node);
    }

    @Test
    public void testSetRoot() {
        AVL tree = new AVL();

        // Insert nodes into the tree
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(5);
        tree.insert(4);
        
        
        //Check if the tree is unbalanced
        assertEquals(1, tree.getBalance(tree.getRoot()));
        
        //Create a new node and set it as the root
        TNode newRoot = new TNode(5, 1, null, null, null);
        newRoot.setRight(new TNode(6, 0, newRoot, null, null));;
        tree.setRoot(newRoot);
        
        assertEquals("setNode did not set the correct node", 5, tree.getRoot().getData());
        //balance of the new tree should be one.
        //insertion order of the old tree into the new tree is 1,3,5,4. 5 should not be a child in the new tree so the balance of the tree should be -1.
        assertEquals(-1, tree.getBalance(tree.getRoot()));
    }
    
}
