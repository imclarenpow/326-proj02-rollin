import java.util.Random;
public class PowisTestMain {
    static Random R = new Random();
    // code snippets pinched from Test.java
    public static void main(String[] args){
        runDie();
    }
    public static void runDie(){
        Rollin powis = new PowisTestImplementation();
        int[] d = new int[6];
        for(int i = 0; i < d.length; i++){
            d[i] = R.nextInt(6) + 1;
        }
        int turns = -1;
        do{
            int roll = R.nextInt(6) + 1;
            System.out.println("Dice: " + java.util.Arrays.toString(d));
            System.out.println("Roll: " + roll);
            int toChange = powis.handleRoll(roll, d);
            System.out.println("PowisTest changes: " + toChange);
            d[toChange] = roll;
            turns++;
        } while (!Rollin.isComplete(d));
        System.out.println(turns + " turns taken");
    }
}
