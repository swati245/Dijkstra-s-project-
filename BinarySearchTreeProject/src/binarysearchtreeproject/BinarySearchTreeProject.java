/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtreeproject;


import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import simplegui.SimpleGUI;

/**
 * This project aims at creating a binary search tree. A random tree with a value between 1-10 is
 * displayed on the SimpleGUI. The user then has to guess where the value could probably be. 
 * If the user guessed it correctly, the value at that position is displayed and the game is continued.
 * If the user selects the wrong position for a particular value, a new game is started.
 * Some of the methods defined here are :
 * 1. createRandomPermutation() - Generates a linked list of random values between 1-10. 
 *    These values are then inserted into the nodes of the trees.
 * 2. generateUserRandomNumbers() - Generates a linked list of random values between 1-10.
 *    This linked list is used to display new values on SimpleGUI if the user guessed the correct position(node)
 *    for a particular value.
 * 3. getUserAnswerArea() - Displays a random value reading from the LinkedList produced from the method 
 *    generateUserRandomNumbers(). This also takes the input the point where the user clicked
 *    as a possible solution.
 * 
 * 
 *
 * @author Swati
 */
public class BinarySearchTreeProject {

    static SimpleGUI sg = new SimpleGUI(1300, 700);

    static BinarySearchTree bst = new BinarySearchTree();

    protected void createRandomPermutation() throws InterruptedException {
        LinkedList<Integer> randomNumberList = new LinkedList<Integer>();

        for (int i = 1; i <= 10; i++) {
            randomNumberList.add(i);
        }

        Collections.shuffle(randomNumberList);

        bst.addValueToTree(randomNumberList);
        generateUserRandomNumbers();
    }

    private void generateUserRandomNumbers() throws InterruptedException {
        LinkedList<Integer> questions = new LinkedList<Integer>();

        for (int i = 1; i <= 10; i++) {
            questions.add(i);
        }

        Collections.shuffle(questions);
        Iterator<Integer> iter = questions.listIterator();
        while (iter.hasNext()) {
            getUserAnswerArea((int) iter.next());
        }
    }

    protected void getUserAnswerArea(int randomNumber) throws InterruptedException {
        sg.print("Find the correct place for the number " + randomNumber);
        int[] userClick = sg.waitForMouseClick();

        checkUserAnswer(userClick, randomNumber);
    }

    protected void checkUserAnswer(int[] input, int randomNumber) throws InterruptedException {
        boolean userResult = bst.checkUserAnswer(input, randomNumber);

        if (userResult) {  // If user found the correct node
            generateUserRandomNumbers();
        } else {    // Else start a new game
            sg.eraseAllDrawables();
            Thread.sleep(1500); // Time gap between the end of previous game and the start of next game
            createRandomPermutation();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        BinarySearchTreeProject bt = new BinarySearchTreeProject();
        bt.createRandomPermutation();
    }
}
