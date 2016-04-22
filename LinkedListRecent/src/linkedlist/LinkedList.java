/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import simplegui.SimpleGUI;

/**
 *
 * @author tuf18082
 */
public class LinkedList {
// Name : Swati Shrivastava
// Assignment 4 
// This assignment aims to create a double linked list. It then tells us to delete the points which have the least significance.
// The final image of the deer is the one of an abstracted one which has deleted the least significant points of the deer image.

// Some of the methods used in this class are as follows : 
// 1. catchException() - This method displays an error message if any kind of exception is encountered while reading the file.
// 2. readCoordinatesFile() - This file reads an X-Coordinate and a Y-Coordinate from the given file. 
// 3. createNodes() - These method create a double linked list. The coordinates read from the above method are stored in nodes of the double linked list thus created.
// 4. drawDeer() - This method simply takes the coordinates and and draws an unabstracted image of the deer.   
// 5. eucledianFormulae() - This method computes the eucledian formulae for any two points.
// 6. calcVisualSignificance() - These method simply calculates the importance of each coordinate. The importance is calculated using eucledianFormulae().
// 7. findLowestSignificance() - This method finds the least significant coordinate from the list of coordinates.
// 8. deleteLowestSignificance() - This method deletes or removes the coordinate from the node of the double linked list.
//    The coordinate to be removed is obtained from the above method.
// 9. drawAbstractedDeer() - After all the least significant coordinates are removed from the list and only 38 coordinates are there in the list, 
//    this method draws the image of the abstracted deer. It simply calls the drawDeer() which is defined above.    
    
    private static final double MAX_SIGNIFICANCE = 1000000000;
    
    SimpleGUI sg = new SimpleGUI(800, 750);
    static Scanner readFile;
    Point newPoint;

    protected Node newNode;
    protected Node start;
    private Node current;

    private int tempX;
    private int tempY;
    private int noOfNodes;
    private double significanceValue;

    protected void catchException() {
        try {
            readFile = new Scanner(new File("shapelist.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    protected void readCoordinatesFile() {
        while (readFile.hasNextInt()) {
            tempX = readFile.nextInt();
            tempY = readFile.nextInt();
            newPoint = new Point(tempX + 50, 700 - tempY);
            createNodes(newPoint);
        }
    }

    protected void createNodes(Point newPoint) {
        if (start == null) {
            start = current = newNode = new Node(newPoint, null, null);
            noOfNodes++;
            readCoordinatesFile();
        } else {
            newNode = new Node(newPoint, null, null);
            current.setNext(newNode);
            newNode.setPrevious(current);
            current = newNode;
            noOfNodes++;
            readCoordinatesFile();
        }
    }

    protected void drawDeer(int xStart, int yStart, int xEnd, int yEnd) {
        sg.drawLine(xStart, yStart, xEnd, yEnd);
    }

    private double eucledianFormulae(Point firstPoint, Point secondPoint){
        double result = Math.sqrt(Math.pow((firstPoint.getY() - secondPoint.getY()), 2) + Math.pow((firstPoint.getX() - secondPoint.getX()), 2));
        return result;
    }
    
    protected void calcVisualSignificance() {
        double lengthFirstSecond;
        double lengthSecondThird;
        double lengthThirdFirst;

        current = start.returnNext();

        start.returnPoint().setSignificance(MAX_SIGNIFICANCE);  // Sets the significance value of the first node to infinity so that it is not deleted
        if (current.returnNext() == null) {
            current.returnPoint().setSignificance(MAX_SIGNIFICANCE);
        }

        while (current.returnNext() != null) {
            Point currentPoint = current.returnPoint();
            Point previousPoint = current.returnPrevious().returnPoint();
            Point nextPoint = current.returnNext().returnPoint();
            
            lengthFirstSecond = eucledianFormulae(currentPoint, previousPoint);
            lengthSecondThird = eucledianFormulae(nextPoint, currentPoint);
            lengthThirdFirst = eucledianFormulae(nextPoint, previousPoint);

            significanceValue = lengthFirstSecond + lengthSecondThird - lengthThirdFirst;
            current.returnPoint().setSignificance(significanceValue);
            current = current.returnNext();
        }
        findLowestSignificance();
    }

    private void findLowestSignificance() {
        current = start.returnNext();

        double lowestSignificance = current.returnPoint().getSignificance();

        current = current.returnNext();

        while (current.returnNext() != null) {
            if (current.returnPoint().getSignificance() < lowestSignificance) {
                lowestSignificance = current.returnPoint().getSignificance();
            }
            current = current.returnNext();
        }
        deleteLowestSignificance(lowestSignificance);
    }

    private void deleteLowestSignificance(double leastSignificant) {
        current = start.returnNext();

        while (noOfNodes > 38 && current.returnNext() != null) {
            if (current.returnPoint().getSignificance() == leastSignificant) {
            	Node temp = current;
                if(noOfNodes % 25 == 0 ){  // This shows me an error 					
                    drawAbstractedDeer();
                    try {
						Thread.sleep(500);
						sg.eraseAllDrawables();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
                current = temp;
                current.returnPrevious().setNext(current.returnNext());
                current.returnNext().setPrevious(current.returnPrevious());
                noOfNodes--;
                calcVisualSignificance();
            } else {
                current = current.returnNext();
            }
        }
        drawAbstractedDeer();
    }

    private void drawAbstractedDeer() {
        current = start;

        while (current.returnNext() != null) {
            drawDeer(current.returnPoint().getX(), current.returnPoint().getY(), current.returnNext().returnPoint().getX(), current.returnNext().returnPoint().getY());
            current = current.returnNext();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       LinkedList deerLinkedList = new LinkedList();  // This is to call the non-static methods in this class
        deerLinkedList.catchException();
        deerLinkedList.readCoordinatesFile();
        deerLinkedList.calcVisualSignificance(); // TODO code application logic here
    }
}
