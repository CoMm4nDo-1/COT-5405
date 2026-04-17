# 0/1 Knapsack Problem — COT 5405

Cameron Anderson and Carlos Torres

## Overview

This project compares two approaches to the 0/1 Knapsack problem: Recursive Backtracking (O(2^n)) and Dynamic Programming (O(n * W)). The code runs timed experiments on both algorithms and generates graphs to visualize the performance differences.

## Files

- `Algorithms.java` — Contains both algorithm implementations (`btKnapsack` and `dpKnapsack`)
- `Experiment.java` — Runs timed experiments varying n and W, outputs results as CSV
- `GenerateGraphs.py` — Reads `results.csv` and produces comparison graphs

## Requirements

- Java JDK 8 or higher
- Python 3 with matplotlib (`pip install matplotlib`)

## How to Compile and Run

1. Compile the Java files:
   ```
   javac Algorithms.java Experiment.java
   ```

2. Run the experiments and save results:
   ```
   java Experiment > results.csv
   ```

3. Generate the graphs:
   ```
   python3 GenerateGraphs.py
   ```

## Output

- `results.csv` — Experiment timing data (average of 5 trials per configuration)
- `graph1.png` — Number of Items (n) vs Execution Time
- `graph2.png` — Knapsack Capacity (W) vs Execution Time
- `graph3.png` — Solution Comparison (correctness verification)

## Experiments

- **Experiment 1** — Varies n (5, 10, 15, 20, 25) with fixed W=50. Backtracking is skipped at n=25 due to exponential runtime.
- **Experiment 2** — Varies W (10 to 100) with fixed n=15. Shows DP's linear dependence on W vs backtracking's independence from W.
- **Experiment 3** — Runs both algorithms on test cases and compares outputs to verify correctness.

## Reproducibility

All random inputs use a fixed seed (42), so results are identical across runs. Each timing measurement is the average of 5 trials using `System.nanoTime()`.
