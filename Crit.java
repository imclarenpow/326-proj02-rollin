public class Crit extends Rollin {

    public Crit() {

    }

    public int handleRoll(int roll, int[] dice) {
        return 0;
    }

    public static int[] oneSet(int[] dice) {
        for (int[][] si : setIndices) {
            if (isSet(si[0], dice)) {
                return si[0];
            } else if (isSet(si[1], dice)) {
                return si[1];
            }
        }
        return new int[] { -1, -1, -1 };
    }
}
