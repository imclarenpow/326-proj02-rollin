import java.util.Random;
/**
 * A class to test Rollin' implementation and find out how many turns on average it takes.
 *
 * @author Tristan Kitto, Isaac Powell, Cayden Scott and Rochelle Cole
 */
public class CritTestMain {

    static Random R = new Random();

    // Code snippets pinched from Test.java
    public static void main(String[] args) {
        averageTurnsPerGame();
        //runDie();
    }
    /**
     * This is a method that displays the initial roll, what the roll is,
     * how many changes were made, Runs random until complete.
     */
    public static void runDie() {
        Rollin Crit = new CritRoller();
        int[] d = new int[6];
        for (int i = 0; i < d.length; i++) {
            d[i] = R.nextInt(6) + 1;
        }
        System.out.println("Initial dice: " + java.util.Arrays.toString(d));
        int roll = R.nextInt(6) + 1;
        System.out.println("Roll: " + roll);
        int toChange = Crit.handleRoll(roll, d);
        if (toChange != -1) {
            System.out.println("KittrTest changes: " + toChange);
            d[toChange] = roll;
        } else {
            System.out.println("KittrTest does not change");
        }
        System.out.println("Running random until complete...");
        int turns = 1;
        while (!Rollin.isComplete(d)) {
            System.out.println("Dice: " + java.util.Arrays.toString(d));
            roll = R.nextInt(6) + 1;
            System.out.println("Roll: " + roll);
            toChange = Crit.handleRoll(roll, d);
            if (toChange == -1) {
                System.out.println("KittrTest does not change");
                turns++;
                continue;
            }
            System.out.println("KittrTest changes: " + toChange);
            d[toChange] = roll;
            System.out.println("Dice: " + java.util.Arrays.toString(d));
            turns++;
        }
        System.out.println(turns + " turns taken");
    }
    /**
     * This is a method that records and calculates the average number of turns per game
     */
    public static void averageTurnsPerGame() {
        Rollin Crit = new CritRoller();
        int[] d = new int[6];
        int totalTurns = 0;
        for (int j = 0; j < 10000; j++) {
            for (int i = 0; i < d.length; i++) {
                d[i] = R.nextInt(6) + 1;
            }
            int roll = R.nextInt(6) + 1;
            int toChange = Crit.handleRoll(roll, d);
            if (toChange != -1) {
                d[toChange] = roll;
            }
            int turns = 1;
            while (!Rollin.isComplete(d)) {
                roll = R.nextInt(6) + 1;
                toChange = Crit.handleRoll(roll, d);
                if (toChange == -1) {
                    turns++;
                    continue;
                }
                d[toChange] = roll;
                turns++;
            }
            totalTurns += turns;
        }
        System.out.println("Average turns: " + ((float) totalTurns / 10000));
    }
}
