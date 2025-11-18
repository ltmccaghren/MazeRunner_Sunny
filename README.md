# **MazeSolver Application**

## **Project Overview**

The MazeSolver Application is a Java program designed to solve mazes using two classic algorithms: Depth-First Search (DFS) and Breadth-First Search (BFS).

This project demonstrates the use of Stacks for DFS, Queues for BFS, 2D arrays for maze representation, and parent tracking for path reconstruction. 
Users can visualize the solved maze and compare the behavior of DFS and BFS.

------
## **Features** 

## **Load Maze**

* Prompt the user to enter a maze text file.

## **Choose Solver**
* Option to select:
  * DFS (Stack)
  * BFS (Queue)
* Input Validation: The program handles invalid input gracefully. If the user enters a value that is not 1 or 2 (including letters or symbols), the program will prompt again until a valid choice is entered.
* Once solved, the program provides a menu to:
   * Stop and exit the program 
   * Load a different maze 
   * Solve the same maze using a different method

## **Display Solution**
* Show a solved maze with path marked with * characters
* Prints:
  * Number of steps in the path 
  * Total number of cells explored by the algorithm
* Successfully identifies unsolvable mazes by displaying a "No solution found" message.
-------
## **How to Compile**
1. Open the project in IntelliJ IDEA
2. Make sure src/ folder contains all .java files.
3. The IDE will compile the code automatically and generate .class files.
------
## **How to Run**
1. Locate the MazeSolver.java file.
2. Right-click and select **Run**
3. Enter a maze filename (e.g., maze_solvable.txt) when prompted.
4. Choose the solver algorithm by typing 1 for DFS or 2 for BFS.
5. Displays maze solvability, path length, and number of cells explored.
6. After solving, the user can select:
   * 1 to Stop
   * 2 to load a different maze
   * 3 to solve the same maze using a different method
--------
## **Development Notes**

-----------------
## **Technology Used**

-------------
* Java for all program logic
* Stack for DFS
* Queue (LinkedList) for BFS
* 2D char arrays for maze representation
* File I/O and Exception Handling for robust input

## **Challenges Faced**

----------------
1. Properly reconstructing the path using parent pointers.
2. Ensuring that both DFS and BFS handled unsolvable mazes gracefully.**

## **What We Learned**

----------
1. Learned to implement search algorithms like DFS and BFS.
2. Observed the fundamental differences in behavior between DFS and BFS.
3. Learned how to construct solution path by backtracking from start to end.
---------
## **Sample Mazes & Program Outputs:**

--------
## **Solvable Maze**
**Sample:**
![Screenshot 2025-11-09 at 18.21.09.png](src/Screenshots/Screenshot%202025-11-09%20at%2018.21.09.png)

**DFS Output:**
![Screenshot 2025-11-09 at 18.19.49.png](src/Screenshots/Screenshot%202025-11-09%20at%2018.19.49.png)


**BFS Output:**
![Screenshot 2025-11-09 at 18.20.49.png](src/Screenshots/Screenshot%202025-11-09%20at%2018.20.49.png)

------
## **Unsolvable Maze**
**Sample:**

![Screenshot 2025-11-09 at 18.21.28.png](src/Screenshots/Screenshot%202025-11-09%20at%2018.21.28.png)

**DFS Output:**
![Screenshot 2025-11-09 at 18.21.52.png](src/Screenshots/Screenshot%202025-11-09%20at%2018.21.52.png)
**BFS Output:**
![Screenshot 2025-11-09 at 18.22.21.png](src/Screenshots/Screenshot%202025-11-09%20at%2018.22.21.png)
-----


## **Contact**

------
****Developed by**:**

Lach Mccaghren and Sunny Qian

**Emails:**

sqian@mxschool.edu & ltmccaghren@mxschool.edu




