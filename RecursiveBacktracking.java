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

    public static void main(String[] args) {
        // Sample input
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int capacity = 5;
        // Result of the problem 
        int recurresult = knapsack(weights, values, capacity, 0);
        // Prints result
        System.out.println("Max value = " + recurresult);
        System.out.println("Recursive calls = " + recurcallcount);
    }
}