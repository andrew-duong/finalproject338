package com.example.datastructures.Linear;

import com.example.datastructures.nodes.DNode;

/**
 * The `CSLL` (Circular Singly Linked List) class represents a circular singly
 * linked list
 * which extends the `SLL` (Singly Linked List) class.
 */
public class CSLL extends SLL {
    /**
     * Constructs a new `CSLL` object with the specified head node.
     * 
     * @param node the head node of the circular singly linked list
     */
    public CSLL(DNode node) {
        super(node);
        node.setNext(node); // Loop back to head
    }

    /**
     * Constructs a new empty `CSLL` object.
     */
    public CSLL() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Inserts a new node at the head of the circular singly linked list.
     * 
     * @param node the node to be inserted at the head of the circular singly linked
     *             list
     */
    @Override
    public void InsertHead(DNode node) {
        if (head == null) { // If list is empty, set head and loop back
            head = node;
            tail = node;
            node.setNext(head);
        } else { // If list is not empty, insert at the beginning and update head and tail
            DNode temp = head;
            node.setNext(temp);
            head = node;
            tail.setNext(head);
        }
        size++; // Increase size by 1
    }

    /**
     * Inserts the given node at the end of the list.
     *
     * @param node the node to insert
     */
    @Override
    public void InsertTail(DNode node) {
        if (tail == null) {
            head = node;
            tail = node;
            node.setNext(head);
        } else {
            tail.setNext(node);
            tail = node;
            tail.setNext(head);
        }
        size++;
    }

    /**
     * Inserts the given node into the list in sorted order.
     *
     * @param node the node to insert
     */
    @Override
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
            tail.setNext(head);
        } else if (node.getData() >= tail.getData()) { // if the node should be inserted at the end of the list
            tail.setNext(node);
            tail = node;
            tail.setNext(head);
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
     * Removes the head node from the list.
     */
    @Override
    public void DeleteHead() {
        if (head == null) {
            return;
        }
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            tail.setNext(head);
        }
        size--;
    }

    /**
     * Removes the tail node from the list.
     */
    @Override
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
            current.setNext(head);
            tail = current;
        }
        size--;
    }

    /**
     * Removes the given node from the list.
     *
     * @param node the node to remove
     */
    @Override
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
            DeleteTail();
            return;
        }
        size--;
    }

    /**
     * Sorts the list in ascending order using insertion sort.
     */
    @Override
    public void Sort() {
        if (size <= 1) {
            return; // Already sorted
        }

        DNode sortedHead = head; // Start with the first node as the sorted head

        // Iterate over the unsorted part of the list
        DNode unsorted = head.getNext();
        while (unsorted != head) {
            // Get the next node to insert into the sorted part of the list
            DNode nodeToInsert = unsorted;
            unsorted = unsorted.getNext();

            // Find the insertion position in the sorted part of the list
            DNode current = sortedHead;
            DNode prev = null;
            while (current != head && current.getData() < nodeToInsert.getData()) {
                prev = current;
                current = current.getNext();
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
        tail.setNext(head);
    }

    /**
     * 
     * Returns a boolean value indicating whether the elements in the circular
     * doubly linked list
     * are sorted in non-decreasing order or not.
     * 
     * @return true if the list is sorted in non-decreasing order, false otherwise.
     */
    @Override
    public boolean isSorted() {
        if (head == null || head.getNext() == null) {
            return true;
        }
        DNode current = head;
        while (current.getNext() != head) {
            if (current.getData() > current.getNext().getData()) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }
}
