# OS2_Scheduling_Simulation

Java program simulating CPU process scheduling in a bar scenario, comparing FCFS and SJF algorithms with metrics analysis. This project simulates a bar scheduling system, where patrons arrive at the bar and are served by a single barman (Andre) according to a specified scheduling algorithm. The simulation keeps track of various metrics, such as arrival times, waiting times, response times, and turnaround times for each patron, and the throughput

## Prerequisites

- Java Development Kit (JDK) installed
- Make utility (typically available on Unix-like systems)

## Compilation

To compile the program, navigate to the root directory of the project and execute the following command:

```
make
```

This will compile all the Java files in the `src` directory and generate the corresponding class files in the `bin` directory.

## Usage

To run the program, execute one of the following commands:

```
make run
```

or

```
make run ARGS="<noPatrons>"

```

 or

```
make run ARGS="<noPatrons> <schedulingAlgorithm>"
```

Where:

- `<noPatrons>` is the total number of patrons to enter the bar (default: 100).
- `<schedulingAlgorithm>` is the scheduling algorithm to be used (0 for FCFS, 1 SJF) (default: 0).

For example, to simulate with 500 patrons using the Shortest Job First cheduling algorithm, run:

```
make run ARGS="500 1"
```

## Output

The simulation generates two CSV files:

1. `metric_times_<schedulingAlgorithm>_with_<noPatrons>_patrons.csv`: This file contains the metrics for each patron, including Patron ID, Arrival Time, Turnaround Time, Waiting Time, and Response Time.
2. `throughputs_<schedulingAlgorithm>_with_<noPatrons>_patrons.csv`: This file contains the throughput values (patrons served per 10 seconds) at regular intervals during the simulation.

## Clean Up

To clean up the generated class files, execute the following command:

```
make clean
```

This will remove all class files from the `bin` directory.

## Notes

- The simulation runs until all patrons have been served and have left the bar.
- The barman (Andre) is implemented as a separate thread and handles patrons according to the specified scheduling algorithm.
- Patrons are also implemented as separate threads and interact with the barman's thread.
- Timer implemented in another class called BackGroundTimer and implimented as a thread in the Scheduling Simulatr class
- The `CountDownLatch` and `AtomicInteger` classes are used to synchronize the threads and ensure proper execution.
