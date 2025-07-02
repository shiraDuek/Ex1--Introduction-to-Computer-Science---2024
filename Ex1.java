import java.util.Arrays;
/**
 * Introduction to Computer Science, Ariel University, Ex1 (manual
 Example + a Template for your solution)
 * See: https://docs.google.com/document/d/1C1BZmi_Qv6oRrL4T5oN9N2bBMFOHPzSI/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true
 *
 * name: Shira Duek
 * myID - 325175115 (Make sure you write your ID at the top of the file - I wrote both as a static function and here)
 *
 * Ex1 Bulls & Cows - Automatic solution.
 *
 * **** Add a general readme text here ****
 * Add your explanation here:
 * This program is used as an automatic solution for the game of balls and cows, in interaction with a game server.
 * The program tries to guess the secret code using a systematic strategy.
 *
 * **** General Solution (algorithm) ****
 * Add your explanation here:
 The main idea is to try all possible combinations of numbers for a given code length.
 After each attempt, a filter enters.
 He checks the bulls and cows obtained from the trial and compares them to the number of bulls and cows in the relevant numbers in the array.
 If the bulls and cows do not match, the number in the array is marked as irrelevant, this process is repeated until the correct code is found.

 *
 * **** Results ****
 * Make sure to state the average required guesses
 * for 2,3,4,5,6 digits code:
 *
 * Average required guesses 2: 5.5
 * Average required guesses 3: 5.73
 * Average required guesses 4: 6.08
 * Average required guesses 5: 6.66
 * Average required guesses 6: 6.89
 *  average all numOfDigits : 6.172000000000001
 *
 */



public class Ex1 {
    public static final String Title = "Ex1 demo: manual Bulls & Cows game";

    public static double COUNT = 0;
    public static int numOfDigit = 4;
    public static  long myID = 325175115;

    public static void main(String[] args) {
        BP_Server game = new BP_Server();   // Starting the "game-server"
       long myID = 325175115;             // Your ID should be written here
       //int numOfDigits = 4;                // Number of digits [2,6]
        game.startGame(myID, numOfDigit);  // Starting a game
        System.out.println(Title + " with code of " + numOfDigit + " digits");
        autoEx1Game(game);
    }

    /**
     * Simulates an automated game playing strategy for a given BP_Server game.
     *
     * @param game The BP_Server instance representing the game to be played.
     * @return The number of attempts (guesses) it took to win the game.
     */
    public static double autoEx1Game(BP_Server game) {
        COUNT = 0;         // Initialize the attempt count to 0.
        int ind = 0;      // Index of the guess
        int numOfDigits = game.getNumOfDigits();       // Get the number of digits in the game.
        int[][] allOptions = AllOptions(numOfDigits);  // Get all possible combinations of digits.
        boolean[] filtered = new boolean[allOptions.length];    // Create an array to track which options have been filtered.
        while (game.isRunning()) {
            int[] guess = getRandom(allOptions, filtered);    // Generate a random guess from the available options.
            if (guess.length == 0) {        // Check if the guess is empty (in case there are no more valid options).

            }
            int[] result = game.play(guess);  // Play the generated guess and get the result.
            System.out.println(ind + ") B: " + result[0] + ",  C: " + result[1]); // Prints the Bulls [0], and the Cows [1]
            ind += 1;               // Increasing the index
            COUNT++;                // Increase the overall attempt count.


            if (result[0] == numOfDigits) {   // Check if the game is won (all Bulls).
                break;  // Exit the loop if the game is won.
            }
            Filter(allOptions, filtered, guess, result[0], result[1]);   // Filter out options based on the result of the current guess.
        }
        System.out.println(game.getStatus()); // Print the final status of the game.
        return COUNT;  // Return the total number of attempts (guesses) it took to win the game.

}

    /**
     * Simple parsing function that gets an int and returns an array of digits
     *
     * @param a    - a natural number (as a guess)
     * @param size - number of digits (to handle the 00 case).
     * @return an array of digits
     */
    public static int[] toArray(int a, int size) {
        int[] c = new int[size];    // Create an array to store the digits.
        for (int i = 0; i < c.length; i++) {    // Iterate through the array in reverse order.
            c[c.length-i-1] = a % 10;    // Extract the last digit of the integer and store it in the array.
            a = a / 10;  // Remove the last digit from the integer.
        }
        return c;  // Return the array of digits in reverse order.
    }

    /**
     * Generates all possible combinations of digits for a given number of digits.
     * For example, if numOfDigits1 is 3, it will generate all combinations from 000 to 999.
     *
     * @param numOfDigits1 The number of digits for each combination.
     * @return A 2D array containing all possible combinations.
     */
    public static int[][] AllOptions(int numOfDigits1) {
        double numOfOpt = Math.pow(10, numOfDigits1);  // Calculate the total number of possible combinations based on the given number of digits.
        int options[][] = new int[(int) numOfOpt][numOfDigits1];  // Create a 2D array to store all the combinations.
        for (int i = 0; i < numOfOpt; i++) {    // Iterate through all the combinations and populate the options array.
            options[i] = toArray(i,numOfDigits1);   // Convert the current combination to an array of digits using the toArray method.
        }
        return options;   // Return the 2D array containing all possible combinations.
    }

    /**
     * Retrieves a random combination of digits from the provided options array, considering a filter
     * to exclude certain combinations.
     *
     * @param options   The 2D array containing all possible combinations of digits.
     * @param filtered  An array of booleans indicating whether each corresponding combination in options is filtered.
     * @return A random combination of digits that satisfies the filtering criteria.
     */
    public static int[] getRandom(int[][] options, boolean[] filtered) {
        int index = (int) (Math.random() * options.length);   // Generate a random index within the range of the options array.
        int[] option = options[index];    // Retrieve the combination at the random index.
        while (filtered[index]) {      // Continue generating a new random index until an unfiltered combination is found.
            index = (int)( Math.random() * options.length);
            option = options[index];
        }
        return option;    // Return the randomly selected, unfiltered combination.
    }

    /**
     * Calculates the number of bulls and cows in the given guess compared to the secret code.
     *
     * @param guess The array representing the player's guess.
     * @param code  The array representing the secret code to be guessed.
     * @return An array containing the count of bulls and cows in the guess.
     */
    public static int[] calculateBullsAndCows(int[] guess, int[] code) {
        int bulls = 0;
        int cows = 0;

        // Create a copy of the guess array
        int[] guessCopy = Arrays.copyOf(guess, guess.length);
        int[] codeCopy = Arrays.copyOf(code, code.length);
        // Count Bulls
        for (int i = 0; i < guess.length; i++) {
            // Check if the digit at the current position matches the code.
          if (guessCopy[i] == codeCopy[i] ) {
                    bulls++;
              // Mark the digits as used in the copy arrays to avoid double counting.
                    guessCopy[i] = -1;
                    codeCopy[i] = -1;
                }
            }
        // Count Cows
        for (int i = 0; i < guess.length; i++) {
            for (int j = 0; j < code.length; j++) {
                // Check for a non-matching position and matching digits.
                if ( i!=j && guessCopy[i] != -1 && codeCopy[j] != -1 && guessCopy[i] == codeCopy[j]  ){
                    cows++;
                    // Mark the digits as used in the copy arrays to avoid double counting.
                    guessCopy[i] = -1;
                    codeCopy[j] = -1;
                    break;             // Move to the next unmatched digit in the guess
                }
            }
        }

        return new int[]{bulls, cows};   // Return an array containing the count of bulls and cows.
    }

    /**
     * Filters the given options based on the provided criteria of bulls and cows obtained from a previous guess.
     *
     * @param options  The 2D array containing all possible combinations of digits.
     * @param filtered An array of booleans indicating whether each corresponding combination in options is filtered.
     * @param guess    The array representing the player's guess.
     * @param bulls    The number of bulls obtained in the previous comparison.
     * @param cows     The number of cows obtained in the previous comparison.
     * @return The filtered options array.
     */
    public static int[][] Filter(int[][] options , boolean[] filtered, int[] guess, int bulls, int cows) {
        // Iterate through all options and update the filter based on the comparison with the previous guess.
        for (int i = 0; i < options .length; i++) {
            // Check if the combination is not already filtered.
            if(!filtered[i]){
                int[] result = calculateBullsAndCows(guess,options [i]);    // Calculate bulls and cows for the current combination.
                // If the calculated bulls or cows do not match the expected values, mark the combination as filtered.
                if (result[0] != bulls || result[1] != cows) {
                    filtered[i] = true;
                }
            }
        }
        return options ;  // Return the filtered options array.
    }


}






