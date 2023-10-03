package com.example.datastructures.trees;

import com.example.datastructures.nodes.TNode;

/**
 * 
 * AVL class, extends the BST class and implements the AVL tree self-balancing
 * algorithm.
 */
public class AVL extends BST {
    /**
     * 
     * Default constructor.
     */
    public AVL() {
        super();
    }

    /**
     * 
     * Constructor that takes an integer value to set as the root of the AVL tree.
     * 
     * @param val The integer value to set as the root.
     */
    public AVL(int val) {
        super(val);
    }

    /**
     * 
     * Constructor that takes a TNode object and constructs an AVL tree from it.
     * 
     * @param obj The TNode object to construct the AVL tree from.
     */
    public AVL(TNode obj) {
        super(obj);
        if (obj.getLeft() != null || obj.getRight() != null) {
            balance(super.root);
        }
    }

    /**
     * 
     * Private method to balance the AVL tree starting from the given node.
     * 
     * @param node The TNode object to start balancing from.
     * 
     * @return The TNode object that was balanced.
     */
    private TNode balance(TNode node) {
        if (node == null) {
            return null;
        }
        int balanceFactor = getBalance(node);

        if (balanceFactor < -1) {
            if (height(node.getLeft().getLeft()) >= height(node.getLeft().getRight())) {
                node = rotateRight(node);
            } else {
                TNode left = rotateLeft(node.getLeft());
                node.setLeft(left);
                node = rotateRight(node);
            }
        } else if (balanceFactor > 1) {
            if (height(node.getRight().getRight()) >= height(node.getRight().getLeft())) {
                node = rotateLeft(node);
            } else {
                TNode right = rotateRight(node.getRight());
                node.setRight(right);
                node = rotateLeft(node);
            }
        }
        node.setBalance(getBalance(node));

        return node;
    }

    /**
     * Performs a right rotation on the subtree rooted at the given node, if
     * necessary.
     * 
     * @param node the root node of the subtree to rotate
     * @return the new root node of the subtree
     */
    private TNode rotateRight(TNode node) {
        TNode newRoot = node.getLeft();
        TNode right = newRoot.getRight();
        TNode parent = node.getParent();
        node.setLeft(right);
        newRoot.setRight(node);
        node.setParent(newRoot);
        newRoot.setParent(parent);
        if (right != null) {
            right.setParent(node);
        }
        node.setBalance(getBalance(node));
        newRoot.setBalance(getBalance(newRoot));
        return newRoot;
    }

    /**
     * Performs a left rotation on the subtree rooted at the given node, if
     * necessary.
     * 
     * @param node the root node of the subtree to rotate
     * @return the new root node of the subtree
     */
    private TNode rotateLeft(TNode node) {
        TNode newRoot = node.getRight();
        TNode left = newRoot.getLeft();
        TNode parent = node.getParent();
        node.setRight(left);
        newRoot.setLeft(node);
        node.setParent(newRoot);
        newRoot.setParent(parent);
        if (left != null) {
            left.setParent(node);
        }
        node.setBalance(getBalance(node));
        newRoot.setBalance(getBalance(newRoot));
        return newRoot;
    }

    /**
     * Calculates the height of the subtree rooted at the given node.
     * 
     * @param node the root node of the subtree to measure
     * @return the height of the subtree
     */
    private int height(TNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    /**
     * Returns the root node of the AVL tree.
     * 
     * @return the root node
     */
    public TNode getRoot() {
        return super.getRoot();
    }

    /**
     * Sets the root node of the AVL tree to the given node and rebalances the tree
     * if necessary.
     * If the given node has children, the tree is first balanced with the given
     * node as the root.
     * Then, all nodes in the current tree are inserted into a temporary AVL tree,
     * excluding the root node.
     * Finally, the root node of the temporary tree is set as the new root of the
     * current tree and the tree is rebalanced.
     * 
     * @param newRoot the new root node to be set
     */
    @Override
    public void setRoot(TNode newRoot) {
        if (newRoot.getLeft() != null || newRoot.getRight() != null) {
            newRoot = balance(newRoot);
        }
        AVL temp = new AVL(newRoot);

        insertNodes(super.root, temp);
        super.root = temp.getRoot();
        super.root = balance(super.root);
    }

    /**
     * Recursively inserts all nodes in the given node's subtree into the given AVL
     * tree, excluding the root node.
     * 
     * @param node the root of the subtree to insert
     * @param tree the AVL tree to insert the nodes into
     */
    private void insertNodes(TNode node, AVL tree) {
        if (node == null) {
            return;
        }
        insertNodes(node.getLeft(), tree);
        insertNodes(node.getRight(), tree);
        if (node != super.root) {
            System.out.println(node);
            tree.insert(node.getData());
        }
    }

    /**
     * 
     * Inserts a new node into the AVL tree, balances the tree if necessary, and
     * updates balance factors.
     * 
     * @param node the node to be inserted
     */
    @Override
    public void insert(TNode node) {
        super.insert(node);
        node = balance(node);
        updateBalance(node);
        updateBalance(super.root);
        if (super.root.getRight() != null || super.root.getLeft() != null) {
            TNode doubleRight = checkDoubleRight(super.root);
            TNode doubleLeft = checkDoubleLeft(super.root);
            TNode rotateRight = rightRotate(super.root);
            TNode rotateLeft = leftRotate(super.root);
            if (rotateRight != null && rotateRight.getData() != super.root.getData()) {
                TNode parentRight = rotateRight.getParent();
                TNode right = balance(rotateRight);
                right.setParent(parentRight);
                if (parentRight.getLeft().getData() == rotateRight.getData()) {
                    parentRight.setLeft(right);
                } else {
                    parentRight.setRight(right);
                }
            } else if (rotateLeft != null && rotateLeft.getData() != super.root.getData()) {
                TNode parentLeft = rotateLeft.getParent();
                TNode left = balance(rotateLeft);
                left.setParent(parentLeft);
                if (parentLeft.getLeft().getData() == rotateLeft.getData()) {
                    parentLeft.setLeft(left);
                } else {
                    parentLeft.setRight(left);
                }
            }
            if (rotateRight != null && rotateLeft != null) {
                if (rotateRight.getData() == super.root.getData() || rotateLeft.getData() == super.root.getData()) {
                    super.root = balance(super.root);
                }
            }
            if (doubleLeft == null && doubleRight == null) {
                super.root = balance(super.root);
            } else if (doubleRight != null) {
                TNode parentRight = doubleRight.getParent();
                TNode right = balance(doubleRight);
                if (parentRight.getLeft().getData() == doubleRight.getData()) {
                    parentRight.setLeft(right);
                } else {
                    parentRight.setRight(right);
                }
            } else if (doubleLeft != null) {
                TNode parentLeft = doubleLeft.getParent();
                TNode left = balance(doubleLeft);
                if (parentLeft.getLeft().getData() == doubleLeft.getData()) {
                    parentLeft.setLeft(left);
                } else {
                    parentLeft.setRight(left);
                }
            }
        }
    }

    /**
     * 
     * Inserts a new node with the given value into the AVL tree and rebalances the
     * tree if necessary.
     * 
     * @param val the value to be inserted into the AVL tree
     */
    public void insert(int val) {
        super.insert(val);
        TNode node = Search(val);
        node = balance(node);
        updateBalance(node);
        updateBalance(super.root);
        if (super.root.getRight() != null || super.root.getLeft() != null) {
            TNode doubleRight = checkDoubleRight(super.root);
            TNode doubleLeft = checkDoubleLeft(super.root);
            TNode rotateRight = rightRotate(super.root);
            TNode rotateLeft = leftRotate(super.root);
            if (rotateRight != null && rotateRight.getData() != super.root.getData()) {
                TNode parentRight = rotateRight.getParent();
                TNode right = balance(rotateRight);
                right.setParent(parentRight);
                if (parentRight.getLeft().getData() == rotateRight.getData()) {
                    parentRight.setLeft(right);
                } else {
                    parentRight.setRight(right);
                }
            } else if (rotateLeft != null && rotateLeft.getData() != super.root.getData()) {
                TNode parentLeft = rotateLeft.getParent();
                TNode left = balance(rotateLeft);
                left.setParent(parentLeft);
                if (parentLeft.getLeft().getData() == rotateLeft.getData()) {
                    parentLeft.setLeft(left);
                } else {
                    parentLeft.setRight(left);
                }
            }
            if (rotateRight != null && rotateLeft != null) {
                if (rotateRight.getData() == super.root.getData() || rotateLeft.getData() == super.root.getData()) {
                    super.root = balance(super.root);
                }
            }
            if (doubleLeft == null && doubleRight == null) {
                super.root = balance(super.root);
            } else if (doubleRight != null) {
                TNode parentRight = doubleRight.getParent();
                TNode right = balance(doubleRight);
                if (parentRight.getLeft().getData() == doubleRight.getData()) {
                    parentRight.setLeft(right);
                } else {
                    parentRight.setRight(right);
                }
            } else if (doubleLeft != null) {
                TNode parentLeft = doubleLeft.getParent();
                TNode left = balance(doubleLeft);
                if (parentLeft.getLeft().getData() == doubleLeft.getData()) {
                    parentLeft.setLeft(left);
                } else {
                    parentLeft.setRight(left);
                }
            }
        }
    }

    /**
     * Checks for a left rotation starting from the given node and returns the last node that requires a left rotation
     * 
     *
     * @param node the starting node to check left rotation on
     * @return the last node that requires a left rotation
     */
    private TNode leftRotate(TNode node) {

        if (node == null) {
            return null;
        }

        TNode result = null;

        if (node.getBalance() == 2) {
            result = node;
        }

        TNode leftResult = leftRotate(node.getLeft());
        if (leftResult != null) {
            result = leftResult;
        }

        TNode rightResult = leftRotate(node.getRight());
        if (rightResult != null) {
            result = rightResult;
        }

        return result;
    }

    /**
     * Checks for a right rotation starting from the given node and returns the last node that requires a right rotation
     *
     *
     * @param node the starting node to check right rotation on
     * @return the last node that requires a right rotation
     */
    private TNode rightRotate(TNode node) {
        if (node == null) {
            return null;
        }

        TNode result = null;

        if (node.getBalance() == -2) {

            result = node;
        }

        TNode leftResult = rightRotate(node.getLeft());
        if (leftResult != null) {
            result = leftResult;
        }

        TNode rightResult = rightRotate(node.getRight());
        if (rightResult != null) {
            result = rightResult;
        }

        return result;
    }

    /**
     * Checks if a node violates the double left rotation condition and returns the
     * node that violates it.
     * A node violates the double left rotation condition if it has a right child
     * whose right child is null and left child is not null.
     * 
     * @param node the root of the subtree to be checked
     * @return the node that violates the double left rotation condition, or null if
     *         no such node is found
     */
    private TNode checkDoubleLeft(TNode node) {
        if (node == null) {
            return null;
        }

        TNode result = null;

        // Check for condition: right child's right child is null and right child's left
        // is not null
        if (node.getRight() != null && node.getLeft() == null && node.getRight().getRight() == null
                && node.getRight().getLeft() != null) {
            result = node;
        }

        // Recursively check left and right subtrees
        TNode leftResult = checkDoubleLeft(node.getLeft());
        TNode rightResult = checkDoubleLeft(node.getRight());

        if (leftResult != null) {
            result = leftResult;
        }

        if (rightResult != null) {
            result = rightResult;
        }

        return result;
    }

    /**
     * Checks if a node violates the double right rotation condition and returns the
     * node that violates it.
     * A node violates the double right rotation condition if it has a left child
     * whose left child is null and right child is not null.
     * 
     * @param node the root of the subtree to be checked
     * @return the node that violates the double right rotation condition, or null
     *         if no such node is found
     */
    private TNode checkDoubleRight(TNode node) {
        if (node == null) {
            return null;
        }
        TNode result = null;

        if (node.getLeft() != null && node.getRight() == null && node.getLeft().getLeft() == null
                && node.getLeft().getRight() != null) {
            result = node;
        }

        // Recursively check left and right subtrees
        TNode leftResult = checkDoubleRight(node.getLeft());
        TNode rightResult = checkDoubleRight(node.getRight());

        if (leftResult != null) {
            result = leftResult;
        }
        if (rightResult != null) {
            result = rightResult;
        }

        return result;
    }

    /**
     * 
     * Deletes the node with the given value from the AVL tree.
     * If the node is not found in the tree, prints an error message and returns.
     * If the node has no children, it is simply removed from the tree.
     * If the node has one child, the child replaces the node in the tree.
     * If the node has two children, its successor is found, swapped with the node,
     * and then the successor is deleted.
     * After the node is deleted, the AVL tree checks each node on the path from the
     * deleted node to the root
     * and performs rotations if necessary to maintain the balance.
     * 
     * @param val the value of the node to delete
     */
    @Override
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
                root = null;
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
                root = child;
                child.setParent(null);
            } else if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(child);
                child.setParent(node.getParent());
            } else {
                node.getParent().setRight(child);
                child.setParent(node.getParent());
            }
        } else {
            // Case 3: Node has two children
            TNode successor = super.findSuccessor(node);
            int temp = successor.getData();
            delete(successor.getData());
            node.setData(temp);
        }
        TNode curr = (node.getParent() == null) ? root : node.getParent();
        while (curr != null) {
            if (curr.getParent() == curr) {
                curr.setParent(null);
            }
            if (Math.abs(getBalance(curr)) > 1) {
                curr = balance(curr);
            }
            curr.setBalance(getBalance(curr));
            curr = curr.getParent();
        }
    }

    /**
     * 
     * Returns the balance factor of the given node, which is the difference between
     * the heights of its right and left subtrees.
     * 
     * @param node the node to calculate the balance factor for
     * @return the balance factor of the node
     */
    public int getBalance(TNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.getRight()) - height(node.getLeft());
    }

    /**
     * 
     * Updates the balance factor for each node on the path from the given node to
     * the root of the AVL tree.
     * This method is called after a node is inserted or deleted, to maintain the
     * balance of the tree.
     * 
     * @param node the node to update the balance factors for
     */
    private void updateBalance(TNode node) {
        while (node != null) {
            node.setBalance(getBalance(node)); // update balance factor
            node = node.getParent(); // move up to the parent node
        }
    }

    /**
     * 
     * Searches the AVL tree for a node with the given value and returns it if
     * found.
     * 
     * @param val the value to search for
     * @return the node with the given value, or null if not found
     */
    public TNode Search(int val) {
        return super.Search(val);
    }

    /**
     * 
     * Prints the nodes of the AVL tree in order (left, root, right).
     * 
     * @param node the root of the tree to print
     */
    public void printInOrder(TNode node) {
        super.printInOrder(node);
    }

    /**
     * 
     * Prints the nodes of the AVL tree in breadth-first order (level by level).
     */
    public void printBF() {
        super.printBF();
    }
}
