/**
 * Represents a single cell or position in the maze grid.
 *
 * Each Position stores its row and column coordinates, as well as a reference
 * to its parent Position. The parent is used to reconstruct the path from
 * start (S) to end (E) after a maze-solving algorithm (DFS or BFS) finishes.
 */

public class Position {

    /** Row indexes of this position in the maze grid */
    int row;

    /** Column index of this position in the maze grid */
    int col;

    /** Reference to the previous Position from which this Position was reached */
    Position parent;

    /**
     * Constructor to create a Position with no parent.
     *
     * @param row The row index
     * @param col The column index
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
        this.parent = null;
    }

    /**
     * Checks equality of two Position objects based on row and column.
     * @param obj  The object to compare
     * @return  true if both Positions have the same row and column, false otherwise.
     */
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Position)) return false;
        Position other = (Position) obj;
        return this.row == other.row && this.col == other.col;
    }

    /**
     * Generates a hash code based on row and column.
     * Useful is Position objects are stored in hash-based collections.
     *
     * @return the hash code for this Position.
     */
    public int hashCode() {
        return row*31 + col;
    }
}
