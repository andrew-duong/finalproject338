package myLib.datastructures.Linear;

import myLib.datastructures.nodes.DNode;

/**
 * 
 * This class represents a stack data structure implemented using a singly
 * linked list.
 * The elements in the stack are inserted and removed from the top of the stack.
 */
public class StackLL extends SLL {
    /**
     * Constructor for StackLL class
     */
    public StackLL() {
        super();
    }

    /**
     * Inserts a new node at the top of the stack
     * 
     * @param node the node to be added to the stack
     */
    public void push(DNode node) {
        super.InsertHead(node);
    }

    /**
     * Removes and returns the node at the top of the stack
     * 
     * @return the node at the top of the stack, or null if the stack is empty
     */
    public DNode pop() {
        if (super.size == 0) {
            return null;
        }
        ;
        DNode head = super.head;
        super.DeleteHead();
        return head;
    }

    /**
     * Returns the node at the top of the stack without removing it
     * 
     * @return the node at the top of the stack, or null if the stack is empty
     */
    public DNode peek() {
        return super.head;
    }

    /**
     * Removes all elements from the stack
     */
    public void clear() {
        super.Clear();
    }

    /**
     * Checks whether the stack is empty
     * 
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        if (super.size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Searches for a node in the stack
     * 
     * @param node the node to search for
     * @return the position of the node in the stack, or -1 if the node is not found
     */
    public int search(DNode node) {
        DNode current = super.head;
        int count = 0;
        if (node.getData() == current.getData()) {
            return count;
        }
        while (current != null) {
            if (current.getData() == node.getData()) {
                return count;
            }
            current = current.getNext();
            count++;
        }
        return -1;
    }

    /**
     * Overrides the InsertTail() method of the SLL class to disable it
     */
    @Override
    public void InsertTail(DNode node) {
    };

    /**
     * Overrides the Insert() method of the SLL class to disable it
     */
    @Override
    public void Insert(DNode node, int position) {
    };

    /**
     * Overrides the SortedInsert() method of the SLL class to disable it
     */
    @Override
    public void SortedInsert(DNode node) {
    }

    /**
     * Overrides the isSorted() method of the SLL class to always return false
     * since stacks are not sorted
     */
    @Override
    public boolean isSorted() {
        return false;
    };

    /**
     * Overrides the DeleteTail() method of the SLL class to disable it
     */
    @Override
    public void DeleteTail() {
    };

    /**
     * Overrides the Delete() method of the SLL class to disable it
     */
    @Override
    public void Delete(DNode node) {
    };

    /**
     * Overrides the Sort() method of the SLL class to disable it
     */
    @Override
    public void Sort() {
    };

    /**
     * Overrides the Print() method of the SLL class to print stack-specific
     * information
     */
    @Override
    public void Print() {
        // Print list length
        System.out.println("List length: " + super.size);

        // Print sorted status
        if (isSorted()) {
            System.out.println("List is sorted");
        } else {
            System.out.println("List is not sorted because it is a stack.");
        }

        // Print list content
        System.out.print("List content: ");
        DNode current = head;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }
}
