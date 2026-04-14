/* Cameron Anderson and Carlos Torres
0/1 Knapsack Problem
COT 5405 */

public class Algorithms {

    //Recursive Backtracking

    // Tracks the number of times the function is called for comparison
    static int recurcallcount = 0;

    public static int btKnapsack(int[] weights, int[] values, int capacity, int index) {
        recurcallcount++;
        // Base case: no more items or capacity is 0
        if (index == weights.length || capacity == 0) {
            return 0;
        }
        // If the item is too heavy it skips it
        if (weights[index] > capacity) {
            return btKnapsack(weights, values, capacity, index + 1);
        }
        // Takes the item
        int take = values[index] + btKnapsack(weights, values, capacity - weights[index], index + 1);
        // Skips the item if not fit/taken
        int skip = btKnapsack(weights, values, capacity, index + 1);
        // Returns the maximum of taking or skipping the item
        return Math.max(take, skip);
    }

    //Dynamic Programming

    // Tracks the number of table cell computations for comparison
    static int tableAccesses = 0;

    public static int dpKnapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        // Build table where dp[i][w] = max value using first i items at capacity w
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                tableAccesses++;

                // If the item is too heavy it skips it
                if (weights[i - 1] > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // Excludes the item
                    int exclude = dp[i - 1][w];
                    // Includes the item
                    int include = values[i - 1] + dp[i - 1][w - weights[i - 1]];
                    // Returns the maximum of including or excluding the item
                    dp[i][w] = Math.max(exclude, include);
                }
            }
        }

        return dp[n][capacity];
    }
}
