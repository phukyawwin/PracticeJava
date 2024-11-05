import java.io.*;
import java.util.stream.*;



public class ProjectEuler {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());
                long sum = sumOfMultiples(n);
                System.out.println(sum);
                //System.out.println(n);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
    private static long sumOfMultiples(int n) {
        
        long sum3 = sumOfDivisibleBy(3, n - 1);
        long sum5 = sumOfDivisibleBy(5, n - 1);
        long sum15 = sumOfDivisibleBy(15, n - 1);

        return sum3 + sum5 - sum15;
    }

    private static long sumOfDivisibleBy(int x, int limit) {
        // Find the largest multiple of x below the limit
        long p = limit / x;
        // Use the arithmetic sum formula
        return x * p * (p + 1) / 2;
    }
    
}
