import java.util.Random;

public class Experiment {

    private static final int TRIALS = 5;
    private static final long SEED = 42;

    private static int[][] generateItems(int n, Random rng) {
        int[] weights = new int[n];
        int[] values  = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = rng.nextInt(20) + 1;
            values[i]  = rng.nextInt(50) + 1;
        }
        return new int[][] { weights, values };
    }

    private static void experiment1() {
        System.out.println("# Experiment 1 - Vary n (fixed W=50)");
        System.out.println("n,Backtracking_time,Backtracking_calls,DP_time,DP_tableAccesses");

        int[] nValues = {5, 10, 15, 20, 25};
        int capacity = 50;

        for (int n : nValues) {
            Random rng = new Random(SEED);
            int[][] items = generateItems(n, rng);
            int[] weights = items[0];
            int[] values  = items[1];

            // n=25 takes way too long for backtracking so we can skip it
            String btTime  = "N/A";
            String btCalls = "N/A";
            if (n <= 20) {
                long totalNano = 0;
                long totalCalls = 0;
                for (int t = 0; t < TRIALS; t++) {
                    Algorithms.recurcallcount = 0;
                    long start = System.nanoTime();
                    Algorithms.btKnapsack(weights, values, capacity, 0);
                    long end = System.nanoTime();
                    totalNano  += (end - start);
                    totalCalls += Algorithms.recurcallcount;
                }
                double btAvgTime    = (totalNano / (double) TRIALS) / 1000000;
                long   btAvgCalls = totalCalls / TRIALS;
                btTime  = String.format("%.4f", btAvgTime);
                btCalls = String.valueOf(btAvgCalls);
            }

            long totalNano = 0;
            long totalAccesses = 0;
            for (int t = 0; t < TRIALS; t++) {
                Algorithms.tableAccesses = 0;
                long start = System.nanoTime();
                Algorithms.dpKnapsack(weights, values, capacity);
                long end = System.nanoTime();
                totalNano     += (end - start);
                totalAccesses += Algorithms.tableAccesses;
            }
            double dpAvgTime     = (totalNano / (double) TRIALS) / 1000000;
            long   dpAvgAccess = totalAccesses / TRIALS;

            System.out.printf("%d,%s,%s,%.4f,%d%n",
                    n, btTime, btCalls, dpAvgTime, dpAvgAccess);
        }
        System.out.println();
    }

    // same structure as experiment1 but we leave n the same and modify W instead
    private static void experiment2() {
        System.out.println("# Experiment 2 - Vary W (fixed n=15)");
        System.out.println("W,Backtracking_time,Backtracking_calls,DP_time,DP_tableAccesses");

        int n = 15;
        int[] wValues = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        Random rng = new Random(SEED);
        int[][] items = generateItems(n, rng);
        int[] weights = items[0];
        int[] values  = items[1];

        for (int W : wValues) {
            // backtracking
            long btNano = 0;
            long totalCalls  = 0;
            for (int t = 0; t < TRIALS; t++) {
                Algorithms.recurcallcount = 0;
                long start = System.nanoTime();
                Algorithms.btKnapsack(weights, values, W, 0);
                long end = System.nanoTime();
                btNano += (end - start);
                totalCalls  += Algorithms.recurcallcount;
            }
            double btAvgTime   = (btNano / (double) TRIALS) / 1000000;
            long   btAvgCall = totalCalls / TRIALS;

            // dp
            long dpNano = 0;
            long totalAccesses = 0;
            for (int t = 0; t < TRIALS; t++) {
                Algorithms.tableAccesses = 0;
                long start = System.nanoTime();
                Algorithms.dpKnapsack(weights, values, W);
                long end = System.nanoTime();
                dpNano += (end - start);
                totalAccesses += Algorithms.tableAccesses;
            }
            double dpAvgTime     = (dpNano / (double) TRIALS) / 1000000;
            long   dpAvgAccess = totalAccesses / TRIALS;
            System.out.printf("%d,%.4f,%d,%.4f,%d%n",
                    W, btAvgTime, btAvgCall, dpAvgTime, dpAvgAccess);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("# COT 5405 -- 0/1 Knapsack Experiment Results");
        System.out.println("# Cameron Anderson and Carlos Torres");
        System.out.println("# Each timing is the average of " + TRIALS + " trials (ms)");
        System.out.println();

        experiment1();
        experiment2();

        System.out.println("# Experiments complete.");
    }
}
