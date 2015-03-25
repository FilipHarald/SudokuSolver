/**
 * @author Filip Harald & Jonas Danebjer.
 * Student Lund ID: tfr12fha & kin13jda.
 * Course: Programmeringsteknik Fördjupningskurs.
 * Course Code: EDAA01.
 * Inlämningsuppgift - Sudoku.
 * Datateknik LTH.
 * Main Class, constructs a SudokuSolver with a Graphical User Interface. 
 */

public class SudokuApplication {
	public static void main(String[] args) {
		SudokuSolver sudokuSolver = new SudokuSolver();

		new SudokuView(sudokuSolver, "Sudoku");
	}

}