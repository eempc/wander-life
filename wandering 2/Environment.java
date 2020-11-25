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

    //private ArrayList<Cell> tempNewLiveCells;
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
        //tempNewLiveCells = new ArrayList<>();
        nRows = numRows;
        nCols = numCols;
        setup(numRows, numCols);
        randomize();
        view = new EnvironmentView(this, numRows, numCols);
        view.showCells();
        liveCells = new ArrayList<>();
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
    public void step() {
        ArrayList<Cell> tempNewLiveCells = new ArrayList<>();

        int numRows = cells.length;
        int numCols = cells[0].length;

        Iterator<Cell> it = liveCells.iterator();

        while (it.hasNext()) {
            Cell c = it.next();
            int currentCellRow = c.getRowIndex();
            int currentCellColumn = c.getColIndex();

            System.out.println(currentCellRow);
            System.out.println(currentCellColumn);

            ArrayList<Cell> choices = new ArrayList<>();

            // If not top row, add the south
            if (currentCellRow != 0) {
                choices.add(cells[currentCellRow - 1][currentCellColumn]);
            }

            // If not left column, add the west
            if (currentCellColumn != 0){
                choices.add(cells[currentCellRow][currentCellColumn - 1]);
            }
            
            // If not bottom row, add the south
            if (currentCellRow != numRows - 1) {
                choices.add(cells[currentCellRow + 1][currentCellColumn]);
            }

            // If not right column, add the east
            if (currentCellColumn != numCols - 1) {
                choices.add(cells[currentCellRow][currentCellColumn + 1]);
            }

            int cat = rng.nextInt(choices.size());
            tempNewLiveCells.add(choices.get(cat));

            // Assign this cell's neighbours - edge detection required
//            Cell north = (currentCellRow != 0) ? cells[currentCellRow + 1][currentCellColumn] : new Cell(-1, -1);
//            Cell east = (currentCellColumn != 0) ? cells[currentCellRow][currentCellColumn + 1] : new Cell(-1, -1);
//            Cell south = (currentCellRow != numRows - 1) ? cells[currentCellRow - 1][currentCellColumn] : new Cell(-1, -1);
//            Cell west = (currentCellColumn != numCols - 1) ? cells[currentCellRow][currentCellColumn -1] : new Cell(-1, -1);

            // Edge and corner detection
            // 0 = north, 1 = east, 2 = south, 3 = west
//            if (currentCellRow == 0 && currentCellColumn == 0) {
//                // Go south or east 2 or 1
//                Cell[] topLeftCornerChoices = { east, south };
//                int x = rng.nextInt(topLeftCornerChoices.length);
//                tempNewLiveCells.add(topLeftCornerChoices[x]);
//            }
//            else if (currentCellRow == 0 && currentCellColumn == numCols - 1) {
//                // go south or west 2 or 3
//                Cell[] topRightCornerChoices = { west, south };
//                int x = rng.nextInt(topRightCornerChoices.length);
//                tempNewLiveCells.add(topRightCornerChoices[x]);
//            }
//            else if (currentCellRow == numRows - 1 && currentCellColumn == 0) {
//                // go north or east 0 or 1
//                Cell[] bottomLeftChoices = { north, east };
//                int x = rng.nextInt(bottomLeftChoices.length);
//                tempNewLiveCells.add(bottomLeftChoices[x]);
//            }
//            else if (currentCellRow == numRows - 1 && currentCellColumn == numCols - 1) {
//                // go north or west, 0 or 3
//                Cell[] bottomRightChoices = { north, west };
//                int x = rng.nextInt(bottomRightChoices.length);
//                tempNewLiveCells.add(bottomRightChoices[x]);
//            }
//            else if (currentCellRow == 0) {
//                // go go south, east or west, 1, 2, 3
//                Cell[] topChoices = { south, east, west };
//                int x = rng.nextInt(topChoices.length);
//                tempNewLiveCells.add(topChoices[x]);
//            }
//            else if (currentCellRow == numRows - 1) {
//                // go north, east or west, 0, 1, 3
//                Cell[] bottomChoices = { north, east, west };
//                int x = rng.nextInt(bottomChoices.length);
//                tempNewLiveCells.add(bottomChoices[x]);
//            }
//            else if (currentCellColumn == 0) {
//                // go east, north or south 0 1 2
//                Cell[] leftChoices = { north, east, south };
//                int x = rng.nextInt(leftChoices.length);
//                tempNewLiveCells.add(leftChoices[x]);
//            }
//            else if (currentCellColumn == numCols - 1) {
//                // go west, north or south, 0 , 2, 3
//                Cell[] rightChoices = { north, west, south };
//                int x = rng.nextInt(rightChoices.length);
//                tempNewLiveCells.add(rightChoices[x]);
//            }
//            else {
//                Cell[] allChoices = {north, west, south, east};
//                int x = rng.nextInt(allChoices.length);
//                tempNewLiveCells.add(allChoices[x]);
//            }


            // Basic logic for moving cells without taking into account the edges/corners
//            int dir = rng.nextInt(4);
//
//            switch (dir) {
//                case 0:
//                    tempNewLiveCells.add(north); // north
//                    break;
//                case 1:
//                    tempNewLiveCells.add(east); // east
//                    break;
//                case 2:
//                    tempNewLiveCells.add(south); // south
//                    break;
//                case 3:
//                    tempNewLiveCells.add(west); // west
//                    break;
//            }

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
        setCellState(25, 25, 0);
        liveCells = new ArrayList<>();
        liveCells.add(cells[25][25]);
    }

    public void multi() {
        reset();
        liveCells = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
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
