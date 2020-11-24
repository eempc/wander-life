import java.security.SecureRandom;
import java.util.*;

/**
 * Maintain the environment for a 2D cellular automaton.
 * 
 * @author David J. Barnes
 * @version  2016.02.29
 */
public class Environment
{
    // Default size for the environment.
    private static final int DEFAULT_ROWS = 50;
    private static final int DEFAULT_COLS = 50;
    
    // The grid of cells.
    private Cell[][] cells;
    // Visualization of the environment.
    private final EnvironmentView view;

    private int nRows, nCols;

    /**
     * Create an environment with the default size.
     */
    public Environment()
    {
        this(DEFAULT_ROWS, DEFAULT_COLS);
    }

    /**
     * Create an environment with the given size.
     * @param numRows The number of rows.
     * @param numCols The number of cols;
     */
    public Environment(int numRows, int numCols)
    {
        nRows = numRows;
        nCols = numCols;
        setup(numRows, numCols);
        randomize();
        view = new EnvironmentView(this, numRows, numCols);
        view.showCells();
    }

    public int getRows() {
        return nRows;
    }

    public int getCols() {
        return nCols;
    }
    
    /**
     * Run the automaton for one step.
     */
    public void step()
    {
        int numRows = cells.length;
        int numCols = cells[0].length;
        // Build a record of the next state of each cell.
        int[][] nextStates = new int[numRows][numCols];
        // Ask each cell to determine its next state.
        for(int row = 0; row < numRows; row++) {
            int[] rowOfStates = nextStates[row];
            for(int col = 0; col < numCols; col++) {
                rowOfStates[col] = cells[row][col].getNextState(nRows, nCols);
            }
        }
        // Update the cells' states.
        for(int row = 0; row < numRows; row++) {
            int[] rowOfStates = nextStates[row];
            for(int col = 0; col < numCols; col++) {
                setCellState(row, col, rowOfStates[col]);

            }
        }
    }
    
    /**
     * Reset the state of the automaton to all DEAD.
     */
    public void reset()
    {
        int numRows = cells.length;
        int numCols = cells[0].length;
        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                setCellState(row, col, Cell.DEAD);
            }
        }
    }
    
    /**
     * Generate a random setup.
     */
    public void randomize()
    {
        int numRows = cells.length;
        int numCols = cells[0].length;
        SecureRandom rand = new SecureRandom();
        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                setCellState(row, col, rand.nextInt(Cell.NUM_STATES));
            }
        }
    }

    public void single() {
        reset();
        setCellState(0, 0, 0);
    }

    /**
     * Set the state of one cell.
     * @param row The cell's row.
     * @param col The cell's col.
     * @param state The cell's state.
     */
    public void setCellState(int row, int col, int state)
    {
        cells[row][col].setDirection();
        cells[row][col].setState(state);
    }
    
    /**
     * Return the grid of cells.
     * @return The grid of cells.
     */
    public Cell[][] getCells()
    {
        return cells;
    }
    
    /**
     * Setup a new environment of the given size.
     * @param numRows The number of rows.
     * @param numCols The number of cols;
     */
    private void setup(int numRows, int numCols)
    {
        cells = new Cell[numRows][numCols];
        for(int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                cells[row][col] = new Cell(col, row);
            }
        }
        setupNeighbors();
    }
    
    /**
     * Give to a cell a list of its neighbors.
     */
    private void setupNeighbors()
    {
        int numRows = cells.length;
        int numCols = cells[0].length;
        // Allow for 8 neighbors plus the cell.
        ArrayList<Cell> neighbors = new ArrayList<>(9);
        for(int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            for(int colIndex = 0; colIndex < numCols; colIndex++) {
                Cell cell = cells[rowIndex][colIndex];
                // This process will also include the cell.
                // Replace with a process than only does the 4 immediate cells.
//                for(int dr = -1; dr <= 1; dr++) {
//                    for(int dc = -1; dc <= 1; dc++) {
////                        int nr = (numRows + rowIndex + dr) % numRows;
////                        int nc = (numCols + colIndex + dc) % numCols;
//                        if (rowIndex + dr < 0 || rowIndex + dr >= numRows || colIndex + dc < 0 || colIndex + dc >= numCols) {
//                            neighbors.add(new Cell(colIndex + dc, rowIndex + dr));
//                        } else {
//                            neighbors.add(cells[rowIndex + dr][colIndex + dc]);
//                        }
//
//
//                        //neighbors.add(cells[nr][nc]);
//                    }
//                }

                // Add north, south, west and east neighbours
                // What if it is at the edge?
                if (rowIndex != 0) { // If not at top row, add the one north
                    neighbors.add(cells[colIndex][rowIndex - 1]); // Add north
                }

                if (rowIndex != numRows - 1) {
                    //Cell south = new Cell(colIndex, rowIndex - 1);
                    neighbors.add(cells[colIndex][rowIndex + 1]); // Add south
                }

                if (colIndex != 0) {
                    //Cell west = new Cell(colIndex - 1, rowIndex);
                    neighbors.add(cells[colIndex - 1][rowIndex]); // Add west
                }

                if (colIndex != numCols - 1) {
                    //Cell east = new Cell(colIndex + 1, rowIndex);
                    neighbors.add(cells[colIndex + 1][rowIndex]); // Add east
                }


                // The neighbours should not include the cell at
                // (rowIndex,colIndex) so remove it.
                //neighbors.remove(cell);
                cell.setNeighbors(neighbors);
                neighbors.clear();
            }
        }
    }

}
