package de.mkotzjan;

import java.util.Scanner;

/**
 * Program to output an input
 *
 * @author mkotzjan
 * @version 1.0
 */

public class InputExp {
 
	/**
     * Main
     *
     * @param args Kommandozeilenparameter
     */
   public static void main(String[] args) {
 
       System.out.print("Input something: ");
       String input;
       Scanner in = new Scanner(System.in);
 
       // Reads a single line from the console 
       // and stores into name variable
       input = in.nextLine();
       in.close();            
 
       // Prints name and age to the console
       System.out.println("Input: "+input.toUpperCase());
 
    }
}