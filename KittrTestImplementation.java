import java.lang.Math;

// TODO: Needs comprehensive commenting

public class KittrTestImplementation extends Rollin {
    public KittrTestImplementation() {
    }

    public int handleRoll(int roll, int[] dice) {
        // if dice are already complete, we are done
        if (Rollin.isComplete(dice)) {
            return -1;
        }

        // if we can complete the dice with the roll, do it
        int[] remainingDice = new int[dice.length];
        System.arraycopy(dice, 0, remainingDice, 0, dice.length);
        for (int i = 0; i < remainingDice.length; i++) {
            remainingDice[i] = roll;
            if (Rollin.isComplete(remainingDice)) {
                return i;
            }
            remainingDice[i] = dice[i];
        }

        // check if there is one set already present
        int[] set = oneSet(dice);
        if (set[0] == -1) {
            // there isn't one set:

            if (canCompleteSet(remainingDice, roll) == -1) {
                // the roll cannot complete a set within the remaining dice
                int[] pairIndices = checkPair(remainingDice);
                if (pairIndices[0] != -1) {
                    // there is a pair
                    if (Math.abs(roll - remainingDice[pairIndices[0]]) <= 2) {
                        for (int i = 0; i < remainingDice.length; i++) {
                            if (remainingDice[i] != remainingDice[pairIndices[0]]) {
                                return i;
                            }
                        }
                    } else {
                        return -1;
                    }
                } else {
                    // there is no pair
                    int pairIndex = canCreatePair(remainingDice, roll);
                    if (pairIndex != -1) {
                        for (int i = 0; i < remainingDice.length; i++) {
                            if (i != pairIndex && Math.abs(remainingDice[i] - roll) > 2) {
                                return i;
                            }
                        }
                        return -1;
                    } else {
                        return -1;
                    }
                }
            } else {
                // the roll can complete a set within the remaining dice

                int[] pairIndices = checkPair(remainingDice);
                if (pairIndices[0] != -1) {
                    // there is a pair

                    int[] pairAndConsecutiveIndices = new int[3];
                    pairAndConsecutiveIndices[0] = pairIndices[0];
                    pairAndConsecutiveIndices[1] = pairIndices[1];
                    pairAndConsecutiveIndices[2] = -1;
                    int[] useableDiceIndices = new int[3];

                    // Find a dice that is +- 1 or +- 2 value from the pair's value
                    for (int i = 0; i < remainingDice.length; i++) {
                        if (Math.abs(remainingDice[i] - remainingDice[pairIndices[0]]) == 1) {
                            pairAndConsecutiveIndices[2] = i;
                        }
                    }
                    if (pairAndConsecutiveIndices[2] == -1) {
                        for (int i = 0; i < remainingDice.length; i++) {
                            if (Math.abs(remainingDice[i] - remainingDice[pairIndices[0]]) == 2) {
                                pairAndConsecutiveIndices[2] = i;
                            }
                        }
                    }
                    for (int i = 0; i < useableDiceIndices.length; i++) {
                        if (i != pairAndConsecutiveIndices[0] && i != pairAndConsecutiveIndices[1]
                                && i != pairAndConsecutiveIndices[2]) {
                            useableDiceIndices[i] = i;
                        }
                    }

                    int canCompleteSetIndex = -1;
                    for (int i = 0; i < remainingDice.length; i++) {
                        int temp;
                        if (i != pairAndConsecutiveIndices[0] && i != pairAndConsecutiveIndices[1]
                                && i != pairAndConsecutiveIndices[2]) {
                            temp = remainingDice[i];
                            remainingDice[i] = roll;
                            if (oneSet(remainingDice)[0] != -1) {
                                canCompleteSetIndex = i;
                            }
                            remainingDice[i] = temp;
                        }
                    }

                    if (canCompleteSetIndex != -1) {
                        return canCompleteSetIndex;
                    } else {
                        for (int i = 0; i < remainingDice.length; i++) {
                            int temp;
                            if (i != pairAndConsecutiveIndices[0] && i != pairAndConsecutiveIndices[1]) {
                                temp = remainingDice[i];
                                remainingDice[i] = roll;
                                if (oneSet(remainingDice)[0] != -1) {
                                    canCompleteSetIndex = i;
                                }
                                remainingDice[i] = temp;
                            }
                        }
                        if (canCompleteSetIndex != -1) {
                            return canCompleteSetIndex;
                        } else if (canCompleteSet(remainingDice, roll) != -1) {
                            int setIndex = canCompleteSet(remainingDice, roll);
                            return setIndex;
                        } else {
                            return -1;
                        }
                    }

                } else {
                    // there is no pair
                    int pairIndex = canCreatePair(remainingDice, roll);
                    if (pairIndex != -1) {
                        for (int i = 0; i < remainingDice.length; i++) {
                            if (i != pairIndex && Math.abs(remainingDice[i] - roll) > 2) {
                                return i;
                            }
                        }
                        return -1;
                    } else {
                        return -1;
                    }
                }
            }

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
                        int index = getIndexOf(dice, remainingDice[2 - keepDiceIndex]);
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

    // check if a roll can complete a set of 3 dice
    public static int canCompleteSet(int[] dice, int roll) {
        int temp;
        for (int i = 0; i < dice.length; i++) {
            temp = dice[i];
            dice[i] = roll;
            if (oneSet(dice)[0] != -1) {
                return i;
            }
            dice[i] = temp;
        }
        return -1;
    }
}
