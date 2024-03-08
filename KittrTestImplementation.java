import java.lang.Math;

public class KittrTestImplementation extends Rollin {
    public KittrTestImplementation() {
    }

    public int handleRoll(int roll, int[] dice) {
        // if dice are already complete, we are done
        if (Rollin.isComplete(dice)) {
            return -1;
        }

        // if we can complete the dice with the roll, do it
        int[] diceCopy = new int[dice.length];
        System.arraycopy(dice, 0, diceCopy, 0, dice.length);
        for (int i = 0; i < diceCopy.length; i++) {
            diceCopy[i] = roll;
            if (Rollin.isComplete(diceCopy)) {
                dice[i] = roll;
                return i;
            }
            diceCopy[i] = dice[i];
        }

        // check if there is one set already present
        int[] set = oneSet(dice);
        int[] remainingDice;
        if (set[0] == -1) {
            // there isn't one set:

            remainingDice = diceCopy;
            for (int i = 0; i < remainingDice.length; i++) {
                int temp = remainingDice[i];
                remainingDice[i] = roll;
                if (oneSet(remainingDice)[0] != -1) {
                    dice[i] = roll;
                    return i;
                }
                remainingDice[i] = temp;
            }

            // TODO: implement algorithm for when there isn't already one set
            // for now, just return -1
            return -1;
        } else {
            // there is one set already, take only the remaining 3 dice and use those
            remainingDice = new int[3];
            int counter = 0;
            for (int i = 0; i < dice.length; i++) {
                if (i != set[0] && i != set[1] && i != set[2]) {
                    remainingDice[counter] = dice[i];
                    counter++;
                }
            }

            int[] pairIndices = checkPair(remainingDice);
            if (pairIndices[0] != -1) {
                // there is a pair:

                // find the index in remainingDice of the non-pair value
                int nonPairIndex = 3 - pairIndices[0] - pairIndices[1];
                // if the roll value is closer to the pair than the non-pair is, change the
                // non-pair
                if (Math.abs(remainingDice[pairIndices[0]] - roll) < Math
                        .abs(remainingDice[pairIndices[0]] - remainingDice[nonPairIndex])) {
                    int index = getIndexOf(dice, remainingDice[nonPairIndex]);
                    dice[index] = roll;
                    return index;
                } else {
                    return -1;
                }
            } else {
                // there is not a pair:

                // check if a pair can be created:
                if (canCreatePair(remainingDice, roll) != -1) {
                    // a pair can be created
                    int indexOfPair = canCreatePair(remainingDice, roll);
                    int keepDiceIndex = -1;
                    for (int i = 0; i < remainingDice.length; i++) {
                        if (Math.abs(remainingDice[i] - roll) == 1) {
                            keepDiceIndex = i;
                        } else if (Math.abs(remainingDice[i] - roll) == 2) {
                            keepDiceIndex = i;
                        }
                    }
                    if (keepDiceIndex != -1) {
                        int index = 3 - indexOfPair - keepDiceIndex;
                        dice[index] = roll;
                        return index;
                    } else {
                        return -1;
                    }
                } else {
                    // a pair cannot be created
                    int keepDiceIndex = -1;
                    for (int i = 0; i < remainingDice.length; i++) {
                        if (Math.abs(remainingDice[i] - roll) == 1) {
                            keepDiceIndex = i;
                        } else if (Math.abs(remainingDice[i] - roll) == 2) {
                            keepDiceIndex = i;
                        }
                    }
                    if (keepDiceIndex != -1) {
                        int index = 3 - keepDiceIndex;
                        dice[index] = roll;
                        return index;
                    } else {
                        return -1;
                    }
                }
            }
        }
    }

    // Check if a set of dice contain a single complete set of 3
    public static int[] oneSet(int[] dice) {
        for (int[][] si : setIndices) {
            if (isSet(si[0], dice)) {
                return si[0];
            } else if (isSet(si[1], dice)) {
                return si[1];
            }
        }
        // return {-1,-1,-1} if there is no set
        return new int[] { -1, -1, -1 };
    }

    // Check if a set of dice contain a single pair
    public static int[] checkPair(int[] dice) {
        for (int i = 0; i < dice.length; i++) {
            for (int j = i + 1; j < dice.length; j++) {
                if (dice[i] == dice[j]) {
                    return new int[] { i, j };
                }
            }
        }
        // return {-1,-1} if there is no pair
        return new int[] { -1, -1 };
    }

    // find the index of a value in an int array
    public static int getIndexOf(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        // return -1 if the value is not found
        return -1;
    }

    // check if an int can be replaced in an array to create a pair
    public static int canCreatePair(int[] dice, int roll) {
        for (int i = 0; i < dice.length; i++) {
            if (dice[i] == roll) {
                return i;
            }
        }
        return -1;
    }
}
