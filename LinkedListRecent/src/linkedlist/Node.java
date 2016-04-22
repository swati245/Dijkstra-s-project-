/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

/**
 *
 * @author tuf18082
 */
public class Node {
    // This is the node class which has a Point type variable (which has the x, y coordinates and other attributes of a point),
// a node type previous field which has a reference of the previous node and a node type next field which has a reference
// of the next node in the double linked list.

    private Point XYPoint;  // Point type field
    private Node previous;  // This field holds a reference of the previous node
    private Node next;      // This field holds a reference of the next node

    public Node(Point newPoint, Node previousNode, Node nextNode) {  // A constructor to initialize all the three fields
        this.XYPoint = newPoint;
        previous = previousNode;
        next = nextNode;
    }

    protected Node returnPrevious() {  // Returns the previous node
        return this.previous;
    }

    protected Node returnNext() { // Returns the next node
        return this.next;
    }

    protected Point returnPoint() {  // Returns the Point in that node
        return XYPoint;
    }

    protected void setPrevious(Node newPrevious) {  // Sets the previous field to point to some other node
        previous = newPrevious;
    }

    protected void setNext(Node newNext) {  // Initializes the next field so that the current node has a node after it
        next = newNext;
    }


}
