import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

//  name: Shira Duek
//  myID - 325175115

class Ex1Test {

    @Test
    void testAllOptions() {
        // Test for generating all options with 2 digits
        int numOfDigits = 4;
        int[][] result = Ex1.AllOptions(numOfDigits);

        // Check if the number of generated options is correct
        assertEquals((int) Math.pow(10, numOfDigits), result.length);

        // Check if each option has the correct number of digits
        for (int[] option : result) {
            assertEquals(numOfDigits, option.length);
        }

        // Check if there are no duplicate options
        for (int i = 0; i < result.length; i++) {
            for (int j = i + 1; j < result.length; j++) {
                assertFalse(Arrays.equals(result[i], result[j]));
            }
        }
    }

    @Test
    public void toArrayTest() {
        int a = 56, size = 3;
        int[] expectedResult = new int[]{0, 5, 6};
        int[] actualResult = Ex1.toArray(a, size);

        if (Arrays.equals(expectedResult, actualResult)) {
            System.err.println("toArrayTest: good");
        } else {
            System.err.println("toArrayTest: err");
        }
    }

    @Test
    public void getRandomTest() {
        int numOfDigits = 3;
        int[][] Allopsion = {{5, 6, 7}, {2, 4, 9}, {0, 4, 1}};

        boolean[] filtered = new boolean[Allopsion.length];

        Arrays.fill(filtered, false);

        int[] randomOption = Ex1.getRandom(Allopsion, filtered);

        // Check if the randomly selected option is one of the specified options
        assertTrue(Arrays.equals(randomOption, new int[]{5, 6, 7}) ||
                        Arrays.equals(randomOption, new int[]{2, 4, 9}) ||
                        Arrays.equals(randomOption, new int[]{0, 4, 1}),
                "Random option does not match any specified option");

        System.out.println("Random Option: " + Arrays.toString(randomOption));
    }

    @Test
    public void testFilter() {
        int[][] options = {{7, 4, 9}, {0, 0, 0}, {7, 8, 9}};
        boolean[] filtered = {false, true, true};
        int[] guess = {4, 5, 6};
        int bulls = 0;
        int cows = 1;

        int[][] result = Ex1.Filter(options, filtered, guess, bulls, cows);


        assertFalse(filtered[0], "Option 1 should not be filtered. Result: " + Arrays.toString(result[0]));
        assertTrue(filtered[1], "Option 2 should be filtered. Result: " + Arrays.toString(result[1]));
        assertTrue(filtered[2], "Option 3 should be filtered. Result: " + Arrays.toString(result[2]));
        // Update the guess and bulls/cows values
        guess = new int[]{4, 5, 6};
        bulls = 0;
        cows = 1;

        result = Ex1.Filter(options, filtered, guess, bulls, cows);

        assertFalse(filtered[0], "Option 1 should not be filtered. Result: " + Arrays.toString(result[0]));
        assertTrue(filtered[1], "Option 2 should be filtered. Result: " + Arrays.toString(result[1]));
        assertTrue(filtered[2], "Option 3 should be filtered. Result: " + Arrays.toString(result[2]));

        System.out.println("Final filtered array: " + Arrays.toString(filtered));

    }

    @Test
    public void testCalculateBullsAndCows() {
        int[] guess = {1, 2, 3};
        int[] code = {1, 2, 3};

        int[] result = Ex1.calculateBullsAndCows(guess, code);

        assertEquals(3, result[0], "Bulls should be 3");
        assertEquals(0, result[1], "Cows should be 0");

        // Test with a different guess and code
        guess = new int[]{4, 5, 6};
        code = new int[]{7, 4, 9};

        result = Ex1.calculateBullsAndCows(guess, code);

        assertEquals(0, result[0], "Bulls should be 1");
        assertEquals(1, result[1], "Cows should be 1");

        // Test with no bulls and no cows
        guess = new int[]{1, 2, 3};
        code = new int[]{4, 5, 6};

        result = Ex1.calculateBullsAndCows(guess, code);

        assertEquals(0, result[0], "Bulls should be 0");
        assertEquals(0, result[1], "Cows should be 0");
    }

    @Test
    public void testAutoEx1GameAllNumOfDigit() {
        int numGames = 100;
        double sum2D = 0.0;
        double sum3D = 0.0;
        double sum4D = 0.0;
        double sum5D = 0.0;
        double sum6D = 0.0;

        for (int i = 0; i < 100; i++) {
            BP_Server game = new BP_Server();
            game.startGame(Ex1.myID, 2);
            Ex1.autoEx1Game(game);
            sum2D += Ex1.COUNT;
        }

        for (int i = 0; i < 100; i++) {
            BP_Server game = new BP_Server();
            game.startGame(Ex1.myID, 3);
            Ex1.autoEx1Game(game);
            sum3D += Ex1.COUNT;
        }

        for (int i = 0; i < 100; i++) {
            BP_Server game = new BP_Server();
            game.startGame(Ex1.myID, 4);
            Ex1.autoEx1Game(game);
            sum4D += Ex1.COUNT;
        }

        for (int i = 0; i < 100; i++) {
            BP_Server game = new BP_Server();
            game.startGame(Ex1.myID, 5);
            Ex1.autoEx1Game(game);
            sum5D += Ex1.COUNT;
        }

        for (int i = 0; i < 100; i++) {
            BP_Server game = new BP_Server();
            game.startGame(Ex1.myID, 6);
            Ex1.autoEx1Game(game);
            sum6D += Ex1.COUNT;
        }

        double averageD2 = sum2D / numGames;
        double averageD3 = sum3D / numGames;
        double averageD4 = sum4D / numGames;
        double averageD5 = sum5D / numGames;
        double averageD6 = sum6D / numGames;

        System.out.println("average numOfDigits 2: " + averageD2);
        System.out.println("average numOfDigits 3: " + averageD3);
        System.out.println("average numOfDigits 4: " + averageD4);
        System.out.println("average numOfDigits 5: " + averageD5);
        System.out.println("average numOfDigits 6: " + averageD6);

        double averageAll = (averageD2 + averageD3 + averageD4 + averageD5 + averageD6) / 5;
        System.out.println("average all numOfDigits : " + averageAll);
    }
    @Test
    public void testAutoEx1NumOfDigit2() {
        int numGames = 100;
        int numDigit = Ex1.numOfDigit = 2;
        double sum = 0.0;

        for (int i = 0; i < 100; i++) {
            BP_Server game = new BP_Server();
            game.startGame(Ex1.myID, numDigit);
            Ex1.autoEx1Game(game);
            sum += Ex1.COUNT;
        }

        double average = sum / numGames;


        System.out.println("average numOfDigits 2: " + average);

    }
    @Test
    public void testAutoEx1NumOfDigit3() {
        int numGames = 100;
        int numDigit = Ex1.numOfDigit = 3;
        double sum = 0.0;

        for (int i = 0; i < 100; i++) {
            BP_Server game = new BP_Server();
            game.startGame(Ex1.myID, numDigit);
            Ex1.autoEx1Game(game);
            sum += Ex1.COUNT;
        }

        double average = sum / numGames;


        System.out.println("average numOfDigits 3: " + average);

    }
    @Test
    public void testAutoEx1NumOfDigit4() {
        int numGames = 100;
        int numDigit = Ex1.numOfDigit = 4;
        double sum = 0.0;

        for (int i = 0; i < 100; i++) {
            BP_Server game = new BP_Server();
            game.startGame(Ex1.myID, numDigit);
            Ex1.autoEx1Game(game);
            sum += Ex1.COUNT;
        }

        double average = sum / numGames;


        System.out.println("average numOfDigits 4: " + average);

    }
    @Test
    public void testAutoEx1NumOfDigit5() {
        int numGames = 100;
        int numDigit = Ex1.numOfDigit = 5;
        double sum = 0.0;

        for (int i = 0; i < 100; i++) {
            BP_Server game = new BP_Server();
            game.startGame(Ex1.myID, numDigit);
            Ex1.autoEx1Game(game);
            sum += Ex1.COUNT;
        }

        double average = sum / numGames;


        System.out.println("average numOfDigits 5: " + average);

    }
    @Test
    public void testAutoEx1NumOfDigit6() {
        int numGames = 100;
        int numDigit = Ex1.numOfDigit = 6;
        double sum = 0.0;

        for (int i = 0; i < 100; i++) {
            BP_Server game = new BP_Server();
            game.startGame(Ex1.myID, numDigit);
            Ex1.autoEx1Game(game);
            sum += Ex1.COUNT;
        }

        double average = sum / numGames;


        System.out.println("average numOfDigits 6: " + average);

    }
}



