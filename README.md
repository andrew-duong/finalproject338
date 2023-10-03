# Java Data Structures Implementation Project

This repository contains the implementation of various data structures in Java. The initial set of classes and their base functionalities are described below:

## Singly Linked List (SLL)
This is a Singly Linked List data structure that implements the following functionalities:
- Uses a head object of the base class Node and a tail object to keep track of the end of the list.
- Maintains an integer member variable to keep track of the size of the list.
- Provides two constructors:
  - Default constructor with no arguments that creates a null head object.
  - Overload constructor with a Node object argument to use as the head.
- Supports operations such as InsertHead(node), InsertTail(node), Insert(node, position), SortedInsert(node), Search(node), DeleteHead(), DeleteTail(), Delete(node), Sort(), Clear(), and Print().

## Doubly Linked List (DLL)
This is a Doubly Linked List data structure that extends the Singly Linked List (SLL) and adds extra functionality. Some modifications and overriding of methods from the SLL class may be needed.

## Circular Singly Linked List (CSLL)
This is a Circular Singly Linked List data structure that extends the Singly Linked List (SLL) and adds extra functionality. Some modifications and overriding/overloading of methods from the SLL class may be needed.

## Circular Doubly Linked List (CDLL)
This is a Circular Doubly Linked List data structure that extends the Doubly Linked List (DLL) and adds extra functionality. Modifications and overriding/overloading of methods from the DLL class might be needed.

## Stack based on Singly Linked List (LLStack)
This is a Stack data structure based on the Singly Linked List (SLL) and extends the SLL class. It includes proper naming conventions for stack operations and overrides methods from SLL that are not applicable to stacks.

## Queue based on Singly Linked List (LLQueue)
This is a Queue data structure based on the Singly Linked List (SLL) and extends the SLL class. It includes proper naming conventions for queue operations and overrides methods from SLL that are not applicable to queues.

## TNode Class (TNode)
This class is a general tree node class that has requirements for both BST and AVL trees. It includes the following member variables:
- int data
- TNode left
- TNode right
- TNode parent
- int balance
The class implements setters, getters, print, toString, and constructors for creating tree nodes.

## BST (Binary Search Tree)
This class is the implementation of the Binary Search Tree (BST) for integer data. It provides operations such as insertion, deletion, search, and traversal (in-order and breadth-first).

## AVL Tree (AVL)
This class is the implementation of the self-balancing AVL tree for integer data members. It ensures that the tree remains balanced after insertions and deletions, providing efficient search and retrieval operations.

## Node Class (DNode)
The DNode class is used as the base class for nodes in the linked lists. It provides the necessary structure for the linked lists, including references to the next and previous nodes.

Please note that this implementation aims to provide a comprehensive set of data structures, and you can choose the one that best suits your specific application. Additionally, the use of the DNode class maximizes code reusability across different linear structures.

## Usage
You can use these data structures in your Java projects by importing the relevant classes from the `datastructures.Linear` and `datastructures.Trees` packages. Be sure to review the documentation and method descriptions in each class for more details on usage.

Feel free to explore and adapt these data structures to your project's requirements. If you encounter any issues or have suggestions for improvements, please don't hesitate to contribute or open an issue.
