package myLib.datastructures.nodes;

/**
 * A class representing a doubly linked node in a doubly linked list.
 */
public class DNode {
    private int data;
    private DNode next;
    private DNode previous;

    /**
     * Constructs a new doubly linked node with the given data, next node, and
     * previous node.
     *
     * @param data     the data to be stored in the node
     * @param next     the next node in the linked list
     * @param previous the previous node in the linked list
     */
    public DNode(int data, DNode next, DNode previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    /**
     * Constructs a new doubly linked node with the given data and next node.
     *
     * @param data the data to be stored in the node
     * @param next the next node in the linked list
     */
    public DNode(int data, DNode next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Constructs a new doubly linked node with the given data, and no next or
     * previous nodes.
     *
     * @param data the data to be stored in the node
     */
    public DNode(int data) {
        this(data, null, null);
    }

    /**
     * Returns the data stored in this node.
     *
     * @return the data stored in this node
     */
    public int getData() {
        return this.data;
    }

    /**
     * Returns the next node in the linked list.
     *
     * @return the next node in the linked list
     */
    public DNode getNext() {
        return this.next;
    }

    /**
     * Returns the previous node in the linked list.
     *
     * @return the previous node in the linked list
     */
    public DNode getPrevious() {
        return this.previous;
    }

    /**
     * Sets the data stored in this node to the given value.
     *
     * @param num the new value to be stored in this node
     */
    public void setData(int num) {
        this.data = num;
    }

    /**
     * Sets the next node in the linked list to the given node.
     *
     * @param nextNode the new next node in the linked list
     */
    public void setNext(DNode nextNode) {
        this.next = nextNode;
    }

    /**
     * Sets the previous node in the linked list to the given node.
     *
     * @param prevNode the new previous node in the linked list
     */
    public void setPrevious(DNode prevNode) {
        this.previous = prevNode;
    }
}
