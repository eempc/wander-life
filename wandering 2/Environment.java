import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.*;

/**
 * Maintain the environment for a 2D cellular automaton.
 * <p>
 * method 2: hold a list of live cells here and move them around here
 * still requires the x, y coordinates of the live cells?
 *
 * @author David J. Barnes
 * @version 2016.02.29
 */
public class Environment {
    // Default size for the environment.
    private static final int DEFAULT_ROWS = 50;
    private static final int DEFAULT_COLS = 50;

    // The grid of cells.
    private Cell[][] cells;
    // Visualization of the environment.
    private final EnvironmentView view;

    private ArrayList<Cell> liveCells;

    private int nRows, nCols;

    private Random rng;

    /**
     * Create an environment with the default size.
     */
    public Environment() {
        this(DEFAULT_ROWS, DEFAULT_COLS);
    }

    /**
     * Create an environment with the given size.
     *
     * @param numRows The number of rows.
     * @param numCols The number of cols;
     */
    public Environment(int numRows, int numCols) {
        rng = new Random();
        nRows = numRows;
        nCols = numCols;
        setup(numRows, numCols);
        randomize();
        view = new EnvironmentView(this, numRows, numCols);
        view.showCells();
        liveCells = new ArrayList<>();
    }

    /**
     * Run the automaton for one step.
     */
    public void step() {
        ArrayList<Cell> tempNewLiveCells = new ArrayList<>();

//        int numRows = cells.length;
//        int numCols = cells[0].length;

        Iterator<Cell> it = liveCells.iterator();

        while (it.hasNext()) {
            Cell c = it.next();
            int currentCellRow = c.getRowIndex();
            int currentCellColumn = c.getColIndex();

//            System.out.println(currentCellRow);
//            System.out.println(currentCellColumn);

            ArrayList<Cell> choices = new ArrayList<>();

            // neighbours
//            Cell north = cells[currentCellRow - 1][currentCellColumn];
//            Cell south = cells[currentCellRow + 1][currentCellColumn];
//            Cell west = cells[currentCellRow][currentCellColumn - 1];
//            Cell east = cells[currentCellRow][currentCellColumn + 1];

            // If not top row, add the north
            if (currentCellRow != 0) {
                choices.add(cells[currentCellRow - 1][currentCellColumn]);
            }

            // If not left column, add the west
            if (currentCellColumn != 0){
                choices.add(cells[currentCellRow][currentCellColumn - 1]);
            }

            // If not bottom row, add the south
            if (currentCellRow != nRows - 1) {
                choices.add(cells[currentCellRow + 1][currentCellColumn]);
            }

            // If not right column, add the east
            if (currentCellColumn != nCols - 1) {
                choices.add(cells[currentCellRow][currentCellColumn + 1]);
            }

            int cat = rng.nextInt(choices.size());
            tempNewLiveCells.add(choices.get(cat));

            // Kill current live cell
            setCellState(currentCellRow, currentCellColumn, Cell.DEAD);
            it.remove();
        }

        for (Cell c : tempNewLiveCells) {
            c.setState(Cell.ALIVE);
            liveCells.add(c);
        }
    }

    /**
     * Reset the state of the automaton to all DEAD.
     */
    public void reset() {
        int numRows = cells.length;
        int numCols = cells[0].length;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                setCellState(row, col, Cell.DEAD);
            }
        }
    }

    /**
     * Generate a random setup.
     */
    public void randomize() {
        int numRows = cells.length;
        int numCols = cells[0].length;
        SecureRandom rand = new SecureRandom();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                setCellState(row, col, rand.nextInt(Cell.NUM_STATES));
            }
        }
    }

    public void single() {
        reset();
        setCellState(0, 0, 0);
        liveCells = new ArrayList<>();
        liveCells.add(cells[0][0]);
    }

    public void multi() {
        reset();
        liveCells = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            int cat = rng.nextInt(nRows);
            int dog = rng.nextInt(nCols);
            setCellState(cat, dog, 0);
            liveCells.add(cells[cat][dog]);
        }
    }

    /**
     * Set the state of one cell.
     *
     * @param row   The cell's row.
     * @param col   The cell's col.
     * @param state The cell's state.
     */
    public void setCellState(int row, int col, int state) {
        cells[row][col].setState(state);
    }

    /**
     * Return the grid of cells.
     *
     * @return The grid of cells.
     */
    public Cell[][] getCells() {
        return cells;
    }

    /**
     * Setup a new environment of the given size.
     *
     * @param numRows The number of rows.
     * @param numCols The number of cols;
     */
    private void setup(int numRows, int numCols) {
        cells = new Cell[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }
}
