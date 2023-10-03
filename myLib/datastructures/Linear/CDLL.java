package myLib.datastructures.Linear;

import myLib.datastructures.nodes.DNode;

/**
 * A circular doubly linked list implementation.
 */
public class CDLL extends DLL {
    /**
     * Constructs a new CDLL with the given node as its head and tail. The list
     * will be circular, with the head's previous node pointing to the tail,
     * and the tail's next node pointing to the head.
     *
     * @param node the node to use as the head and tail of the list
     */
    public CDLL(DNode node) {
        super(node);
        node.setNext(node);
    }

    /**
     * Constructs a new, empty CDLL.
     */
    public CDLL() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Inserts the given node at the beginning of the list.
     *
     * @param node the node to insert
     */
    @Override
    public void InsertHead(DNode node) {
        if (head == null) {
            head = node;
            tail = node;
            tail.setNext(head);
            head.setPrevious(tail);
        } else {
            node.setNext(head);
            head.setPrevious(node);
            head = node;
            tail.setNext(head);
            head.setPrevious(tail);
        }
        size++;
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
            tail.setNext(head);
            head.setPrevious(tail);
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
            tail.setNext(head);
            head.setPrevious(tail);
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
            head.setPrevious(node);
            head = node;
            tail.setNext(head);
        } else if (node.getData() >= tail.getData()) { // if the node should be inserted at the end of the list
            tail.setNext(node);
            node.setPrevious(tail);
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
            node.setPrevious(previous);
            current.setPrevious(node);
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
            head.setPrevious(tail);
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
            DNode newTail = tail.getPrevious();
            newTail.setNext(head);
            head.setPrevious(newTail);
            tail = newTail;
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
        DNode previous = current.getPrevious();
        while (current != null && current != node) {
            previous = current;
            current = current.getNext();
        }
        if (current == null) { // if the node to delete was not found
            return;
        }
        previous.setNext(current.getNext());
        current.getNext().setPrevious(previous);
        if (current == tail) { // if the node to delete is the tail
            tail = previous;
            tail.setNext(head);
            head.setPrevious(tail);
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
                nodeToInsert.setPrevious(null);
                sortedHead = nodeToInsert;
            } else {
                // Insert after prev
                nodeToInsert.setNext(prev.getNext());
                nodeToInsert.setPrevious(prev);
                prev.setNext(nodeToInsert);
            }
        }

        // Update head and tail pointers
        head = sortedHead;
        DNode current = head;
        for (int i = 1; i < this.size; i++) {
            DNode temp = current;
            current = current.getNext();
            current.setPrevious(temp);
        }
        tail = current;
        head.setPrevious(tail);
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
