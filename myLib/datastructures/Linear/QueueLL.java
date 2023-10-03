package myLib.datastructures.Linear;

import myLib.datastructures.nodes.DNode;

/**
 * 
 * This class represents a queue data structure implemented using a singly
 * linked list.
 * It extends the SLL class and overrides some of its methods to make them
 * specific to queue operations.
 */
public class QueueLL extends SLL {
    /**
     * Constructor that creates a new QueueLL instance.
     */
    public QueueLL() {
        super();
    }

    /**
     * Adds the specified node to the tail of the queue.
     *
     * @param node the node to be added to the tail of the queue
     */
    public void enqueue(DNode node) {
        super.InsertTail(node);
    }

    /**
     * Removes the node at the head of the queue.
     */
    public void dequeue() {
        super.DeleteHead();
    }

    /**
     * Returns the node at the head of the queue without removing it.
     *
     * @return the node at the head of the queue
     */
    public DNode peek() {
        return super.head;
    }

    /**
     * Returns the current size of the queue.
     *
     * @return the current size of the queue
     */
    public int size() {
        return super.size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        if (super.size == 0) {
            return true;
        }
        return false;
    }

    /**
     * This method is not supported for a queue and does nothing.
     *
     * @param node the node to be inserted at the head of the list
     */
    @Override
    public void InsertHead(DNode node) {
    };

    /**
     * This method is not supported for a queue and does nothing.
     *
     * @param node     the node to be inserted at the specified position in the list
     * @param position the position at which the node is to be inserted
     */
    @Override
    public void Insert(DNode node, int position) {
    };

    /**
     * This method is not supported for a queue and does nothing.
     *
     * @param node the node to be inserted into the sorted list
     */
    @Override
    public void SortedInsert(DNode node) {
    }

    /**
     * This method is not supported for a queue and always returns false.
     *
     * @return false
     */
    @Override
    public boolean isSorted() {
        return false;
    };

    /**
     * This method is not supported for a queue and does nothing.
     */
    @Override
    public void DeleteTail() {
    };

    /**
     * This method is not supported for a queue and does nothing.
     *
     * @param node the node to be deleted from the list
     */
    @Override
    public void Delete(DNode node) {
    };

    /**
     * This method is not supported for a queue and does nothing.
     */
    @Override
    public void Sort() {
    };

    /**
     * Searches the queue for the specified node and returns its position.
     *
     * @param node the node to be searched for
     * @return the position of the node in the queue, or -1 if the node is not found
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
     * 
     * Overrides the Print method of the parent class and prints the length of the
     * queue and its content.
     * If the queue is not sorted, it prints a message indicating that it is sorted
     * from oldest (head) to newest (tail).
     */
    @Override
    public void Print() {
        // Print list length
        System.out.println("Queue length: " + super.size);

        if (!isSorted()) {
            System.out.println("Queue is sorted from oldest(head) to newest(tail).");
        }
        // Print list content
        System.out.print("Queue content: ");
        DNode current = head;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }
}
