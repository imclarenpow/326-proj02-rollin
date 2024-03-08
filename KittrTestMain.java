import java.util.Random;

public class KittrTestMain {

    static Random R = new Random();

    // code snippets pinched from Test.java
    public static void main(String[] args) {
        runDie();
    }

    public static void runDie() {
        Rollin kittr = new KittrTestImplementation();
        int[] d = new int[6];
        for (int i = 0; i < d.length; i++) {
            d[i] = R.nextInt(6) + 1;
        }
        System.out.println("Initial dice: " + java.util.Arrays.toString(d));
        int roll = R.nextInt(6) + 1;
        System.out.println("Roll: " + roll);
        int toChange = kittr.handleRoll(roll, d);
        if (toChange != -1) {
            System.out.println("KittrTest changes: " + toChange);
        } else {
            System.out.println("KittrTest does not change");
        }
        System.out.println("Running random until complete...");
        int turns = 1;
        while (!Rollin.isComplete(d)) {
            System.out.println("Dice: " + java.util.Arrays.toString(d));
            roll = R.nextInt(6) + 1;
            System.out.println("Roll: " + roll);
            toChange = kittr.handleRoll(roll, d);
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
}
