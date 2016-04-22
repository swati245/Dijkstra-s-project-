package binarysearchtreeproject;

import simplegui.SimpleGUI;
import java.awt.Color;
import java.util.LinkedList;

/* Some of the methods defined in this class are :
 * 1. addValueToTree() - Creates a BinarySearchTree by inserting values after reading the values from the 
 *    LinkedList created in the main from createRandomPermutation().
 * 2. drawNodes() - Draws nodes i.e. boxes on SimpleGUI where the user can think about the value to be.
 * 3. drawTree() - This method simply calls the drawNodes() and drawLinks().
 * 4. drawLinks() - This method is simply used to connect one node to another 
 *    i.e. to connect the boxes together to show a tree structure on SimpleGUI.
 * 5. checkUserAnswer() - Checks if the user selected the corret node or box on SimpleGUI.
 * 6. findNode() - This is a recursive function which finds the node in the tree which has a 
 *    randomly selected value. The node is found using the process of BinarySearch.
 *
 *
 *
*/
public class BinarySearchTree {
    
    Node root;
    Node current ;
    SimpleGUI gui = BinarySearchTreeProject.sg;

    public void addValueToTree(LinkedList<Integer> randomNumberList) {
        boolean inserted = false;
        
        for (int i = 0; i < 10; i++) {
            
        int posX = gui.getWidth()/2 ; 
        int posY = 50;
        int offsetX = 300;
        int offsetY = 60;
        
            Node newNode = new Node(randomNumberList.get(i), posX, posY);
            if (root == null) {
                root = newNode;
            } else {
                current = root;
                inserted = false;
                while (!inserted) {
                    if (randomNumberList.get(i) < current.value) {
                        posX  -= offsetX;
                        posY += offsetY;
                        offsetX /= 2;
                        if (current.leftChild == null) {
                            current.leftChild = new Node(randomNumberList.get(i), posX, posY);
                            inserted = true;
                        } else {
                            current = current.leftChild;
                        }
                    } else {
                        posX += offsetX;
                        posY += offsetY;
                        offsetX /=2;
                        if (current.rightChild == null) {
                            current.rightChild = new Node(randomNumberList.get(i), posX, posY);
                            inserted = true;
                        } else {
                            current = current.rightChild;
                        }
                    }
                }
            }
        }
        drawTree(root);
    }
    
    protected void drawNodes(Node node){
        if(node== null)
            return;
        else{
            gui.drawFilledBox(node.posX, node.posY, node.width, node.height, Color.red, 1, null);
            drawNodes(node.leftChild);
            drawNodes(node.rightChild);
        }
    }
    
    protected void drawTree(Node startNode){
        drawLinks(startNode);
        drawNodes(startNode);
    }
    
    protected void drawLinks(Node root){        
        if(root == null)
        	return;
        Node current = root;
        Node left = root.leftChild;
        Node right = root.rightChild;
        if(left != null) {
        	gui.drawLine(root.posX+20, root.posY, left.posX, left.posY);
        	drawLinks(left);
        }
        if(right != null) {
            gui.drawLine(root.posX+10, root.posY, right.posX, right.posY);
            drawLinks(right);
        }

    }
    
    protected boolean checkUserAnswer(int[] click, int randomNumber){
        Node solutionNode = findNode(root, randomNumber);
        
        if((click[0] >= solutionNode.posX && click[0] < solutionNode.posX+solutionNode.width) 
                && (click[1] >= solutionNode.posY && click[1] < solutionNode.posY+solutionNode.height)){
            gui.drawText(""+solutionNode.value, solutionNode.posX+15, solutionNode.posY+15);
            return true;
        }
        else{
            dispAnswers();
            root = null;  // So that a new tree with only ten nodes are created
            return false;
        }
    }
    
     protected Node findNode(Node currentNode, final int randNumber) {
         if (currentNode == null || currentNode.value == randNumber)
            return currentNode;
        // Traverse down the tree.
        if (randNumber < currentNode.value) {
            return findNode(currentNode.leftChild, randNumber);
        } else {
            return findNode(currentNode.rightChild, randNumber);
        }
     }
     
     protected void dispAnswers(){
         Node current = root;
         while(current.leftChild != null){
             gui.drawText(""+current.value, current.posX+15, current.posY+15);
             current = current.leftChild;
         }
         if(current == null)
             current = root;
         while(current.rightChild != null){
             gui.drawText(""+current.value, current.posX+15, current.posY+15);
             current = current.rightChild;
         }
     }
}
