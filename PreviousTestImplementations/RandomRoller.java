import java.util.Random;

public class RandomRoller extends Rollin {
  
  Random R = new Random();

  public RandomRoller() {
  }

  public int handleRoll(int roll, int[] dice) {
      // Do nothing if we have a complete set
    if (Rollin.isComplete(dice)) {
          return -1;
    }
      // Replace a random die
    return R.nextInt(6);
  }
}
