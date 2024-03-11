
import java.util.ArrayList;

public class PowisTestImplementation extends Rollin{
    private int[][] sets = new int [2][3];
    private int[][] partials = new int [2][2];
    private int[] dieCheck = new int[7];
    private int turns = 0;
    // TODO figure this out
    @SuppressWarnings("unchecked")
    private ArrayList<Integer>[] checker = new ArrayList[6];
    public PowisTestImplementation(){
        initChecker();
    }
    public int handleRoll(int roll, int[] dice) {
        // 1. Check if already complete
      if (Rollin.isComplete(dice)) {
            return turns;
      }
      // add roll & dice to array of 7 die
      dieCheck[0] = roll;
      for(int i=1; i<dieCheck.length; i++){
        dieCheck[i] = dice[i-1];
      }
      // main checking loop
        // add to list
        for(int i=0; i<dieCheck.length; i++){
            checker[dieCheck[i]-1].add(dieCheck[i]);
        }
        if(stepTwo()){
            }else if(stepThree()){
            }else if(stepFour()){
        }
        // value to find
        int temp = 0;
        boolean flag = true;
        for(int i=0; i<checker.length; i++){
           if(checker[i].size()>0){
                temp = checker[i].get(0);
                flag = false;
                break;
           }
        }
        // if there is nothing in the checker, check partials and choose first
        if(flag){
            temp = partials[0][0];
        }
        for(int i=0; i< dice.length; i++){
            if(temp==dice[i]){
                return i;
            }
        }
        resetChecker();
        turns++;
      return 0;
    }
    // 2. Check for identical sets of 3 and incremental
    public boolean stepTwo(){
        // check for 3 incremental numbers
        int increments = 0;
        for(int i=0; i<checker.length-1; i++){
            // if found incremental numbers add to token
            if(checker[i].size()>0 && checker[i+1].size()>0){
                increments++;
            // else reset
            }else{
                increments = 0;
            }
            // also check that i>1 in case it tries to find an index of -1.
            if(increments==2 && i>1){
                // TODO - make this less lazy
                if(sets[0][0]==0){
                    for(int j=0; j<sets[0].length-1; j++){
                        sets[0][j] = getCheckVal(i-j);
                    }
                    break;
                }else{
                    for(int j=0; j<sets[1].length-1; j++){
                        sets[1][j] = getCheckVal(i-j);
                    }
                    return true;
                }
            }
        }
        // check if there are three of any numbers
        for(int i=0; i<checker.length; i++){
            // if there are three of any number
            if(checker[i].size()>2){
                // if first set hasn't been found yet add it into first slot
                // TODO - make this less lazy
                if(sets[0][0]==0){
                    // loop through set and add identical values
                    for(int j=0; j<sets[0].length; j++){
                        // add and delete value from list
                        sets[0][j] = getCheckVal(i);
                    }
                    break;
                // else if first is taken
                } else {
                    // loop through set and add identical values
                    for(int j=0; j<sets[0].length; j++){
                        // add and delete value from list
                        sets[1][j] = getCheckVal(i);
                    }
                    return true;
                }
            }
        }
        // not final set so return false
        return false;
    }

    // 3. Check for 2,3 3,4 4,5.
    public boolean stepThree(){
        for(int i=1; i<checker.length-2; i++){
            // if found incremental numbers add to token
            if(checker[i].size()!=0 && checker[i+1].size()!=0){
                // TODO - make this less lazy
                if(sets[0][0]==0){
                    for(int j=0; j<2; j++){
                        partials[0][j] = checker[i+j].get(checker[i+j].size()-1);
                        checker[i+j].remove(checker[i+j].size()-1);
                    }
                    break;
                }else{
                    for(int j=0; j<2; j++){
                        partials[1][j] = checker[i+j].get(checker[i+j].size()-1);
                        checker[i+j].remove(checker[i+j].size()-1);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    // 4. Check for identical two numbers and incremental numbers that aren't the above method
    public boolean stepFour(){
        // check for identical
        for(int i=0; i<checker.length; i++){
            if(checker[i].size()>1){
                if(partials[0][0]==0){
                    for(int j=0; j<partials[0].length; j++){
                        partials[0][j] = checker[i].get(checker[i].size()-1);
                        checker[i].remove(checker[i].size()-1);
                    }
                    break;
                }else{
                    for(int j=0; j<partials[0].length; j++){
                        partials[1][j] = checker[i].get(checker[i].size()-1);
                        checker[i].remove(checker[i].size()-1);
                    }
                    return true;
                }
            }
        }
        // check for incremental
        // TODO - this runs over some of the same numbers that stepThree() does, maybe change this later?       
        for(int i=0; i<checker.length-1; i++){
            // check for one increment difference
            if(i<checker.length-2){
                if(checker[i].size()>0 && checker[i+2].size()>0){
                    for(int j=0; j<partials.length; j++){
                        if(sets[j][0]==0){
                            partials[j][0] = getCheckVal(i);
                            partials[j][1] = getCheckVal(i+2);
                            break;
                        }
                    }
                }
            }
            // if found incremental numbers add to token
            if(checker[i].size()!=0 && checker[i+1].size()!=0){
                // this is a short nested loop
                for(int j=0; j<partials.length; j++){
                    if(sets[j][0]==0){
                        partials[j][0] = getCheckVal(i);
                        partials[j][1] = getCheckVal(i+1);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public int getCheckVal(int index){
        int value = 0;
        value = checker[index].get(checker[index].size()-1);
        checker[index].remove(checker[index].size()-1);
        return value;
    }

    // initialise list
    public void initChecker(){
        for(int i=0; i<checker.length; i++){
            checker[i] = new ArrayList<>();
        }
    }
    // reset the lists
    public void resetChecker(){
        for (ArrayList<Integer> list : checker) {
            list.clear();
        }
    }
}
