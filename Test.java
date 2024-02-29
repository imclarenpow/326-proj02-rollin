import java.util.Random;

public class Test {

    static Random R = new Random();
    
    public static void main(String[] args) {
        Rollin lazy = new LazyRoller();
        Rollin rand = new RandomRoller();
        int[] d = new int[6];
        for(int i = 0; i < d.length; i++) {
            d[i] = R.nextInt(6) + 1;
        }

        System.out.println("Initial dice: " + java.util.Arrays.toString(d));
        int roll = R.nextInt(6) + 1;
        System.out.println("Roll: " + roll);
        System.out.println("LazyRoller changes: " + lazy.handleRoll(roll, d));
        System.out.println("RandomRoller changes: " + rand.handleRoll(roll, d));

        System.out.println("Running random until complete...");
        while (!Rollin.isComplete(d)) {
            roll = R.nextInt(6) + 1;
            System.out.println("Roll: " + roll);
            int toChange = rand.handleRoll(roll, d);
            System.out.println("RandomRoller changes: " + toChange);
            d[toChange] = roll;
            System.out.println("Dice: " + java.util.Arrays.toString(d));
        }
    }
  
}
