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
        // Build a record of the next state of each cell.
//        int[][] nextStates = new int[numRows][numCols];
//        // Ask each cell to determine its next state.
//        for (int row = 0; row < numRows; row++) {
//            int[] rowOfStates = nextStates[row];
//            for (int col = 0; col < numCols; col++) {
//                rowOfStates[col] = cells[row][col].getNextState(nRows, nCols);
//            }
//        }
//        // Update the cells' states.
//        for (int row = 0; row < numRows; row++) {
//            int[] rowOfStates = nextStates[row];
//            for (int col = 0; col < numCols; col++) {
//                setCellState(row, col, rowOfStates[col]);
//            }
//        }

        Iterator<Cell> it = liveCells.iterator();

        while (it.hasNext()) {
            Cell c = it.next();
            int currentCellRow = c.getRowIndex();
            int currentCellColumn = c.getColIndex();

            System.out.println(currentCellRow);
            System.out.println(currentCellColumn);

            // Assign this cell's neighbours
            Cell north = cells[currentCellRow + 1][currentCellColumn];
            Cell east = cells[currentCellRow][currentCellColumn + 1];
            Cell south = cells[currentCellRow - 1][currentCellColumn];
            Cell west = cells[currentCellRow][currentCellColumn -1];

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
            int dir = rng.nextInt(4);

            switch (dir) {
                case 0:
                    tempNewLiveCells.add(north); // north
                    break;
                case 1:
                    tempNewLiveCells.add(east); // east
                    break;
                case 2:
                    tempNewLiveCells.add(south); // south
                    break;
                case 3:
                    tempNewLiveCells.add(west); // west
                    break;
            }

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

//        int a = 25;
//        setCellState(a, a, 0);
//        liveCells.add(cells[a][a]);
//
//        int b = 20;
//        setCellState(b, b, 0);
//        liveCells.add(cells[b][b]);
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
        //setupNeighbors();
    }

    /**
     * Give to a cell a list of its neighbors.
     */
//    private void setupNeighbors() {
//        int numRows = cells.length;
//        int numCols = cells[0].length;
//        // Allow for 8 neighbors plus the cell.
//        ArrayList<Cell> neighbors = new ArrayList<>(9);
//        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
//            for (int colIndex = 0; colIndex < numCols; colIndex++) {
//                Cell cell = cells[rowIndex][colIndex];
//                // This process will also include the cell.
//                // Replace with a process than only does the 4 immediate cells.
////                for(int dr = -1; dr <= 1; dr++) {
////                    for(int dc = -1; dc <= 1; dc++) {
//////                        int nr = (numRows + rowIndex + dr) % numRows;
//////                        int nc = (numCols + colIndex + dc) % numCols;
////                        if (rowIndex + dr < 0 || rowIndex + dr >= numRows || colIndex + dc < 0 || colIndex + dc >= numCols) {
////                            neighbors.add(new Cell(colIndex + dc, rowIndex + dr));
////                        } else {
////                            neighbors.add(cells[rowIndex + dr][colIndex + dc]);
////                        }
////
////
////                        //neighbors.add(cells[nr][nc]);
////                    }
////                }
//
//                // Add north, south, west and east neighbours
//                // What if it is at the edge?
//                if (rowIndex != 0) { // If not at top row, add the one north
//                    neighbors.add(cells[colIndex][rowIndex - 1]); // Add north
//                }
//
//                if (rowIndex != numRows - 1) {
//                    //Cell south = new Cell(colIndex, rowIndex - 1);
//                    neighbors.add(cells[colIndex][rowIndex + 1]); // Add south
//                }
//
//                if (colIndex != 0) {
//                    //Cell west = new Cell(colIndex - 1, rowIndex);
//                    neighbors.add(cells[colIndex - 1][rowIndex]); // Add west
//                }
//
//                if (colIndex != numCols - 1) {
//                    //Cell east = new Cell(colIndex + 1, rowIndex);
//                    neighbors.add(cells[colIndex + 1][rowIndex]); // Add east
//                }
//
//
//                // The neighbours should not include the cell at
//                // (rowIndex,colIndex) so remove it.
//                //neighbors.remove(cell);
//                cell.setNeighbors(neighbors);
//                neighbors.clear();
//            }
//        }
//    }

}
