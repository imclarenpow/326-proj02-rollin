 
 # Algorithm for Dice Game
 Algorithm has been (hopefully) completely implemented in `KittrTestImplementation.java`. Testing done in `KittrTestMain.java` provides an average of approximately 3.2 turns per game averaged over 10,000 games.

 ## Algorithm:
 
 - Check for a complete game
    - If there is, return -1;
    - If there isn't, continue;
 - Check if the roll will complete the game
    - If it does, return the index that will complete it;
    - If it doesn't, continue;
 - Check if there is already one set
    - There **is** one set:
        - Check if the remaining dice have a pair
            - If there is a pair, check if abs(roll - pairValue) < abs(nonPairValue - pairValue)
                - If true, replace nonPairValue with roll
                - If false, return -1;
            - If there is not a pair try to create a pair with the roll
                - Check if you can create a pair
                    - If yes, check if either of the other 2 numbers is +- 1 from the roll
                        - If yes, swap the roll for the remaining 3rd number
                        - If no, check if either of the other 2 numbers is +- 2 from the roll
                            - If yes, swap the roll for the remaining 3rd number
                            - If no, return -1;
                - If you can't create a pair, check if the roll is +- 1 from a dice
                    - If yes, replace a dice that is not +- 1 from the roll
                    - If it isn't, check if the roll is +- 2 from a dice
                        - If yes, replace any dice that is not +- 2 from the roll
                        - If no, return -1;        
    - There **isn't** one set **(NEEDS CHECKING)**:
        - Check if the roll will complete a set
            - If no, check for a pair
                - If there are pairs then check if the roll is +- 1 or +- 2 from any pair, then add the roll anywhere other than the pair
                - If no pair, check if the roll will complete a pair
                    - If yes, check if any of the other 5 dice are +- 1 from the roll value
                        - If yes, don't replace the pair value or the +- 1, replace any of the other 4 dice
                        - If no, check if any of the other 5 dice are +- 2 from the roll value
                            - If yes, don't replace the +- 2, replace any of the other 4 dice
                            - If no, replace any of the other 5 dice
                    - If no, return -1;
            - If yes, check for a pair
                - If there is a pair, check the pair for a +- 1 and group those 3 together separately
                    - If there is no +- 1 then group the pair with the +- 2 (there has to be a +- 2 in this scenario)
                        - Check if the roll will complete a set within the remaining 3 dice
                            - If not, check if the roll will complete a set within the remaining 3 dice plus the +- 1/2 dice
                                - If not, check if the roll will complete a set within all dice, and complete the set if it can
                - If no pair, check if the roll will complete a pair
                    - If yes, check if any of the other 5 dice are +- 1 from the roll value
                        - If yes, don't replace the pair value or the +- 1, replace any of the other 4 dice
                        - If no, check if any of the other 5 dice are +- 2 from the roll value
                            - If yes, don't replace the +- 2, replace any of the other 4 dice
                            - If no, replace any of the other 5 dice
                    - If no, return -1;