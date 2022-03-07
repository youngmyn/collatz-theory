import java.math.BigDecimal;

public class CollatzRun {
    public static void main(String[] args) {
        long maxsteps = 0;
        long hardNum = 0;
        for (long i = 2; i<99999999; i++) {
            long l = coll_func(BigDecimal.valueOf(i));
            if (l>maxsteps){
                maxsteps = l;
                hardNum = i;
            }
        }
        System.out.println("The most hard humber is "+ hardNum +". We need " + maxsteps + " steps for it!");
    }

    public static long coll_func(BigDecimal n){
        BigDecimal copyNum = n;
        long stepsCounter = 0;
        while(!n.equals(BigDecimal.valueOf(1))){
            stepsCounter++;
            if(n.remainder(BigDecimal.valueOf(2)).equals(BigDecimal.valueOf(0))){
                n = n.divide(BigDecimal.valueOf(2));
            }
            else{
                n = n.multiply(BigDecimal.valueOf(3)).add(BigDecimal.valueOf(1));
            }
        }
        //System.out.println("It's working for "+ copyNum + ". Count of steps: "+ stepsCounter);
        return stepsCounter;
    }
}
