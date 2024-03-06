import java.util.Random;
public class PowisTestMain {
    static Random R = new Random();
    // code snippets pinched from Test.java
    public static void main(String[] args){
        Rollin powis = new PowisTestImplementation();
        int[] d = new int[6];
        for(int i = 0; i < d.length; i++){
            d[i] = R.nextInt(6) + 1;
        }
        System.out.println("Initial dice: " + java.util.Arrays.toString(d));
        int roll = R.nextInt(6) + 1;
        System.out.println("Roll: " + roll);
        System.out.println("PowisTest changes: " + powis.handleRoll(roll, d));
        System.out.println("Running random until complete...");
        int turns = 0;
        while (!Rollin.isComplete(d)) {
            roll = R.nextInt(6) + 1;
            System.out.println("Roll: " + roll);
            int toChange = powis.handleRoll(roll, d);
            System.out.println("PowisTest changes: " + toChange);
            d[toChange] = roll;
            System.out.println("Dice: " + java.util.Arrays.toString(d));
            turns++;
        }
        System.out.println(turns + " turns taken");
    }
}
