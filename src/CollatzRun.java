import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.math.BigInteger;

public class CollatzRun {
    private static final long diapason = 50_000_000L;
    private static final Cache<BigInteger, Long> collatzCache = CacheBuilder.newBuilder().softValues().build();

    public static void main(String[] args) {
        long maxSteps = 0;
        long maxHardNumber = 0;
        for (long i = 1; i < diapason; i++) {
            long l = collatzFunc(BigInteger.valueOf(i), 0);
            collatzCache.put(BigInteger.valueOf(i), l);

            if (l > maxSteps) {
                maxSteps = l;
                maxHardNumber = i;
            }
            //TODO draw a graphic to show the steps.
        }
        System.out.println("The most hard humber is " + maxHardNumber + ". We need " + maxSteps + " steps to make 1");
    }

    public static long collatzFunc(BigInteger n, long stepsCounter) {
        if (n.equals(BigInteger.ONE)) return stepsCounter;
        if (isEven(n))
            n = n.divide(BigInteger.TWO);
        else
            n = n.multiply(BigInteger.valueOf(3)).add(BigInteger.ONE);

        Long stepsInCache = collatzCache.getIfPresent(n);
        if (stepsInCache != null)//If the function has a value in the cache, we get it from there.
            return stepsInCache + stepsCounter + 1;
        else return collatzFunc(n, stepsCounter + 1);//otherwise, simple recursive call
    }
    public static boolean isEven(BigInteger number){
        return !number.testBit(0);
    }
}
