import com.sun.source.tree.CaseTree;

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
    // Directions
    //static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

    // Coordinates of this cell
    private int colIndex, rowIndex;

    // Info about the game board's size - default to 50 for now
    //private int columnsSize = 50, rowsSize = 50;

    // The cell's state.
    private int state;
    // The cell's neighbors.
    //private Cell[] neighbors;
    // direction
    //private int direction;
    //
    Random rng;

    /**
     * Set the initial state to be DEAD.
     */
    public Cell(int row, int col) {
        colIndex = col;
        rowIndex = row;
        state = DEAD;
        //neighbors = new Cell[0];
        rng = new Random();
        //setDirection();
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
        //neighbors = new Cell[0];
        rng = new Random();
        //setDirection();
    }

//    public void setDirection() {
//        if (rowIndex == 0) {
//            if (colIndex == 0) { direction = DOWN; } // TL
//            else if (colIndex == columnsSize - 1) { direction = LEFT; }  // TR
//            else { direction = rng.nextInt(2) + 2; }
//        } else if (rowIndex == rowsSize - 1) {
//            if (colIndex == 0) { direction = UP; } // BL
//            else if (colIndex == columnsSize - 1) { direction = LEFT; }  // BR
//            else { direction = rng.nextInt(2) + 2; }
//        }
//
//        if ((colIndex == 0 || colIndex == columnsSize - 1) && rowIndex != 0 && rowIndex != rowsSize - 1) {
//            direction = rng.nextInt(2);
//        }
//    }

    public int getColIndex() {
        return colIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * Determine this cell's next state, based on the
     * state of its neighbors.
     * This is an implementation of the rules for Brian's Brain.
     *
     * @return The next state.
     */
//    public int getNextState(int nRows, int nCols) {
////        int aliveCount = 0;
////        for (Cell n : neighbors) {
////            if (n.getState() == ALIVE) {
////                aliveCount++;
////            }
////        }
////
////        if (state == DEAD) {
////            return (aliveCount == 3) ? ALIVE : DEAD;
////        } else {
////            //return (aliveCount < 2 || aliveCount > 3) ? DEAD : ALIVE;
////            return (aliveCount == 2 || aliveCount == 3) ? ALIVE : DEAD;
////        }
//
//
//        for (Cell n : neighbors) {
//            // Is this dead cell next to a live cell? Is the cell next to be resurrected? You can tell by the direction of the live cell
//            // Why can't I get the freaking coordinates?
//            if (n.getState() == ALIVE) {
//                switch(n.getDirection()) {
//                    // If the neighbour is the one below and has a direction of UP, then this one is next to revive
//                    case UP:
//                        if (n.getColIndex() == this.colIndex && n.getRowIndex() - this.rowIndex == -1) {
//                            return ALIVE;
//                        }
//                        break;
//                    case DOWN:
//                        if (n.getColIndex() == this.colIndex && n.getRowIndex() - this.rowIndex == 1) {
//                            return ALIVE;
//                        }
//                        break;
//                    case LEFT:
//                        if (n.getColIndex() + 1 == this.colIndex && n.getRowIndex() == this.rowIndex) {
//                            return ALIVE;
//                        }
//                        break;
//                    case RIGHT:
//                        if (n.getColIndex() - 1 == this.colIndex && n.getRowIndex() == this.rowIndex) {
//                            return ALIVE;
//                        }
//                        break;
//                }
//            }
//        }
//
//        return DEAD;
//
//    }
//
//    public int getDirection() {
//        return direction;
//    }

    /**
     * Receive the list of neighboring cells and take
     * a copy.
     *
     * @param neighborList Neighboring cells.
     */
//    public void setNeighbors(ArrayList<Cell> neighborList) {
//        neighbors = new Cell[neighborList.size()];
//        neighborList.toArray(neighbors);
//    }

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
