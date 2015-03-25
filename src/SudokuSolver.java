

/**
 * This class contains the algorithms for calculating the Sudoku.
 * 
 * @author Filip Harald & Jonas Danebjer.
 */

public class SudokuSolver {

	private int gameBoard[][];
	
	
	/**
	 * Constructs a new SudokuSolver.
	 */
	public SudokuSolver() {
		gameBoard = new int[9][9];

	}

	/**
	 * Scans the gameBoard too see if numbers are correctly entered.
	 * 
	 * @return true if numbers entered into gameBoard are in correlation with
	 *         the Sudoku rules, false otherwise
	 */
	public boolean gameBoardScan() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int temp = gameBoard[i][j];
				if (temp > 0) {
					gameBoard[i][j] = 0;
					if (!(nbrAllowedRow(temp, i) && nbrAllowedColumn(temp, j) && nbrAllowedBox(
							temp, i, j))) {
						gameBoard[i][j] = temp;
						return false;
					}
					gameBoard[i][j] = temp;
				}
			}
		}
		return true;
	}

	/**
	 * Returns the gameBoard.
	 * 
	 * @return the gameBoard
	 */
	public int[][] getGameBoard() {
		return gameBoard;
	}

	/** Clears the gameBoard. */

	public void clearBoard() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				gameBoard[i][j] = 0;

			}
		}
	}

	/**
	 * Sets the gameBoard to gB.
	 * 
	 * @param gB
	 */
	public void setGameBoard(int[][] gB) {
		gameBoard = gB;
	}

	/**
	 * Checks if nbr is allowed at the given row according to the Sudoku rules.
	 * 
	 * @param nbr
	 * @param currentRowPos
	 * @return true if allowed and false if not allowed
	 */
	private boolean nbrAllowedRow(int nbr, int currentRowPos) {
		for (int i = 0; i < 9; i++) {
			if (gameBoard[currentRowPos][i] == nbr) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if nbr is allowed at the given column according to the Sudoku
	 * rules.
	 * 
	 * @param nbr
	 * @param currentColumnPos
	 * @return true if allowed and false if not allowed
	 */
	private boolean nbrAllowedColumn(int nbr, int currentColumnPos) {
		for (int i = 0; i < 9; i++) {
			if (gameBoard[i][currentColumnPos] == nbr) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if nbr is allowed at the given box according to the Sudoku rules.
	 * 
	 * @param nbr
	 * @param currentRowPos
	 * @param currentColumnPos
	 * @return true if allowed and false if not allowed
	 */
	private boolean nbrAllowedBox(int nbr, int currentRowPos,
			int currentColumnPos) {
		int boxVerticalPos = (currentRowPos / 3) * 3;
		int boxHorizontalPos = (currentColumnPos / 3) * 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gameBoard[boxVerticalPos + i][boxHorizontalPos + j] == nbr) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Calls the recursive solve method with initial values 0,0.
	 * 
	 * @return true if solveable, false if unsolveable
	 */
	public boolean solve() {

		return recSolve(0, 0);
	}

	/**
	 * Recursive method, solves the sudoku if solveable.
	 * 
	 * @param row
	 * @param column
	 * @return true if solveable, false if unsolveable
	 */
	private boolean recSolve(int row, int column) {

		if (row > 8) {
			return true;
		}

		if (gameBoard[row][column] == 0) {

			for (int i = 1; i < 10; i++) {

				if (checkNumberInCell(i, row, column)) {

					gameBoard[row][column] = i;

 
					if (!nextCell(row, column)) {
						gameBoard[row][column] = 0;
					} else {

						return true;
					}
				}

			}
			gameBoard[row][column] = 0;
			return false;

		} else {
			return nextCell(row, column);
		}

	}

	/**
	 * Checks if the number is allowed in the current cell, combines the checks
	 * for Row, Column and Box
	 * 
	 * @param nbr
	 * @param row
	 * @param column
	 * @return false if not allowed and true if allowed
	 */
	private boolean checkNumberInCell(int nbr, int row, int column) {
		return nbrAllowedRow(nbr, row) && nbrAllowedColumn(nbr, column)
				&& nbrAllowedBox(nbr, row, column);
	}

	/**
	 * Calls the recursive solve method for the next cell
	 * 
	 * @param row
	 * @param column
	 * @return true if solveable and false if not
	 */
	private boolean nextCell(int row, int column) {
		if (column == 8) {
			return recSolve(row + 1, 0);
		} else {
			return recSolve(row, column + 1);
		}
	}

}
