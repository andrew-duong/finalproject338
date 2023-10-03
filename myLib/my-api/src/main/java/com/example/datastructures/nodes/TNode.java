package com.example.datastructures.nodes;

/**
 * 
 * A node class for a binary search tree that holds an integer value,
 * 
 * its balance factor, and references to its parent, left child, and right
 * child.
 */
public class TNode {
    private int data;
    private TNode left;
    private TNode right;
    private TNode parent;
    private int balance;

    /**
     * 
     * Constructs a new TNode with default values.
     * The data, left child, right child, parent and balance factor are initialized
     * to 0 or null.
     */
    public TNode() {
        this.data = 0;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.balance = 0;
    }

    /**
     * 
     * Constructs a new TNode with the specified values.
     * 
     * @param data    the integer value to be stored in this node.
     * @param balance the balance factor to be set for this node.
     * @param parent  the parent node of this node.
     * @param left    the left child of this node.
     * @param right   the right child of this node.
     */
    public TNode(int data, int balance, TNode parent, TNode left, TNode right) {
        this.data = data;
        this.balance = balance;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    /**
     * 
     * Sets the integer data stored in this node.
     * 
     * @param data the integer value to be stored in this node.
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * 
     * Returns the integer data stored in this node.
     * 
     * @return the integer data stored in this node.
     */
    public int getData() {
        return data;
    }

    /**
     * 
     * Sets the left child of this node.
     * 
     * @param left the left child node to be set for this node.
     */

    public void setLeft(TNode left) {
        this.left = left;
    }

    /**
     * 
     * Returns the left child of this node.
     * 
     * @return the left child of this node.
     */
    public TNode getLeft() {
        return left;
    }

    /**
     * 
     * Sets the right child of this node.
     * 
     * @param right the right child node to be set for this node.
     */
    public void setRight(TNode right) {
        this.right = right;
    }

    /**
     * 
     * Returns the right child of this node.
     * 
     * @return the right child of this node.
     */
    public TNode getRight() {
        return right;
    }

    /**
     * 
     * Sets the parent of this node.
     * 
     * @param parent the parent node to be set for this node.
     */
    public void setParent(TNode parent) {
        this.parent = parent;
    }

    /**
     * 
     * Returns the parent of this node.
     * 
     * @return the parent of this node.
     */
    public TNode getParent() {
        return parent;
    }

    /**
     * 
     * Sets the balance factor of the TNode.
     * 
     * @param balance the balance factor to set
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * 
     * Returns the balance factor of this node.
     * 
     * @return the balance factor of this node.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Prints the data and balance factor of the node to the console.
     */
    public void print() {
        System.out.println("Data: " + data + " Balance: " + balance);
    }

    /**
     * Returns a string representation of the node's data.
     *
     * @return a string representation of the node's data.
     */
    public String toString() {
        return Integer.toString(data);
    }
}
