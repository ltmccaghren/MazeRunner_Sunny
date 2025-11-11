import java.io.FileNotFoundException;
import java.util.*;

/**
 * Main class to drive the maze solving application.
 *
 * This program:
 * -Prompts the user to enter a maze file name.
 * -Loads and displays the maze.
 * -Allows the user to choose a solver algorithm: DFS(Stack) or BFS(Queue).
 * -Executes the selected algorithm to find a path from start(S) to end(E).
 * -Reconstructs and displays the solution path, if one exists.
 * -Prints the number of steps in the path and total cells.
 */
public class MazeSolver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // 1. Prompt user for maze file name
            System.out.print("Enter maze file name (e.g., maze1.txt): ");
            String filename = scanner.nextLine();

            // 2. Load the maze from file
            Maze maze = new Maze(filename);
            System.out.println("\nMaze loaded:");
            maze.printMaze();

            //3. Prompt user to choose solver algorithm
            // Type 1 for DFS (Depth-First Search, uses a Stack)
            // Type 2 for BFS (Breadth-First Search, uses a Queue)
            System.out.print("\nChoose solver 1 or 2: 1 = DFS (Stack), 2 = BFS (Queue): ");
            String choiceStr = scanner.nextLine();
            int choice = Integer.parseInt(choiceStr.trim());

            SolverResult result;

            // 4. Run the selected solver
            if (choice == 1) {
                result = solveDFS(maze);
                System.out.println("\n--- DFS Result ---");
            } else if (choice ==2) {
                result = solveBFS(maze);
                System.out.println("\n--- BFS Result ---");
            } else {
                System.out.println("Invalid choice.");
                return;
            }

            // 5. Display the solution
            if (result.path != null) {
                System.out.println("Path found!");
                printSolution(maze, result.path);
                System.out.println("Steps: " + result.path.size());
            } else {
                System.out.println("No solution found.");
                System.out.println("Steps: 0");
            }
            System.out.println("Cells explored: " + result.exploredCells);

        } catch (FileNotFoundException e) {
            System.err.println("Error: Maze file not found!");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 6. Close scanner to prevent resource leak
            scanner.close();
        }
    }

    /**
     * Solves the maze using Depth-First Search (DFS) with a Stack.
     *
     * @param maze The maze to solve.
     * @return SolverResult containing the path from S to E (or null if no path)
     *         and the total number of explored cells
     */
    public static SolverResult solveDFS(Maze maze) {
        System.out.println("Solving with DFS (Stack)...");
        Stack<Position> stack = new Stack<>();
        Position start = maze.getStart();
        boolean[][] visited = new boolean[maze.getRows()][maze.getCols()];
        int explored = 0;

        stack.push(start);

        while (!stack.isEmpty()) {
            Position current = stack.pop();

            // Skip if already visited
            if (visited[current.row][current.col]) continue;

            visited[current.row][current.col] = true;
            explored++;

            // Check if end is reached
            if (current.equals(maze.getEnd())) {
                return new SolverResult(reconstructPath(current), explored);
            }

            // Add valid neighbors to stack for further exploration
                for (Position neighbor: getNeighbors(current, maze)) {
                    if (!visited[neighbor.row][neighbor.col]) {
                        neighbor.parent = current; // track path for reconstruction
                        stack.push(neighbor);
                    }
                }
            }

        // No path found
        return new SolverResult(null, explored);
    }

    /**
     * Solves the maze using Breadth-First Search (BFS) with a Queue.
     * Guarantees shortest path in terms of steps.
     *
     * @param maze The maze to solve.
     * @return SolverResult containing the path from S to E (or null if no path)
     *         and the total number of explored cells
     */
    public static SolverResult solveBFS(Maze maze) {
        System.out.println("Solving with BFS (Queue)...");
        Queue<Position> queue = new LinkedList<>();
        Position start = maze.getStart();
        boolean[][] visited = new boolean[maze.getRows()][maze.getCols()];
        int explored =0;

        queue.add(start);

        while(!queue.isEmpty()){
            Position current = queue.poll();

            // Skip if already visited
            if(visited[current.row][current.col]) continue;

            visited[current.row][current.col] = true;
            explored++;

            // Check if end is reached
            if(current.equals(maze.getEnd())) {
                return new SolverResult(reconstructPath(current), explored);
            }

            // Add valid neighbors to queue for further exploration
            for (Position neighbor : getNeighbors(current, maze)) {
                if (!visited[neighbor.row][neighbor.col]) {
                    neighbor.parent = current; // track path for reconstruction
                    queue.add(neighbor);
                }
            }
        }
        return new SolverResult(null, explored); // No path found
    }

    /**
     * Reconstructs the path from the end node back to the start node
     * using the 'parent' pointers stored during DFS/BFS.
     *
     * @param end The end Position (which contains a chain of parents).
     * @return A List of Positions from start to end.
     */
    private static List<Position> reconstructPath(Position end) {
        List<Position> path = new ArrayList<>();
        Position current = end;
        while (current != null){
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path); // reverse to get path from start to end
        return path;
    }

    /**
     * Returns all valid neighbors of the current position (up, down, left, right)
     *
     * @param pos The current position
     * @param maze  The maze
     * @return  List of neighboring positions that can be visited
     */
    public static List<Position> getNeighbors(Position pos, Maze maze) {
        List<Position> neighbors = new ArrayList<>();
        int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; // directions: up, down, left, right

        for (int[]d : deltas) {
            int r = pos.row +d[0];
            int c = pos.col + d[1];
            if (maze.isValid(r, c)) {
                neighbors.add(new Position(r, c));
            }
        }
        return neighbors;
    }

    /**
     * Prints the maze to the console, marking the solution path with '*'
     *
     * @param maze The maze
     * @param path The solution path
     */
    public static void printSolution(Maze maze, List<Position> path) {
        char[][] gridCopy = new char[maze.getRows()][maze.getCols()];

        // Copy original maze grid
        for (int i = 0; i < maze.getRows(); i++){
            for (int j = 0; j < maze.getCols(); j++){
                gridCopy[i][j] = maze.getGrid()[i][j];
            }
        }

        // Mark the path on the grid
        for (Position p : path) {
            if (!p.equals(maze.getStart()) && !p.equals(maze.getEnd())) {
                gridCopy[p.row][p.col] = '*';
            }
        }

        // Print the grid
        for (int i =0; i< maze.getRows(); i++) {
            for (int j =0; j< maze.getCols(); j++) {
                System.out.print(gridCopy[i][j]);
            }
            System.out.println();
        }
    }
}