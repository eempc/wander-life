import java.util.*;

/**
 * A cell in a 2D cellular automaton.
 * The cell has multiple possible states.
 * This is an implementation of the rules for Brian's Brain.
 *
 * author David J. Barnes and Michael Kölling
 * version 2016.02.29
 * see en.wikipedia.org/wiki/Brian%27s_Brain
 */
public class Cell {
    // The possible states.
    public static final int ALIVE = 0, DEAD = 1; // DYING = 2;
    // The number of possible states.
    public static final int NUM_STATES = 2;
    // Coordinates of this cell
    private int colIndex, rowIndex;
    // The cell's state.
    private int state;
    Random rng;

    /**
     * Set the initial state to be DEAD.
     */
    public Cell(int row, int col) {
        colIndex = col;
        rowIndex = row;
        state = DEAD;
        rng = new Random();
    }

    /**
     * Set the initial state.
     *
     * @param initialState The initial state
     */
    public Cell(int row, int col, int initialState) {
        colIndex = col;
        rowIndex = row;
        state = initialState;
        rng = new Random();
    }

    public int getColIndex() {
        return colIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * Get the state of this cell.
     *
     * @return The state.
     */
    public int getState() {
        return state;
    }

    /**
     * Set the state of this cell.
     *
     * @param
     */
    public void setState(int state) {
        this.state = state;
    }
}
