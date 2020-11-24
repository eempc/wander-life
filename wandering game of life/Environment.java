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

    // List of alive cells locations
    private ArrayList<Cell> liveCells;

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
        setup(numRows, numCols);
        randomize();
        view = new EnvironmentView(this, numRows, numCols);
        view.showCells();
    }
    
    /**
     * Run the automaton for one step.
     */
    public void step()
    {

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
//        int numRows = cells.length;
//        int numCols = cells[0].length;
//        SecureRandom rand = new SecureRandom();
//        for(int row = 0; row < numRows; row++) {
//            for(int col = 0; col < numCols; col++) {
//                setCellState(row, col, rand.nextInt(Cell.NUM_STATES));
//            }
//        }
    }

    public void setSingleLiveCell() {
        reset();
        setCellState(3, 3, Cell.ALIVE);

    }
    
    /**
     * Set the state of one cell.
     * @param row The cell's row.
     * @param col The cell's col.
     * @param state The cell's state.
     */
    public void setCellState(int row, int col, int state)
    {
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
                cells[row][col] = new Cell();
            }
        }
        //setupNeighbors();
    }
    
    /**
     * Give to a cell a list of its neighbors.
     */
//    private void setupNeighbors()
//    {
//        int numRows = cells.length;
//        int numCols = cells[0].length;
//        // Allow for 8 neighbors plus the cell.
//        ArrayList<Cell> neighbors = new ArrayList<>(9);
//        for(int rowIndex = 0; rowIndex < numRows; rowIndex++) {
//            for(int colIndex = 0; colIndex < numCols; colIndex++) {
//                Cell cell = cells[rowIndex][colIndex];
//                // This process will also include the cell.
//                for(int dr = -1; dr <= 1; dr++) {
//                    for(int dc = -1; dc <= 1; dc++) {
////                        int nr = (numRows + rowIndex + dr) % numRows;
////                        int nc = (numCols + colIndex + dc) % numCols;
//                        if (rowIndex + dr < 0 || rowIndex + dr >= numRows || colIndex + dc < 0 || colIndex + dc >= numCols) {
//                            neighbors.add(new Cell());
//                        } else {
//                            neighbors.add(cells[rowIndex + dr][colIndex + dc]);
//                        }
//
//
//                        //neighbors.add(cells[nr][nc]);
//                    }
//                }
//                // The neighbours should not include the cell at
//                // (rowIndex,colIndex) so remove it.
//                neighbors.remove(cell);
//                cell.setNeighbors(neighbors);
//                neighbors.clear();
//            }
//        }
//    }

}
