/** CMPT_440L_111
 * Homework 1 -- Implement a DFA
 * Filename: ManWolf.java
 * Student Name: Eric Stenton
 * Due Date: March 11, 2020
 * Version 1.0
 *
 * This file contains functions pertaining to the creation of a DFA to analyze
 * the man, wolf, goat, and cabbage riddle's language in terms of its input that
 * leads to an accepting state.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * ManWolf
 *
 * This class defines a ManWolf object which provides the ability to analyze if
 * a string input is part of the defined DFA's language.
 */
class ManWolf {

    // The states available in this DFA
    private static final int q0 = 0; // Start state
    private static final int q1 = 1;
    private static final int q2 = 2;
    private static final int q3 = 3;
    private static final int q4 = 4;
    private static final int q5 = 5;
    private static final int q6 = 6;
    private static final int q7 = 7;
    private static final int q8 = 8;
    private static final int q9 = 9; // Acceptance state
    private static final int q10 = 10; // Error state

    // The current state
    private static int state;

    // The transition table (read from CSV in same directory)
    private static int[][] delta = new int[11][4];


    /** ManWolf
     *  parameters: nothing
     *  return value: nothing
     *
     *  This function serves as the constructor for the ManWolf object. It
     *  requires no input variables; it initializes the DFA's transition table
     *  by reading a CSV file called 'state-transition-table.csv' in the
     *  current directory that describes all the transitions. It also
     *  initializes the state to the starting state 'q0'.
     */
    ManWolf() {

        try {
            // Read transition table
            BufferedReader csvReader = new BufferedReader(
                    new FileReader("./state-transition-table.csv"));

            String row;
            int rowNum = 0;
            csvReader.readLine(); // Skip header
            while ((row = csvReader.readLine()) != null) { // Loop states
                String[] data = row.split(",");

                delta[rowNum] = new int[data.length];
                for (int i = 0; i < data.length - 1; i++) { // Loop inputs
                    delta[rowNum][i] = Integer.parseInt(data[i + 1]);
                }

                rowNum++;
            }
            csvReader.close();
        } catch (IOException e) {
            System.out.println( e.getMessage() );
        }

        // Initialize to start state
        state = q0;

    }


    /** process
     *  parameters:
     *      input -- A string that is to be checked if it is a member of the
     *               DFA's regular language.
     *  return value: nothing
     *
     *  This function loops through the characters of the input string,
     *  translates the characters to integers corresponding to the inputs of the
     *  transition table, and replaces the current state with the next one
     *  defined by the output of the transition table.
     */
    static void process(String input) {

        // Explore state transitions per input
        for (int i = 0; i < input.length(); i++) {

            // Get input # --> g, w, c, n = 0, 1, 2, 3
            char c = input.charAt(i);
            int in = 4;
            if (c == 'g') {
                in = 0;
            } else if (c == 'w') {
                in = 1;
            } else if (c == 'c') {
                in = 2;
            } else if (c == 'n') {
                in = 3;
            }

            // Transition to new state
            try {
                state = delta[state][in];
            } catch (ArrayIndexOutOfBoundsException e) {
                state = q10;
            }

        }
    }

    /** reset
     *  parameters: nothing
     *  return value: nothing
     *
     *  This function simply changes the state back to the starting state value.
     */
    public void reset() {
        state = q0;
    }

    /** accepted
     *  parameters: nothing
     *  return value:
     *      boolean -- True if the state variable is equal to the accept state
     *                 'q9' and false if not.
     *
     *  This function simply checks if the state variable is equal to the
     *  accepting state 'q9'. If it is, it returns true and the given string
     *  input can be verified as a member of the DFA's regular language.
     */
    public boolean accepted() {
        return state == q9;
    }
}