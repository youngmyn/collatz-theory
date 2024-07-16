import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.math.BigDecimal;

public class CollatzRun {
    private static final long diapason = 999_999;
    private static final Cache<BigDecimal, Long> collatzCache = CacheBuilder.newBuilder().maximumSize(Long.MAX_VALUE).build();


    public static void main(String[] args) {
        long maxSteps = 0;
        long maxHardNumber = 0;
        for (long i = 1; i < diapason; i++) {
            long l = collatzFunc(BigDecimal.valueOf(i), 0);
            collatzCache.put(BigDecimal.valueOf(i), l);

            if (l > maxSteps) {
                maxSteps = l;
                maxHardNumber = i;
            }
            //TODO draw a graphic to show the steps.
        }
        System.out.println("The most hard humber is " + maxHardNumber + ". We need " + maxSteps + " steps to make 1");
        //System.out.println("Cache contents:");
        //collatzCache.asMap().forEach((key, value) -> System.out.println(key + " " + value));
    }

    public static long collatzFunc(BigDecimal n, long stepsCounter) {
        if (n.equals(BigDecimal.valueOf(1))) return stepsCounter;

        if (n.remainder(BigDecimal.valueOf(2)).equals(BigDecimal.valueOf(0)))//if N is even, then divide by 2. (N = N/2)
            n = n.divide(BigDecimal.valueOf(2));
        else //if N is odd,N = N*3+1
            n = n.multiply(BigDecimal.valueOf(3)).add(BigDecimal.valueOf(1));

        Long stepsInCache = collatzCache.getIfPresent(n);
        if (stepsInCache != null)//If the function has a value in the cache, we get it from there.
            return stepsInCache + stepsCounter + 1;
        else return collatzFunc(n, stepsCounter + 1);//otherwise, simple recursive call
    }
}
