package myLib.datastructures.Linear;

import myLib.datastructures.nodes.DNode;

/**
 * 
 * A singly-linked list implementation with basic operations such as insertion,
 * deletion, sorting, and printing.
 */
public class SLL {
    DNode head;
    DNode tail;
    int size;

    /**
     * Returns the head node of the SLL.
     *
     * @return the head node of the SLL.
     */
    public DNode getHead() {
        return this.head;
    }

    /**
     * Returns the tail node of the SLL.
     *
     * @return the tail node of the SLL.
     */
    public DNode getTail() {
        return this.tail;
    }

    /**
     * Returns the number of nodes in the SLL.
     *
     * @return the size of the SLL.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Constructs an empty SLL.
     */
    public SLL() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Constructs a SLL with a single node.
     *
     * @param node the node to be added to the SLL.
     */
    public SLL(DNode node) {
        this.head = node;
        this.tail = node;
        this.size = 1;
    }

    /**
     * Inserts a node at the beginning of the SLL.
     *
     * @param node the node to be inserted at the beginning of the SLL.
     */
    public void InsertHead(DNode node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head = node;
        }
        size++;
    }

    /**
     * Inserts a node at the end of the SLL.
     *
     * @param node the node to be inserted at the end of the SLL.
     */
    public void InsertTail(DNode node) {
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    /**
     * Inserts a node at the specified position in the SLL.
     *
     * @param node     the node to be inserted at the specified position in the SLL.
     * @param position the position at which the node is to be inserted in the SLL.
     * @throws IndexOutOfBoundsException if the specified position is out of range
     *                                   (position < 0 || position > size).
     */
    public void Insert(DNode node, int position) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException();
        }
        if (position == 0) {
            InsertHead(node);
        } else if (position == size) {
            InsertTail(node);
        } else {
            DNode current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.getNext();
            }
            node.setNext(current.getNext());
            current.setNext(node);
            size++;
        }
    }

    /**
     * 
     * Inserts a node into the list in sorted order.
     * If the list is not sorted, it will be sorted before the insertion.
     * 
     * @param node the node to be inserted
     */
    public void SortedInsert(DNode node) {
        if (!isSorted()) {
            Sort();
        }
        if (head == null) { // if the list is empty
            head = node;
            tail = node;
        } else if (node.getData() <= head.getData()) { // if the node should be inserted at the beginning of the list
            node.setNext(head);
            head = node;
        } else if (node.getData() >= tail.getData()) { // if the node should be inserted at the end of the list
            tail.setNext(node);
            tail = node;
        } else { // if the node should be inserted somewhere in the middle of the list
            DNode current = head.getNext();
            DNode previous = head;
            while (current != null && node.getData() > current.getData()) {
                previous = current;
                current = current.getNext();
            }
            node.setNext(current);
            previous.setNext(node);
        }
        // check if the list is sorted

    }

    /**
     * 
     * Checks if the list is sorted in non-descending order.
     * 
     * @return true if the list is sorted, false otherwise
     */
    public boolean isSorted() {
        if (head == null || head.getNext() == null) {
            return true;
        }
        DNode current = head;
        while (current.getNext() != null) {
            if (current.getData() > current.getNext().getData()) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    /**
     * 
     * Searches for a node in the list that contains the same data as the specified
     * node.
     * 
     * @param node the node to search for
     * @return the node in the list that matches the specified node, or null if not
     *         found
     */
    public DNode Search(DNode node) {
        DNode current = head;
        for (int i = 1; i < this.size; i++) {
            if (current.getData() == node.getData()) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * 
     * Removes the first node in the list.
     */
    public void DeleteHead() {
        if (head == null) {
            return;
        }
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
        }
        size--;
    }

    /**
     * 
     * Removes the last node in the list.
     */
    public void DeleteTail() {
        if (tail == null) {
            return;
        }
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            DNode current = head;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            current.setNext(null);
            tail = current;
        }
        size--;
    }

    /**
     * 
     * Removes a specified node from the list.
     * 
     * @param node the node to be removed
     */
    public void Delete(DNode node) {
        if (head == null) { // if the list is empty
            return;
        }
        if (head == node) { // if the node to delete is the head
            DeleteHead();
            return;
        }
        DNode current = head.getNext();
        DNode previous = head;
        while (current != null && current != node) {
            previous = current;
            current = current.getNext();
        }
        if (current == null) { // if the node to delete was not found
            return;
        }
        previous.setNext(current.getNext());
        if (current == tail) { // if the node to delete is the tail
            tail = previous;
        }
        size--;
    }

    /**
     * Sorts the doubly linked list in non-decreasing order using insertion sort.
     * If the list has size 0 or 1, it is already sorted and this method returns
     * immediately.
     */
    public void Sort() {
        if (size <= 1) {
            return; // Already sorted
        }

        DNode sortedHead = head; // Start with the first node as the sorted head

        // Iterate over the unsorted part of the list
        DNode unsorted = head.getNext();
        while (unsorted != null) {
            System.out.println("out");
            // Get the next node to insert into the sorted part of the list
            DNode nodeToInsert = unsorted;
            unsorted = unsorted.getNext();

            // Find the insertion position in the sorted part of the list
            DNode current = sortedHead;
            DNode prev = null;
            while (current != null && current.getData() < nodeToInsert.getData()) {
                prev = current;
                current = current.getNext();
                System.out.println("in");
            }

            // Insert the node into the sorted part of the list
            if (prev == null) {
                // Insert at the beginning of the sorted part of the list
                nodeToInsert.setNext(sortedHead);
                sortedHead = nodeToInsert;
            } else {
                // Insert after prev
                nodeToInsert.setNext(prev.getNext());
                prev.setNext(nodeToInsert);
            }
        }

        // Update head and tail pointers
        head = sortedHead;
        DNode current = head;
        for (int i = 1; i < this.size; i++) {
            current = current.getNext();
        }
        tail = current;
        tail.setNext(null);
    }

    /**
     * Removes all elements from the doubly linked list, setting the head and tail
     * pointers to null and size to 0.
     */
    public void Clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Prints the length of the doubly linked list, whether it is sorted or not, and
     * the contents of the list in order.
     */
    public void Print() {
        // Print list length
        System.out.println("List length: " + size);

        // Print sorted status
        if (isSorted()) {
            System.out.println("List is sorted");
        } else {
            System.out.println("List is not sorted");
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
