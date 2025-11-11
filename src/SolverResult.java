import java.util.List;

/**
 * A simple container class to hold the results of a maze solving algorithm.
 *
 * Stores:
 * 1. The path found from start to end as a List of Position objects.
 * 2. The total number of cells explored during the search.
 *
 * This class is used to return results from DFS or BFS solvers in a structured way.
 */

public class SolverResult {

    /**
     * The path from start (S) to end (E) of the maze.
     * If no path exists, this will be null.
     */
    List<Position> path;

    /**
     * The total number of cells explored while searching for the path.
     * Useful for comparing the efficiency of DFS and BFS.
     */
    int exploredCells;

    /**
     * Constructor to create a SolverResult object.
     *
     * @param path  The path found(can be null if no solution exists)
     * @param exploredCells The number of cells explored during search
     */
    public SolverResult(List<Position> path, int exploredCells) {
        this.path = path;
        this.exploredCells = exploredCells;
    }
}
