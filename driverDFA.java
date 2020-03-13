/** CMPT_440L_111
 * Homework 1 -- Implement a DFA
 * Filename: driverDFA.java
 * Student Name: Eric Stenton
 * Due Date: March 11, 2020
 * Version 1.0
 *
 * This file contains the main function for implementing a DFA to solve the man,
 * wolf, goat, and cabbage riddle. It reads a single string of input from
 * the command line and processes it using the ManWolf class to determine if
 * string leads to an acceptance state or not.
 */


/**
 * driverDFA
 *
 * This class implements the ManWolf object to determine if a given string of
 * input is part of the defined DFA's language.
 */
public class driverDFA {

    /**
     * main
     * parameters:
     *      args -- the array of command line argument values
     * return value: nothing
     *
     * This function reads input from the command line and outputs whether it
     * is an accepted language in the ManWolf DFA.
     */
    public static void main(String[] args) {

        // Get DFA
        ManWolf dfa = new ManWolf();

        // Test input with DFA
        dfa.process(args[0]);

        // Print if input leads to an accepted state or not
        if ( dfa.accepted() ) {
            System.out.println("That is a solution.");
        } else {
            System.out.println("That is not a solution.");
        }


    }
}