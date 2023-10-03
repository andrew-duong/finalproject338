package myLib.datastructures.trees;

import myLib.datastructures.nodes.TNode;
import java.util.*;

/**
 * 
 * Binary Search Tree (BST) data structure.
 * 
 */

public class BST {
    TNode root;

    /**
     * Constructs an empty BST with a null root.
     */
    public BST() {
        this.root = null;
    }

    /**
     * Constructs a BST with a single node that has the specified value.
     *
     * @param val the value to be stored in the root node
     */
    public BST(int val) {
        this.root = new TNode(val, 0, null, null, null);
    }

    /**
     * Constructs a BST with the specified root node.
     *
     * @param obj the root node
     */
    public BST(TNode obj) {
        this.root = obj;
    }

    /**
     * Sets the root node of this BST.
     *
     * @param root the new root node
     */
    public void setRoot(TNode root) {
        this.root = root;
    }

    /**
     * Returns the root node of this BST.
     *
     * @return the root node of this BST
     */

    public TNode getRoot() {
        return this.root;
    }

    /**
     * Inserts a new node with the specified value into this BST.
     *
     * @param val the value to be inserted
     */
    public void insert(int val) {
        TNode newNode = new TNode(val, 0, null, null, null);
        if (this.root == null) {
            this.root = newNode;
        } else {
            TNode current = this.root;
            while (true) {
                if (val < current.getData()) {
                    if (current.getLeft() == null) {
                        current.setLeft(newNode);
                        newNode.setParent(current);
                        break;
                    } else {
                        current = current.getLeft();
                    }
                } else if (val > current.getData()) {
                    if (current.getRight() == null) {
                        current.setRight(newNode);
                        newNode.setParent(current);
                        break;
                    } else {
                        current = current.getRight();
                    }
                } else {
                    // Value already exists in tree, do nothing
                    break;
                }
            }
        }
    }

    /**
     * Inserts a new node into this BST.
     *
     * @param node the node to be inserted
     */
    public void insert(TNode node) {
        if (this.root == null) {
            this.root = node;
        } else {
            TNode current = this.root;
            while (true) {
                if (node.getData() < current.getData()) {
                    if (current.getLeft() == null) {
                        current.setLeft(node);
                        node.setParent(current);
                        break;
                    } else {
                        current = current.getLeft();
                    }
                } else if (node.getData() > current.getData()) {
                    if (current.getRight() == null) {
                        current.setRight(node);
                        node.setParent(current);
                        break;
                    } else {
                        current = current.getRight();
                    }
                } else {
                    // Value already exists in tree, do nothing
                    break;
                }
            }
        }
    }

    /**
     * Deletes a node with the specified value from this BST.
     *
     * @param val the value to be deleted
     */
    public void delete(int val) {
        TNode node = Search(val);
        if (node == null) {
            System.out.println("Value not found in tree.");
            return;
        }

        if (node.getLeft() == null && node.getRight() == null) {
            // Case 1: Node has no children
            if (node.getParent() == null) {
                // Node is root
                this.root = null;
            } else if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(null);
            } else {
                node.getParent().setRight(null);
            }
        } else if (node.getLeft() == null || node.getRight() == null) {
            // Case 2: Node has one child
            TNode child = (node.getLeft() != null) ? node.getLeft() : node.getRight();
            if (node.getParent() == null) {
                // Node is root
                this.root = child;
                child.setParent(null);
            } else if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(child);
                System.out.println(child);
                child.setParent(node.getParent());
            } else {
                node.getParent().setRight(child);
                child.setParent(node.getParent());
            }
            // Set child's parent pointer
            if (child != null) {
                child.setParent(node.getParent());
            }
        } else {
            // Case 3: Node has two children
            TNode successor = findSuccessor(node);
            int temp = successor.getData();
            delete(successor.getData());
            node.setData(temp);
        }
    }

    /**
     * 
     * Finds the successor node of the specified node in the tree.
     * 
     * @param node the node to find the successor of
     * @return the successor node of the specified node, or null if no successor
     *         exists
     */
    public TNode findSuccessor(TNode node) {
        TNode current = node.getLeft();
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }

    /**
     * 
     * Searches the tree for a node with the specified value.
     * 
     * @param val the value to search for
     * @return the node with the specified value, or null if no such node exists
     */
    public TNode Search(int val) {
        TNode current = this.root;
        while (current != null) {
            if (val == current.getData()) {
                return current;
            } else if (val < current.getData()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return null; // Node with specified value not found in tree
    }

    public void printInOrder(TNode node) {
        if (node != null) {
            printInOrder(node.getLeft());
            System.out.print(node.getData() + " ");
            printInOrder(node.getRight());
        }
    }

    /**
     * 
     * Prints the tree in order (left subtree, root, right subtree).
     * 
     * @param node the root of the subtree to print
     */
    public void printBF() {
        if (this.root == null) {
            return;
        }
        Queue<TNode> queue = new LinkedList<>();
        queue.add(this.root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TNode node = queue.poll();
                System.out.print(node.getData() + " ");
                if (node.getLeft() != null) {
                    queue.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.add(node.getRight());
                }
            }
            System.out.println();
        }
    }

}
