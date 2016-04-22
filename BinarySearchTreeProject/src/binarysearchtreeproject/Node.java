/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package binarysearchtreeproject;

/**
 *  This is the node class. This class only has a constructor to initialize the nodes with values.
 * 
 * @author Swati
 */
public class Node {
    int value;
    Node leftChild, rightChild;
    int posX , posY;
    int offX = 320;
    int offY = 20;
    int width = 30;
    int height = 30;
    
    public Node(int valueNode, int positionX, int positionY){
        value = valueNode;
        posX = positionX;
        posY = positionY;
    }
}
