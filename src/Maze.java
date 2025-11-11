import java.io.*;
import java.util.*;

/**
 * Represents a maze loaded from a text file.
 *
 * The maze is stored as a 2D grid of characters. Each character represents
 * a different type of cell:
 * -'S': Start
 * -'E': End
 * -'■' Wall (impassable)
 * '.': Open path (passable)
 *
 * This class provides methods to:
 * - Load a maze from a file
 * - Access maze properties (start/end positions, dimensions)
 * - Validate positions
 * - Print the maze, with or without a path
 */
public class Maze {

    /** 2D grid storing the maze */
    private char[][] grid;

    /** Number of rows in the maze */
    private int rows;

    /** Number of columns in the maze */
    private int cols;

    /** Start position (S) */
    private Position start;

    /** End position (E) */
    private Position end;

    /**
     * Constructor that loads a maze from a text file.
     *
     * @param filename  Path to the maze text file
     * @throws FileNotFoundException if the file does not exist
     * @throws IllegalArgumentException if the maze file is empty, non-rectangular,
     *                                  or has invalid number of start/end points
     */
    public Maze(String filename) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();

        // Read all lines from the file
        try (Scanner sc = new Scanner(new File(filename))){
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
        }

        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Maze file is empty!");
        }
        rows = lines.size();
        cols = lines.get(0).length();

        // Ensure the maze is rectangular
        for (String line : lines) {
            if (line.length() != cols) {
                throw new IllegalArgumentException("Maze is not rectangular!");
            }
        }

        grid = new char[rows][cols];

        int startCount = 0, endCount = 0;

        // Fill the grid and locate start/end positions
        for(int r = 0; r < rows; r++){
            String line = lines.get(r);
            for(int c = 0; c < cols; c++) {
                char ch = line.charAt(c);
                grid[r][c] = ch;
                if (ch == 'S') {
                    start = new Position(r,c);
                    startCount++;
                }
                else if (ch == 'E') {
                    end = new Position(r,c);
                    endCount++;
                }
            }
        }

        // Validate that there is exactly one start and one end
        if(startCount != 1) {
            throw new IllegalArgumentException("Maze must have exactly one start (S) point!");
        }
        if (endCount !=1) {
            throw new IllegalArgumentException("Maze must have exactly one end (E) point!");
        }
    }

    /** Returns the start position (S) */
    public Position getStart() {return start;}

    /**Returns the end position (E) */
    public Position getEnd() {return end; }

    /** Returns the number of rows*/
    public int getRows() { return rows;}

    /** Returns the number of columns */
    public int getCols() {return cols;}

    /**
     * Checks if a given cell is valid (within bounds and passable)
     *
     * @param row Row index
     * @param col Column index
     * @return true if the cell is inside the grid and either '.', 'S', or 'E'
     */
    public boolean isValid(int row, int col){
        return row >= 0 && row < rows && col >= 0 && col < cols
                && (grid[row][col] == '.' || grid[row][col] =='S' || grid[row][col] == 'E');

    }

    /** Returns the character at the specified cell*/
    public char getCell(int row, int col) {
        return grid[row][col];
    }

    /** Prints the maze the console as-is */
    public void printMaze() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++){
                System.out.print(grid[r][c]);
            }
            System.out.println();
        }
    }

    /**
     * Prints the maze with a path overlaid.
     * The path is represented with '*' characters.
     * Start(S) and End (E) are preserved
     *
     * @param path  List of positions representing the solution path
     */
    public void printMazeWithPath(List<Position> path){
        char[][] copy = new char[rows][cols];
        for (int r =0; r < rows; r++){
            System.arraycopy(grid[r], 0, copy[r], 0, cols);
        }

        // Overlay the path (skip start and end)
        for (Position p: path) {
            if (copy[p.row][p.col] != 'S' && copy[p.row][p.col] != 'E'){
                copy[p.row][p.col] = '*';
            }
        }

        // Print the grid
        for (int r =0; r <rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(copy[r][c]);
            }
            System.out.println();
        }
    }

    /** Returns the internal 2D character grid */
    public char[][] getGrid() {
        return grid;
    }
}
