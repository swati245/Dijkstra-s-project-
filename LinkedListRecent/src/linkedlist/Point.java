/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

/**
 *
 * @author tuf18082
 */
public class Point {
    // This Point class has the basic fields like X-Coordinate of the deer, Y-Coordinate of the deer and the significance value of a coordinate
    private Point newPoint;
    protected int xCoordinate;
    protected int yCoordinate;

    protected double significance;

    public Point() {   // This is just a default constructor
        this.xCoordinate = -1;
        this.yCoordinate = -1;
        this.significance = -1;
    }

    public Point(int x, int y) {  // This constructor initializes the X and Y coordinates of the deer
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    protected int getX() {  // Returns the X-Coordinate
        return xCoordinate;
    }

    protected int getY() {  // Returns the Y-Coordinate
        return yCoordinate;
    }

    protected void setSignificance(double newSignificance) {  // This method is used to initialize the significance value of the first and last coordinate
        significance = newSignificance;                      // because they are most significant
    }

    protected double getSignificance() {  // Returns the significance value of that point.
        return significance;
    }

}
