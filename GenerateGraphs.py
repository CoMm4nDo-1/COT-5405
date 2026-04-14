import csv, matplotlib.pyplot as plt

def parse_sections(path):
    sections = []
    current = []
    with open(path) as f:
        for line in f:
            line = line.strip()
            if line.startswith("#") or line == "":
                if current:
                    sections.append(current)
                    current = []
                continue
            current.append(line)
    if current:
        sections.append(current)
    return [list(csv.DictReader(s)) for s in sections]

exp1, exp2 = parse_sections("results.csv")

# Graph 1: n vs execution time
bt_n    = [int(r["n"]) for r in exp1 if r["Backtracking_time"] != "N/A"]
bt_time = [float(r["Backtracking_time"]) for r in exp1 if r["Backtracking_time"] != "N/A"]
dp_n    = [int(r["n"]) for r in exp1]
dp_time = [float(r["DP_time"]) for r in exp1]

plt.figure()
plt.plot(bt_n, bt_time, "r-o", label="Recursive Backtracking")
plt.plot(dp_n, dp_time, "b-o", label="Dynamic Programming")
plt.xticks(dp_n)
plt.title("Number of Items vs Execution Time")
plt.xlabel("Number of Items (n)")
plt.ylabel("Execution Time (ms)")
plt.legend()
plt.tight_layout()
plt.savefig("graph1.png")

# Graph 2: W vs execution time
w_vals     = [int(r["W"]) for r in exp2]
bt_time_2  = [float(r["Backtracking_time"]) for r in exp2]
dp_time_2  = [float(r["DP_time"]) for r in exp2]

plt.figure()
plt.plot(w_vals, bt_time_2, "r-o", label="Recursive Backtracking")
plt.plot(w_vals, dp_time_2, "b-o", label="Dynamic Programming")
plt.xticks(w_vals)
plt.title("Knapsack Capacity vs Execution Time")
plt.xlabel("Knapsack Capacity (W)")
plt.ylabel("Execution Time (ms)")
plt.legend()
plt.tight_layout()
plt.savefig("graph2.png")

print("Saved graph1.png, graph2.png")
