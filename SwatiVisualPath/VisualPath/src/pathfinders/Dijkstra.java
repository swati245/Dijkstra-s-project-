/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinders;

import graph.Graph;
import java.util.PriorityQueue;

public class Dijkstra implements PathFinder {

    private final int NO_PARENT_NODE = -1;
    private final int initial_Node_Assignment = Integer.MIN_VALUE;
    private final int REACHED_DESTINATION = 1;

    // returns path as a sequence of node-indices.
    public int[] findPath(Graph g) {
        int[] path = findArray(g);

        return (path);
    }

    protected int[] findArray(Graph g) {
        int noNodes = g.getNumberOfNodes();

        int[] previous = new int[noNodes + 1];
        int[] distance = new int[noNodes + 1];

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

        for (int i = 0; i < noNodes; i++) {
            previous[i] = NO_PARENT_NODE;
            distance[i] = Integer.MAX_VALUE;
        }

        distance[0] = 0;
        previous[0] = initial_Node_Assignment;
        queue.add(0);  // Adding the start node to the queue
        int temp = 0;

        while (!queue.isEmpty() && temp != REACHED_DESTINATION) {  // Condition test to exit from the loop 
            int tempDistance = queue.poll();
            temp = findNodeForDistance(tempDistance, distance);
            if (temp == NO_PARENT_NODE) {
                continue;
            }

            int[][] adjNodes = g.getNeighborsOfNode(temp);

            for (int i = 0; i < adjNodes.length; i++) {  // Finds the shortest distance among all child nodes i.e. neighboring nodes
                int newDistance = distance[temp] + adjNodes[i][1];
                int node = adjNodes[i][0];
                if (previous[temp] != NO_PARENT_NODE && newDistance < distance[node]) {
                    distance[node] = newDistance;
                    previous[node] = temp;
                    queue.add(newDistance); // Adding elements to queue
                }
            }
        }

        int pathtemp = REACHED_DESTINATION;
        int count = 0;
        while (pathtemp != initial_Node_Assignment) {
            if (pathtemp == NO_PARENT_NODE) {
                System.out.println("No neigboring nodes to visit");
                break;
            }
            pathtemp = previous[pathtemp];
            count++;
        }

        int[] path = new int[count];
        pathtemp = REACHED_DESTINATION;
        while (pathtemp != initial_Node_Assignment && pathtemp != NO_PARENT_NODE) {
            count--;
            path[count] = pathtemp;
            pathtemp = previous[pathtemp];
        }
        return path;
    }

    int findNodeForDistance(int distance, int[] nodes) {  // Finds the index number of a particular node
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == distance) {
                return i;
            }
            if (distance == 0) {
                return 0;
            }
        }
        return -1;
    }

}
