/* Cameron Anderson
0/1 Knapsack Problem
COT 5405 */


public class RecursiveBacktracking {
    static int recurcallcount = 0;
    public static int knapsack(int[] weights, int[] values, int capacity, int index) {
        // Tracks the number of times the function is called for comparsion
        recurcallcount++;
        // Base case: no more items or capacity is 0
        if (index == weights.length || capacity == 0) {
            return 0;
        } 
        // If the item is too heavy it skips it
        if (weights[index] > capacity) {
            return knapsack(weights, values, capacity, index + 1);
        }
        // Takes the item
        int take = values[index] + knapsack(weights, values, capacity - weights[index], index + 1);
        // Skips the item if not fit/taken
        int skip = knapsack(weights, values, capacity, index + 1);
        // Returns the maximum of taking or skipping the item
        return Math.max(take, skip);
    }

    // Dynamic Programming
    static int tableAccesses = 0;

    public static int dpKnapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                tableAccesses++;

                if (weights[i - 1] > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    int exclude = dp[i - 1][w];
                    int include = values[i - 1] + dp[i - 1][w - weights[i - 1]];
                    dp[i][w] = Math.max(exclude, include);
                }
            }
        }

        return dp[n][capacity];
    }

    public static void main(String[] args) {
        // Sample input
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int capacity = 30;
        // Result of the problem 
        int recurresult = knapsack(weights, values, capacity, 0);

        // Prints result for recursive backtracking
        System.out.println("Max value = " + recurresult);
        System.out.println("Recursive calls = " + recurcallcount);

        // DP result
        int dpResult = dpKnapsack(weights, values, capacity);
        System.out.println("DP Max value = " + dpResult);
        System.out.println("Table accesses = " + tableAccesses);
    }
}